package com.application.tchapj.task.presenter;

import com.application.tchapj.App;
import com.application.tchapj.base.BaseMvpPresenter;
import com.application.tchapj.task.bean.MyTaskSquareModel;
import com.application.tchapj.task.view.MyTaskSquareView;
import com.king.base.util.LogUtils;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

// 任务广场界面的 Presenter 层 业务逻辑继承该层的接口  并 实现View层接口和Model层业务的处理逻辑
public class MyTaskSquarePresenter extends BaseMvpPresenter<MyTaskSquareView> {


    public MyTaskSquarePresenter(App app) {
        super(app);
    }

    // 我发的任务
    public void getFaTaskSquare(String memberId,String pageNum, String pageSize){
        // isViewAttached()方法判断是否在显示器上显示
        if(isViewAttached()){
            getView().showProgress();
        }

        // 观察者被观察者模式
        // 得到根接口路径
        getAppComponent()
                .getAPIService() // 所有接口对象
                .getFaTaskSquareinfoModelResult(memberId,pageNum,pageSize,"002","1.0","","JSON") // 得到登录接口
                .subscribeOn(Schedulers.io()) // 订阅方式
                .observeOn(AndroidSchedulers.mainThread()) // 指定线程
                .subscribe(new Subscriber<MyTaskSquareModel>() {  // 将数据绑定到实体类的操作
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
                    public void onNext(MyTaskSquareModel taskSquareModel) {
                        LogUtils.d("Response:"+ taskSquareModel);
                        if(isViewAttached())
                            getView().onGetMyFaTaskSquareModels(taskSquareModel); // 得到一层数据

                    }
                });
    }

    // 我领的任务
    public void getTaskSquare(String memberId,String pageNum, String pageSize){
        // isViewAttached()方法判断是否在显示器上显示
        if(isViewAttached()){
            getView().showProgress();
        }

        // 观察者被观察者模式
        // 得到根接口路径
        getAppComponent()
                .getAPIService() // 所有接口对象
                .getTaskSquareinfoModelResult(memberId,pageNum,pageSize,"002","1.0","","JSON") // 得到登录接口
                .subscribeOn(Schedulers.io()) // 订阅方式
                .observeOn(AndroidSchedulers.mainThread()) // 指定线程
                .subscribe(new Subscriber<MyTaskSquareModel>() {  // 将数据绑定到实体类的操作
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
                    public void onNext(MyTaskSquareModel taskSquareModel) {
                        LogUtils.d("Response:"+ taskSquareModel);
                        if(isViewAttached())
                            getView().onGetMyTaskSquareModels(taskSquareModel); // 得到一层数据

                    }
                });
    }

}
