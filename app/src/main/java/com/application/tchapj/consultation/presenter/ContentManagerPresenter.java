package com.application.tchapj.consultation.presenter;

import com.application.tchapj.App;
import com.application.tchapj.base.BaseMvpPresenter;
import com.application.tchapj.consultation.bean.ConsultationTopModel;
import com.application.tchapj.consultation.bean.IsAuthorModel;
import com.application.tchapj.consultation.bean.UpdateAuthorModel;
import com.application.tchapj.consultation.view.IConsultationTobView;
import com.application.tchapj.my.bean.UserModel;
import com.application.tchapj.widiget.LogUtils;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class ContentManagerPresenter extends BaseMvpPresenter<IConsultationTobView> {

    public ContentManagerPresenter(App app) {
        super(app);
    }

    // 分类
    public void getConsultationTobResult(){
        // isViewAttached()方法判断是否在显示器上显示
        if(isViewAttached()){
            getView().showProgress();
        }

        // 观察者被观察者模式
        // 得到根接口路径
        getAppComponent()
                .getAPIService() // 所有接口对象
                .getConsultationTopResult("002","1.0","","JSON") // 得到登录接口
                .subscribeOn(Schedulers.io()) // 订阅方式
                .observeOn(AndroidSchedulers.mainThread()) // 指定线程
                .subscribe(new Subscriber<ConsultationTopModel>() {  // 将数据绑定到实体类的操作
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
                    public void onNext(ConsultationTopModel consultationTopModel) {
                        LogUtils.d("Response:"+ consultationTopModel);
                        if(isViewAttached())
                            getView().onGetConsultationTobResult(consultationTopModel); // 得到一层数据

                    }
                });
    }




}
