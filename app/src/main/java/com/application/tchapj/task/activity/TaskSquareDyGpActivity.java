package com.application.tchapj.task.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v4.widget.NestedScrollView;
import android.text.TextUtils;
import android.util.Log;
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
import com.application.tchapj.task.presenter.DailyTaskDouyinFaPresenter;
import com.application.tchapj.task.view.DailyTaskDouyinFaView;
import com.application.tchapj.utils.CommonDialogUtil;
import com.application.tchapj.utils.CommonUtils;
import com.application.tchapj.utils.Utils;
import com.application.tchapj.utils2.FriendReleaseDialog;
import com.application.tchapj.utils2.picture.PictureSelector;
import com.application.tchapj.utils2.qiniu.utils.StringUtils;
import com.application.tchapj.utils2.share.SharedPreferencesUtils;
import com.application.tchapj.widiget.ToolbarHelper;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import freemarker.template.utility.StringUtil;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import me.drakeet.materialdialog.MaterialDialog;

/**
 * Created by 安卓开发 on 2018/7/26.
 * 抖音合拍
 */
// 抖音合拍的领任务
public class TaskSquareDyGpActivity extends BaseMvpActivity<DailyTaskDouyinFaView, DailyTaskDouyinFaPresenter> implements DailyTaskDouyinFaView {


    @BindView(R.id.taskdygenpaiStateLine)
    ImageView taskdygenpaiStateLine;
    @BindView(R.id.taskdyStateOn1)
    ImageView taskdyStateOn1;
    @BindView(R.id.taskdyStateoff4)
    ImageView taskdyStateoff4;
    @BindView(R.id.taskdyStateOff2)
    ImageView taskdyStateOff2;
    @BindView(R.id.taskdyStateOff3)
    ImageView taskdyStateOff3;
    @BindView(R.id.taskdyfTv1)
    TextView taskdyfTv1;
    @BindView(R.id.taskdyfTv2)
    TextView taskdyfTv2;
    @BindView(R.id.taskdyfTv3)
    TextView taskdyfTv3;
    @BindView(R.id.taskdyfTv4)
    TextView taskdyfTv4;
    @BindView(R.id.releasefBg)
    ImageView releasefBg;
    @BindView(R.id.taskdyfImg)
    ImageView taskdyfImg;
    @BindView(R.id.taskdyCircleImg)
    ImageView taskdyCircleImg;
    @BindView(R.id.taskdyCircleTv)
    TextView taskdyCircleTv;
    @BindView(R.id.taskdyfNameTv)
    TextView taskdyfNameTv;
//    @BindView(R.id.activityTimeTv)
//    TextView activityTimeTv;
//    @BindView(R.id.taskdyTimeValueTv)
//    TextView taskdyTimeValueTv;
//    @BindView(R.id.releasefBg1)
//    ImageView releasefBg1;
//    @BindView(R.id.taskdyfCrowdTv)
//    TextView taskdyfCrowdTv;
//    @BindView(R.id.taskdyfCrowdValueTv1)
//    TextView taskdyfCrowdValueTv1;
//    @BindView(R.id.taskdyfCrowdValueTv2)
//    TextView taskdyfCrowdValueTv2;
//    @BindView(R.id.taskdyfCrowdValueTv3)
//    TextView taskdyfCrowdValueTv3;
//    @BindView(R.id.releasefBg2)
//    ImageView releasefBg2;
//    @BindView(R.id.taskdyfChannelTv)
//    TextView taskdyfChannelTv;
//    @BindView(R.id.taskdyfChannelValueTv1)
//    TextView taskdyfChannelValueTv1;
//    @BindView(R.id.releasefBg3)
//    ImageView releasefBg3;
    @BindView(R.id.taskdyfActivitysTv)
    TextView taskdyfActivitysTv;
    @BindView(R.id.releasefActivitysValueTv1)
    TextView releasefActivitysValueTv1;
    @BindView(R.id.releasefBg4)
    ImageView releasefBg4;
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
    @BindView(R.id.releasefBg6)
    ImageView releasefBg6;
    @BindView(R.id.releasefCountdownTv)
    TextView releasefCountdownTv;
    @BindView(R.id.releasefCountdownValueTv1)
    TextView releasefCountdownValueTv1;
    @BindView(R.id.releasefDownloadTv)
    TextView releasefDownloadTv;
    @BindView(R.id.releasefValueRv)
    ImageView releasefValueRv;
    @BindView(R.id.hairFriendsBtn)
    Button hairFriendsBtn;
    @BindView(R.id.ScrollView)
    NestedScrollView ScrollView;
    private String id;
    private String memberId;
    private MaterialDialog dialog;
    private long countdownTime;
    private Disposable disposable1;
    private String timeStr;
    private Disposable disposable;
//    private boolean showDialog;
//    private FriendReleaseDialog friendReleaseDialog;
    private String imgurl;
    private String taskImgUrl;


    @Override
    protected void initToolbar(ToolbarHelper toolbarHelper) {
        toolbarHelper.setTitle("领任务");
        // mToolbarHelper = toolbarHelper;

    }

    @Override
    public int getRootViewId() {
        return R.layout.activity_task_douyin2;
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
    public DailyTaskDouyinFaPresenter createPresenter() {
        return new DailyTaskDouyinFaPresenter(getApp());
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
    public void onGetDailyTaskDouyinModels(final TaskSquareInfoModel dailyTaskDouyinModel) {

        if (TextUtils.equals("000", dailyTaskDouyinModel.getCode())) {
            String Status = dailyTaskDouyinModel.getData().getTask().getStatus();
            String name = dailyTaskDouyinModel.getData().getTask().getName();

            taskdyfNameTv.setText(name);
            String startTime = Utils.getDataFormatString(dailyTaskDouyinModel.getData().getTask().getStartTime(), "yyyy-MM-dd HH:mm:ss");
            String endTime = Utils.getDataFormatString(dailyTaskDouyinModel.getData().getTask().getEndTime(), "yyyy-MM-dd HH:mm:ss");
            BigDecimal unitPrice = dailyTaskDouyinModel.getData().getTask().getUnitPrice();
            String nickName = dailyTaskDouyinModel.getData().getTask().getNickName();
            String taskGuidance = dailyTaskDouyinModel.getData().getTask().getTaskGuidance();//活动引导
            String taskType = dailyTaskDouyinModel.getData().getTask().getTaskType();
            String require = dailyTaskDouyinModel.getData().getTask().getRequire();
            int fans = dailyTaskDouyinModel.getData().getTask().getFans();
            imgurl = dailyTaskDouyinModel.getData().getTask().getImgUrl();

            taskImgUrl = dailyTaskDouyinModel.getData().getTask().getTaskImgUrl();

            /*RequestOptions options = new RequestOptions().centerCrop()
                                                         .priority(Priority.HIGH)
                                                         .diskCacheStrategy(DiskCacheStrategy.NONE);*/

            RequestOptions options1 = new RequestOptions().centerCrop()
                    .placeholder(R.color.color_f6) // 占位符
                    .diskCacheStrategy(DiskCacheStrategy.ALL); // 缓存策略

            Glide.with(this)
                    .asBitmap()
                    .load(taskImgUrl)
                    .apply(options1)
                    .into(releasefValueRv);

//            taskdyTimeValueTv.setText(startTime + " ~ " + endTime);
//            taskdyfCrowdValueTv1.setText(dailyTaskDouyinModel.getData().getTask().getCircleTypeId());
            releasefActivitysValueTv1.setText(taskGuidance);
            releasefActivitysReValueTv1.setText(require);
//            releaseflimitValueTv1.setText("抖音粉丝大于" + fans + "人");
            releasefMoneyValueTv.setText(CommonUtils.moneyToVMoney(unitPrice) + "V币/条");
            taskdyCircleTv.setText(nickName);

            disposable1 = Observable.interval(1, TimeUnit.SECONDS).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Long>() {
                @Override
                public void accept(Long aLong) throws Exception {
                    countdownTime = dailyTaskDouyinModel.getData().getTask().getEndTime() - System.currentTimeMillis();
                    timeStr =  CommonUtils.getTimeStampDiffer(countdownTime);
                    releasefCountdownValueTv1.setText(timeStr);
                    if (countdownTime == 0) {
                        disposable1.dispose();
//                        if (friendReleaseDialog != null) {
//                            if (friendReleaseDialog.isShowing()) {
//                                friendReleaseDialog.dismiss();
//                            }
//                        }
                        startActivity(new Intent(TaskSquareDyGpActivity.this, UploadAuditActivity.class).putExtra("ID", id));
                        finish();
                    }
                }
            });
            if (TextUtils.equals("0", taskType)) {
//                taskdyfChannelValueTv1.setText("朋友圈");

                RequestOptions options = new RequestOptions().centerCrop().priority(Priority.HIGH).diskCacheStrategy(DiskCacheStrategy.NONE);

                Glide.with(this).asBitmap().apply(options).load(R.drawable.quzi).into(new BitmapImageViewTarget(taskdyCircleImg) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        RoundedBitmapDrawable circularBitmapDrawable = RoundedBitmapDrawableFactory.create(TaskSquareDyGpActivity.this.getResources(), resource);
                        //circularBitmapDrawable.setCornerRadius(8); // 圆角
                        taskdyCircleImg.setImageDrawable(circularBitmapDrawable);
                    }
                });

            } else if (TextUtils.equals("1", taskType)) {
//                taskdyfChannelValueTv1.setText("微博");
                RequestOptions options = new RequestOptions().centerCrop().priority(Priority.HIGH).diskCacheStrategy(DiskCacheStrategy.NONE);

                Glide.with(this).asBitmap().apply(options).load(R.drawable.weibo).into(new BitmapImageViewTarget(taskdyCircleImg) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        RoundedBitmapDrawable circularBitmapDrawable = RoundedBitmapDrawableFactory.create(TaskSquareDyGpActivity.this.getResources(), resource);
                        //circularBitmapDrawable.setCornerRadius(8); // 圆角
                        taskdyCircleImg.setImageDrawable(circularBitmapDrawable);
                    }
                });

            } else if (TextUtils.equals("2", taskType)) {
//                taskdyfChannelValueTv1.setText("抖音合拍");
                RequestOptions options = new RequestOptions().centerCrop().priority(Priority.HIGH).diskCacheStrategy(DiskCacheStrategy.NONE);

                Glide.with(this).asBitmap().apply(options).load(R.drawable.douyin).into(new BitmapImageViewTarget(taskdyCircleImg) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        RoundedBitmapDrawable circularBitmapDrawable = RoundedBitmapDrawableFactory.create(TaskSquareDyGpActivity.this.getResources(), resource);
                        //circularBitmapDrawable.setCornerRadius(8); // 圆角
                        taskdyCircleImg.setImageDrawable(circularBitmapDrawable);
                    }
                });
            } else if (TextUtils.equals("3", taskType)) {
//                taskdyfChannelValueTv1.setText("抖音原创");
                RequestOptions options = new RequestOptions().centerCrop().priority(Priority.HIGH).diskCacheStrategy(DiskCacheStrategy.NONE);

                Glide.with(this).asBitmap().apply(options).load(R.drawable.douyin).into(new BitmapImageViewTarget(taskdyCircleImg) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        RoundedBitmapDrawable circularBitmapDrawable = RoundedBitmapDrawableFactory.create(TaskSquareDyGpActivity.this.getResources(), resource);
                        //circularBitmapDrawable.setCornerRadius(8); // 圆角
                        taskdyCircleImg.setImageDrawable(circularBitmapDrawable);
                    }
                });
            } else if (TextUtils.equals("4", taskType)) {

            }

            RequestOptions options = new RequestOptions().centerCrop().priority(Priority.HIGH).diskCacheStrategy(DiskCacheStrategy.NONE);

            Glide.with(this).asBitmap().apply(options).load(imgurl).into(new BitmapImageViewTarget(taskdyfImg) {
                @Override
                protected void setResource(Bitmap resource) {
                    RoundedBitmapDrawable circularBitmapDrawable = RoundedBitmapDrawableFactory.create(TaskSquareDyGpActivity.this.getResources(), resource);
                    //circularBitmapDrawable.setCornerRadius(8); // 圆角
                    taskdyfImg.setImageDrawable(circularBitmapDrawable);
                }
            });


        }


    }


    private void showInfoDialog() {
        dialog = new MaterialDialog(TaskSquareDyGpActivity.this).setTitle("提示").setMessage("你需要具有达人身份才可以领任务哦").setPositiveButton("激活达人身份", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();

            }
        }).setNegativeButton("取消", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    @OnClick({ R.id.hairFriendsBtn, R.id.releasefValueRv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.hairFriendsBtn:
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
            case R.id.releasefValueRv:

                // 初始化图片选择器
                PictureSelector.create(TaskSquareDyGpActivity.this)
                        .externalPictureVideo(taskImgUrl);
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
//        if (showDialog) {
//            if (friendReleaseDialog == null) {
//                friendReleaseDialog = new FriendReleaseDialog(this);
//            }
//            friendReleaseDialog.setOnDialogLinstener(new FriendReleaseDialog.OnDialogLinstener() {
//                @Override
//                public void cancle() {
//                    disposable.dispose();
//                    friendReleaseDialog.dismiss();
//                }
//
//                @Override
//                public void init(final TextView tv) {
//                    disposable = Observable.interval(1, TimeUnit.SECONDS).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Long>() {
//                        @Override
//                        public void accept(Long aLong) throws Exception {
//
//                            tv.setText(timeStr);
//                            if (countdownTime == 0) {
//                                disposable.dispose();
//                            }
//                        }
//                    });
//                }
//            });
//            friendReleaseDialog.createFriendReleaseDialog();
//            showDialog = false;
//        }
    }

    @Override
    public void ledTask(BaseBean baseBean) {
        if (!StringUtils.isNullOrEmpty(baseBean.getCode()) && baseBean.getCode().equals("000")) {
            intentApp();
            Toast.makeText(TaskSquareDyGpActivity.this, "任务领取成功", Toast.LENGTH_SHORT).show();
            finish();
        }else{
            if(!StringUtils.isNullOrEmpty(baseBean.getDescription())){
                Toast.makeText(TaskSquareDyGpActivity.this, baseBean.getDescription(), Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(TaskSquareDyGpActivity.this, "操作失败", Toast.LENGTH_SHORT).show();
            }

        }

//        showDialog = true;
    }

    @Override
    public void submitAuditData(BaseBean baseBean) {

    }

    // 打开抖音App
    public void intentApp() {
        String url = "snssdk1128://aweme/detail/6534452667488406792?refer=web&gd_label=click_wap_detail_download_feature&appParam=%7B%22__type__%22%3A%22wap%22%2C%22position%22%3A%22900718067%22%2C%22parent_group_id%22%3A%226553813763982626051%22%2C%22webid%22%3A%226568996356873356814%22%2C%22gd_label%22%3A%22click_wap%22%7D&needlaunchlog=1";
        try {
            Uri uri = Uri.parse(url);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            intent.setFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
            startActivity(intent);
        } catch (Exception e) {

            Toast.makeText(this, "检查到您手机没有安装抖音，请安装后使用该功能", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (disposable1 != null) {
            disposable1.dispose();
        }
        if (disposable != null) {
            disposable.dispose();
        }
    }
}
