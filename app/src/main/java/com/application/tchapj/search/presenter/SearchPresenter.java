package com.application.tchapj.search.presenter;

import com.application.tchapj.App;
import com.application.tchapj.base.BaseMvpPresenter;
import com.application.tchapj.my.bean.MicroTabBean;
import com.application.tchapj.search.bean.SearchBean;
import com.application.tchapj.search.view.ISearchView;
import com.application.tchapj.widiget.LogUtils;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by 安卓开发 on 2018/8/6.
 */

public class SearchPresenter extends BaseMvpPresenter<ISearchView> {

    public SearchPresenter(App app) {
        super(app);
    }

    // 标签
    public void onGetMicroTabResult(){
        // isViewAttached()方法判断是否在显示器上显示
        if(isViewAttached()){
            getView().showProgress();
        }

        // 观察者被观察者模式
        // 得到根接口路径
        getAppComponent()
                .getAPIService() // 所有接口对象
                .getMicroTabBeanResult("002","1.0","","JSON") // 得到登录接口
                .subscribeOn(Schedulers.io()) // 订阅方式
                .observeOn(AndroidSchedulers.mainThread()) // 指定线程
                .subscribe(new Subscriber<MicroTabBean>() {  // 将数据绑定到实体类的操作
                    @Override
                    public void onCompleted() {
                        if(isViewAttached()) {
                            getView().onCompleted();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if(isViewAttached()) {
                            getView().onError(e);
                        }
                    }

                    @Override // 得到数据
                    public void onNext(MicroTabBean microTabBean) {
                        LogUtils.d("Response:"+ microTabBean);
                        if(isViewAttached()) {
                            getView().onGetMicroTabBeanResult(microTabBean); // 得到一层数据
                        }

                    }
                });
    }

    // 搜索内容
    public void onGetSearchBeanResult(String name){
        // isViewAttached()方法判断是否在显示器上显示
        if(isViewAttached()){
            getView().showProgress();
        }

        // 观察者被观察者模式
        // 得到根接口路径
        getAppComponent()
                .getAPIService() // 所有接口对象
                .getSearchBeanResult(name,"002","1.0","","JSON") // 得到登录接口
                .subscribeOn(Schedulers.io()) // 订阅方式
                .observeOn(AndroidSchedulers.mainThread()) // 指定线程
                .subscribe(new Subscriber<SearchBean>() {  // 将数据绑定到实体类的操作
                    @Override
                    public void onCompleted() {
                        if(isViewAttached()) {
                            getView().onCompleted();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if(isViewAttached()) {
                            getView().onError(e);
                        }
                    }

                    @Override // 得到数据
                    public void onNext(SearchBean searchBean) {
                        LogUtils.d("Response:"+ searchBean);
                        if(isViewAttached()) {
                            getView().onGetSearchBeanResult(searchBean); // 得到一层数据
                        }

                    }
                });
    }

    // 查看更多内容
    public void onGetSearchBeanResultMost(String name,String id){
        // isViewAttached()方法判断是否在显示器上显示
        if(isViewAttached()){
            getView().showProgress();
        }

        // 观察者被观察者模式
        // 得到根接口路径
        getAppComponent()
                .getAPIService() // 所有接口对象
                .getSearchBeanResultmost(name,id,"002","1.0","","JSON") // 得到登录接口
                .subscribeOn(Schedulers.io()) // 订阅方式
                .observeOn(AndroidSchedulers.mainThread()) // 指定线程
                .subscribe(new Subscriber<SearchBean>() {  // 将数据绑定到实体类的操作
                    @Override
                    public void onCompleted() {
                        if(isViewAttached()) {
                            getView().onCompleted();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if(isViewAttached()) {
                            getView().onError(e);
                        }
                    }

                    @Override // 得到数据
                    public void onNext(SearchBean searchBean) {
                        LogUtils.d("Response:"+ searchBean);
                        if(isViewAttached()) {
                            getView().onGetSearchBeanResultMost(searchBean); // 得到一层数据
                        }

                    }
                });
    }

}
