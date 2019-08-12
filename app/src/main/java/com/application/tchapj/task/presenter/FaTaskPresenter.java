package com.application.tchapj.task.presenter;

import android.util.Log;

import com.application.tchapj.App;
import com.application.tchapj.base.BaseBean;
import com.application.tchapj.base.BaseMvpPresenter;
import com.application.tchapj.bean.PromotionPayResultBean;
import com.application.tchapj.bean.PromotionResultBean;
import com.application.tchapj.my.bean.MoneyInfoBean;
import com.application.tchapj.task.bean.FaTaskBean;
import com.application.tchapj.task.bean.FaTaskSuccessBean;
import com.application.tchapj.task.bean.FaTaskSuccessafterBean;
import com.application.tchapj.task.view.IFaTaskView;
import com.application.tchapj.widiget.AopUtils;
import com.application.tchapj.widiget.LogUtils;

import java.util.HashMap;
import java.util.Map;

import rx.Observer;
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

    /**
     *  发送任务 获取任务id
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
     */
    public void getFaTaskBeanResult(String memberId,String taskType,String taskImageType
            ,String name,String require,String activityImgUrl,String startTime
            ,String endTime,String unitPrice,String taskImgUrl
            ,String taskNum,String taskGuidance,String phonenumber,String copywriting,String Fans
            ,String ForwardUrl){
        // isViewAttached()方法判断是否在显示器上显示
        if(isViewAttached()){
            getView().showProgress();
        }

        Log.e("DOAING++++++",name);
        getAppComponent()
                .getAPIService() // 所有接口对象
                .getFaTaskBeanResult(memberId,taskType,taskImageType,name
                        ,require,activityImgUrl,startTime,endTime,unitPrice,taskImgUrl
                        ,taskNum,taskGuidance,phonenumber,copywriting,Fans,ForwardUrl
                        ,"002","1.0","","JSON","1","","pm.task.release")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<FaTaskBean>() {
                    @Override
                    public void onCompleted() {
                        if(isViewAttached()) {
                            getView().onCompleted();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if(isViewAttached()) {
                            getView().onError(e);
                        }
                    }

                    @Override // 得到数据
                    public void onNext(FaTaskBean faTaskBean) {
                        LogUtils.d("Response:"+ faTaskBean);
                        if(isViewAttached()) {
                            getView().onGetFaTaskBeanModels(faTaskBean); // 得到一层数据
                        }
                    }
                });
    }

    public void promotionPayOrder(String amount,String taskId){

        getAppComponent()
                .getAPIService()
                .promotionPayOrder("002","","promotion.app.pay","1.0","JSON","","1",amount,taskId,App.getId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<PromotionPayResultBean>() {
                    @Override
                    public void onCompleted() {
                        if(isViewAttached()) {
                            getView().onCompleted();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if(isViewAttached()) {
                            getView().onError(e);
                        }
                    }

                    @Override
                    public void onNext(PromotionPayResultBean promotionPayResultBean) {
                        if(isViewAttached()) {
                            getView().onGetPromotionPaySuccess(promotionPayResultBean);
                        }
                    }


                });

    }
    // 预付订单
    public void getAlipayOrderInfoResult(String amount, String name){


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
                .getFaTaskSuccessBeanResult(amount,"alipay.app.pay",name
                        ,"002","1.0",sign1,"JSON")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<FaTaskSuccessBean>() {
                    @Override
                    public void onCompleted() {
                        if(isViewAttached()) {
                            getView().onCompleted();
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        if(isViewAttached()) {
                            getView().onError(e);
                        }
                    }

                    @Override // 得到数据
                    public void onNext(FaTaskSuccessBean faTaskSuccessBean) {
                        if(isViewAttached()) {
                            getView().onGetAlipayOrderInfoSuccessBeanModels(faTaskSuccessBean); // 得到一层数据*/
                        }
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
                        if(isViewAttached()) {
                            getView().onCompleted();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if(isViewAttached()) {
                            getView().onError(e);
                        }
                    }

                    @Override // 得到数据
                    public void onNext(FaTaskSuccessafterBean faTaskSuccessafterBean) {
                        LogUtils.d("Response:"+ faTaskSuccessafterBean);
                        if(isViewAttached()) {
                            getView().onGetFaTaskSuccessafterBeanModels(faTaskSuccessafterBean); // 得到一层数据
                        }
                    }
                });
    }

    /**
     * 获取佣金余额
     */
    public void getBrokerage(){
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
                .getMoneyInfoBeanResult(App.getId(),"002","1.0",sign1,"JSON") // 得到登录接口
                .subscribeOn(Schedulers.io()) // 订阅方式
                .observeOn(AndroidSchedulers.mainThread()) // 指定线程
                .subscribe(new Observer<MoneyInfoBean>() {
                    @Override
                    public void onCompleted() {
                        if(isViewAttached()) {
                            getView().onCompleted();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if(isViewAttached()) {
                            getView().onError(e);
                        }
                    }

                    @Override
                    public void onNext(MoneyInfoBean moneyInfoBean) {

                        Log.e("DOAING",moneyInfoBean.getCode());
                        if(isViewAttached()) {
                            getView().onGetBrokerage(moneyInfoBean);
                        }
                    }
                });

    }

}
