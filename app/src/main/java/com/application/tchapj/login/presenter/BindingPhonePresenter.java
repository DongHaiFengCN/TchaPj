package com.application.tchapj.login.presenter;

import com.application.tchapj.App;
import com.application.tchapj.R;
import com.application.tchapj.base.BaseBean;
import com.application.tchapj.base.BaseMvpPresenter;
import com.application.tchapj.base.BaseMvpView;
import com.application.tchapj.base.BasePresenter;
import com.application.tchapj.bean.BindingPhoneBean;
import com.application.tchapj.bean.SmsCodeBean;
import com.application.tchapj.login.bean.LoginResult;
import com.application.tchapj.login.bean.NewPhoneLoginResult;
import com.application.tchapj.login.view.IBindingPhoneView;
import com.application.tchapj.login.view.ILoginView;
import com.king.base.util.LogUtils;
import com.king.base.util.StringUtils;

import java.util.regex.Pattern;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.application.tchapj.DataManager.getDataManager;

public class BindingPhonePresenter extends BasePresenter<IBindingPhoneView> {

    public BindingPhonePresenter(App app) {
        super(app);
    }


    // 验证码
    public void getSmsCodeResult(String phone){
        // isViewAttached()方法判断是否在显示器上显示
        if(isViewAttached()){
            getView().showProgress();
        }

        // 观察者被观察者模式
        // 得到根接口路径
        getAppComponent()
                .getAPIService() // 所有接口对象
                .getBindingSmsCodeResult(phone,"002","1.0","","JSON") // 得到登录接口
                .subscribeOn(Schedulers.io()) // 订阅方式
                .observeOn(AndroidSchedulers.mainThread()) // 指定线程
                .subscribe(new Subscriber<BaseBean<SmsCodeBean>>() {  // 将数据绑定到实体类的操作
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
                    public void onNext(BaseBean<SmsCodeBean> bean) {
                        getView().onGetSmsCodeResult(bean); // 得到一层数据
                    }
                });
    }

    // 新手机验证登录
    public void onThirdLoginBindingResult(String phone){
        // isViewAttached()方法判断是否在显示器上显示
        if(isViewAttached()){
            getView().showProgress();
        }

        // 观察者被观察者模式
        // 得到根接口路径
        getAppComponent()
                .getAPIService() // 所有接口对象
                .onThirdLoginBindingResult(phone, getDataManager().quickGetMetaData(R.string.id,String.class),"002","1.0","","JSON") // 得到登录接口
                .subscribeOn(Schedulers.io()) // 订阅方式
                .observeOn(AndroidSchedulers.mainThread()) // 指定线程
                .subscribe(new Subscriber<BaseBean<BindingPhoneBean>>() {  // 将数据绑定到实体类的操作
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
                    public void onNext(BaseBean<BindingPhoneBean> newPhoneLoginResult) {
                        LogUtils.d("Response:"+ newPhoneLoginResult);
                        if(isViewAttached())
                            getView().onThirdLoginBindingResult(newPhoneLoginResult); // 得到一层数据

                    }
                });
    }

}
