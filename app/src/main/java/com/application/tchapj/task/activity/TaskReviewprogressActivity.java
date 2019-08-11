package com.application.tchapj.task.activity;

import android.support.annotation.NonNull;

import com.application.tchapj.R;
import com.application.tchapj.base.BaseMvpActivity;
import com.application.tchapj.task.bean.TaskSquareInfoModel;
import com.application.tchapj.task.presenter.TaskSquareInfoPresenter;
import com.application.tchapj.task.view.ITaskSquareInfoView;
import com.application.tchapj.widiget.ToolbarHelper;

/**
 * Created by 安卓开发 on 2018/7/25.
 * 审核进度接口
 */

public class TaskReviewprogressActivity extends BaseMvpActivity<ITaskSquareInfoView, TaskSquareInfoPresenter>
        implements ITaskSquareInfoView {



    @Override
    protected void initToolbar(ToolbarHelper toolbarHelper) {

        toolbarHelper.setTitle("活动进度");

    }

    @Override
    public int getRootViewId() {
        return R.layout.activity_reviewprogress_test;
    }

    @Override
    public void initUI() {




    }

    @Override
    public void initData() {

    }

    @NonNull
    @Override
    public TaskSquareInfoPresenter createPresenter() {
        return new TaskSquareInfoPresenter(getApp());
    }

    @Override
    public void onGetTaskSquareInfoModels(TaskSquareInfoModel taskSquareInfoModel) {


    }

    @Override
    public void showProgress() {

    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {

    }

}
