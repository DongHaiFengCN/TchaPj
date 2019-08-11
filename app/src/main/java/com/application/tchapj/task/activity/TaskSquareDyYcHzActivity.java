package com.application.tchapj.task.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.application.tchapj.App;
import com.application.tchapj.R;
import com.application.tchapj.base.BaseBean;
import com.application.tchapj.base.BaseMvpActivity;
import com.application.tchapj.task.bean.TaskSquareInfoModel;
import com.application.tchapj.task.presenter.DailyTaskDouyinPresenter;
import com.application.tchapj.task.view.DailyTaskDouyinView;
import com.application.tchapj.utils.Utils;
import com.application.tchapj.widiget.DensityUtil;
import com.application.tchapj.widiget.ToolbarHelper;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

import butterknife.BindView;
import me.drakeet.materialdialog.MaterialDialog;

/**
 * Created by 安卓开发 on 2018/7/26.
 * index 1==正在联系合作, 3==合作完成
 */

public class TaskSquareDyYcHzActivity extends BaseMvpActivity<DailyTaskDouyinView, DailyTaskDouyinPresenter> implements DailyTaskDouyinView {

    @BindView(R.id.uploadAudioStateLine)
    ImageView uploadAudioStateLine;
    @BindView(R.id.uploadAudioStateOn1)
    ImageView uploadAudioStateOn1;
    @BindView(R.id.orginalstateLine)
    ImageView orginalstateLine;
    @BindView(R.id.releasefStateOn1)
    ImageView releasefStateOn1;
    @BindView(R.id.releasefStateOff4)
    ImageView releasefStateOff4;
    @BindView(R.id.releasefStateOff2)
    ImageView releasefStateOff2;
    @BindView(R.id.releasefTv1)
    TextView releasefTv1;
    @BindView(R.id.originalfTv2)
    TextView originalfTv2;
    @BindView(R.id.originalfTv3)
    TextView originalfTv3;
    @BindView(R.id.uploadAudioBg)
    ImageView uploadAudioBg;
    @BindView(R.id.uploadAudioImg)
    ImageView uploadAudioImg;
    @BindView(R.id.uploadAudioImg1)
    ImageView uploadAudioImg1;
    @BindView(R.id.uploadAudioTv)
    TextView uploadAudioTv;
    @BindView(R.id.uploadAudioNameTv)
    TextView uploadAudioNameTv;
    @BindView(R.id.uploadAudioCountDownTv)
    TextView uploadAudioCountDownTv;
    @BindView(R.id.uploadAudioCountDownValueTv)
    TextView uploadAudioCountDownValueTv;
    @BindView(R.id.uploadAudioBg1)
    ImageView uploadAudioBg1;
    @BindView(R.id.uploadAudioImg2)
    ImageView uploadAudioImg2;
    private String id;
    private String memberId;
    private MaterialDialog dialog;
    private String index;


    @Override
    protected void initToolbar(ToolbarHelper toolbarHelper) {
        toolbarHelper.setTitle("联系合作");
        // mToolbarHelper = toolbarHelper;

    }

    @Override
    public int getRootViewId() {
        return R.layout.activity_contact_cooperation;
    }

    @Override
    public void initUI() {
        Intent intent = getIntent();
        id = intent.getStringExtra("ID");
        memberId = App.getId();
        index = intent.getStringExtra("index");
        getPresenter().getTaskSquareInfo(memberId, id);
        //3代表合作成功，1代表合作中
        if (TextUtils.equals("3", index)) {

            originalfTv3.setTextColor(getResources().getColor(R.color.hotpink));

            RequestOptions options = new RequestOptions()
                    .centerCrop()
                    .priority(Priority.HIGH)
                    .diskCacheStrategy(DiskCacheStrategy.NONE);

            Glide.with(this)
                    .asBitmap()
                    .apply(options)
                    .load(R.drawable.release_state_on3)
                    .into(new BitmapImageViewTarget(releasefStateOff4) {
                        @Override
                        protected void setResource(Bitmap resource) {
                            RoundedBitmapDrawable circularBitmapDrawable =
                                    RoundedBitmapDrawableFactory.create(TaskSquareDyYcHzActivity.this.getResources(), resource);
                            //circularBitmapDrawable.setCornerRadius(8); // 圆角
                            releasefStateOff4.setImageDrawable(circularBitmapDrawable);
                        }
                    });

            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(DensityUtil.dp2px(TaskSquareDyYcHzActivity.this, 100),
                    DensityUtil.dp2px(TaskSquareDyYcHzActivity.this, 100));
            uploadAudioImg2.setLayoutParams(layoutParams);
            uploadAudioImg2.setImageResource(R.drawable.wanchenghezu);



        } else {
            originalfTv3.setTextColor(getResources().getColor(R.color.gainsboro));

            RequestOptions options = new RequestOptions()
                    .centerCrop()
                    .priority(Priority.HIGH)
                    .diskCacheStrategy(DiskCacheStrategy.NONE);

            Glide.with(this)
                    .asBitmap()
                    .apply(options)
                    .load(R.drawable.release_state_off_3)
                    .into(new BitmapImageViewTarget(releasefStateOff4) {
                        @Override
                        protected void setResource(Bitmap resource) {
                            RoundedBitmapDrawable circularBitmapDrawable =
                                    RoundedBitmapDrawableFactory.
                                            create(TaskSquareDyYcHzActivity.this.getResources(), resource);
                            //circularBitmapDrawable.setCornerRadius(8); // 圆角
                            releasefStateOff4.setImageDrawable(circularBitmapDrawable);
                        }
                    });


            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(DensityUtil.dp2px(TaskSquareDyYcHzActivity.this, 140),
                    DensityUtil.dp2px(TaskSquareDyYcHzActivity.this, 100));
            uploadAudioImg2.setLayoutParams(layoutParams);
            uploadAudioImg2.setImageResource(R.drawable.lianxihezuo);

        }

    }

    @Override
    public void initData() {

    }

    @NonNull
    @Override
    public DailyTaskDouyinPresenter createPresenter() {
        return new DailyTaskDouyinPresenter(getApp());
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

    @Override
    public void onGetDailyTaskDouyinModels(TaskSquareInfoModel dailyTaskDouyinModel) {

        if (TextUtils.equals("000", dailyTaskDouyinModel.getCode())) {
            String Status = dailyTaskDouyinModel.getData().getTask().getStatus();
            String name = dailyTaskDouyinModel.getData().getTask().getName();

            uploadAudioNameTv.setText(name);
            String startTime = Utils.getDataFormatString(dailyTaskDouyinModel.getData().getTask().getStartTime(), "yyyy-MM-dd HH:mm:ss");
            String endTime = Utils.getDataFormatString(dailyTaskDouyinModel.getData().getTask().getEndTime(), "yyyy-MM-dd HH:mm:ss");
            String nickName = dailyTaskDouyinModel.getData().getTask().getNickName();
            // String taskGuidance = dailyTaskDouyinModel.getData().getTask().getTaskGuidance();//活动引导
            String taskType = dailyTaskDouyinModel.getData().getTask().getTaskType();
            String imgurl = dailyTaskDouyinModel.getData().getTask().getImgUrl();

            uploadAudioCountDownValueTv.setText(startTime + " ~ " + endTime);

            uploadAudioTv.setText(nickName);


            if (TextUtils.equals("0", taskType)) {

                RequestOptions options = new RequestOptions().centerCrop().priority(Priority.HIGH).diskCacheStrategy(DiskCacheStrategy.NONE);

                Glide.with(this).asBitmap().apply(options).load(R.drawable.quzi).into(new BitmapImageViewTarget(uploadAudioImg1) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        RoundedBitmapDrawable circularBitmapDrawable = RoundedBitmapDrawableFactory.create(TaskSquareDyYcHzActivity.this.getResources(), resource);
                        //circularBitmapDrawable.setCornerRadius(8); // 圆角
                        uploadAudioImg1.setImageDrawable(circularBitmapDrawable);
                    }
                });

            } else if (TextUtils.equals("1", taskType)) {

                RequestOptions options = new RequestOptions().centerCrop().priority(Priority.HIGH).diskCacheStrategy(DiskCacheStrategy.NONE);

                Glide.with(this).asBitmap().apply(options).load(R.drawable.weibo).into(new BitmapImageViewTarget(uploadAudioImg1) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        RoundedBitmapDrawable circularBitmapDrawable = RoundedBitmapDrawableFactory.create(TaskSquareDyYcHzActivity.this.getResources(), resource);
                        //circularBitmapDrawable.setCornerRadius(8); // 圆角
                        uploadAudioImg1.setImageDrawable(circularBitmapDrawable);
                    }
                });

            } else if (TextUtils.equals("2", taskType) || TextUtils.equals("3", taskType)) {

                RequestOptions options = new RequestOptions().centerCrop().priority(Priority.HIGH).diskCacheStrategy(DiskCacheStrategy.NONE);

                Glide.with(this).asBitmap().apply(options).load(R.drawable.douyin).into(new BitmapImageViewTarget(uploadAudioImg1) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        RoundedBitmapDrawable circularBitmapDrawable = RoundedBitmapDrawableFactory.create(TaskSquareDyYcHzActivity.this.getResources(), resource);
                        //circularBitmapDrawable.setCornerRadius(8); // 圆角
                        uploadAudioImg1.setImageDrawable(circularBitmapDrawable);
                    }
                });
            } else if (TextUtils.equals("4", taskType)) {

            }

            RequestOptions options = new RequestOptions().centerCrop().priority(Priority.HIGH).diskCacheStrategy(DiskCacheStrategy.NONE);

            Glide.with(this).asBitmap().apply(options).load(imgurl).into(new BitmapImageViewTarget(uploadAudioImg) {
                @Override
                protected void setResource(Bitmap resource) {
                    RoundedBitmapDrawable circularBitmapDrawable = RoundedBitmapDrawableFactory.create(TaskSquareDyYcHzActivity.this.getResources(), resource);
                    //circularBitmapDrawable.setCornerRadius(8); // 圆角
                    uploadAudioImg.setImageDrawable(circularBitmapDrawable);
                }
            });


        }


    }

    @Override
    public void onTaskDouyin(BaseBean baseBean) {

    }


}
