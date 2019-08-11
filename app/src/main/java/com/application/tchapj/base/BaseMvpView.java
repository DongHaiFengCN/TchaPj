package com.application.tchapj.base;

import com.hannesdorfmann.mosby.mvp.MvpView;

// MVP中View的基类
public interface BaseMvpView extends MvpView{


    void showProgress();

    void onCompleted();

    void onError(Throwable e);
}
