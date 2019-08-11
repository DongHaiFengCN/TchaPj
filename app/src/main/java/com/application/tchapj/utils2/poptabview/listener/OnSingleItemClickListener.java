package com.application.tchapj.utils2.poptabview.listener;


import com.application.tchapj.utils2.poptabview.base.SuperListener;

import java.util.List;

/**
 * 一级筛选的回调
 * Created by chenchangjun on 17/7/6.
 */

public interface OnSingleItemClickListener extends SuperListener {


     void onSingleItemClickListener(List<Integer> integerList);
}
