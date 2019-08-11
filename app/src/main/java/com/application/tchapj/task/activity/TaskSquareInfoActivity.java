package com.application.tchapj.task.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.application.tchapj.App;
import com.application.tchapj.R;
import com.application.tchapj.base.BaseMvpActivity;
import com.application.tchapj.task.bean.TaskSquareInfoModel;
import com.application.tchapj.task.presenter.TaskSquareInfoPresenter;
import com.application.tchapj.task.view.ITaskSquareInfoView;
import com.application.tchapj.utils2.SDDateUtil;
import com.application.tchapj.widiget.FlowTagLayout;
import com.application.tchapj.widiget.KV;
import com.application.tchapj.widiget.TagAdapter;
import com.application.tchapj.widiget.ToolbarHelper;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;

/**
 * Created by 安卓开发 on 2018/7/25.
 */

public class TaskSquareInfoActivity extends BaseMvpActivity<ITaskSquareInfoView, TaskSquareInfoPresenter>
        implements ITaskSquareInfoView {


    @BindView(R.id.task_squareinfo_iv)
    ImageView task_squareinfo_iv ;
    @BindView(R.id.task_squareinfo_tv)
    TextView task_squareinfo_tv ;
    @BindView(R.id.task_squareinfo_vip)
    ImageView task_squareinfo_vip ;
    @BindView(R.id.task_squareinfo_tvip)
    TextView task_squareinfo_tvip ;

    @BindView(R.id.start_time_tv)
    TextView start_time_tv ;
    @BindView(R.id.start_end_tv)
    TextView start_end_tv ;

    @BindView(R.id.crowd_fl)
    FlowTagLayout crowd_fl ;

    @BindView(R.id.channel_tv)
    TextView channel_tv ;

    @BindView(R.id.guidance_tv)
    TextView guidance_tv ;

    @BindView(R.id.requirements_tv)
    TextView requirements_tv ;

    @BindView(R.id.money_iv)
    ImageView money_iv ;
    @BindView(R.id.money_tv)
    TextView money_tv ;

    @BindView(R.id.conditions_tv)
    TextView conditions_tv ;

    @BindView(R.id.leading_task_bt)
    Button leading_task_bt ;

    ToolbarHelper mToolbarHelper;

    private String id;

    private String squareInfoId;

    private KV kv;                 // 保存缓存数据的对象

    private TagAdapter<String> mSizeTagAdapter;

    private TaskSquareInfoModel.TaskSquareInfoResult.TaskSquareInfo taskSquareInfo ;

    @Override
    protected void initToolbar(ToolbarHelper toolbarHelper) {

        Intent intent = getIntent();
        id = intent.getStringExtra("ID");

        toolbarHelper.setTitle("领任务");
        mToolbarHelper = toolbarHelper;

    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_squareinfo_test;
    }

    @Override
    public void initUI() {

        kv = new KV(this);             // 保存基础数据

        getPresenter().getTaskSquareInfo(id);

        mSizeTagAdapter = new TagAdapter<>(this);

        crowd_fl.setAdapter(mSizeTagAdapter);

        /*Glide.with(App.getInstance())
                .load(kv.getString(Constants.task_squareinfo_iv, ""))
                .into(task_squareinfo_iv);

        task_squareinfo_tv.setText(kv.getString(Constants.task_squareinfo_tv, "")); // 得到缓存 KV 中的账号数据*/

        leading_task_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Toast.makeText(TaskSquareInfoActivity.this,"ssss",Toast.LENGTH_LONG).show();

                Intent intent = new Intent(TaskSquareInfoActivity.this, TaskSquareInfoActivity2.class);

                // intent.putExtra("squareInfoId", taskSquareInfo.getId());

                startActivity(intent);

            }
        });

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

        if ("000".equals(taskSquareInfoModel.getCode())) {
            taskSquareInfo = taskSquareInfoModel.getData().getTask();

            RequestOptions options = new RequestOptions()
                    .centerCrop()
                    .priority(Priority.HIGH)
                    .diskCacheStrategy(DiskCacheStrategy.NONE);

            Glide.with(App.getInstance())
                    .asBitmap()
                    .apply(options)
                    .load(taskSquareInfo.getImgUrl())
                    .into(new BitmapImageViewTarget(task_squareinfo_iv) {
                        @Override
                        protected void setResource(Bitmap resource) {
                            RoundedBitmapDrawable circularBitmapDrawable =
                                    RoundedBitmapDrawableFactory.
                                            create(App.getInstance().getResources(), resource);
                            //circularBitmapDrawable.setCornerRadius(8); // 圆角
                            task_squareinfo_iv.setImageDrawable(circularBitmapDrawable);
                        }
                    });

            task_squareinfo_tv.setText(taskSquareInfo.getName()); // 得到缓存 KV 中的账号数据

            task_squareinfo_tvip.setText(taskSquareInfo.getNickName());

            start_time_tv.setText(SDDateUtil.mil2yyyyMMdd(taskSquareInfo.getStartTime()));
            start_end_tv.setText(SDDateUtil.mil2yyyyMMdd(taskSquareInfo.getEndTime()));



            initSizeData();

            if(taskSquareInfo.getTaskType()=="1"){
                channel_tv.setText("微博");
            }else {
                channel_tv.setText("其他");
            }

            guidance_tv.setText(taskSquareInfo.getPhonenumber());
            requirements_tv.setText(taskSquareInfo.getStatus());
            money_tv.setText(taskSquareInfo.getUnitPrice() + "");
            conditions_tv.setText(taskSquareInfo.getRequire());



            // kv.put(Constants.task_squareinfo_vip, taskSquareInfo.getImgUrl()).commit();  // VIP图片


            /*kv.put(Constants.task_squareinfo_iv, taskSquareInfo.getImgUrl()).commit();
            kv.put(Constants.task_squareinfo_tv, taskSquareInfo.getName()).commit();*/

            /*
            kv.put(Constants.task_squareinfo_tvip, taskSquareInfo.getNickName()).commit();
            kv.put(Constants.start_time_tv, taskSquareInfo.getCreateTime()).commit();
            kv.put(Constants.start_end_tv, taskSquareInfo.getEndTime()).commit();
            kv.put(Constants.crowd_fl, taskSquareInfo.getTfPeoples()).commit();

            kv.put(Constants.channel_tv, taskSquareInfo.getTaskType()).commit(); // 投放渠道
            kv.put(Constants.guidance_tv, taskSquareInfo.getPhonenumber()).commit(); // 活动引导

            kv.put(Constants.requirements_tv, taskSquareInfo.getStatus()).commit(); // 活动要求

            kv.put(Constants.money_tv, taskSquareInfo.getUnitPrice()).commit(); // 单价

            kv.put(Constants.conditions_tv, taskSquareInfo.getRequire()).commit(); // 限制*/

        }


    }

    // 初始化数据
    private void initSizeData() {

        String[] str = taskSquareInfo.getTfPeoples().split(",");
        List<String> tagList = Arrays.asList(str);

        List<String> dataSource = new ArrayList<>();
        for (int i = 0; i < tagList.size(); i++) {
            dataSource.add(tagList.get(i));

        }

        mSizeTagAdapter.onlyAddAll(dataSource);
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
