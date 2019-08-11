package com.application.tchapj.my.presenter;

import com.application.tchapj.App;
import com.application.tchapj.base.BaseBean;
import com.application.tchapj.base.BaseMvpPresenter;
import com.application.tchapj.http.APIService;
import com.application.tchapj.my.bean.AttentionListBean;
import com.application.tchapj.my.bean.FansListBean;
import com.application.tchapj.my.view.IAttentionFanView;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class AttentionFanPresenter extends BaseMvpPresenter<IAttentionFanView> {

    public AttentionFanPresenter(App app) {
        super(app);
    }

    // 我的关注-名人列表
    public void getMyAttentionCelebrityListModelResult(int pageNum, int pageSize){

        // isViewAttached()方法判断是否在显示器上显示
        if(isViewAttached()){
            getView().showProgress();
        }

        // 观察者被观察者模式
        // 得到根接口路径
        getAppComponent()
                .getAPIService() // 所有接口对象
                .getMyAttentionCelebrityListModelResult(APIService.APP_KEY,APIService.V,APIService.SIGN, APIService.FORMAT, App.getId(),pageNum + "", pageSize + "") // 得到登录接口
                .subscribeOn(Schedulers.io()) // 订阅方式
                .observeOn(AndroidSchedulers.mainThread()) // 指定线程
                .subscribe(new Subscriber<BaseBean<AttentionListBean>>() {  // 将数据绑定到实体类的操作
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
                    public void onNext(BaseBean<AttentionListBean> baseBean) {
                        if(isViewAttached() && baseBean != null && baseBean.getCode().equals("000") && baseBean.getData() != null)
                            getView().getAttentionCelebrityListResult(baseBean.getData().getAttentionbymrList()); // 得到一层数据

                    }
                });
    }

    // 我的关注-媒体列表
    public void getMyAttentionMediaListModelResult(int pageNum, int pageSize){

        // isViewAttached()方法判断是否在显示器上显示
        if(isViewAttached()){
            getView().showProgress();
        }

        // 观察者被观察者模式
        // 得到根接口路径
        getAppComponent()
                .getAPIService() // 所有接口对象
                .getMyAttentionMediaListModelResult(APIService.APP_KEY,APIService.V,APIService.SIGN, APIService.FORMAT,  App.getId(),pageNum + "", pageSize + "") // 得到登录接口
                .subscribeOn(Schedulers.io()) // 订阅方式
                .observeOn(AndroidSchedulers.mainThread()) // 指定线程
                .subscribe(new Subscriber<BaseBean<AttentionListBean>>() {  // 将数据绑定到实体类的操作
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
                    public void onNext(BaseBean<AttentionListBean> baseBean) {
                        if(isViewAttached() && baseBean != null && baseBean.getCode().equals("000") && baseBean.getData() != null)
                            getView().getAttentionCelebrityListResult(baseBean.getData().getAttentionbymtList()); // 得到一层数据

                    }
                });
    }


    // 我的关注-圈子列表
    public void getMyAttentionCircleListModelResult(int pageNum, int pageSize){

        // isViewAttached()方法判断是否在显示器上显示
        if(isViewAttached()){
            getView().showProgress();
        }

        // 观察者被观察者模式
        // 得到根接口路径
        getAppComponent()
                .getAPIService() // 所有接口对象
                .getMyAttentionCircleListModelResult(APIService.APP_KEY,APIService.V,APIService.SIGN, APIService.FORMAT, App.getId(), pageNum + "", pageSize + "") // 得到登录接口
                .subscribeOn(Schedulers.io()) // 订阅方式
                .observeOn(AndroidSchedulers.mainThread()) // 指定线程
                .subscribe(new Subscriber<BaseBean<AttentionListBean>>() {  // 将数据绑定到实体类的操作
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
                    public void onNext(BaseBean<AttentionListBean> baseBean) {
                        if(isViewAttached() && baseBean != null && baseBean.getCode().equals("000") && baseBean.getData() != null)
                            getView().getAttentionCircleListResult(baseBean.getData().getAttentionbyQzList()); // 得到一层数据


                    }
                });
    }

    // 我的粉丝
    public void getMyFansListModelResult(int pageNum, int pageSize){

        // isViewAttached()方法判断是否在显示器上显示
        if(isViewAttached()){
            getView().showProgress();
        }

        // 观察者被观察者模式
        // 得到根接口路径
        getAppComponent()
                .getAPIService() // 所有接口对象
                .getMyFansListModelResult(APIService.APP_KEY,APIService.V,APIService.SIGN, APIService.FORMAT, App.getId(), pageNum + "", pageSize + "") // 得到登录接口
                .subscribeOn(Schedulers.io()) // 订阅方式
                .observeOn(AndroidSchedulers.mainThread()) // 指定线程
                .subscribe(new Subscriber<BaseBean<FansListBean>>() {  // 将数据绑定到实体类的操作
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
                    public void onNext(BaseBean<FansListBean> baseBean) {
                        if(isViewAttached() && baseBean != null && baseBean.getCode().equals("000") && baseBean.getData() != null){
                            getView().getFansListResult(baseBean.getData().getFansList());
                        }



                    }
                });
    }

}
