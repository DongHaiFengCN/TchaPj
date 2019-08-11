package com.application.tchapj.task.presenter;

import android.util.Log;

import com.application.tchapj.App;
import com.application.tchapj.base.BaseMvpPresenter;
import com.application.tchapj.my.bean.QiniuBean;
import com.application.tchapj.task.bean.FriendReleaseBean;
import com.application.tchapj.task.bean.TaskSquareInfoModel;
import com.application.tchapj.task.bean.TaskSquareModel;
import com.application.tchapj.task.view.ITaskSquareView;
import com.king.base.util.LogUtils;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

// 任务广场界面的 Presenter 层 业务逻辑继承该层的接口  并 实现View层接口和Model层业务的处理逻辑
public class TaskSquarePresenter extends BaseMvpPresenter<ITaskSquareView> {


    public TaskSquarePresenter(App app) {
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

    // 任务广场界面
    public void getTaskSquare(String memberId, String pageNum, String pageSize){
        // isViewAttached()方法判断是否在显示器上显示
        if(isViewAttached()){
            getView().showProgress();
        }

        // 观察者被观察者模式
        // 得到根接口路径
        getAppComponent()
                .getAPIService() // 所有接口对象
                .getTaskSquareModelResult(memberId,pageNum,pageSize,"002","1.0","","JSON") // 得到登录接口
                .subscribeOn(Schedulers.io()) // 订阅方式
                .observeOn(AndroidSchedulers.mainThread()) // 指定线程
                .subscribe(new Subscriber<TaskSquareModel>() {  // 将数据绑定到实体类的操作
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
                    public void onNext(TaskSquareModel taskSquareModel) {
                        LogUtils.d("Response:"+ taskSquareModel);
                        if(isViewAttached())
                            getView().onGetTaskSquareModels(taskSquareModel); // 得到一层数据

                    }
                });
    }

    // 任务分析界面
    public void getTaskAnalysis(String memberId, String status,String pageNum, String pageSize){
        // isViewAttached()方法判断是否在显示器上显示
        if(isViewAttached()){
            getView().showProgress();
        }

        // 观察者被观察者模式
        // 得到根接口路径
        getAppComponent()
                .getAPIService() // 所有接口对象
                .getMyTaskListResult(memberId,status,pageNum,pageSize,"002","1.0","","JSON") // 得到登录接口
                .subscribeOn(Schedulers.io()) // 订阅方式
                .observeOn(AndroidSchedulers.mainThread()) // 指定线程
                .subscribe(new Subscriber<TaskSquareModel>() {  // 将数据绑定到实体类的操作
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
                    public void onNext(TaskSquareModel taskSquareModel) {
                        LogUtils.d("Response:"+ taskSquareModel);
                        if(isViewAttached())
                            getView().onGetTaskSquareModels(taskSquareModel); // 得到一层数据

                    }
                });
    }

    /**
     * 获取任务详情
     */
    public void getFriendReleaseData(String memberId,String id) {
        if (isViewAttached()) {
            getView().showProgress();
        }
        getAppComponent()
                .getAPIService()
                .getTaskDetail(memberId, id, "002", "1.0", "", "JSON")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<TaskSquareInfoModel>() {
            @Override
            public void onCompleted() {
                Log.e("135", "onCompleted");
                if (isViewAttached()) {
                    getView().onCompleted();
                }
            }

            @Override
            public void onError(Throwable e) {
                Log.e("135", e.toString());
                if (isViewAttached()) {
                    getView().onError(e);
                }
            }

            @Override
            public void onNext(TaskSquareInfoModel friendReleaseBean) {
                if (isViewAttached()) {
                    getView().onGetFriendReleaseData(friendReleaseBean);
                }
            }
        });
    }

}
