package com.application.tchapj.my.presenter;

import com.application.tchapj.App;
import com.application.tchapj.base.BaseMvpPresenter;
import com.application.tchapj.login.bean.LoginResult;
import com.application.tchapj.login.bean.SmsCodeResponse;
import com.application.tchapj.my.bean.DarenDataBean;
import com.application.tchapj.my.bean.DarenDataOneBean;
import com.application.tchapj.my.view.IDarenOneView;
import com.application.tchapj.widiget.LogUtils;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by 安卓开发 on 2018/8/20.
 */

public class DarenOnePresenter extends BaseMvpPresenter<IDarenOneView> {

    public DarenOnePresenter(App app) {
        super(app);
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

    // 达人资料
    public void getDarenDataOneBeanResult(String memberId, String resourcesTypeId
            , String realName, String sex, String age, String phoneNumber
            , String idNumber, String content, String headimageUrl, String nickName
            , String catType, String price, String screenshotIngUrl, String cityId, String inviteCodeStr){
        // isViewAttached()方法判断是否在显示器上显示
        if(isViewAttached()){
            getView().showProgress();
        }

        // 观察者被观察者模式
        // 得到根接口路径
        getAppComponent()
                .getAPIService() // 所有接口对象
                .getDarenDataOneBeanResult(memberId,resourcesTypeId,realName
                        ,sex,age,phoneNumber,idNumber,content,headimageUrl,nickName
                        ,"002","1.0","","JSON", catType, price
                        , screenshotIngUrl, cityId, inviteCodeStr) // 得到登录接口
                .subscribeOn(Schedulers.io()) // 订阅方式
                .observeOn(AndroidSchedulers.mainThread()) // 指定线程
                .subscribe(new Subscriber<DarenDataOneBean>() {  // 将数据绑定到实体类的操作
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
                    public void onNext(DarenDataOneBean darenDataOneBean) {
                        LogUtils.d("Response:"+ darenDataOneBean);
                        if(isViewAttached())
                            getView().onGetDarenDataOneBeanResult(darenDataOneBean); // 得到一层数据
                    }
                });
    }

    // 上传达人频道资料
    public void getDarenDataBeanResult(String Id,String MemberId,String catType
            ,String fans,String nickName,String price,String screenshotIngUrl,String province
            ,String city,String conpanyImgUrl
            ,String Views,String comments,String likes){
        // isViewAttached()方法判断是否在显示器上显示
        if(isViewAttached()){
            getView().showProgress();
        }

        // 观察者被观察者模式
        // 得到根接口路径
        getAppComponent()
                .getAPIService() // 所有接口对象
                .getDarenDataBeanResult(Id,MemberId,catType,fans,nickName,price,screenshotIngUrl
                        ,province,city,conpanyImgUrl,Views,comments,likes
                        ,"002","1.0","","JSON") // 得到登录接口
                .subscribeOn(Schedulers.io()) // 订阅方式
                .observeOn(AndroidSchedulers.mainThread()) // 指定线程
                .subscribe(new Subscriber<DarenDataBean>() {  // 将数据绑定到实体类的操作
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
                    public void onNext(DarenDataBean darenDataBean) {
                        LogUtils.d("Response:"+ darenDataBean);
                        if(isViewAttached())
                            getView().onGetDarenDataBeanResult(darenDataBean); // 得到一层数据
                    }
                });
    }
}
