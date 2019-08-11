package com.application.tchapj.my.presenter;

import com.application.tchapj.App;
import com.application.tchapj.base.BaseBean;
import com.application.tchapj.base.BaseMvpPresenter;
import com.application.tchapj.bean.SmsCodeBean;
import com.application.tchapj.login.bean.LoginResult;
import com.application.tchapj.my.bean.GuanggaoBean;
import com.application.tchapj.my.view.IGuangGaoView;
import com.application.tchapj.widiget.LogUtils;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2018\8\23 0023.
 */

public class GuangGaoPresenter extends BaseMvpPresenter<IGuangGaoView> {

    public GuangGaoPresenter(App app) {
        super(app);
    }

    // 验证码
    public void getBindingSmsCodeResult(String username){
        // isViewAttached()方法判断是否在显示器上显示
        if(isViewAttached()){
            getView().showProgress();
        }

        // 观察者被观察者模式
        // 得到根接口路径
        getAppComponent()
                .getAPIService() // 所有接口对象
                .getBindingSmsCodeResult(username,"002","1.0","","JSON") // 得到登录接口
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
                    public void onNext(BaseBean<SmsCodeBean> loginResultBean) {
                        LogUtils.d("Response:"+ loginResultBean);
                        if(isViewAttached())
                            getView().onGetSmsCodeResult(loginResultBean); // 得到一层数据
                    }
                });
    }


    // 上传广告资料
    public void getDarenDataOneBeanResult(String memberId,String realName
            ,String nickName,String companyName,String conpanyCreditCode,String conpanyImgUrl
            ,String phoneNumber,String code,String industry){
        // isViewAttached()方法判断是否在显示器上显示
        if(isViewAttached()){
            getView().showProgress();
        }

        // 观察者被观察者模式
        // 得到根接口路径
        getAppComponent()
                .getAPIService() // 所有接口对象
                .getGuanggaoBeanResult(memberId,realName,nickName
                        ,companyName,conpanyCreditCode,conpanyImgUrl,phoneNumber,code,industry
                        ,"002","1.0","","JSON") // 得到登录接口
                .subscribeOn(Schedulers.io()) // 订阅方式
                .observeOn(AndroidSchedulers.mainThread()) // 指定线程
                .subscribe(new Subscriber<GuanggaoBean>() {  // 将数据绑定到实体类的操作
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
                    public void onNext(GuanggaoBean guanggaoBean) {
                        LogUtils.d("Response:"+ guanggaoBean);
                        if(isViewAttached())
                            getView().onGetGuangGaoResult(guanggaoBean); // 得到一层数据
                    }
                });
    }

}
