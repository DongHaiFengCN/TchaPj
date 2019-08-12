package com.application.tchapj.video.presenter;

import com.application.tchapj.App;
import com.application.tchapj.base.BaseMvpPresenter;
import com.application.tchapj.video.bean.VideosModel;
import com.application.tchapj.video.view.IVideosView;
import com.king.base.util.LogUtils;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

// 视频界面的 Presenter 层 业务逻辑继承该层的接口  并 实现View层接口和Model层业务的处理逻辑
public class VideoPresenter extends BaseMvpPresenter<IVideosView> {


    public VideoPresenter(App app) {
        super(app);
    }

    // 视频界面
    public void getVideoResult(String pageNum, String pageSize){
        // isViewAttached()方法判断是否在显示器上显示
        if(isViewAttached()){
            getView().showProgress();
        }

        // 观察者被观察者模式
        // 得到根接口路径
        getAppComponent()
                .getAPIService() // 所有接口对象
                .getVideosModelResult(pageNum,pageSize,"002","1.0","","JSON") // 得到登录接口
                .subscribeOn(Schedulers.io()) // 订阅方式
                .observeOn(AndroidSchedulers.mainThread()) // 指定线程
                .subscribe(new Subscriber<VideosModel>() {  // 将数据绑定到实体类的操作
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
                    public void onNext(VideosModel videosModel) {
                        LogUtils.d("Response:"+ videosModel);
                        if(isViewAttached()) {
                            getView().onGetVideos(videosModel); // 得到一层数据
                        }

                    }
                });
    }

}
