package com.application.tchapj.my.presenter;

import com.application.tchapj.App;
import com.application.tchapj.base.BaseMvpPresenter;
import com.application.tchapj.my.bean.DamuInfo;
import com.application.tchapj.my.bean.PostDamuInfo;
import com.application.tchapj.my.view.IDamuView;
import com.application.tchapj.widiget.LogUtils;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
/**
 * Created by 安卓开发 on 2018/7/30.
 */

public class DamuPresenter extends BaseMvpPresenter<IDamuView> {

    public DamuPresenter(App app) {
        super(app);
    }

    public void onGetDamuInfoResult(){
        // isViewAttached()方法判断是否在显示器上显示
        if(isViewAttached()){
            getView().showProgress();
        }

        // 观察者被观察者模式
        // 得到根接口路径
        getAppComponent()
                .getAPIService() // 所有接口对象
                .getDamuInfoResult("002","1.0","","JSON") // 得到登录接口
                .subscribeOn(Schedulers.io()) // 订阅方式
                .observeOn(AndroidSchedulers.mainThread()) // 指定线程
                .subscribe(new Subscriber<DamuInfo>() {  // 将数据绑定到实体类的操作
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
                    public void onNext(DamuInfo damuInfo) {
                        LogUtils.d("Response:"+ damuInfo);
                        if(isViewAttached())
                            getView().onGetDamuInfoResult(damuInfo); // 得到一层数据

                    }
                });
    }

    public void onGetPostDamuInfoResult(String memberId,String opinions,String nickName){

        // isViewAttached()方法判断是否在显示器上显示
        if(isViewAttached()){
            getView().showProgress();
        }

        // 观察者被观察者模式
        // 得到根接口路径
        getAppComponent()
                .getAPIService() // 所有接口对象
                .getPostDamuInfoResult(memberId,opinions,nickName,"002","1.0","","JSON") // 得到登录接口
                .subscribeOn(Schedulers.io()) // 订阅方式
                .observeOn(AndroidSchedulers.mainThread()) // 指定线程
                .subscribe(new Subscriber<PostDamuInfo>() {  // 将数据绑定到实体类的操作
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
                    public void onNext(PostDamuInfo postDamuInfo) {
                        LogUtils.d("Response:"+ postDamuInfo);
                        if(isViewAttached())
                            getView().onGetPostDamuInfoResult(postDamuInfo); // 得到一层数据

                    }
                });
    }

}
