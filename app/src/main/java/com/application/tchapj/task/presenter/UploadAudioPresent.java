package com.application.tchapj.task.presenter;

import android.util.Log;

import com.application.tchapj.App;
import com.application.tchapj.base.BaseMvpPresenter;
import com.application.tchapj.task.bean.FriendReleaseBean;
import com.application.tchapj.task.bean.TaskSquareInfoModel;
import com.application.tchapj.task.view.IUploadAudioView;

import okhttp3.ResponseBody;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class UploadAudioPresent extends BaseMvpPresenter<IUploadAudioView> {

    public UploadAudioPresent(App app) {
        super(app);
    }

    /**
     * 获取任务详情
     */
    public void getFriendReleaseData(String memberId,String id) {
        Log.e("135", "getFriendReleaseData");
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
                    getView().onGetUploadAudioData(friendReleaseBean);
                }
            }
        });
    }

    public void submitTaskReview(String memberId,String imgUrl,String id) {
        if (isViewAttached()) {
            getView().showProgress();
        }

        getAppComponent()
                .getAPIService()
                .submitTaskReview(memberId, imgUrl, id, "002", "1.0", "", "JSON")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
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
            public void onNext(ResponseBody responseBody) {
                if (isViewAttached()) {
                    getView().submit(responseBody);
                }
            }
        });
    }
}
