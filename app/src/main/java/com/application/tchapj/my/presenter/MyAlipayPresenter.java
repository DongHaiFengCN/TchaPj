package com.application.tchapj.my.presenter;

import com.application.tchapj.App;
import com.application.tchapj.base.BaseBean;
import com.application.tchapj.base.BaseMvpPresenter;
import com.application.tchapj.main.bean.MemberInfoWhbyBean;
import com.application.tchapj.my.bean.AlipayPowerBean;
import com.application.tchapj.my.bean.AlipayPrivateKeyBean;
import com.application.tchapj.my.bean.QiniuBean;
import com.application.tchapj.my.bean.UpMyInfoBean;
import com.application.tchapj.my.view.IMyAlipayView;
import com.application.tchapj.widiget.AopUtils;
import com.application.tchapj.widiget.LogUtils;

import java.util.HashMap;
import java.util.Map;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

// 授权
public class MyAlipayPresenter extends BaseMvpPresenter<IMyAlipayView> {

    public MyAlipayPresenter(App app) {
        super(app);
    }

    // 七牛token
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

    public void onGetAlipayPrivateKeyBeanResult(){
        // isViewAttached()方法判断是否在显示器上显示
        if(isViewAttached()){
            getView().showProgress();
        }

        Map<String, String> map = new HashMap<>();
        map.put("appKey", "002");
        map.put("v", "1.0");
        map.put("format", "JSON");
        String sign1 = AopUtils.sign(map, "abc");

        // 观察者被观察者模式
        // 得到根接口路径
        getAppComponent()
                .getAPIService() // 所有接口对象
                .getAlipayPrivateKeyBeanResult("002","1.0",sign1,"JSON") // 得到登录接口
                .subscribeOn(Schedulers.io()) // 订阅方式
                .observeOn(AndroidSchedulers.mainThread()) // 指定线程
                .subscribe(new Subscriber<AlipayPrivateKeyBean>() {  // 将数据绑定到实体类的操作
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
                    public void onNext(AlipayPrivateKeyBean alipayPrivateKeyBean) {
                        LogUtils.d("Response:"+ alipayPrivateKeyBean);
                        if(isViewAttached())
                            getView().onGetAlipayPrivateKeyBeanResult(alipayPrivateKeyBean); // 得到一层数据

                    }
                });
    }

    public void onGetAlipayPowerBeanResult(String memberId,String authCode){
        // isViewAttached()方法判断是否在显示器上显示
        if(isViewAttached()){
            getView().showProgress();
        }

        Map<String, String> map = new HashMap<>();
        map.put("appKey", "002");
        map.put("v", "1.0");
        map.put("format", "JSON");
        String sign1 = AopUtils.sign(map, "abc");
        // 观察者被观察者模式
        // 得到根接口路径
        getAppComponent()
                .getAPIService() // 所有接口对象
                .getAlipayPowerBeanResult(memberId,authCode,"002","1.0",sign1,"JSON") // 得到登录接口
                .subscribeOn(Schedulers.io()) // 订阅方式
                .observeOn(AndroidSchedulers.mainThread()) // 指定线程
                .subscribe(new Subscriber<AlipayPowerBean>() {  // 将数据绑定到实体类的操作
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
                    public void onNext(AlipayPowerBean alipayPowerBean) {
                        LogUtils.d("Response:"+ alipayPowerBean);
                        if(isViewAttached())
                            getView().onGetAlipayPowerBeanResult(alipayPowerBean); // 得到一层数据

                    }
                });
    }

    public void onGetUpMyInfoBeanResult(String memberId,String sex,String headimgurl,String nickName,String province,String city,String birthday){
        // isViewAttached()方法判断是否在显示器上显示
        if(isViewAttached()){
            getView().showProgress();
        }

        // 观察者被观察者模式
        // 得到根接口路径
        getAppComponent()
                .getAPIService() // 所有接口对象
                .getUpMyInfoBeanResult(memberId,sex,headimgurl,nickName,province,city,birthday,"002","1.0","","JSON") // 得到登录接口
                .subscribeOn(Schedulers.io()) // 订阅方式
                .observeOn(AndroidSchedulers.mainThread()) // 指定线程
                .subscribe(new Subscriber<UpMyInfoBean>() {  // 将数据绑定到实体类的操作
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
                    public void onNext(UpMyInfoBean upMyInfoBean) {
                        LogUtils.d("Response:"+ upMyInfoBean);
                        if(isViewAttached())
                            getView().onGetUpMyInfoBeanResult(upMyInfoBean); // 得到一层数据

                    }
                });
    }

    //获取微呼百应号信息
    public void onGetMemberWhbyhResult(String memberId){
        // isViewAttached()方法判断是否在显示器上显示
        if(isViewAttached()){
            getView().showProgress();
        }

        // 观察者被观察者模式
        // 得到根接口路径
        getAppComponent()
                .getAPIService() // 所有接口对象
                .getMemberWhbyhResult(memberId,"002","1.0","","JSON") // 得到登录接口
                .subscribeOn(Schedulers.io()) // 订阅方式
                .observeOn(AndroidSchedulers.mainThread()) // 指定线程
                .subscribe(new Subscriber<BaseBean<MemberInfoWhbyBean>>() {  // 将数据绑定到实体类的操作
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
                    public void onNext(BaseBean<MemberInfoWhbyBean> infoWhbyBeanBaseBean) {
                        LogUtils.d("Response:"+ infoWhbyBeanBaseBean);
                        if(isViewAttached())
                            getView().onGetMemberWhbyhResult(infoWhbyBeanBaseBean.getData()); // 得到一层数据

                    }
                });
    }

    /**
     * 修改微呼百应号信息
     * 参数signature 用户签名字段
     */
    public void onUpdateWhbyInfoResult(String headimgurl,String nickName,String province,String city,String mobile, String signature){
        if(isViewAttached()){
            getView().showProgress();
        }

        // 观察者被观察者模式
        // 得到根接口路径
        getAppComponent()
                .getAPIService() // 所有接口对象
                .updateWhbyInfoResult(App.getId(), headimgurl,nickName,province,city,mobile,signature,"002","1.0","","JSON") // 得到登录接口
                .subscribeOn(Schedulers.io()) // 订阅方式
                .observeOn(AndroidSchedulers.mainThread()) // 指定线程
                .subscribe(new Subscriber<BaseBean>() {  // 将数据绑定到实体类的操作
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
                    public void onNext(BaseBean baseBean) {
                        LogUtils.d("Response:"+ baseBean);
                        if(isViewAttached())
                            getView().onUpdateWhbyInfoResult(baseBean); // 得到一层数据

                    }
                });
    }

}
