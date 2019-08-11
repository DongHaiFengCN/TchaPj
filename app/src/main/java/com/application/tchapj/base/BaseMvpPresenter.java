package com.application.tchapj.base;


import com.application.tchapj.App;
import com.application.tchapj.di.component.AppComponent;
import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;
import com.king.base.util.LogUtils;

import javax.inject.Inject;

// MVP中Presenter的基类
public class BaseMvpPresenter<V extends BaseMvpView> extends MvpBasePresenter<V> {

    private App app;

    private AppComponent mAppComponent; // 注入框架基类

    @Inject
    public BaseMvpPresenter(App app){
        super();
        this.app = app;

        // 得到接口的根
        mAppComponent = app.getAppComponent();
    }

    public AppComponent getAppComponent(){
        return mAppComponent;
    }

    public App getApp(){
        return App.getInstance();
    }

    @Override
    public boolean isViewAttached() {
        LogUtils.d("isViewAttached:" + super.isViewAttached());
        return super.isViewAttached();
    }

}
