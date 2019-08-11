package com.application.tchapj.consultation.presenter;

import android.util.Log;

import com.application.tchapj.App;
import com.application.tchapj.base.BaseBean;
import com.application.tchapj.base.BaseMvpPresenter;
import com.application.tchapj.consultation.bean.AddNewsCommentResultBean;
import com.application.tchapj.consultation.bean.CommentsResultBean;
import com.application.tchapj.consultation.bean.ConsultationTopModel;
import com.application.tchapj.consultation.bean.NewsAttentionResultBean;
import com.application.tchapj.consultation.bean.NewsCommentResultBean;
import com.application.tchapj.consultation.bean.ZanBean;
import com.application.tchapj.consultation.view.IFindDetailView;
import com.application.tchapj.widiget.LogUtils;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class FindDetailPresenter extends BaseMvpPresenter<IFindDetailView> {

    public FindDetailPresenter(App app) {
        super(app);
    }
    public void getNewsCommentsResult(String newsId, int pageNum, int pageSize) {

        if(isViewAttached()){
            getView().showProgress();
        }

        // 观察者被观察者模式
        // 得到根接口路径
        getAppComponent()
                .getAPIService() // 所有接口对象
                .getNewsCommentsRequest("002","1.0","","JSON", pageNum + "", pageSize + "", App.getId(), newsId) // 得到登录接口
                .subscribeOn(Schedulers.io()) // 订阅方式
                .observeOn(AndroidSchedulers.mainThread()) // 指定线程
                .subscribe(new Subscriber<BaseBean<NewsCommentResultBean>>() {  // 将数据绑定到实体类的操作
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
                    public void onNext(BaseBean<NewsCommentResultBean> baseBean) {
                        if(isViewAttached())
                            getView().getNewsCommentsResult(baseBean.getData()); // 得到一层数据

                    }
                });
    }


    //发表评论 newsId：新闻id；commentContent：评论内容
    public void getAddNewsCommentsResult(String newsId, String commentContent) {

        if(isViewAttached()){
            getView().showProgress();
        }

        // 观察者被观察者模式
        // 得到根接口路径
        getAppComponent()
                .getAPIService() // 所有接口对象
                .getAddNewsCommentsRequest("002","1.0","","JSON", App.getId(), newsId, commentContent) // 得到登录接口
                .subscribeOn(Schedulers.io()) // 订阅方式
                .observeOn(AndroidSchedulers.mainThread()) // 指定线程
                .subscribe(new Subscriber<BaseBean<AddNewsCommentResultBean>>() {  // 将数据绑定到实体类的操作
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
                    public void onNext(BaseBean<AddNewsCommentResultBean> baseBean) {
                        if(isViewAttached())
                            getView().getAddNewsCommentsResult(baseBean); // 得到一层数据

                    }
                });
    }


    /**
     * 对评论发表评论
     * @param newsId  新闻id
     * @param commentContent  评论内容
     * @param replyType  0:回复评论 1:回复别人
     * @param commentId  被评论id
     * @param toMemberId  replyType=1时填写，被@的人id  replyType=0时填写，楼主id
     */
    public void getAddNewsReplyCommentsResult(String newsId, String commentContent, int replyType, String commentId, String toMemberId) {

        if(isViewAttached()){
            getView().showProgress();
        }

        // 观察者被观察者模式
        // 得到根接口路径
        getAppComponent()
                .getAPIService() // 所有接口对象
                .getAddNewsReplyCommentsRequest("002","1.0","","JSON", App.getId(), newsId, commentContent, replyType, commentId, toMemberId) // 得到登录接口
                .subscribeOn(Schedulers.io()) // 订阅方式
                .observeOn(AndroidSchedulers.mainThread()) // 指定线程
                .subscribe(new Subscriber<BaseBean<AddNewsCommentResultBean>>() {  // 将数据绑定到实体类的操作
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
                    public void onNext(BaseBean<AddNewsCommentResultBean> baseBean) {
                        if(isViewAttached())
                            getView().getAddNewsCommentsResult(baseBean); // 得到一层数据

                    }
                });
    }


    /**
     * 关注、喜欢
     * @param newsId 资源id
     * @param isCancel 0：关注 1：取消关注
     */
    public void getNewsAttentionResult(String newsId, String isCancel) {

        if(isViewAttached()){
            getView().showProgress();
        }

        // 观察者被观察者模式
        // 得到根接口路径
        getAppComponent()
                .getAPIService() // 所有接口对象
                .getNewsAttentionRequest("002","1.0","","JSON", App.getId(), newsId, isCancel)
                .subscribeOn(Schedulers.io()) // 订阅方式
                .observeOn(AndroidSchedulers.mainThread()) // 指定线程
                .subscribe(new Subscriber<BaseBean<NewsAttentionResultBean>>() {  // 将数据绑定到实体类的操作
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
                    public void onNext(BaseBean<NewsAttentionResultBean> baseBean) {
                        if(isViewAttached())
                            getView().getNewsAttentionResult(baseBean); // 得到一层数据

                    }
                });
    }

    /**
     * 回复评论列表
     * @param newsId  资讯id
     * @param commentId 评论id
     * @param pageNum 当前页数
     * @param pageSize 每页条数
     */
    public void getNewsCommentsReplyResult(String newsId, String commentId, int pageNum, int pageSize) {

        if(isViewAttached()){
            getView().showProgress();
        }

        // 观察者被观察者模式
        // 得到根接口路径
        getAppComponent()
                .getAPIService() // 所有接口对象
                .getNewsCommentsReplyRequest("002","1.0","","JSON", App.getId(), newsId, commentId, pageNum + "", pageSize + "")
                .subscribeOn(Schedulers.io()) // 订阅方式
                .observeOn(AndroidSchedulers.mainThread()) // 指定线程
                .subscribe(new Subscriber<BaseBean<CommentsResultBean>>() {  // 将数据绑定到实体类的操作
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
                    public void onNext(BaseBean<CommentsResultBean> baseBean) {
                        if(isViewAttached())
                            getView().getNewsCommentsReplyResult(baseBean); // 得到一层数据

                    }
                });
    }

    // 点赞
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


}
