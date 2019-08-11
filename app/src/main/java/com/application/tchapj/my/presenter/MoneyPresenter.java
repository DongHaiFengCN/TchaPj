package com.application.tchapj.my.presenter;

import android.content.Context;

import com.application.tchapj.App;
import com.application.tchapj.base.BaseBean;
import com.application.tchapj.base.BaseMvpPresenter;
import com.application.tchapj.http.APIService;
import com.application.tchapj.my.bean.MoneyInfoBean;
import com.application.tchapj.my.bean.MoneyInfoListBean;
import com.application.tchapj.my.bean.MoneyTransferBean;
import com.application.tchapj.my.view.IMoneyView;
import com.application.tchapj.utils.ToastUtil;
import com.application.tchapj.widiget.AopUtils;
import com.application.tchapj.widiget.LogUtils;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
/**
 * Created by Administrator on 2018\9\7 0007.
 */

public class MoneyPresenter extends BaseMvpPresenter<IMoneyView> {

    public MoneyPresenter(App app) {
        super(app);
    }

    // 获取钱包信息
    public void onGetMoneyInfoBeanResult(String memberId){
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
                .getMoneyInfoBeanResult(memberId,"002","1.0",sign1,"JSON") // 得到登录接口
                .subscribeOn(Schedulers.io()) // 订阅方式
                .observeOn(AndroidSchedulers.mainThread()) // 指定线程
                .subscribe(new Subscriber<MoneyInfoBean>() {  // 将数据绑定到实体类的操作
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
                    public void onNext(MoneyInfoBean moneyInfoBean) {
                        LogUtils.d("Response:"+ moneyInfoBean);
                        if(isViewAttached())
                            getView().onGetMoneyInfoBeanResult(moneyInfoBean); // 得到一层数据

                    }
                });
    }

    // 收益提现和退款--支付宝
    public void onGetMoneyTransferBeanResult(int amount, String id, String memberId, BigDecimal balance, Context context){
        if(amount <= 0){
            ToastUtil.show(context, "提现金额必须大于0元");
            return;
        }

        if(new BigDecimal(amount).compareTo(balance) == 1){
            //表示 amount * 10 > balance
            ToastUtil.show(context, "余额不足");
            return;
        }

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
                .getMoneyTransferBeanResult(amount + "",id,memberId,"002","1.0",sign1,"JSON") // 得到登录接口
                .subscribeOn(Schedulers.io()) // 订阅方式
                .observeOn(AndroidSchedulers.mainThread()) // 指定线程
                .subscribe(new Subscriber<MoneyTransferBean>() {  // 将数据绑定到实体类的操作
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
                    public void onNext(MoneyTransferBean moneyTransferBean) {
                        LogUtils.d("Response:"+ moneyTransferBean);
                        if(isViewAttached())
                            getView().onGetMoneyTransferBeanResult(moneyTransferBean); // 得到一层数据

                    }
                });
    }


    /**
     * 大额提现--人工转账
     *
     * @param amount 提现金额
     * @param bankOutlets 开户行
     * @param name 提现人姓名
     * @param accountNumber 银行卡账号
     */
    public void onGetArtificialTransferBeanResult(String amount, String bankOutlets, String name, String accountNumber){
        if(isViewAttached()){
            getView().showProgress();
        }

        Map<String, String> map = new HashMap<>();
        map.put("appKey", APIService.APP_KEY);
        map.put("v", APIService.V);
        map.put("format", APIService.FORMAT);
        String sign1 = AopUtils.sign(map, "abc");

        // 观察者被观察者模式
        // 得到根接口路径
        getAppComponent()
                .getAPIService() // 所有接口对象
                .getArtificialTransferResult(APIService.APP_KEY, APIService.V, sign1, APIService.FORMAT, App.getId(), amount, bankOutlets, name, accountNumber) // 得到登录接口
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

                    @Override
                    public void onNext(BaseBean baseBean) {
                        if(isViewAttached())
                            getView().onGetArtificialTransferBeanResult(baseBean);

                    }
                });
    }

    // 我的任务列表--零钱模块
    public void onGetMoneyInfoListBeanResult(String pageNum,String pageSize,String memberId){
        // isViewAttached()方法判断是否在显示器上显示
        if(isViewAttached()){
            getView().showProgress();
        }

        // 观察者被观察者模式
        // 得到根接口路径
        getAppComponent()
                .getAPIService() // 所有接口对象
                .getMoneyInfoListBeanResult(pageNum,pageSize,memberId,"002","1.0","","JSON") // 得到登录接口
                .subscribeOn(Schedulers.io()) // 订阅方式
                .observeOn(AndroidSchedulers.mainThread()) // 指定线程
                .subscribe(new Subscriber<MoneyInfoListBean>() {  // 将数据绑定到实体类的操作
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
                    public void onNext(MoneyInfoListBean moneyInfoListBean) {
                        LogUtils.d("Response:"+ moneyInfoListBean);
                        if(isViewAttached())
                            getView().onGetMoneyInfoListBeanResult(moneyInfoListBean); // 得到一层数据

                    }
                });
    }

}
