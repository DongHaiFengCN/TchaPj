package com.application.tchapj.task.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.application.tchapj.R;
import com.application.tchapj.base.BaseMvpActivity;
import com.application.tchapj.task.bean.TaskSquareInfoModel;
import com.application.tchapj.task.presenter.TaskSquareInfoPresenter;
import com.application.tchapj.task.view.ITaskSquareInfoView;
import com.application.tchapj.widiget.ToolbarHelper;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

import butterknife.BindView;

/**
 * Created by 安卓开发 on 2018/7/25.
 * 审核通过
 */

public class TaskReviewfinishActivity extends BaseMvpActivity<ITaskSquareInfoView, TaskSquareInfoPresenter>
        implements ITaskSquareInfoView {


    @BindView(R.id.uploadAudioImg2)
    ImageView iv;
    @BindView(R.id.fail_tv)
    TextView failTv;
    @BindView(R.id.success_tv)
    TextView successTv;


    private String index;
    private ToolbarHelper toolbarHelper;


    @Override
    protected void initToolbar(ToolbarHelper toolbarHelper) {

        toolbarHelper.setTitle("活动进度");
        this.toolbarHelper = toolbarHelper;

    }

    @Override
    public int getRootViewId() {
        return R.layout.activity_reviewfinish_test;
    }

    @Override
    public void initUI() {
        Intent intent = getIntent();
        index = intent.getStringExtra("index");
        if (TextUtils.equals("1", index)) {
            //审核成功
            failTv.setVisibility(View.GONE);//隐藏审核失败控件
            successTv.setVisibility(View.VISIBLE);
            iv.setImageResource(R.drawable.shenhetongguo);
        } else {
            //审核失败
            failTv.setVisibility(View.VISIBLE);//显示审核失败控件
            successTv.setVisibility(View.GONE);
            iv.setImageResource(R.drawable.shenheshibai);
        }


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
