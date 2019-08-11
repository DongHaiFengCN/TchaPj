package com.application.tchapj.utils2.poptabview.filter.link;


import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.application.tchapj.R;
import com.application.tchapj.utils2.poptabview.FilterConfig;
import com.application.tchapj.utils2.poptabview.base.BaseFilterTabBean;
import com.application.tchapj.utils2.poptabview.base.SuperAdapter;
import com.application.tchapj.utils2.poptabview.base.SuperPopWindow;
import com.application.tchapj.utils2.poptabview.listener.OnFilterSetListener;
import com.application.tchapj.utils2.poptabview.listener.OnSecondItemClickListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 左右双栏筛选PopupWindow
 *
 * @author ccj on 17/3/23.
 */
public class LinkFilterPopupWindow extends SuperPopWindow implements FirstFilterAdapter.OnFirstItemClickListener, OnSecondItemClickListener {


    private LinearLayoutManager mLayoutManagerPrimary;

    private LinearLayout ll_bottom;

    private TextView tv_reset, tv_confirm;
    private ImageView iv_collapse;


    private HashMap<Integer, List<Integer>> mSecondSelectedMap;

    private RecyclerView rv_primary, rv_secondary;
    private FirstFilterAdapter mFirstAdapter;


    private int firstPosition = 0;

    public LinkFilterPopupWindow(Context context, List<BaseFilterTabBean> data, OnFilterSetListener listener, int filterType, int singleOrMultiply) {
        super(context, data, listener, filterType, singleOrMultiply);
    }



    @Override
    public View initView() {
        View mRootView = LayoutInflater.from(getContext()).inflate(R.layout.popup_filter_link, null);
        rv_primary = (RecyclerView) mRootView.findViewById(R.id.rv_primary);
        rv_secondary = (RecyclerView) mRootView.findViewById(R.id.rv_secondary);

        if (getSingleOrMultiply() == FilterConfig.FILTER_TYPE_MUTIFY) {
            ll_bottom = (LinearLayout) mRootView.findViewById(R.id.ll_bottom);
            iv_collapse = (ImageView) mRootView.findViewById(R.id.iv_collapse);
            tv_reset = (TextView) mRootView.findViewById(R.id.tv_reset);
            tv_confirm = (TextView) mRootView.findViewById(R.id.tv_confirm);
            ll_bottom.setVisibility(View.VISIBLE);
            iv_collapse.setOnClickListener(this);
            tv_confirm.setOnClickListener(this);
            tv_reset.setOnClickListener(this);
        }

        return mRootView;
    }


    @Override
    public SuperAdapter setAdapter() {
        SecondFilterAdapter adapter = new SecondFilterAdapter(this, getSingleOrMultiply());
        return adapter;
    }


    @Override
    public void initAdapter(SuperAdapter adapter) {

        //一级adapter
        mLayoutManagerPrimary = new LinearLayoutManager(getContext());
        mFirstAdapter = new FirstFilterAdapter(this);
        rv_primary.setLayoutManager(mLayoutManagerPrimary);
        rv_primary.setAdapter(mFirstAdapter);

        //二级adapter
        GridLayoutManager mLayoutManagerSecondary = new GridLayoutManager(getContext(), FilterConfig.LINKED_SPAN_COUNT);
        rv_secondary.setLayoutManager(mLayoutManagerSecondary);
        rv_secondary.setAdapter(adapter);

    }


    @Override
    public void initSelectData() {
        mSecondSelectedMap = new HashMap<>();

    }


    @Override
    public void show(View anchor, int paddingTop) {
        showAsDropDown(anchor);
        setDataAndSelection();
    }


    /**
     * 设置默认选中状态,每次pop都要设置一次
     */
    private void setDataAndSelection() {
        mFirstAdapter.setData(getData());
        if (getData() != null && getData().size() > firstPosition) {
            mFirstAdapter.setCheckedPosition(firstPosition);//一级默认选择
            onFirstItemClick(firstPosition, getData().get(firstPosition));
        }
        rv_primary.scrollToPosition(0);


    }


    /**
     * 一级菜单点击事件 回调,刷新二级菜单列表,以及默认
     *
     * @param position
     */
    @Override
    public void onFirstItemClick(int position, BaseFilterTabBean mFirstSelectedData) {
        firstPosition = position;
        if (getData() != null && getData().size() > firstPosition) {
            if (getData().get(position) != null && getData().get(position).getTabs().size() > 0) {//二级默认选中

                ((SecondFilterAdapter) getAdapter()).setData(position, getData().get(position).getTabs());

                List cheked = mSecondSelectedMap.get(position);
                if (cheked != null && !cheked.isEmpty()) {
                    getAdapter().setCheckedList(cheked);
                } else {
                    getAdapter().setCheckedList(null);
                }
            }
        }
        setConfirmButtonEnabled();

    }

    /**
     * 二级菜单 点击事件回调
     *
     * @param firstPos
     * @param secondFilterBean
     */
    @Override
    public void onSecondItemClick(int firstPos, ArrayList<Integer> secondFilterBean) {

        List<Integer> secondFilterList= (List<Integer>) secondFilterBean.clone();

        if (getSingleOrMultiply() == FilterConfig.FILTER_TYPE_SINGLE) {
            mSecondSelectedMap.clear();
            mSecondSelectedMap.put(firstPos,secondFilterList );
            if (secondFilterList.size()==1){
                List list = new ArrayList();
                list.add(getData().get(firstPos).getTabs().get(secondFilterList.get(0)));
                getOnFilterSetListener().onSecondFilterSet(firstPosition, list);
            }
            dismiss();
        } else {
            mSecondSelectedMap.put(firstPos, secondFilterList);
            setConfirmButtonEnabled();
        }

    }


    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.iv_collapse) {//
            getOnFilterSetListener().OnFilterCanceled();
            this.dismiss();
        } else if (i == R.id.tv_confirm) {// 确认

            List<BaseFilterTabBean> filterTabBeen = handleMutipleData();
            getOnFilterSetListener().onSecondFilterSet(firstPosition, filterTabBeen);
            this.dismiss();
            setConfirmButtonEnabled();

        } else if (i == R.id.tv_reset) {// 重置
            mSecondSelectedMap.clear();
            setDataAndSelection();
            setConfirmButtonEnabled();

        } else {
            getOnFilterSetListener().OnFilterCanceled();
            this.dismiss();
            setConfirmButtonEnabled();


        }
    }

    private void setConfirmButtonEnabled() {

        if (getSingleOrMultiply() == FilterConfig.FILTER_TYPE_SINGLE) {
            return;
        }

        boolean enabled = !mSecondSelectedMap.isEmpty();

        tv_reset.setEnabled(enabled);
        if (enabled) {
            tv_confirm.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.coloreee));
            tv_confirm.setTextColor(ContextCompat.getColor(getContext(), android.R.color.white));
        } else {
            tv_confirm.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.coloreee));
            tv_confirm.setTextColor(ContextCompat.getColor(getContext(), R.color.color666));
        }
    }

    /**
     * @return
     */
    public List<BaseFilterTabBean> handleMutipleData() {
        List<BaseFilterTabBean> filterTabBeen = new ArrayList<>();

        for (Map.Entry<Integer, List<Integer>> entry : mSecondSelectedMap.entrySet()) {

            if (entry.getValue() != null && entry.getValue().size() > 0) {
                for (int j = 0; j < entry.getValue().size(); j++) {
                    int pos = entry.getValue().get(j);
                    filterTabBeen.add((BaseFilterTabBean) getData().get(firstPosition).getTabs().get(pos));
                }
            }
        }

        return filterTabBeen;
    }


}