package com.application.tchapj.main.presenter;

import com.application.tchapj.App;
import com.application.tchapj.base.BaseMvpPresenter;
import com.application.tchapj.main.bean.MingPeopleListBean;
import com.application.tchapj.main.view.IMingPeopleListView;
import com.king.base.util.LogUtils;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/10/10.
 */

public class MingPeopleListPresenter extends BaseMvpPresenter<IMingPeopleListView> {

    public MingPeopleListPresenter(App app) {
        super(app);
    }

    // 明星活跃周榜
    public void getMingPeopleListResult(){
        // isViewAttached()方法判断是否在显示器上显示
        if(isViewAttached()){
            getView().showProgress();
        }

        // 观察者被观察者模式
        // 得到根接口路径
        getAppComponent()
                .getAPIService() // 所有接口对象
                .getMingPeopleListResult("002","1.0","","JSON") // 得到登录接口
                .subscribeOn(Schedulers.io()) // 订阅方式
                .observeOn(AndroidSchedulers.mainThread()) // 指定线程
                .subscribe(new Subscriber<MingPeopleListBean>() {  // 将数据绑定到实体类的操作
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
                    public void onNext(MingPeopleListBean mingPeopleListBean) {
                        LogUtils.d("Response:"+ mingPeopleListBean);
                        if(isViewAttached())
                            getView().onGetMingPeopleListResult(mingPeopleListBean); // 得到一层数据

                    }
                });
    }

}
