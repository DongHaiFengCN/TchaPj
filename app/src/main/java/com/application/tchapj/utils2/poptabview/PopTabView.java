package com.application.tchapj.utils2.poptabview;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.TextView;

import com.application.tchapj.R;
import com.application.tchapj.utils2.poptabview.base.BaseFilterTabBean;
import com.application.tchapj.utils2.poptabview.base.SuperPopWindow;
import com.application.tchapj.utils2.poptabview.filter.link.LinkFilterPopupWindow;
import com.application.tchapj.utils2.poptabview.listener.OnFilterSetListener;
import com.application.tchapj.utils2.poptabview.listener.OnPopTabSetListener;
import com.application.tchapj.utils2.poptabview.loader.PopEntityLoader;
import com.application.tchapj.utils2.poptabview.loader.PopEntityLoaderImp;
import com.application.tchapj.utils2.poptabview.loader.ResultLoader;
import com.application.tchapj.utils2.poptabview.loader.ResultLoaderImp;
import com.king.base.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * popwindow的容器tab
 * Created by chenchangjun on 17/6/20.
 */
public class PopTabView extends LinearLayout implements OnFilterSetListener, OnDismissListener {

    //自定义属性,待扩展
    private double tab_textsize=-1;
    private int tab_text_color_normal=-1;
    private int tab_text_color_focus=-1;

    private int tab_background_normal=-1;
    private int tab_background_focus=-1;

    private int tab_max_ems=-1;//字数个数超过tab_max_ems 显示...
    private int tab_max_lines=-1;//tab item字数最大行数



    private int tab_pop_anim= R.style.PopupWindowAnimation;


    private Context mContext;


    private ArrayList<SuperPopWindow> mViewLists = new ArrayList<>();//popwindow缓存集合
    private ArrayList<TextView> mTextViewLists = new ArrayList<TextView>(); //筛选标签textiew集合,用于字段展示和点击事件
    private ArrayList<String> mLableLists = new ArrayList<>();//初始筛选展示字符串缓存,用于默认展示

    private OnPopTabSetListener onPopTabSetListener;//PopTabView和activity的,回调


    private PopEntityLoaderImp popEntityLoader;//筛选类型实体加载器
    private ResultLoader resultLoader;//可自定义处理的筛选结果集 处理器


    private int mTabPostion = -1; //记录TAB页号,
    private int currentIndex;//当前点击的下标
    private String selectIndexs = "";


    private void init(Context context, AttributeSet attrs) {
        TypedArray a = null;
        try {
            a = context.obtainStyledAttributes(attrs, R.styleable.PopsTabView);
            tab_background_normal = a.getResourceId(R.styleable.PopsTabView_tab_background_normal, -1);
            tab_background_focus = a.getResourceId(R.styleable.PopsTabView_tab_background_focus, -1);
            tab_pop_anim = a.getResourceId(R.styleable.PopsTabView_tab_pop_anim, R.style.PopupWindowAnimation);

            tab_text_color_normal = a.getColor(R.styleable.PopsTabView_tab_text_color_normal, -1);
            tab_text_color_focus = a.getColor(R.styleable.PopsTabView_tab_text_color_focus, -1);

            tab_textsize = a.getDimension(R.styleable.PopsTabView_tab_textsize, -1);
            tab_max_ems =  a.getInteger(R.styleable.PopsTabView_tab_max_ems, 4);
            tab_max_lines =  a.getInteger(R.styleable.PopsTabView_tab_max_ems, 1);


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (a != null) {
                a.recycle();
            }
        }
        mContext = context;
        setOrientation(LinearLayout.HORIZONTAL);


    }

/*************************************************/
    /**
     * 设置点击 选中,并触发onPopTabSet()方法回调,目前只适用于  一级普通筛选Single,以及一级多排筛选Rows
     * 设置默认选中ui样式
     * @param filterIndex
     * @param checkedPositions  一个/多个下标。1,2
     */
    public void setDefaultSelectedItem(int filterIndex, String checkedPositions){
        this.currentIndex=filterIndex;

        ArrayList<Integer> list = new ArrayList();
        if (!TextUtils.isEmpty(checkedPositions)) {
            this.selectIndexs = checkedPositions + "";
            if (checkedPositions.contains(",")) {
                String[] showIdArray = checkedPositions.split(",");
                for (int i = 0; i < showIdArray.length; i++) {
                    list.add(Integer.parseInt(showIdArray[i]) - 1);
                }

            } else {
                list.add(Integer.parseInt(checkedPositions) - 1);
            }
        }

        mViewLists.get(filterIndex).setClickedItems(list);
    }

    /**
     * 设置点击 选中,并触发onPopTabSet()方法回调,目前只适用于  一级普通筛选Single,以及一级多排筛选Rows
     * @param filterIndex
     * @param clickedItems
     */
    public void setClickedItems(int filterIndex, ArrayList<Integer> clickedItems ){
        this.currentIndex=filterIndex;
        mViewLists.get(filterIndex).setClickedItems(clickedItems);
    }

    /**
     * 设置默认选中,不会触发OnPopSet()回调 目前只适用于  一级普通筛选Single,以及一级多排筛选Rows
     * @param filterIndex
     * @param defaultCheckedItems
     */
    public void setDefaultCheckedItems(int filterIndex, ArrayList<Integer> defaultCheckedItems ){
        this.currentIndex=filterIndex;
        mViewLists.get(filterIndex).setDefaultCheckedItems(defaultCheckedItems);
    }


    /**
     * 设置默认选中,不会触发OnPopSet()回调 目前只适用于  一级普通筛选Single,以及一级多排筛选Rows
     * @param filterIndex
     * @param defaultCheckedItem
     */
    public void setDefaultCheckedItem(int filterIndex,int  defaultCheckedItem){
        this.currentIndex=filterIndex;
        ArrayList<Integer> list=new ArrayList();
        list.add(defaultCheckedItem);
        mViewLists.get(filterIndex).setDefaultCheckedItems(list);
    }


    /**
     * @param title 筛选标题
     * @param data  筛选数据
     * @param filterType   筛选类别- 一级筛选,二级筛选,复杂筛选
     * @param singleOrMutiply  筛选方式-单选or多选
     * @return view 本身
     */
    public PopTabView addFilterItem(String title, List data, int filterType, int singleOrMutiply) {

        ////默认筛选项的布局,如果想修改筛选项样式,也可以在此布局修改
        View labView = inflate(getContext(), R.layout.item_pops_tab_view, null);
        TextView labButton = (TextView) labView.findViewById(R.id.tv_label);

        labButton.setEms(tab_max_ems);
        labButton.setMaxLines(tab_max_lines);


        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, 1.0f);
        labView.setLayoutParams(params);


        if (tab_textsize!=-1){
            labButton.setTextSize((float) tab_textsize);
        }

        setMenuDrawble(labButton, false);

        //筛选类型实体加载器
        if (popEntityLoader == null) {
            popEntityLoader = new PopEntityLoaderImp();
        }
        SuperPopWindow mPopupWindow = (SuperPopWindow) popEntityLoader.getPopEntity(getContext(), data, this, filterType,singleOrMutiply);//得到相应的筛选类型实体
        mPopupWindow.setAnimationStyle(tab_pop_anim);
        mPopupWindow.setOnDismissListener(this);

        //将筛选项布局加入view
        addView(labView);

        //对筛选项控件进行设置,并且缓存位置信息
        labButton.setText(title);
        labView.setTag(++mTabPostion);
        labView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //保证用户点确认按钮后，tab选中状态才会保留
                setDefaultSelectedItem(0, getSelectIndexs());


                //当点击时,设置当前选中状态
                currentIndex = (int) v.getTag();
                setMenuDrawble(mTextViewLists.get(currentIndex), true);
                //弹出当前页pop,或者回收pop
                showPopView(currentIndex);
            }
        });
        //进行缓存
        mTextViewLists.add(labButton);
        mLableLists.add(title);
        mViewLists.add(mPopupWindow);
        return this;
    }


    /**
     * 设置箭头方向及文字颜色
     *
     * @param isUp true 设置为向上箭头，文字红色
     */
    private void setMenuDrawble(TextView tv_checked, boolean isUp) {

        if (isUp) {
            if (tab_background_focus!=-1){
                tv_checked.setBackgroundResource(tab_background_focus);
            }
            if (tab_text_color_focus!=-1){
                tv_checked.setTextColor(tab_text_color_focus);
            }else {
                tv_checked.setTextColor(ContextCompat.getColor(getContext(), R.color.color666));
            }
            tv_checked.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_keyboard_arrow_up_black_24dp, 0);

        } else {

            if (tab_background_normal!=-1){
                tv_checked.setBackgroundResource(tab_background_normal);
            }
            if (tab_text_color_normal!=-1){
                tv_checked.setTextColor(tab_text_color_normal);
            }else {
                tv_checked.setTextColor(ContextCompat.getColor(getContext(), R.color.color666));
            }
            tv_checked.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_keyboard_arrow_down_black_24dp, 0);

        }
    }

    /**
     * 遍历,选取,显示
     *
     * @param position
     */
    public void showPopView(int position) {

        if (mViewLists.size() > position && mViewLists.get(position) != null) {
            //遍历, 将 不是该位置的window消失
            for (int i = 0; i < mViewLists.size(); i++) {
                if (i != position) {
                    mViewLists.get(i).onSuperPopWindowDismiss(false);
               }
            }
            //如果该位置正在展示,就让他消失.如果没有,就展示
            if (mViewLists.get(position).isShowing()) {
                mViewLists.get(position).onSuperPopWindowDismiss(true);
            }else {
                mViewLists.get(position).show(this, 0);
            }

        }
    }

/*****************************筛选成功,回调~************************************/

    /**
     * popwindow的ondisms
     */
    @Override
    public void onDismiss() {
        for (int i = 0; i < mTextViewLists.size(); i++) {
            setMenuDrawble(mTextViewLists.get(i), false);
        }
    }




    /*****************************筛选成功,回调~************************************/
    /**
     * 一级筛选,连接singlepopwindow,rowspopwindow的筛选回调
     * @param selectedList
     */
    @Override
    public void onSingleFilterSet(List<BaseFilterTabBean> selectedList) {
        handleFilterTabsBeanData(false,selectedList);

    }
    /**
     * 二级筛选,连接linkedfilterPopwindow的筛选回调
     * @param selectedSecondList 有效筛选结果 是  二级筛选
     */
    @Override
    public void onSecondFilterSet(int firstPos, List<BaseFilterTabBean> selectedSecondList) {
        handleFilterTabsBeanData(true,selectedSecondList);
    }

    /**
     * sortPopWndow的筛选回调
     * @param selectedSecondList
     */
    @Override
    public void onSortFilterSet(List<BaseFilterTabBean> selectedSecondList) {
        handleFilterTabsBeanData(true,selectedSecondList);
    }

    /**
     * 重置
     */
    @Override
    public void onResetClick() {
//        setSelectIndexs("");
        onPopTabSetListener.onResetClick();
    }

    @Override
    public void OnFilterCanceled() {

    }

    /*****************************筛选成功,end~************************************/

    /**
     * 处理由每个筛选项item到popwindow的数据处理
     *
     * @param selectedList
     */
    public void handleFilterTabsBeanData(boolean hasSecondTabBean,List selectedList) {

        String showIds = null;
        String showValues = null;
        Object showParams = null;

        //如果没有选择,onPopTabSet为null,筛选标签设为初始值;
        if (selectedList == null || selectedList.isEmpty()) {
            mTextViewLists.get(currentIndex).setText(mLableLists.get(currentIndex));
            setSelectIndexs(showIds);//保证用户点确认按钮后，tab选中状态才会保留

            onPopTabSetListener.onPopTabSet(currentIndex, mLableLists.get(currentIndex), showParams, showIds, showValues);

        } else { //如果有选择,onPopTabSet 取值,并展示;
            if (resultLoader == null) {
                resultLoader = new ResultLoaderImp();
            }

            //使用 自定义的结果加载器,得到自己想要的字符串结果
            int tag = mViewLists.get(currentIndex).getFilterType();//拿到样式
            //如果是二级筛选或者复杂筛选,即有FilterTabBean.ChildTabBean
            if (hasSecondTabBean){
                showIds =  resultLoader.getSecondResultShowIds(selectedList, tag);//MyResultLoaderImp - getSecondResultShowIds
                showValues =  resultLoader.getSecondResultShowValues(selectedList, tag);
                showParams = resultLoader.getSecondResultParamsIds(selectedList,tag);
            }else {
                showIds =  resultLoader.getResultShowIds(selectedList, tag);
                showValues =  resultLoader.getResultShowValues(selectedList, tag);
                showParams = resultLoader.getResultParamsIds(selectedList,tag);
            }

            // TODO 可以自己修改展示效果
            //展示取值
            //mTextViewLists.get(currentIndex).setText(showValues); 选择数据内容
            mTextViewLists.get(currentIndex).setText(mLableLists.get(currentIndex));
            //保证用户点确认按钮后，tab选中状态才会保留
            setSelectIndexs(showIds);


            //进行回调
            onPopTabSetListener.onPopTabSet(currentIndex, mLableLists.get(currentIndex), showParams, showIds, showValues);

        }
    }

    /**
     * 多次加载 注意清空
     */
    public void removeItem() {
        mTextViewLists.clear();
        mLableLists.clear();
        mViewLists.clear();
        removeAllViews();
    }



    public OnPopTabSetListener getOnPopTabSetListener() {
        return onPopTabSetListener;
    }

    public PopTabView setOnPopTabSetListener(OnPopTabSetListener onPopTabSetListener) {
        this.onPopTabSetListener = onPopTabSetListener;
        return this;
    }

    public PopTabView(Context context) {
        super(context);
        init(context, null);
    }

    public PopTabView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public PopTabView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);

    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public PopTabView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);

    }


    public ResultLoader getResultLoader() {
        return resultLoader;
    }

    public PopTabView setResultLoader(ResultLoader resultLoader) {
        this.resultLoader = resultLoader;

        return this;
    }

    public void setSelectIndexs(String indexs){
        this.selectIndexs = indexs;
    }

    public String getSelectIndexs(){
       return this.selectIndexs;
    }


    public PopTabView setPopEntityLoader(PopEntityLoader popEntityLoader) {
        return this;
    }

}
