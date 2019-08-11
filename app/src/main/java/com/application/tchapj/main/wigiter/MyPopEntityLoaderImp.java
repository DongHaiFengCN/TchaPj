package com.application.tchapj.main.wigiter;


import android.content.Context;
import android.widget.PopupWindow;

import com.application.tchapj.utils2.poptabview.MyFilterConfig;
import com.application.tchapj.utils2.poptabview.filter.link.LinkFilterPopupWindow;
import com.application.tchapj.utils2.poptabview.filter.rows.RowsFilterWindow;
import com.application.tchapj.utils2.poptabview.filter.single.SingleFilterWindow;
import com.application.tchapj.utils2.poptabview.filter.sort.SortPopupWindow;
import com.application.tchapj.utils2.poptabview.listener.OnFilterSetListener;
import com.application.tchapj.utils2.poptabview.loader.PopEntityLoader;

import java.util.List;

/**
 * 由筛选器类型 建立实体 的loader
 * Created by chenchangjun on 17/6/28.
 */

public class MyPopEntityLoaderImp implements PopEntityLoader {

    /**
     * 由 getPopType 得到不同的类型的filter实体
     * @param context
     * @param data
     * @param filterSetListener 监听
     * @param filterType 筛选品类
     * @param singleOrMultiply 筛选方式--单选 or  多选
     * @return
     */
    @Override
    public PopupWindow getPopEntity(Context context, List data, OnFilterSetListener filterSetListener, int filterType, int singleOrMultiply) {
        PopupWindow popupWindow = null;
        switch (filterType) {
            case MyFilterConfig.TYPE_POPWINDOW_LINKED:
                popupWindow = new LinkFilterPopupWindow(context, data, filterSetListener,filterType,singleOrMultiply);
                break;
            case MyFilterConfig.TYPE_POPWINDOW_SORT:
                popupWindow = new SortPopupWindow(context, data, filterSetListener, filterType,singleOrMultiply);
                break;
            case MyFilterConfig.TYPE_POPWINDOW_ROWS:
                popupWindow = new RowsFilterWindow(context, data, filterSetListener,filterType,singleOrMultiply);
                break;
            case MyFilterConfig.TYPE_POPWINDOW_MY:
                popupWindow = new MyFilterPopWindow(context, data, filterSetListener,filterType,singleOrMultiply);
                break;

            default:
                popupWindow = new SingleFilterWindow(context, data, filterSetListener,filterType,singleOrMultiply);
                break;
        }
        return popupWindow;
    }
}
