package com.application.tchapj.task.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v4.widget.NestedScrollView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.application.tchapj.App;
import com.application.tchapj.R;
import com.application.tchapj.base.BaseBean;
import com.application.tchapj.base.BaseMvpActivity;
import com.application.tchapj.task.bean.DailyTaskDouyinModel;
import com.application.tchapj.task.bean.TaskSquareInfoModel;
import com.application.tchapj.task.presenter.DailyTaskDouyinPresenter;
import com.application.tchapj.task.view.DailyTaskDouyinView;
import com.application.tchapj.utils.CommonDialogUtil;
import com.application.tchapj.utils.CommonUtils;
import com.application.tchapj.utils.Utils;
import com.application.tchapj.utils2.qiniu.utils.StringUtils;
import com.application.tchapj.utils2.share.SharedPreferencesUtils;
import com.application.tchapj.widiget.ToolbarHelper;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

import java.math.BigDecimal;

import butterknife.BindView;
import butterknife.OnClick;
import me.drakeet.materialdialog.MaterialDialog;


/**
 * Created by 安卓开发 on 2018/7/26.
 * 微视原创
 */
// 微视原创的领任务
public class TaskSquareWsYcActivity extends BaseMvpActivity<DailyTaskDouyinView, DailyTaskDouyinPresenter> implements DailyTaskDouyinView {

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
    @BindView(R.id.releasefBg)
    ImageView releasefBg;
    @BindView(R.id.originalimg)
    ImageView originalimg;
    @BindView(R.id.friendCircleImg)
    ImageView friendCircleImg;
    @BindView(R.id.friendCircleTv)
    TextView friendCircleTv;
    @BindView(R.id.releasefNameTv)
    TextView releasefNameTv;
    @BindView(R.id.activityTimeTv)
    TextView activityTimeTv;
    @BindView(R.id.activityTimeValueTv)
    TextView activityTimeValueTv;
    @BindView(R.id.releasefBg1)
    ImageView releasefBg1;
//    @BindView(R.id.releasefCrowdValueTv1)
//    TextView releasefCrowdValueTv1;
//    @BindView(R.id.releasefCrowdValueTv2)
//    TextView releasefCrowdValueTv2;
//    @BindView(R.id.releasefChannelValueTv1)
//    TextView releasefChannelValueTv1;
//    @BindView(R.id.releasefActivitysValueTv1)
//    TextView releasefActivitysValueTv1;
    @BindView(R.id.releasefActivitysReTv)
    TextView releasefActivitysReTv;
    @BindView(R.id.releasefActivitysReValueTv1)
    TextView releasefActivitysReValueTv1;
    @BindView(R.id.releasefBg5)
    ImageView releasefBg5;
    @BindView(R.id.releasefBountyTv)
    TextView releasefBountyTv;
    @BindView(R.id.releasefMoneyImg)
    ImageView releasefMoneyImg;
    @BindView(R.id.releasefMoneyValueTv)
    TextView releasefMoneyValueTv;
    @BindView(R.id.hairFriendsBtn)
    Button hairFriendsBtn;
    @BindView(R.id.ScrollView)
    NestedScrollView ScrollView;
    private String id;
    private String memberId;
    private MaterialDialog dialog;


    @Override
    protected void initToolbar(ToolbarHelper toolbarHelper) {
        toolbarHelper.setTitle("领任务");
        // mToolbarHelper = toolbarHelper;

    }

    @Override
    public int getRootViewId() {
        return R.layout.activity_task_ws_yc;
    }

    @Override
    public void initUI() {
        Intent intent = getIntent();
        id = intent.getStringExtra("ID");
        memberId = App.getId();
        getPresenter().getTaskSquareInfo(memberId, id);
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

            releasefNameTv.setText(name);
            BigDecimal unitPrice = dailyTaskDouyinModel.getData().getTask().getUnitPrice();
            String nickName = dailyTaskDouyinModel.getData().getTask().getNickName();
            String taskGuidance = dailyTaskDouyinModel.getData().getTask().getTaskGuidance();//活动引导
            String taskType = dailyTaskDouyinModel.getData().getTask().getTaskType();
            String require = dailyTaskDouyinModel.getData().getTask().getRequire();
            int fans = dailyTaskDouyinModel.getData().getTask().getFans();
            String imgurl = dailyTaskDouyinModel.getData().getTask().getImgUrl();

            activityTimeValueTv.setText(CommonUtils.getTimeStampDiffer(dailyTaskDouyinModel.getData().getTask().getEndTime() - System.currentTimeMillis()));
//            releasefCrowdValueTv1.setText(dailyTaskDouyinModel.getData().getTask().getCircleTypeId());
//            releasefActivitysValueTv1.setText(taskGuidance);
            releasefActivitysReValueTv1.setText(require);
//            releaseflimitValueTv1.setText("抖音粉丝大于" + fans + "人");
            releasefMoneyValueTv.setText(CommonUtils.moneyToVMoney(unitPrice) + "V币/条");
            friendCircleTv.setText(nickName);


            if (TextUtils.equals("0", taskType)) {
//                releasefChannelValueTv1.setText("朋友圈");
                RequestOptions options = new RequestOptions().centerCrop().priority(Priority.HIGH).diskCacheStrategy(DiskCacheStrategy.NONE);

                Glide.with(this).asBitmap().apply(options).load(R.drawable.quzi).into(new BitmapImageViewTarget(friendCircleImg) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        RoundedBitmapDrawable circularBitmapDrawable = RoundedBitmapDrawableFactory.create(TaskSquareWsYcActivity.this.getResources(), resource);
                        //circularBitmapDrawable.setCornerRadius(8); // 圆角
                        friendCircleImg.setImageDrawable(circularBitmapDrawable);
                    }
                });

            } else if (TextUtils.equals("1", taskType)) {
//                releasefChannelValueTv1.setText("微博");
                RequestOptions options = new RequestOptions().centerCrop().priority(Priority.HIGH).diskCacheStrategy(DiskCacheStrategy.NONE);

                Glide.with(this).asBitmap().apply(options).load(R.drawable.weibo).into(new BitmapImageViewTarget(friendCircleImg) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        RoundedBitmapDrawable circularBitmapDrawable = RoundedBitmapDrawableFactory.create(TaskSquareWsYcActivity.this.getResources(), resource);
                        //circularBitmapDrawable.setCornerRadius(8); // 圆角
                        friendCircleImg.setImageDrawable(circularBitmapDrawable);
                    }
                });

            } else if (TextUtils.equals("2", taskType)) {
//                releasefChannelValueTv1.setText("抖音合拍");
                RequestOptions options = new RequestOptions().centerCrop().priority(Priority.HIGH).diskCacheStrategy(DiskCacheStrategy.NONE);

                Glide.with(this).asBitmap().apply(options).load(R.drawable.douyin).into(new BitmapImageViewTarget(friendCircleImg) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        RoundedBitmapDrawable circularBitmapDrawable = RoundedBitmapDrawableFactory.create(TaskSquareWsYcActivity.this.getResources(), resource);
                        //circularBitmapDrawable.setCornerRadius(8); // 圆角
                        friendCircleImg.setImageDrawable(circularBitmapDrawable);
                    }
                });
            } else if (TextUtils.equals("3", taskType)) {
//                releasefChannelValueTv1.setText("抖音原创");
                RequestOptions options = new RequestOptions().centerCrop().priority(Priority.HIGH).diskCacheStrategy(DiskCacheStrategy.NONE);

                Glide.with(this).asBitmap().apply(options).load(R.drawable.douyin).into(new BitmapImageViewTarget(friendCircleImg) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        RoundedBitmapDrawable circularBitmapDrawable = RoundedBitmapDrawableFactory.create(TaskSquareWsYcActivity.this.getResources(), resource);
                        //circularBitmapDrawable.setCornerRadius(8); // 圆角
                        friendCircleImg.setImageDrawable(circularBitmapDrawable);
                    }
                });
            } else if (TextUtils.equals("4", taskType)) {

            }

            RequestOptions options = new RequestOptions().centerCrop().priority(Priority.HIGH).diskCacheStrategy(DiskCacheStrategy.NONE);

            Glide.with(this).asBitmap().apply(options).load(imgurl).into(new BitmapImageViewTarget(originalimg) {
                @Override
                protected void setResource(Bitmap resource) {
                    RoundedBitmapDrawable circularBitmapDrawable = RoundedBitmapDrawableFactory.create(TaskSquareWsYcActivity.this.getResources(), resource);
                    //circularBitmapDrawable.setCornerRadius(8); // 圆角
                    originalimg.setImageDrawable(circularBitmapDrawable);
                }
            });


        }


    }

    @Override
    public void onTaskDouyin(BaseBean baseBean) {
        if (!StringUtils.isNullOrEmpty(baseBean.getCode()) && baseBean.getCode().equals("000")) {
            Intent intent = new Intent(TaskSquareWsYcActivity.this, TaskSquareDyYcHzActivity.class);
            startActivity(intent);
            finish();

        }else{
            if(!StringUtils.isNullOrEmpty(baseBean.getDescription())){
                Toast.makeText(TaskSquareWsYcActivity.this, baseBean.getDescription(), Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(TaskSquareWsYcActivity.this, "操作失败", Toast.LENGTH_SHORT).show();
            }
        }

    }


    private void showInfoDialog() {
        dialog = new MaterialDialog(TaskSquareWsYcActivity.this)
                .setTitle("提示")
                .setMessage("你需要具有达人身份才可以领任务哦")
                .setPositiveButton("激活达人身份", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();

                    }
                })
                .setNegativeButton("取消", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

        dialog.show();
    }

    @OnClick({ R.id.hairFriendsBtn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.hairFriendsBtn:
                //  getPresenter().getUserModelResult(App.getId());
                if(StringUtils.isNullOrEmpty(App.getId())){
                    CommonDialogUtil.showLoginDialog(this);
                }else{
                    if(SharedPreferencesUtils.getInstance().getUserInfo() != null && SharedPreferencesUtils.getInstance().getUserInfo().getLingState() != null
                            && SharedPreferencesUtils.getInstance().getUserInfo().getLingState().equals("2")) {
                        presenter.ledTask(App.getId(), id);
                    }else{
                        CommonDialogUtil.identityDialog(this, "请先申请达人身份");
                    }
                }
                break;
        }
    }
}
