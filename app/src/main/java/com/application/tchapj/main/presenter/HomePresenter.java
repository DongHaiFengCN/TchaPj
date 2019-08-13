package com.application.tchapj.main.presenter;

import com.application.tchapj.App;
import com.application.tchapj.DataManager;
import com.application.tchapj.R;
import com.application.tchapj.base.BaseModel;
import com.application.tchapj.base.BaseMvpPresenter;
import com.application.tchapj.main.bean.HomeCircleModel;
import com.application.tchapj.main.bean.HomeMediaList;
import com.application.tchapj.main.bean.HomePerson;
import com.application.tchapj.main.bean.HomeTopData;
import com.application.tchapj.main.view.IHomeView;
import com.application.tchapj.my.bean.AlipayPrivateKeyBean;
import com.application.tchapj.my.bean.QiniuBean;
import com.application.tchapj.widiget.AopUtils;
import com.king.base.util.LogUtils;

import java.util.HashMap;
import java.util.Map;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

// 主页界面的 Presenter 层 业务逻辑继承该层的接口  并 实现View层接口和Model层业务的处理逻辑
public class HomePresenter extends BaseMvpPresenter<IHomeView> {


    public HomePresenter(App app) {
        super(app);
    }

    // 轮播图
    public void getHomeBanne() {
        // isViewAttached()方法判断是否在显示器上显示
        if (isViewAttached()) {
            getView().showProgress();
        }

        // 观察者被观察者模式
        // 得到根接口路径
        getAppComponent()
                .getAPIService() // 所有接口对象
                .getHomeBannerResult("002", "1.0", "", "JSON") // 得到登录接口
                .subscribeOn(Schedulers.io()) // 订阅方式
                .observeOn(AndroidSchedulers.mainThread()) // 指定线程
                .subscribe(new Subscriber<HomeTopData>() {  // 将数据绑定到实体类的操作
                    @Override
                    public void onCompleted() {
                        if (isViewAttached())
                            getView().onCompleted();
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (isViewAttached())
                            getView().onError(e);
                    }

                    @Override // 得到数据
                    public void onNext(HomeTopData homeTopDataBean) {
                        LogUtils.d("Response:" + homeTopDataBean);

                        if (homeTopDataBean != null) {
                            if (isViewAttached())
                                getView().onGetHomeBannerResult(homeTopDataBean); // 得到二层数据
                        }
                    }
                });
    }

    // 找名人
    public void getHomePerson() {
        // isViewAttached()方法判断是否在显示器上显示
        if (isViewAttached()) {
            getView().showProgress();
        }

        // 观察者被观察者模式
        // 得到根接口路径
        getAppComponent()
                .getAPIService() // 所有接口对象
                .getHomePersonResult("002", "1.0", "", "JSON") // 得到登录接口
                .subscribeOn(Schedulers.io()) // 订阅方式
                .observeOn(AndroidSchedulers.mainThread()) // 指定线程
                .subscribe(new Subscriber<HomePerson>() {  // 将数据绑定到实体类的操作
                    @Override
                    public void onCompleted() {
                        if (isViewAttached())
                            getView().onCompleted();
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (isViewAttached())
                            getView().onError(e);
                    }

                    @Override // 得到数据
                    public void onNext(HomePerson homePersonBean) {
                        LogUtils.d("Response:" + homePersonBean);

                        if (homePersonBean != null) {
                            if (isViewAttached())
                                getView().onGetHomePersonResult(homePersonBean); // 得到二层数据
                        }
                    }
                });
    }

    // 找媒体
    public void getHomeMediaList() {
        // isViewAttached()方法判断是否在显示器上显示
        if (isViewAttached()) {
            getView().showProgress();
        }

        // 观察者被观察者模式
        // 得到根接口路径
        getAppComponent()
                .getAPIService() // 所有接口对象
                .getHomeMediaListResult("002", "1.0", "", "JSON") // 得到登录接口
                .subscribeOn(Schedulers.io()) // 订阅方式
                .observeOn(AndroidSchedulers.mainThread()) // 指定线程
                .subscribe(new Subscriber<HomeMediaList>() {  // 将数据绑定到实体类的操作
                    @Override
                    public void onCompleted() {
                        if (isViewAttached())
                            getView().onCompleted();
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (isViewAttached())
                            getView().onError(e);
                    }

                    @Override // 得到数据
                    public void onNext(HomeMediaList homeMediaListBean) {
                        LogUtils.d("Response:" + homeMediaListBean);

                        if (homeMediaListBean != null) {
                            if (isViewAttached())
                                getView().onGetHomeMediaListResult(homeMediaListBean); // 得到二层数据
                        }
                    }
                });
    }

    // 找圈子
    public void getHomeCircleModel() {
        // isViewAttached()方法判断是否在显示器上显示
        if (isViewAttached()) {
            getView().showProgress();
        }

        // 观察者被观察者模式
        // 得到根接口路径
        getAppComponent()
                .getAPIService() // 所有接口对象
                .getHomeCircleModelResult("002", "1.0", "", "JSON") // 得到登录接口
                .subscribeOn(Schedulers.io()) // 订阅方式
                .observeOn(AndroidSchedulers.mainThread()) // 指定线程
                .subscribe(new Subscriber<HomeCircleModel>() {  // 将数据绑定到实体类的操作
                    @Override
                    public void onCompleted() {
                        if (isViewAttached())
                            getView().onCompleted();
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (isViewAttached())
                            getView().onError(e);
                    }

                    @Override // 得到数据
                    public void onNext(HomeCircleModel homeCircleModelBean) {
                        LogUtils.d("Response:" + homeCircleModelBean);

                        if (homeCircleModelBean != null) {
                            if (isViewAttached())
                                getView().onGetHomeCircleModelResult(homeCircleModelBean); // 得到二层数据
                        }
                    }
                });
    }

    // 七牛token
    public void onGetQiniuResult() {
        // isViewAttached()方法判断是否在显示器上显示
        if (isViewAttached()) {
            getView().showProgress();
        }

        // 观察者被观察者模式
        // 得到根接口路径
        getAppComponent()
                .getAPIService() // 所有接口对象
                .getQiniuBeanResult("002", "1.0", "", "JSON") // 得到登录接口
                .subscribeOn(Schedulers.io()) // 订阅方式
                .observeOn(AndroidSchedulers.mainThread()) // 指定线程
                .subscribe(new Subscriber<QiniuBean>() {  // 将数据绑定到实体类的操作
                    @Override
                    public void onCompleted() {
                        if (isViewAttached())
                            getView().onCompleted();
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (isViewAttached())
                            getView().onError(e);
                    }

                    @Override // 得到数据
                    public void onNext(QiniuBean qiniuBean) {
                        LogUtils.d("Response:" + qiniuBean);
                        if (isViewAttached())
                            getView().onGetQiniuBeanResult(qiniuBean); // 得到一层数据

                    }
                });
    }

    //获取支付宝私钥
    public void onGetAlipayPrivateKeyBeanResult() {
        // isViewAttached()方法判断是否在显示器上显示
        if (isViewAttached()) {
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
                .getAlipayPrivateKeyBeanResult("002", "1.0", sign1, "JSON") // 得到登录接口
                .subscribeOn(Schedulers.io()) // 订阅方式
                .observeOn(AndroidSchedulers.mainThread()) // 指定线程
                .subscribe(new Subscriber<AlipayPrivateKeyBean>() {  // 将数据绑定到实体类的操作
                    @Override
                    public void onCompleted() {
                        if (isViewAttached())
                            getView().onCompleted();
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (isViewAttached())
                            getView().onError(e);
                    }

                    @Override // 得到数据
                    public void onNext(AlipayPrivateKeyBean alipayPrivateKeyBean) {

                        DataManager.getDataManager().setMetaDataById(R.string.RSA2_PRIVATE, alipayPrivateKeyBean.getData().getPrivatekey());
                        com.application.tchapj.widiget.LogUtils.d("Response:" + alipayPrivateKeyBean);
                        if (isViewAttached())
                            getView().onGetAlipayPrivateKeyBeanResult(alipayPrivateKeyBean); // 得到一层数据

                    }
                });
    }

}
