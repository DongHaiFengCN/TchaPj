package com.application.tchapj.search.view;

import com.application.tchapj.base.BaseMvpView;
import com.application.tchapj.my.bean.MicroTabBean;
import com.application.tchapj.search.bean.SearchBean;

/**
 * Created by 安卓开发 on 2018/8/6.
 */

public interface ISearchView extends BaseMvpView {

    void onGetMicroTabBeanResult(MicroTabBean dicroTabBean);

    void onGetSearchBeanResult(SearchBean searchBean);

    void onGetSearchBeanResultMost(SearchBean searchBean);

}
