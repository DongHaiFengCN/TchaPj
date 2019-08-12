package com.application.tchapj.my.presenter;

import com.application.tchapj.App;
import com.application.tchapj.base.BaseMvpPresenter;
import com.application.tchapj.login.bean.LoginResult;
import com.application.tchapj.login.bean.SmsCodeResponse;
import com.application.tchapj.my.bean.MicroInfoBean;
import com.application.tchapj.my.bean.MicroTabBean;
import com.application.tchapj.my.view.IMicroView;
import com.application.tchapj.widiget.LogUtils;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by 安卓开发 on 2018/8/1.
 */

public class MicroPresenter extends BaseMvpPresenter<IMicroView> {

    public MicroPresenter(App app) {
        super(app);
    }

    // 小微号标签
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
                        if(isViewAttached())
                            getView().onCompleted();
                    }

                    @Override
                    public void onError(Throwable e) {
                        if(isViewAttached())
                            getView().onError(e);
                    }

                    @Override // 得到数据
                    public void onNext(MicroTabBean microTabBean) {
                        LogUtils.d("Response:"+ microTabBean);
                        if(isViewAttached())
                            getView().onGetMicroTabBeanResult(microTabBean); // 得到一层数据

                    }
                });
    }

    // 验证码
    public void getSmsCodeResult(String username){
        // isViewAttached()方法判断是否在显示器上显示
        if(isViewAttached()){
            getView().showProgress();
        }

        // 观察者被观察者模式
        // 得到根接口路径
        getAppComponent()
                .getAPIService() // 所有接口对象
                .getSmsCodeResult(username,"002","1.0","","JSON") // 得到登录接口
                .subscribeOn(Schedulers.io()) // 订阅方式
                .observeOn(AndroidSchedulers.mainThread()) // 指定线程
                .subscribe(new Subscriber<SmsCodeResponse>() {  // 将数据绑定到实体类的操作
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
                    public void onNext(SmsCodeResponse loginResultBean) {
                        LogUtils.d("Response:"+ loginResultBean);
                        if(isViewAttached())
                            getView().onGetSmsCodeResult(loginResultBean); // 得到一层数据
                    }
                });
    }



    /**
     * 认证
     * @param Id 用户id
     * @param catType 0 名人 1 媒体
     * @param resourcesTypeId
     * @param xwhName
     * @param headimageUrl
     * @param realName
     * @param IDnumber
     * @param IDimgurl
     * @param IDbackimgurl
     * @param phoneNumber
     * @param code
     * @param companyName
     * @param conpanyCreditCode
     * @param conpanyImgUrl
     * @param cityId 城市id
     */
    public void getMicroInfoBeanResult(String Id,String catType,String resourcesTypeId,String xwhName
            ,String headimageUrl,String realName,String IDnumber,String IDimgurl,String IDbackimgurl
            ,String phoneNumber,String code,String companyName,String conpanyCreditCode
            ,String conpanyImgUrl, String cityId){
        // isViewAttached()方法判断是否在显示器上显示
        if(isViewAttached()){
            getView().showProgress();
        }

        // 观察者被观察者模式
        // 得到根接口路径
        getAppComponent()
                .getAPIService() // 所有接口对象
                .getMicroInfoBeanResult(Id,catType,resourcesTypeId,xwhName,headimageUrl
                        ,realName,IDnumber,phoneNumber,code
                        ,companyName,conpanyCreditCode,conpanyImgUrl, cityId
                        ,"002","1.0","","JSON") // 得到登录接口
                .subscribeOn(Schedulers.io()) // 订阅方式
                .observeOn(AndroidSchedulers.mainThread()) // 指定线程
                .subscribe(new Subscriber<MicroInfoBean>() {  // 将数据绑定到实体类的操作
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
                    public void onNext(MicroInfoBean microInfoBean) {
                        LogUtils.d("Response:"+ microInfoBean);
                        if(isViewAttached())
                            getView().onGetMicroInfoBeanResult(microInfoBean); // 得到一层数据
                    }
                });
    }

    /**
     * 认证
     * @param Id 用户id
     * @param catType 0 名人 1 媒体
     * @param resourcesTypeId
     * @param xwhName
     * @param headimageUrl
     * @param realName
     * @param IDnumber
     * @param IDimgurl
     * @param IDbackimgurl
     * @param phoneNumber
     * @param code
     * @param companyName
     * @param conpanyCreditCode
     * @param conpanyImgUrl
     */
    public void getMicroInfoBeanResult(String Id,String catType,String resourcesTypeId,String xwhName
            ,String headimageUrl,String realName,String IDnumber,String IDimgurl,String IDbackimgurl
            ,String phoneNumber,String code,String companyName,String conpanyCreditCode
            ,String conpanyImgUrl){
        // isViewAttached()方法判断是否在显示器上显示
        if(isViewAttached()){
            getView().showProgress();
        }

        // 观察者被观察者模式
        // 得到根接口路径
        getAppComponent()
                .getAPIService() // 所有接口对象
                .getMicroInfoBeanResult(Id,catType,resourcesTypeId,xwhName,headimageUrl
                        ,realName,IDnumber,phoneNumber,code
                        ,companyName,conpanyCreditCode,conpanyImgUrl
                        ,"002","1.0","","JSON") // 得到登录接口
                .subscribeOn(Schedulers.io()) // 订阅方式
                .observeOn(AndroidSchedulers.mainThread()) // 指定线程
                .subscribe(new Subscriber<MicroInfoBean>() {  // 将数据绑定到实体类的操作
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
                    public void onNext(MicroInfoBean microInfoBean) {
                        LogUtils.d("Response:"+ microInfoBean);
                        if(isViewAttached())
                            getView().onGetMicroInfoBeanResult(microInfoBean); // 得到一层数据
                    }
                });
    }

}
