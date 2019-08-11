package com.application.tchapj.my.presenter;

import com.application.tchapj.App;
import com.application.tchapj.base.BaseMvpPresenter;
import com.application.tchapj.my.bean.QiniuBean;
import com.application.tchapj.my.bean.UserModel;
import com.application.tchapj.my.view.IQiniuView;
import com.application.tchapj.utils2.share.SharedPreferencesUtils;
import com.application.tchapj.widiget.LogUtils;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class QiniuPresenter extends BaseMvpPresenter<IQiniuView> {

    public QiniuPresenter(App app) {
        super(app);
    }

    public void onGetQiniuResult(){
        // isViewAttached()方法判断是否在显示器上显示
        if(isViewAttached()){
            getView().showProgress();
        }

        // 观察者被观察者模式
        // 得到根接口路径
        getAppComponent()
                .getAPIService() // 所有接口对象
                .getQiniuBeanResult("002","1.0","","JSON") // 得到登录接口
                .subscribeOn(Schedulers.io()) // 订阅方式
                .observeOn(AndroidSchedulers.mainThread()) // 指定线程
                .subscribe(new Subscriber<QiniuBean>() {  // 将数据绑定到实体类的操作
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
                    public void onNext(QiniuBean qiniuBean) {
                        LogUtils.d("Response:"+ qiniuBean);
                        if(isViewAttached())
                            getView().onGetQiniuBeanResult(qiniuBean); // 得到一层数据

                    }
                });
    }

    // 个人数据
    public void getUserModelResult(String memberId){
        // isViewAttached()方法判断是否在显示器上显示
        if(isViewAttached()){
            getView().showProgress();
        }

        // 观察者被观察者模式
        // 得到根接口路径
        getAppComponent()
                .getAPIService() // 所有接口对象
                .getUserModelResult(memberId,"002","1.0","","JSON") // 得到登录接口
                .subscribeOn(Schedulers.io()) // 订阅方式
                .observeOn(AndroidSchedulers.mainThread()) // 指定线程
                .subscribe(new Subscriber<UserModel>() {  // 将数据绑定到实体类的操作
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
                    public void onNext(UserModel userModelBean) {
                        com.king.base.util.LogUtils.d("Response:"+ userModelBean);
                        SharedPreferencesUtils.getInstance().setUserInfo(userModelBean.getData());
                        if(isViewAttached())
                            getView().onGetUserModelResult(userModelBean); // 得到一层数据

                    }
                });
    }

}
