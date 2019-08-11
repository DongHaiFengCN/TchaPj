package com.application.tchapj.task.presenter;

import com.application.tchapj.App;
import com.application.tchapj.base.BaseMvpPresenter;
import com.application.tchapj.task.bean.FaTaskBean;
import com.application.tchapj.task.bean.FaTaskSuccessBean;
import com.application.tchapj.task.bean.FaTaskSuccessafterBean;
import com.application.tchapj.task.view.IFaTaskView;
import com.application.tchapj.widiget.AopUtils;
import com.application.tchapj.widiget.LogUtils;

import java.util.HashMap;
import java.util.Map;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2018\8\25 0025.
 */

public class FaTaskPresenter extends BaseMvpPresenter<IFaTaskView> {

    public FaTaskPresenter(App app) {
        super(app);
    }

    // 发任务

    /**
     *
     * @param memberId
     * @param taskType
     * @param name
     * @param require
     * @param activityImgUrl
     * @param startTime
     * @param endTime
     * @param unitPrice
     * @param taskImgUrl
     * @param taskNum
     * @param taskGuidance
     * @param phonenumber
     * @param copywriting
     * @param Fans
     * @param ForwardUrl
     * @param discount  折扣
     */
    public void getFaTaskBeanResult(String memberId,String taskType
            ,String name,String require,String activityImgUrl,String startTime
            ,String endTime,String unitPrice,String taskImgUrl
            ,String taskNum,String taskGuidance,String phonenumber,String copywriting,String Fans
            ,String ForwardUrl, String discount){
        // isViewAttached()方法判断是否在显示器上显示
        if(isViewAttached()){
            getView().showProgress();
        }

        // 观察者被观察者模式
        // 得到根接口路径
        getAppComponent()
                .getAPIService() // 所有接口对象
                .getFaTaskBeanResult(memberId,taskType,name
                        ,require,activityImgUrl,startTime,endTime,unitPrice,taskImgUrl
                        ,taskNum,taskGuidance,phonenumber,copywriting,Fans,ForwardUrl, discount
                        ,"002","1.0","","JSON") // 得到登录接口
                .subscribeOn(Schedulers.io()) // 订阅方式
                .observeOn(AndroidSchedulers.mainThread()) // 指定线程
                .subscribe(new Subscriber<FaTaskBean>() {  // 将数据绑定到实体类的操作
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
                    public void onNext(FaTaskBean faTaskBean) {
                        LogUtils.d("Response:"+ faTaskBean);
                        if(isViewAttached())
                            getView().onGetFaTaskBeanModels(faTaskBean); // 得到一层数据
                    }
                });
    }

    // 预付订单
    public void getFaTaskSuccessBeanResult(String amount,String name){
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
                .getFaTaskSuccessBeanResult(amount,name
                        ,"002","1.0",sign1,"JSON") // 得到登录接口
                .subscribeOn(Schedulers.io()) // 订阅方式
                .observeOn(AndroidSchedulers.mainThread()) // 指定线程
                .subscribe(new Subscriber<FaTaskSuccessBean>() {  // 将数据绑定到实体类的操作
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
                    public void onNext(FaTaskSuccessBean faTaskSuccessBean) {
                        LogUtils.d("Response:"+ faTaskSuccessBean);
                        if(isViewAttached())
                            getView().onGetFaTaskSuccessBeanModels(faTaskSuccessBean); // 得到一层数据
                    }
                });
    }

    // 任务付款
    public void getFaTaskSuccessafterBeanResult(String amount,String id,String ordernumber,String memberId){
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
                .getFaTaskSuccessafterBeanResult(amount,id,ordernumber,memberId
                        ,"002","1.0",sign1,"JSON") // 得到登录接口
                .subscribeOn(Schedulers.io()) // 订阅方式
                .observeOn(AndroidSchedulers.mainThread()) // 指定线程
                .subscribe(new Subscriber<FaTaskSuccessafterBean>() {  // 将数据绑定到实体类的操作
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
                    public void onNext(FaTaskSuccessafterBean faTaskSuccessafterBean) {
                        LogUtils.d("Response:"+ faTaskSuccessafterBean);
                        if(isViewAttached())
                            getView().onGetFaTaskSuccessafterBeanModels(faTaskSuccessafterBean); // 得到一层数据
                    }
                });
    }


}
