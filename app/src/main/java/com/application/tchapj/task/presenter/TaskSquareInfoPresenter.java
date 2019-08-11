package com.application.tchapj.task.presenter;

import com.application.tchapj.App;
import com.application.tchapj.base.BaseMvpPresenter;
import com.application.tchapj.task.bean.TaskSquareInfoModel;
import com.application.tchapj.task.view.ITaskSquareInfoView;
import com.king.base.util.LogUtils;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by 安卓开发 on 2018/7/25.
 */

public class TaskSquareInfoPresenter extends BaseMvpPresenter<ITaskSquareInfoView> {

    public TaskSquareInfoPresenter(App app) {
        super(app);
    }

    public void getTaskSquareInfo(String id){
        // isViewAttached()方法判断是否在显示器上显示
        if(isViewAttached()){
            getView().showProgress();
        }

        // 观察者被观察者模式
        // 得到根接口路径
        getAppComponent()
                .getAPIService() // 所有接口对象
                .getTaskDetail(App.getId(), id,"002","1.0","","JSON") // 得到登录接口
                .subscribeOn(Schedulers.io()) // 订阅方式
                .observeOn(AndroidSchedulers.mainThread()) // 指定线程
                .subscribe(new Subscriber<TaskSquareInfoModel>() {  // 将数据绑定到实体类的操作
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
                    public void onNext(TaskSquareInfoModel taskSquareInfoModel) {
                        LogUtils.d("Response:"+ taskSquareInfoModel);
                        if(isViewAttached())
                            getView().onGetTaskSquareInfoModels(taskSquareInfoModel); // 得到一层数据

                    }
                });
    }

}
