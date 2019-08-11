package com.application.tchapj.task.presenter;

import android.text.TextUtils;
import android.widget.Toast;

import com.application.tchapj.App;
import com.application.tchapj.base.BaseBean;
import com.application.tchapj.base.BaseMvpPresenter;
import com.application.tchapj.my.activity.IdentityActivity;
import com.application.tchapj.my.bean.UserModel;
import com.application.tchapj.task.bean.DailyTaskDouyinModel;
import com.application.tchapj.task.bean.NewUserModel;
import com.application.tchapj.task.bean.TaskSquareInfoModel;
import com.application.tchapj.task.view.DailyTaskDouyinFaView;
import com.application.tchapj.utils2.share.SharedPreferencesUtils;
import com.hannesdorfmann.mosby.mvp.MvpPresenter;
import com.king.base.util.LogUtils;
import com.king.base.util.StringUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import freemarker.template.utility.StringUtil;
import okhttp3.ResponseBody;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;


public class DailyTaskDouyinFaPresenter extends BaseMvpPresenter<DailyTaskDouyinFaView> implements MvpPresenter<DailyTaskDouyinFaView> {

    public DailyTaskDouyinFaPresenter(App app) {
        super(app);
    }

    public void getTaskSquareInfo(String memberId, String id) {
        // isViewAttached()方法判断是否在显示器上显示

        // 观察者被观察者模式
        // 得到根接口路径
        getAppComponent()
                .getAPIService() // 所有接口对象
                .getTaskDetail(memberId, id, "002", "1.0", "", "JSON") // 得到登录接口
                .subscribeOn(Schedulers.io()) // 订阅方式
                .observeOn(AndroidSchedulers.mainThread()) // 指定线程
                .subscribe(new Subscriber<TaskSquareInfoModel>() {  // 将数据绑定到实体类的操作
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
                    public void onNext(TaskSquareInfoModel dailyTaskDouyinModel) {
                        LogUtils.d("Response:" + dailyTaskDouyinModel);
                        getView().onGetDailyTaskDouyinModels(dailyTaskDouyinModel); // 得到一层数据

                    }
                });
    }


    /**
     * 领取任务
     *
     * @param memberId
     * @param id
     */
    public void ledTask(final String memberId, final String id) {

        if (isViewAttached()) {
            getView().showProgress();
        }

        getAppComponent()
                .getAPIService()
                .releaseTask(memberId, id, "002", "1.0", "", "JSON")
                .subscribeOn(Schedulers.io()) // 订阅方式
                .observeOn(AndroidSchedulers.mainThread()) // 指定线程
                .subscribe(new Subscriber<BaseBean>() {  // 将数据绑定到实体类的操作
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
                    public void onNext(BaseBean baseBean) {

                        if (isViewAttached())
                            getView().ledTask(baseBean);

                    }
                });

    }

    /**
     * 提交任务资料
     * @param imgUrl
     * @param id
     */
    public void submitTaskReview(String imgUrl, String id) {
        if (isViewAttached()) {
            getView().showProgress();
        }

        getAppComponent()
                .getAPIService()
                .submitAuditTask(App.getId(), imgUrl, id, "002", "1.0", "", "JSON")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseBean>() {
                    @Override
                    public void onCompleted() {
                        if (isViewAttached()) {
                            getView().onCompleted();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (isViewAttached()) {
                            getView().onError(e);
                        }
                    }

                    @Override
                    public void onNext(BaseBean responseBody) {
                        if (isViewAttached()) {
                            getView().submitAuditData(responseBody);
                        }
                    }
                });
    }
}
