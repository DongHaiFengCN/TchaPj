package com.application.tchapj.main.presenter;

import com.application.tchapj.App;
import com.application.tchapj.base.BaseMvpPresenter;
import com.application.tchapj.main.bean.MessagenotificationBean;
import com.application.tchapj.main.view.IMessagenoficationView;
import com.application.tchapj.widiget.LogUtils;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by 安卓开发 on 2018/8/10.
 */

public class MessagenoficationPresenter extends BaseMvpPresenter<IMessagenoficationView> {

    public MessagenoficationPresenter(App app) {
        super(app);
    }

    // 小红点消息
    public void onGetMessagenotificationResult(String memberId){
        // isViewAttached()方法判断是否在显示器上显示
        if(isViewAttached()){
            getView().showProgress();
        }

        // 观察者被观察者模式
        // 得到根接口路径
        getAppComponent()
                .getAPIService() // 所有接口对象
                .getMessagenotificationBeanResult(memberId,"002","1.0","","JSON") // 得到登录接口
                .subscribeOn(Schedulers.io()) // 订阅方式
                .observeOn(AndroidSchedulers.mainThread()) // 指定线程
                .subscribe(new Subscriber<MessagenotificationBean>() {  // 将数据绑定到实体类的操作
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
                    public void onNext(MessagenotificationBean messagenotificationBean) {
                        LogUtils.d("Response:"+ messagenotificationBean);

                        if(messagenotificationBean!=null){
                            if(isViewAttached())
                                getView().onGetMessagenotificationResult(messagenotificationBean); // 得到二层数据
                        }
                    }
                });
    }

}
