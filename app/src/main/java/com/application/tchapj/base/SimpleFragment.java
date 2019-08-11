package com.application.tchapj.base;

// 我的页面Fargment基类
public abstract class SimpleFragment extends BaseMvpFragment<BaseMvpView, BaseMvpPresenter<BaseMvpView>> {

    @Override
    public BaseMvpPresenter<BaseMvpView> createPresenter() {
        return new BaseMvpPresenter<>(getApp());
    }
}
