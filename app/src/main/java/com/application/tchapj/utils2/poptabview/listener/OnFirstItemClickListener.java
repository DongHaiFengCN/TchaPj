package com.application.tchapj.utils2.poptabview.listener;


import com.application.tchapj.utils2.poptabview.base.BaseFilterTabBean;
import com.application.tchapj.utils2.poptabview.base.SuperListener;

/**
 * Created by chenchangjun on 17/7/28.
 */

public interface OnFirstItemClickListener extends SuperListener {
    void onFirstItemClick(int position, BaseFilterTabBean filterTabBeen);
}
