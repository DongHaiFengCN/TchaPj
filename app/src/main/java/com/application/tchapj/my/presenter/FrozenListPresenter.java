package com.application.tchapj.my.presenter;

import com.application.tchapj.App;
import com.application.tchapj.base.BaseBean;
import com.application.tchapj.base.BaseMvpPresenter;
import com.application.tchapj.http.APIService;
import com.application.tchapj.my.bean.FrozenListBean;
import com.application.tchapj.my.view.IFrozenListView;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class FrozenListPresenter extends BaseMvpPresenter<IFrozenListView> {

    public FrozenListPresenter(App app) {
        super(app);
    }


    // 冻结资金列表
    public void getMoneyFrozenListModelResult(int pageNum, int pageSize){

        // isViewAttached()方法判断是否在显示器上显示
        if(isViewAttached()){
            getView().showProgress();
        }

        // 观察者被观察者模式
        // 得到根接口路径
        getAppComponent()
                .getAPIService() // 所有接口对象
                .getMoneyFrozenListResult(APIService.APP_KEY,APIService.V,APIService.SIGN, APIService.FORMAT, App.getId(), pageNum + "", pageSize + "") // 得到登录接口
                .subscribeOn(Schedulers.io()) // 订阅方式
                .observeOn(AndroidSchedulers.mainThread()) // 指定线程
                .subscribe(new Subscriber<BaseBean<FrozenListBean>>() {  // 将数据绑定到实体类的操作
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
                    public void onNext(BaseBean<FrozenListBean> baseBean) {
                        if(isViewAttached() && baseBean != null && baseBean.getCode().equals("000") && baseBean.getData() != null)
                            getView().getMoneyFrozenListModelResult(baseBean.getData().getFrozenlist()); // 得到一层数据

                    }
                });
    }

    // 冻结资金转到余额
    public void getFrozenToBalanceModelResult(String id, String amount){

        // isViewAttached()方法判断是否在显示器上显示
        if(isViewAttached()){
            getView().showProgress();
        }

        // 观察者被观察者模式
        // 得到根接口路径
        getAppComponent()
                .getAPIService() // 所有接口对象
                .getFrozenToBalanceResult(APIService.APP_KEY,APIService.V,APIService.SIGN, APIService.FORMAT, App.getId(), id, amount) // 得到登录接口
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
                        getView().getFrozenToBalanceModelResult(baseBean);

                    }
                });
    }
}
