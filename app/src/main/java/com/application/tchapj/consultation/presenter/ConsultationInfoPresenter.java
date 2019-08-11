package com.application.tchapj.consultation.presenter;

import android.util.Log;

import com.application.tchapj.App;
import com.application.tchapj.base.BaseModel;
import com.application.tchapj.base.BaseMvpPresenter;
import com.application.tchapj.consultation.bean.ConsultationNewsModel;
import com.application.tchapj.consultation.bean.InsertComments;
import com.application.tchapj.consultation.bean.ZanBean;
import com.application.tchapj.consultation.view.IConsultationInfoView;
import com.application.tchapj.my.bean.UserModel;
import com.application.tchapj.utils2.share.SharedPreferencesUtils;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

// 发现的 Presenter 层 业务逻辑继承该层的接口  并 实现View层接口和Model层业务的处理逻辑
public class ConsultationInfoPresenter extends BaseMvpPresenter<IConsultationInfoView> {


    public ConsultationInfoPresenter(App app) {
        super(app);
    }

    // 推荐的发现
    public void getConsultationResultAll(String pageNum, String pageSize) {
        // isViewAttached()方法判断是否在显示器上显示
        if (isViewAttached()) {
            getView().showProgress();
        }

        // 观察者被观察者模式
        // 得到根接口路径
        getAppComponent()
                .getAPIService() // 所有接口对象
                .getConsultationNewsModelAllResult(pageNum, pageSize, "002", "1.0", "", "JSON", App.getId()) // 得到登录接口
                .subscribeOn(Schedulers.io()) // 订阅方式
                .observeOn(AndroidSchedulers.mainThread()) // 指定线程
                .subscribe(new Subscriber<ConsultationNewsModel>() {  // 将数据绑定到实体类的操作
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
                    public void onNext(ConsultationNewsModel consultationNewsModelBean) {
                        Log.e("实体类", "Response:" + consultationNewsModelBean);
                        if (isViewAttached())
                            getView().onGetConsultationInfoResultAll(consultationNewsModelBean); // 得到一层数据

                    }
                });
    }

    // 其他的发现
    public void getConsultationResult(String newstype_id, String pageNum, String pageSize) {
        // isViewAttached()方法判断是否在显示器上显示
        if (isViewAttached()) {
            getView().showProgress();
        }

        // 观察者被观察者模式
        // 得到根接口路径
        getAppComponent()
                .getAPIService() // 所有接口对象
                .getConsultationNewsModelResult(newstype_id, pageNum, pageSize, "002", "1.0", "", "JSON", App.getId()) // 得到登录接口
                .subscribeOn(Schedulers.io()) // 订阅方式
                .observeOn(AndroidSchedulers.mainThread()) // 指定线程
                .subscribe(new Subscriber<ConsultationNewsModel>() {  // 将数据绑定到实体类的操作
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
                    public void onNext(ConsultationNewsModel consultationNewsModelBean) {
                        Log.e("实体类2", "Response:" + consultationNewsModelBean.getData().getNews().size());
                        if (isViewAttached())
                            getView().onGetConsultationInfoResult(consultationNewsModelBean); // 得到一层数据
                    }
                });
    }

    // 我的资讯列表
    public void getUserNewsList(String userId, String newModel, String pageNum, String pageSize) {
        // isViewAttached()方法判断是否在显示器上显示
        if (isViewAttached()) {
            getView().showProgress();
        }

        // 观察者被观察者模式
        // 得到根接口路径
        getAppComponent()
                .getAPIService() // 所有接口对象
                .getUserNewsListModelResult(userId, newModel, pageNum, pageSize, "002", "1.0", "", "JSON") // 得到登录接口
                .subscribeOn(Schedulers.io()) // 订阅方式
                .observeOn(AndroidSchedulers.mainThread()) // 指定线程
                .subscribe(new Subscriber<ConsultationNewsModel>() {  // 将数据绑定到实体类的操作
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
                    public void onNext(ConsultationNewsModel consultationNewsModelBean) {
                        if (isViewAttached())
                            getView().onGetConsultationInfoResult(consultationNewsModelBean); // 得到一层数据
                    }
                });
    }



    // 发评论
    public void getInsertCommentsResult(String content, String userId, String news_id) {
        // isViewAttached()方法判断是否在显示器上显示
        if (isViewAttached()) {
            getView().showProgress();
        }

        // 观察者被观察者模式
        // 得到根接口路径
        getAppComponent()
                .getAPIService() // 所有接口对象
                .getInsertCommentsResult(content, userId, news_id, "002", "1.0", "", "JSON") // 得到登录接口
                .subscribeOn(Schedulers.io()) // 订阅方式
                .observeOn(AndroidSchedulers.mainThread()) // 指定线程
                .subscribe(new Subscriber<InsertComments>() {  // 将数据绑定到实体类的操作
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
                    public void onNext(InsertComments insertComments) {
                        Log.i("sssss","Response:" + insertComments);
                        if (isViewAttached())
                            getView().onGetInsertCommentsResult(insertComments); // 得到一层数据
                    }
                });
    }

    // 点赞

    /**
     *
     * @param newsId
     * @param type  type  必选  点赞文章是传 1 点赞评论传2 点赞回复传3
     * @param isCancel 0：点赞 1：取消点赞
     */
    public void getZanBeanResult(String newsId, String type, String isCancel) {
        // isViewAttached()方法判断是否在显示器上显示
        if (isViewAttached()) {
            getView().showProgress();
        }

        // 观察者被观察者模式
        // 得到根接口路径
        getAppComponent()
                .getAPIService() // 所有接口对象
                .getZanBeanResult("002", "1.0", "", "JSON", App.getId(), newsId, type,isCancel) // 得到登录接口
                .subscribeOn(Schedulers.io()) // 订阅方式
                .observeOn(AndroidSchedulers.mainThread()) // 指定线程
                .subscribe(new Subscriber<ZanBean>() {  // 将数据绑定到实体类的操作
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
                    public void onNext(ZanBean zanBean) {
                        Log.i("sssss","Response:" + zanBean);
                        if (isViewAttached())
                            getView().onGetZanBeanResult(zanBean); // 得到一层数据
                    }
                });
    }

    // 个人数据
    public void getUserModelResult(String memberId){
        // isViewAttached()方法判断是否在显示器上显示
        if(isViewAttached()){
            getView().showProgress();
        }

        // 观察者被观察者模式
        // 得到根接口路径
        getAppComponent()
                .getAPIService() // 所有接口对象
                .getUserModelResult(memberId,"002","1.0","","JSON") // 得到登录接口
                .subscribeOn(Schedulers.io()) // 订阅方式
                .observeOn(AndroidSchedulers.mainThread()) // 指定线程
                .subscribe(new Subscriber<UserModel>() {  // 将数据绑定到实体类的操作
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
                    public void onNext(UserModel userModelBean) {
                        Log.i("sssss","Response:" + userModelBean);
                        SharedPreferencesUtils.getInstance().setUserInfo(userModelBean.getData());
                        if(isViewAttached())
                            getView().onGetUserModelResult(userModelBean); // 得到一层数据

                    }
                });
    }

    //删除文章
    public void deleteNewsResult(String news_id){
        // isViewAttached()方法判断是否在显示器上显示
        if(isViewAttached()){
            getView().showProgress();
        }

        // 观察者被观察者模式
        // 得到根接口路径
        getAppComponent()
                .getAPIService() // 所有接口对象
                .getDeleteNewsResult(news_id,"002","1.0","","JSON") // 得到登录接口
                .subscribeOn(Schedulers.io()) // 订阅方式
                .observeOn(AndroidSchedulers.mainThread()) // 指定线程
                .subscribe(new Subscriber<BaseModel>() {  // 将数据绑定到实体类的操作
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
                    public void onNext(BaseModel baseModel) {
                        Log.i("sssss","Response:" + baseModel);
                        if(isViewAttached())
                            getView().onDeleteNewsRespon(baseModel); // 得到一层数据

                    }
                });
    }

}
