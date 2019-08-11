package com.application.tchapj.main.presenter;

import com.application.tchapj.App;
import com.application.tchapj.base.BaseMvpPresenter;
import com.application.tchapj.main.bean.HomeCircleInfoModel;
import com.application.tchapj.main.bean.HomeCircleModel;
import com.application.tchapj.main.view.ICircleView;
import com.king.base.util.LogUtils;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by 安卓开发 on 2018/8/9.
 */

public class CirclePresenter extends BaseMvpPresenter<ICircleView> {

    public CirclePresenter(App app) {
        super(app);
    }

    // 分类
    public void getPersonSelectModelResult(){
        // isViewAttached()方法判断是否在显示器上显示
        if(isViewAttached()){
            getView().showProgress();
        }

        // 观察者被观察者模式
        // 得到根接口路径
        getAppComponent()
                .getAPIService() // 所有接口对象
                .getHomeCircleModelResult("002","1.0","","JSON") // 得到登录接口
                .subscribeOn(Schedulers.io()) // 订阅方式
                .observeOn(AndroidSchedulers.mainThread()) // 指定线程
                .subscribe(new Subscriber<HomeCircleModel>() {  // 将数据绑定到实体类的操作
                    @Override
                    public void onCompleted() {
                        if(isViewAttached())
                            getView().onCompleted();
                    }

                    @Override
                    public void onError(Throwable e) {
                        if(isViewAttached())
                            getView().onError(e);
                    }

                    @Override // 得到数据
                    public void onNext(HomeCircleModel homeCircleModel) {
                        LogUtils.d("Response:"+ homeCircleModel);
                        if(isViewAttached())
                            getView().onGetHomeCircleModelResult(homeCircleModel); // 得到一层数据

                    }
                });
    }

    // 分类详情
    public void getPersonSelectInfoModelResult(String circleTypeId,String dystate,String pyqstate
            ,String wbstate,String pageNum,String pageSize){

        // isViewAttached()方法判断是否在显示器上显示
        if(isViewAttached()){
            getView().showProgress();
        }

        // 观察者被观察者模式
        // 得到根接口路径
        getAppComponent()
                .getAPIService() // 所有接口对象
                .getHomeCircleInfoModelResult(circleTypeId,dystate,pyqstate,wbstate
                        ,pageNum,pageSize
                        ,"002","1.0","","JSON") // 得到登录接口
                .subscribeOn(Schedulers.io()) // 订阅方式
                .observeOn(AndroidSchedulers.mainThread()) // 指定线程
                .subscribe(new Subscriber<HomeCircleInfoModel>() {  // 将数据绑定到实体类的操作
                    @Override
                    public void onCompleted() {
                        if(isViewAttached())
                            getView().onCompleted();
                    }

                    @Override
                    public void onError(Throwable e) {
                        if(isViewAttached())
                            getView().onError(e);
                    }

                    @Override // 得到数据
                    public void onNext(HomeCircleInfoModel homeCircleInfoModel) {
                        LogUtils.d("Response:"+ homeCircleInfoModel);
                        if(isViewAttached())
                            getView().onGetHomeCircleInfoModelResult(homeCircleInfoModel); // 得到一层数据

                    }
                });
    }

}
