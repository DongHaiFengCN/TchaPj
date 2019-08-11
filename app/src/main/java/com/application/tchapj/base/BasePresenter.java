package com.application.tchapj.base;

import com.application.tchapj.App;
import com.application.tchapj.my.bean.UserModel;
import com.application.tchapj.utils2.share.SharedPreferencesUtils;
import com.king.base.util.LogUtils;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Create by zyy on 2019/5/13
 * Description: 全局App用到比较多的接口封装
 */
public class BasePresenter<V extends BaseMvpView> extends BaseMvpPresenter<V> {

    public BasePresenter(App app) {
        super(app);
    }

    /**
     *
     * @param memberid
     */
    public void refreshUserInfo(String... memberid){
        String requestMemberId;
        if(memberid != null && memberid.length > 0){
            requestMemberId = memberid[0];
        }else{
            requestMemberId = App.getId();
        }

        getAppComponent()
                .getAPIService() // 所有接口对象
                .getUserModelResult(requestMemberId,"002","1.0","","JSON") // 得到登录接口
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
                        LogUtils.d("Response:"+ userModelBean);
                        SharedPreferencesUtils.getInstance().setUserInfo(userModelBean.getData());

                    }
                });

    }
}
