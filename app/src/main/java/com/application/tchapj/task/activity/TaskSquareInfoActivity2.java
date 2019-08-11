package com.application.tchapj.task.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import com.application.tchapj.widiget.TagAdapter2;
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
 * Created by 安卓开发 on 2018/7/26.
 */

public class TaskSquareInfoActivity2 extends BaseMvpActivity<ITaskSquareInfoView, TaskSquareInfoPresenter>
        implements ITaskSquareInfoView {

    @BindView(R.id.task_squareinfo_iv2)
    ImageView task_squareinfo_iv2;
    @BindView(R.id.task_squareinfo_tv2)
    TextView task_squareinfo_tv2;
    @BindView(R.id.task_squareinfo_vip2)
    ImageView task_squareinfo_vip2;
    @BindView(R.id.task_squareinfo_tvip2)
    TextView task_squareinfo_tvip2;

    @BindView(R.id.start_time_tv2)
    TextView start_time_tv2;

    @BindView(R.id.copy_info_et)
    EditText copy_info_et;

    @BindView(R.id.crowd_fl2)
    FlowTagLayout crowd_fl2;

    @BindView(R.id.leading_task_bt2)
    Button leading_task_bt2;


    ToolbarHelper mToolbarHelper;

    private String id;

    private String infoid;

    private KV kv;                 // 保存缓存数据的对象

    private TagAdapter2<String> mSizeTagAdapter2;

    private TaskSquareInfoModel.TaskSquareInfoResult.TaskSquareInfo taskSquareInfo;

    @Override
    protected void initToolbar(ToolbarHelper toolbarHelper) {

        Intent intent = getIntent();
        id = intent.getStringExtra("squareInfoId");

        toolbarHelper.setTitle("发朋友圈");
        mToolbarHelper = toolbarHelper;

    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_squareinfo_test2;
    }

    @Override
    public void initUI() {

        kv = new KV(this);             // 保存基础数据

        mSizeTagAdapter2 = new TagAdapter2<>(this);

        getPresenter().getTaskSquareInfo(id);

        crowd_fl2.setAdapter(mSizeTagAdapter2);

        leading_task_bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Toast.makeText(TaskSquareInfoActivity.this,"ssss",Toast.LENGTH_LONG).show();

                Intent intent = new Intent(TaskSquareInfoActivity2.this, TaskSquareDyYcActivity.class);

                // intent.putExtra("squareInfoId", taskSquareInfo.getId());

                startActivity(intent);

            }
        });

        /*Glide.with(App.getInstance())
                .load(kv.getString(Constants.task_squareinfo_iv, ""))
                .into(task_squareinfo_iv);

        task_squareinfo_tv.setText(kv.getString(Constants.task_squareinfo_tv, "")); // 得到缓存 KV 中的账号数据*/


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
                    .into(new BitmapImageViewTarget(task_squareinfo_iv2) {
                        @Override
                        protected void setResource(Bitmap resource) {
                            RoundedBitmapDrawable circularBitmapDrawable =
                                    RoundedBitmapDrawableFactory.
                                            create(App.getInstance().getResources(), resource);
                            //circularBitmapDrawable.setCornerRadius(8); // 圆角
                            task_squareinfo_iv2.setImageDrawable(circularBitmapDrawable);
                        }
                    });

            task_squareinfo_tv2.setText(taskSquareInfo.getName()); // 得到缓存 KV 中的账号数据

            task_squareinfo_tvip2.setText(taskSquareInfo.getNickName());

            start_time_tv2.setText(SDDateUtil.mil2yyyyMMdd(taskSquareInfo.getCreateTime()));

            copy_info_et.setText(taskSquareInfo.getCopywriting());


            initSizeData();


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

        String[] str = taskSquareInfo.getTaskImgUrl().split(",");
        List<String> tagList = Arrays.asList(str);

        List<String> dataSource = new ArrayList<>();
        for (int i = 0; i < tagList.size(); i++) {
            dataSource.add(tagList.get(i));

        }

        mSizeTagAdapter2.onlyAddAll(dataSource);
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
