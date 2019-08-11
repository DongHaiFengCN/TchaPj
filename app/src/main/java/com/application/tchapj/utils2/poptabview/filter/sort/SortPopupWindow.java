package com.application.tchapj.utils2.poptabview.filter.sort;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.application.tchapj.R;
import com.application.tchapj.utils2.poptabview.base.BaseFilterTabBean;
import com.application.tchapj.utils2.poptabview.base.SuperAdapter;
import com.application.tchapj.utils2.poptabview.base.SuperPopWindow;
import com.application.tchapj.utils2.poptabview.filter.link.SecondFilterAdapter;
import com.application.tchapj.utils2.poptabview.listener.OnFilterSetListener;
import com.application.tchapj.utils2.poptabview.listener.OnSortItemClickListener;
import com.application.tchapj.utils2.poptabview.listener.OnSortTagClickListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 通用复合单选PopupWindow
 *
 * @author  by ccj on 17/6/22
 */
public class SortPopupWindow extends SuperPopWindow implements OnSortTagClickListener, OnSortItemClickListener {


    private LinearLayout ll_content;
    private ViewStub mErrorView;
    //private View  iv_collapse;
    private View mInflatedErrorView;
    private TextView tv_reset, tv_confirm;

    private List<SortItemView> sortItemViewLists;

    private HashMap<Integer, ArrayList<Integer>> checkedIndex;

    public SortPopupWindow(Context context, List data, OnFilterSetListener listener, int filterType, int singleOrMultiply) {
        super(context, data, listener, filterType, singleOrMultiply);

    }


    @Override
    public void initSelectData() {
        //在一个存在继承的类中：初始化父类static成员变量,运行父类static初始化块-->初始化子类static成员变量,
        // 运行子类static初始化块-->初始化父类实例成员变量(如果有赋值语句),执行父类普通初始化块-->父类构造方法-->初始化子类实例成员变量(如果有赋值语句)及普通初始化块-->子类构造方法。
        sortItemViewLists = new ArrayList<>();
        checkedIndex = new HashMap<>();
        for (int i = 0; i < getData().size(); i++) {
            BaseFilterTabBean filterTabBean = getData().get(i);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            SortItemView sortItemView = new SortItemView(getContext());
            sortItemView.setLayoutParams(layoutParams);
            sortItemView.setLabTitle(filterTabBean.getTab_name());
            sortItemView.setAdapter(filterTabBean.getTab_name(), getSingleOrMultiply());//将getTab_name 作为 唯一标示
            sortItemView.setFilterTagClick(this);
            sortItemView.setIndex(i);
            sortItemViewLists.add(sortItemView);
            ll_content.addView(sortItemView);
        }
    }

    @Override
    public View initView() {
       View mRootView = LayoutInflater.from(getContext()).inflate(R.layout.common_popup_filter_sort, null);
    
        ll_content = (LinearLayout) mRootView.findViewById(R.id.ll_content);
        tv_reset = (TextView) mRootView.findViewById(R.id.tv_reset);
        tv_confirm = (TextView) mRootView.findViewById(R.id.tv_confirm);
        //iv_collapse = mRootView.findViewById(iv_collapse);
        mInflatedErrorView = null;
        tv_reset.setOnClickListener(this);
        tv_confirm.setOnClickListener(this);
        //iv_collapse.setOnClickListener(this);
        return mRootView;
    }

    @Override
    public SuperAdapter setAdapter() {
        SortFilterAdapter adapter = new SortFilterAdapter(this, getSingleOrMultiply());
        return adapter;
    }

    @Override
    public void initAdapter(SuperAdapter adapter) {

    }

    @Override
    public void show(View anchor, int paddingTop) {
        resetView();
        showAsDropDown(anchor);
        setButtonEnabled(true);
        loadSortItem();
    }

    private void resetView() {
        for (int i = 0; i < getData().size(); i++) {
            sortItemViewLists.get(i).resetView();
        }
    }


    private void loadSortItem() {

        for (int i = 0; i < getData().size(); i++) {
            sortItemViewLists.get(i).setData(getData().get(i).getTabs(), checkedIndex.get(i));
        }
        ll_content.setVisibility(View.VISIBLE);
        if (checkedIndex != null && checkedIndex.size() > 0) {
            setButtonEnabled(true);

        } else {
            setButtonEnabled(false);
        }
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.btn_reload) {//loadData();

        } else if (i == R.id.tv_confirm) {
            List list = getSortList();
            getOnFilterSetListener().onSortFilterSet(list);
            this.dismiss();

        } else if (i == R.id.tv_reset) {
            if (v.isEnabled()) {
                resetView();
                checkedIndex.clear();
                loadSortItem();
                getOnFilterSetListener().onResetClick();
            }

        } /*else if (i == iv_collapse) {
            this.dismiss();

        }*/ else {
            this.dismiss();

        }
    }

    private List getSortList() {
        List list = new ArrayList();
        for (Map.Entry<Integer, ArrayList<Integer>> entry : checkedIndex.entrySet()) {
            List list1 = getData().get(entry.getKey()).getTabs();
            if (list1 != null) {
                for (int j = 0; j < list1.size(); j++) {
                    if (entry.getValue().contains(j)) {
                        list.add(list1.get(j));
                    }
                }
            }
        }
        return list;
    }


    private void setButtonEnabled(boolean enabled) {
        tv_reset.setEnabled(enabled);
        /*if (enabled) {
            tv_confirm.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.product_color));
            tv_confirm.setTextColor(ContextCompat.getColor(getContext(), android.R.color.white));
        } else {
            tv_confirm.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.coloreee));
            tv_confirm.setTextColor(ContextCompat.getColor(getContext(), R.color.color666));
        }*/
    }

    /**
     * @param firstPos
     * @param type     1分类 2商城  这里选tab_name
     */
    @Override
    public void onComFilterTagClick(int firstPos, int secondPos1, ArrayList<Integer> filterTabBeen, String type) {
        if (filterTabBeen == null) {
            return;
        }
        checkedIndex.put(firstPos, (ArrayList<Integer>) (filterTabBeen).clone()); //需要克隆之前的集合,避免item.clean 造成数据消失
        setButtonEnabled(true);

    }

    private void showErrorView() {
        if (mInflatedErrorView == null) {
            mInflatedErrorView = mErrorView.inflate();
            Button btn_reload = (Button) mInflatedErrorView.findViewById(R.id.btn_reload);
            btn_reload.setOnClickListener(this);
        }
        mInflatedErrorView.setVisibility(View.VISIBLE);
    }

    private void hideErrorView() {
        if (mInflatedErrorView != null) {
            mInflatedErrorView.setVisibility(View.GONE);
        }
    }

    @Override
    public void onSortItemClick(int position, List<Integer> filterTabBeen) {
        onComFilterTagClick(0, position, (ArrayList<Integer>) filterTabBeen,getData().get(0).getTab_name());
    }
}
