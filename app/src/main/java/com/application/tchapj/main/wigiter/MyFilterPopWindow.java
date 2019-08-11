package com.application.tchapj.main.wigiter;

import android.content.Context;

import com.application.tchapj.main.adapter.MyFilterAdapter;
import com.application.tchapj.utils2.poptabview.base.SuperAdapter;
import com.application.tchapj.utils2.poptabview.filter.single.SingleFilterWindow;
import com.application.tchapj.utils2.poptabview.listener.OnFilterSetListener;

import java.util.List;

/**
 * Created by chenchangjun on 17/8/25.
 */

public class MyFilterPopWindow extends SingleFilterWindow {


    /**
     * 重写父类构造方法,如果需要其他参数请在本类中定义
     * @param context
     * @param data
     * @param listener
     * @param filterType
     * @param singleOrMutiply
     */
    public MyFilterPopWindow(Context context, List data, OnFilterSetListener listener, int filterType, int singleOrMutiply) {
        super(context,data,listener,filterType,singleOrMutiply);
    }

    /**
     * 重写setAdapter 方法
     * @return
     */
    @Override
    public SuperAdapter setAdapter() {
        return new MyFilterAdapter(getData(), this, getSingleOrMultiply());

    }
}
