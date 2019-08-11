package com.application.tchapj.utils2.poptabview.listener;


import com.application.tchapj.utils2.poptabview.base.BaseFilterTabBean;

import java.util.List;

/**
 * 各个filter和popstabview的交互接口
 * Created by chenchangjun on 17/7/6.
 */

public interface OnFilterSetListener {


    /**
     * 一级 筛选监听
     * @param selectedList
     */
    void onSingleFilterSet(List<BaseFilterTabBean> selectedList);


    /**
     * 二级筛选监听
     * @param firstPos
     * @param selectedSecondList
     */
    void onSecondFilterSet(int firstPos, List<BaseFilterTabBean> selectedSecondList);


    /**
     * 复杂筛选监听   确认按钮
     * @param selectedSecondList
     */
    void onSortFilterSet(List<BaseFilterTabBean> selectedSecondList);

    /**
     * 重置按钮
     */
    void onResetClick();


    /**
     * 筛选取消监听
     */
    void OnFilterCanceled();

}
