package com.application.tchapj.utils2.poptabview.listener;

import com.application.tchapj.utils2.poptabview.base.SuperListener;

import java.util.ArrayList;

/**
 * Created by chenchangjun on 17/7/28.
 */


public interface OnSecondItemClickListener extends SuperListener {
    void onSecondItemClick(int secondPos, ArrayList<Integer> secondFilterBean);
}