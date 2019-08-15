package com.application.tchapj.task.activity;


import android.app.Activity;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.application.tchapj.App;
import com.application.tchapj.MainActivity;
import com.application.tchapj.R;
import com.application.tchapj.base.BaseBean;
import com.application.tchapj.base.BaseMvpActivity;
import com.application.tchapj.my.activity.DarenDataOneActivity;
import com.application.tchapj.my.adpter.GridImageAdapter;
import com.application.tchapj.my.adpter.ImagePickerAdapter;
import com.application.tchapj.my.fragment.FullyGridLayoutManager;
import com.application.tchapj.task.adapter.FriendReleaseAdapter;
import com.application.tchapj.task.bean.FriendReleaseBean;
import com.application.tchapj.task.bean.TaskSquareInfoModel;
import com.application.tchapj.task.presenter.DailyTaskDouyinFaPresenter;
import com.application.tchapj.task.view.DailyTaskDouyinFaView;
import com.application.tchapj.utils.CommonDialogListenerUtil;
import com.application.tchapj.utils.CommonDialogUtil;
import com.application.tchapj.utils.CommonUtils;
import com.application.tchapj.utils.GlideUtils;
import com.application.tchapj.utils.ToastUtil;
import com.application.tchapj.utils.Utils;
import com.application.tchapj.utils2.FriendReleaseDialog;
import com.application.tchapj.utils2.SelectDialog;
import com.application.tchapj.utils2.imagepicker.ImagePicker;
import com.application.tchapj.utils2.imagepicker.bean.ImageItem;
import com.application.tchapj.utils2.imagepicker.imageloader.GlideImageLoader;
import com.application.tchapj.utils2.imagepicker.ui.ImageGridActivity;
import com.application.tchapj.utils2.imagepicker.ui.ImagePreviewDelActivity;
import com.application.tchapj.utils2.imagepicker.view.CropImageView;
import com.application.tchapj.utils2.picture.PictureSelector;
import com.application.tchapj.utils2.picture.compress.Luban;
import com.application.tchapj.utils2.picture.config.PictureConfig;
import com.application.tchapj.utils2.picture.config.PictureMimeType;
import com.application.tchapj.utils2.picture.entity.LocalMedia;
import com.application.tchapj.utils2.qiniu.utils.StringUtils;
import com.application.tchapj.utils2.share.SharedPreferencesUtils;
import com.application.tchapj.widiget.GridSpacingItemDecoration;
import com.application.tchapj.widiget.ToolbarHelper;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.qiniu.android.common.FixedZone;
import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.Configuration;
import com.qiniu.android.storage.UpCompletionHandler;
import com.qiniu.android.storage.UploadManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import static com.application.tchapj.DataManager.getDataManager;
import static com.application.tchapj.task.sonic.SonicJavaScriptInterface.PARAM_CLICK_TIME;
/**
 * Create by zyy on 2019/4/23
 * Description: 领任务图文  朋友圈/qq空间图文
 */
public class LeadTaskWechatActivity extends BaseMvpActivity<DailyTaskDouyinFaView, DailyTaskDouyinFaPresenter> implements DailyTaskDouyinFaView, ImagePickerAdapter.OnRecyclerViewItemClickListener{

    @BindView(R.id.taskdyStateOff2)
    ImageView taskdyStateOff2;
    @BindView(R.id.taskdyStateOff3)
    ImageView taskdyStateOff3;
    @BindView(R.id.taskdyStateoff4)
    ImageView taskdyStateOff4;
    @BindView(R.id.releasefImg)
    ImageView releasefImg;
    @BindView(R.id.friendCircleTv)
    TextView friendCircleTv;
    @BindView(R.id.releasefNameTv)
    TextView releasefNameTv;
    @BindView(R.id.activityTimeValueTv)
    TextView activityTimeValueTv;
    //    @BindView(R.id.releasefCrowdValueTv2)
//    TextView     releasefCrowdValueTv2;
//    @BindView(R.id.releasefCrowdValueTv3)
//    TextView     releasefCrowdValueTv3;
    @BindView(R.id.releasefMoneyValueTv)
    TextView releasefMoneyValueTv;
    @BindView(R.id.releasefDownloadValueTv1)
    TextView releasefDownloadValueTv1;
    @BindView(R.id.coypTv)
    TextView coypTv;
    @BindView(R.id.releasefValueRv)
    RecyclerView releasefValueRv;
    @BindView(R.id.lead_task_wechat_submit_tv)
    TextView submitTv;
    @BindView(R.id.friendCircleImg)
    ImageView friendCircleImg;
    @BindView(R.id.releasefCopywritingTv)
    TextView releasefCopywritingTv;
    @BindView(R.id.releasefMapTv)
    TextView releasefMapTv;
    @BindView(R.id.lead_task_wechat_task_requirement_tip_tv)
    TextView activityRequireTipTv;
    @BindView(R.id.lead_task_wechat_activity_guide_tip_tv)
    TextView activityGuideTipTv;
    @BindView(R.id.lead_task_wechat_task_requirement_tv)
    TextView activityRequireTv;//任务要求-活动要求
    @BindView(R.id.lead_task_wechat_activity_guide_tv)
    TextView activityGuideTv;//任务要求-活动引导
    @BindView(R.id.lead_task_wechat_task_requirement_dotted_view)
    View dottedView;//任务要求里的虚线
    @BindView(R.id.lead_task_wechat_submit_data_ll)
    LinearLayout submitDataLl;//提交审核资料的布局
    @BindView(R.id.lead_task_wechat_task_status_tv)
    TextView taskStatusTv;//任务状态：任务总数、已领任务人数、剩余任务审核名额
    @BindView(R.id.lead_task_wechat_screenshot_rv)
    RecyclerView screenshotRv;//领任务第2步提交申请上传的图片
    @BindView(R.id.lead_task_wechat_submit_tv_count_down)
    TextView countDownSubmitTv;
    @BindView(R.id.lead_task_wechat_audit_ll)
    LinearLayout wechatAuditLl;
    @BindView(R.id.lead_task_wechat_audit_tv)
    TextView wechatAuditTv;
    @BindView(R.id.lead_task_wechat_audit_refuse_tv)
    TextView auditRefuseTv;
    @BindView(R.id.lead_task_wechat_task_remind_tv)
    TextView taskRemindTv;
    @BindView(R.id.lead_task_wechat_auto_save_tv)
    TextView autoSaveTv;


    private TaskSquareInfoModel.TaskSquareInfoResult taskSquareInfoResult;
    private FriendReleaseAdapter friendReleaseAdapter;
    //    private boolean showDialog;
    private long countdownTime;
    private Disposable disposable;
    private String taskType;
    private Disposable disposable1;
    private String timeStr;
    private String id;
//    private FriendReleaseDialog friendReleaseDialog;

    private Context mContext;
    private TaskSquareInfoModel.TaskSquareInfoResult.TaskSquareInfo taskBean;
    private String leadTaskStatus;//0未领取 1已领取 2上传资料审核中 3通过 4 未通过


    //上传截图初始化需要的东西
    private ImagePickerAdapter screenshotAdapter;
    public static final int IMAGE_ITEM_ADD = -1;
    public static final int REQUEST_CODE_SELECT = 100;
    public static final int REQUEST_CODE_PREVIEW = 101;
    private int chooseMode = PictureMimeType.ofAll();
    private int maxSelectNum = 1;
    private List<ImageItem> selectList = new ArrayList<>();
    int themeId = R.style.picture_default_style;
    private String screenshotUrl;//领任务后提交资料中的截图


    private long timeDiffer = 0L;
    Handler handler=new Handler();
    Runnable mRunnerable = new Runnable() {
        @Override
        public void run() {
            if(!submitTv.isClickable()){
                timeDiffer--;
                if(timeDiffer > 0){
                    String timeDifferStr= CommonUtils.getTimeStampDiffer(timeDiffer * 1000);
                    countDownSubmitTv.setText(String.format(getResources().getString(R.string.upload_audit_time_str), timeDifferStr));
                }else{
                    countDownSubmitTv.setText("");
                    submitTv.setBackgroundResource(R.drawable.bg_gradient1);
                    submitTv.setClickable(true);
                }
            }

            long time = taskBean.getEndTime() - System.currentTimeMillis();
            activityTimeValueTv.setText(CommonUtils.getTimeStampDiffer(time));//活动倒计时

            handler.postDelayed(mRunnerable, 1000);
        }
    };
    private Dialog taskRemindDialog;//任务提醒Dialog  获取赏金名额有限，领任务后请尽快提交审核


    @Override
    protected void initToolbar(ToolbarHelper toolbarHelper) {
        toolbarHelper.setTitle(getResources().getString(R.string.led_the_task));
    }

    @Override
    public int getRootViewId() {
        return R.layout.activity_lead_task_wechat;
    }

    @Override
    public void initUI() {

        mContext = this;

        Intent intent = getIntent();
        id = intent.getStringExtra("ID");
        presenter.getTaskSquareInfo(App.getId(), id);

        initRecyclerView();
        initData();
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
    public void ledTask(BaseBean baseBean) {
        if(baseBean != null && baseBean.getCode().equals("000")){
            ToastUtil.show(this, "领取成功");

            if(!TextUtils.isEmpty(taskSquareInfoResult.getIsDoTask()) && taskSquareInfoResult.getIsDoTask().equals("1")){
                //1需要提醒，说明没领过任务
//                Handler handler = new Handler();
//                handler.postDelayed(new Runnable() {
//
//                    @Override
//                    public void run() {
//                        taskRemindDialog.dismiss();
//                        showWechatQQDialog();
//                    }
//                }, 3000);//3秒自动关闭任务提醒dialog
                showTaskRemindDialog();//显示任务提醒dialog
            }else{
                showWechatQQDialog();
            }


        }else{
            String toastInfo = "领取失败";
            if(!StringUtils.isNullOrEmpty(baseBean.getDescription())){
                toastInfo = toastInfo + ":" + baseBean.getDescription();
            }
            ToastUtil.show(this, toastInfo);
        }

    }

    private void showTaskRemindDialog() {

        taskRemindDialog = new Dialog(this, R.style.DialogStyle);
        View view = LayoutInflater.from(this).inflate(R.layout.layout_dialog_lead_task_remind, null);
        taskRemindDialog.setContentView(view);
        TextView confirmTv = view.findViewById(R.id.dialog_lead_task_remind_confirm_tv);
        confirmTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                taskRemindDialog.dismiss();
                showWechatQQDialog();
            }
        });


        Window window = taskRemindDialog.getWindow();
        WindowManager.LayoutParams dialogParams = window.getAttributes();
        dialogParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        dialogParams.height = WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(dialogParams);

        taskRemindDialog.show();
    }

    //打开微信、qq的弹窗
    private void showWechatQQDialog() {
        CommonDialogUtil.showReleaseTaskDialog(LeadTaskWechatActivity.this, new CommonDialogListenerUtil.ReleaseTaskListener() {
            @Override
            public void confirmWechat() {
                try {
                    Intent intent = new Intent(Intent.ACTION_MAIN);
                    ComponentName cmp = new ComponentName("com.tencent.mm", "com.tencent.mm.ui.LauncherUI");
                    intent.addCategory(Intent.CATEGORY_LAUNCHER);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.setComponent(cmp);
                    startActivity(intent);
                    finish();
                } catch (ActivityNotFoundException e) {
                    showToast("检查到您手机没有安装微信，请安装后使用该功能");
                    finish();
                }
            }

            @Override
            public void confirmQQ() {
                try {
                    Intent intent = getPackageManager().getLaunchIntentForPackage("com.tencent.mobileqq");
                    startActivity(intent);
                    finish();
                } catch (ActivityNotFoundException e) {
                    showToast("检查到您手机没有安装QQ，请安装后使用该功能");
                    finish();
                }
            }
        });
    }

    //做任务第2步，提交审核资料回调
    @Override
    public void submitAuditData(BaseBean baseBean) {
        if(baseBean != null && baseBean.getCode().equals("000")){
            showToast("提交申请成功");
            finish();
        }else{
            String toastInfo = "领取失败";
            if(!StringUtils.isNullOrEmpty(baseBean.getDescription())){
                toastInfo = toastInfo + ":" + baseBean.getDescription();
            }
            showToast(toastInfo);
        }
    }

    @Override
    public void onGetDailyTaskDouyinModels(final TaskSquareInfoModel friendReleaseBean) {

        if (TextUtils.equals(friendReleaseBean.getCode(), "000")) {
            taskSquareInfoResult = friendReleaseBean.getData();
            taskBean = friendReleaseBean.getData().getTask();
            taskType = taskBean.getTaskType();
            leadTaskStatus = friendReleaseBean.getData().getTaskstatus();

            /*if (TextUtils.equals(friendReleaseBean.getData().getTaskstatus(), "0")) {
                submitTv.setVisibility(View.VISIBLE);
            } else {
                submitTv.setVisibility(View.GONE);
            }*/

            if (TextUtils.equals(taskType, "0")) {
                //朋友圈类型
                releasefCopywritingTv.setText("图文文案");
                releasefMapTv.setText("图文配图");
                RequestOptions options = new RequestOptions().centerCrop().priority(Priority.HIGH).diskCacheStrategy(DiskCacheStrategy.NONE);

                Glide.with(this).asBitmap().apply(options).load(R.mipmap.ic_py_select).into(new BitmapImageViewTarget(friendCircleImg) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        RoundedBitmapDrawable circularBitmapDrawable = RoundedBitmapDrawableFactory.create(mContext.getResources(), resource);
                        //circularBitmapDrawable.setCornerRadius(8); // 圆角
                        friendCircleImg.setImageDrawable(circularBitmapDrawable);
                    }
                });


                taskStatusTv.setText("任务总数：" + taskBean.getTaskNum() + "\n已领任务人数："
                        + friendReleaseBean.getData().getHaveReceivedCount() + "\n剩余任务审核名额：" + friendReleaseBean.getData().getReceiveTotal());

                setViewAccordingStatus(leadTaskStatus);

            } else if (TextUtils.equals(taskType, "1")) {
                //微博
                submitTv.setText(getResources().getString(R.string.hair_weibo));
                releasefCopywritingTv.setText("微博文案");
                releasefMapTv.setText("微博配图");
                RequestOptions options = new RequestOptions().centerCrop().priority(Priority.HIGH).diskCacheStrategy(DiskCacheStrategy.NONE);

                Glide.with(this).asBitmap().apply(options).load(R.drawable.weibo).into(new BitmapImageViewTarget(friendCircleImg) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        RoundedBitmapDrawable circularBitmapDrawable = RoundedBitmapDrawableFactory.create(mContext.getResources(), resource);
                        //circularBitmapDrawable.setCornerRadius(8); // 圆角
                        friendCircleImg.setImageDrawable(circularBitmapDrawable);
                    }
                });
            }

            //任务要求
            if (!StringUtils.isNullOrEmpty(taskBean.getRequire())) {
                activityRequireTv.setText(taskBean.getRequire());
            }
            if (!StringUtils.isNullOrEmpty(taskBean.getTaskGuidance())) {
              activityGuideTv.setText(taskBean.getTaskGuidance());
            }

            disposable1 = Observable.interval(1, TimeUnit.SECONDS).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Long>() {
                @Override
                public void accept(Long aLong) throws Exception {

                    countdownTime = taskBean.getEndTime() - System.currentTimeMillis();
                    timeStr = CommonUtils.getTimeStampDiffer(countdownTime);
                    activityTimeValueTv.setText(timeStr);//活动倒计时
                    if (countdownTime == 0) {
                        disposable1.dispose();
//                        if (friendReleaseDialog != null) {
//                            if (friendReleaseDialog.isShowing()) {
//                                friendReleaseDialog.dismiss();
//                            }
//                        }
                        startActivity(new Intent(mContext, UploadAuditActivity.class).putExtra("ID", id));
                        finish();
                    }
                }
            });
            GlideUtils.loadRoundedImageView(this, taskBean.getImgUrl(), releasefImg, R.color.gainsboro);
            releasefNameTv.setText(taskBean.getName());
            friendCircleTv.setText(taskBean.getNickName());
            releasefDownloadValueTv1.setText(taskBean.getCopywriting());
            releasefMoneyValueTv.setText(CommonUtils.moneyToVMoney(taskBean.getUnitPrice()));

            List<String> imgList = new ArrayList<>();

            // 资源路径
            String[] imgs = taskBean.getTaskImgUrl().split(",");
            for (int i = 0; i < imgs.length; i++) {
                imgList.add(imgs[i]);
            }

            // 将资源路径设置到图片选择实体类
            final ArrayList<ImageItem> imageItems = new ArrayList<>();
            for (String url : imgList) {
                ImageItem imageItem = new ImageItem();
                imageItem.mimeType = "copy";
                imageItem.path = url;
                imageItems.add(imageItem);
                Log.e("135", "url = " + url);
            }

            // 为适配器赋值 进行图片预览
            friendReleaseAdapter.setDataList(imgList);
            friendReleaseAdapter.setOnItemClickListener(new FriendReleaseAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(int pos) {
                    Intent intentPreview = new Intent(mContext, ImagePreviewCopyActivity.class);
                    intentPreview.putExtra(ImagePicker.EXTRA_IMAGE_ITEMS, imageItems);
                    intentPreview.putExtra(ImagePicker.EXTRA_SELECTED_IMAGE_POSITION, pos);
                    intentPreview.putExtra(ImagePicker.EXTRA_FROM_ITEMS, true);
//                    startActivityForResult(intentPreview, REQUEST_CODE_PREVIEW);
                    startActivity(intentPreview);
                }
            });
            friendReleaseAdapter.notifyDataSetChanged();
        }
    }

    public void intentApp() {
        if (TextUtils.equals(taskType, "0")) {
            try {
                Intent intent = new Intent(Intent.ACTION_MAIN);
                ComponentName cmp = new ComponentName("com.tencent.mm", "com.tencent.mm.ui.LauncherUI");
                intent.addCategory(Intent.CATEGORY_LAUNCHER);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setComponent(cmp);
                startActivity(intent);
            } catch (ActivityNotFoundException e) {
                Toast.makeText(this, "检查到您手机没有安装微信，请安装后使用该功能", Toast.LENGTH_SHORT).show();
            }
        } else if (TextUtils.equals(taskType, "1")) {
            try {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory("android.intent.category.DEFAULT");
                intent.setData(Uri.parse("sinaweibo://splash"));
                startActivity(intent);
            } catch (ActivityNotFoundException e) {
                Toast.makeText(this, "检查到您手机没有安装新浪微博，请安装后使用该功能", Toast.LENGTH_SHORT).show();
            }
        }

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

    @OnClick({R.id.coypTv, R.id.lead_task_wechat_submit_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.coypTv:
                CommonUtils.copyTextToClipboard(this, releasefDownloadValueTv1.getText().toString().trim());
                Toast.makeText(this, "文本复制成功", Toast.LENGTH_SHORT).show();
                break;
            case R.id.lead_task_wechat_submit_tv:
                if(StringUtils.isNullOrEmpty(App.getId())){
                    CommonDialogUtil.showLoginDialog(this);
                }else{
                    if("2".equals(getDataManager().quickGetMetaData(R.string.lingState,String.class))) {
                        submitTaskClick(leadTaskStatus);

                    }else{
                        CommonDialogUtil.identityDialog(this, "请先申请达人身份");
                    }
                }
                break;
        }
    }

    /**
     * 根据任务status调不同接口
     * @param leadTaskStatus 0未领取 1已领取 2上传资料审核中 3通过 4 未通过
     */
    private void submitTaskClick(String leadTaskStatus) {
        switch (leadTaskStatus){
            case "0":
                //复制文案，保存图片
                CommonUtils.copyTextToClipboard(this, releasefDownloadValueTv1.getText().toString().trim());
                String[] imgs = taskBean.getTaskImgUrl().split(",");
                for (int i = 0; i < imgs.length; i++) {
                    CommonUtils.saveImage(LeadTaskWechatActivity.this, imgs[i]);
                }
                //是达人身份
                presenter.ledTask(App.getId(), id);
                break;
            case "1":
            case "4":
                if (!TextUtils.isEmpty(screenshotUrl) && taskBean != null) {
                    presenter.submitTaskReview(screenshotUrl, taskBean.getId());
                } else {
                    Toast.makeText(this, "请上传截图", Toast.LENGTH_SHORT).show();
                }

                break;
        }

    }


    /**
     * 根据任务status展示view
     * @param leadTaskStatus 0未领取 1已领取 2上传资料审核中 3通过 4 未通过
     */
    private void setViewAccordingStatus(String leadTaskStatus) {
        if(TextUtils.isEmpty(leadTaskStatus))
            return;

        switch (leadTaskStatus){
            case "0":
                //未领取任务
                submitDataLl.setVisibility(View.GONE);
                submitTv.setText(getResources().getString(R.string.release_img_txt));
                taskRemindTv.setVisibility(View.VISIBLE);
                autoSaveTv.setVisibility(View.VISIBLE);
                break;
            case "1":
            case "4":
                //1 已领取待提交资料
                //4 未通过
                submitDataLl.setVisibility(View.VISIBLE);
                taskdyStateOff2.setImageResource(R.drawable.release_state_on2);
                taskRemindTv.setVisibility(View.VISIBLE);

                //可以提交资料的时间判断。
                timeDiffer = taskSquareInfoResult.getCountDownTime();
                if(leadTaskStatus.equals("4")){
                    timeDiffer = 0;//未通过，就不显示倒计时直接可以提
                }

                if(timeDiffer <= 0 ){
                    //大于X个小时 可以提交资料
                    submitTv.setBackgroundResource(R.drawable.bg_gradient1);
                    submitTv.setClickable(true);
                }else{
                    submitTv.setBackgroundResource(R.drawable.bg_gradient1_gray);
                    submitTv.setClickable(false);
                    submitTv.setVisibility(View.VISIBLE);

                    String timeDifferStr= CommonUtils.getTimeStampDiffer(timeDiffer * 1000);
                    countDownSubmitTv.setText(String.format(getResources().getString(R.string.upload_audit_time_str), timeDifferStr));
                    countDownSubmitTv.setVisibility(View.VISIBLE);
                    handler.postDelayed(mRunnerable,1000);

                }
                if(leadTaskStatus.equals("4")){
                    //审核被拒
                    if(!TextUtils.isEmpty(taskSquareInfoResult.getRefusal())){
                        auditRefuseTv.setVisibility(View.VISIBLE);
                        auditRefuseTv.setText("审核未通过原因：" + taskSquareInfoResult.getRefusal());
                    }
                    submitTv.setText(getResources().getString(R.string.re_submit));
                }else{
                    submitTv.setText(getResources().getString(R.string.submit));
                }
                break;
            case "2":
                //任务审核中
                taskdyStateOff2.setImageResource(R.drawable.release_state_on2);
                taskdyStateOff3.setImageResource(R.drawable.release_state_on3);
                submitTv.setVisibility(View.GONE);
                wechatAuditLl.setVisibility(View.VISIBLE);
                wechatAuditTv.setText("审核中");
                break;
            case "3":
                //通过
                taskdyStateOff2.setImageResource(R.drawable.release_state_on2);
                taskdyStateOff3.setImageResource(R.drawable.release_state_on3);
                taskdyStateOff4.setImageResource(R.drawable.release_state_on4);
                submitTv.setVisibility(View.GONE);
                wechatAuditLl.setVisibility(View.VISIBLE);
                wechatAuditTv.setText("审核成功");
                break;
        }


    }


    // 初始化朋友圈图片列表
    private void initRecyclerView() {

        releasefValueRv.setHasFixedSize(true);
        releasefValueRv.setNestedScrollingEnabled(false);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        releasefValueRv.addItemDecoration(new GridSpacingItemDecoration(3, Utils.dip2px(15f, this), Utils.dip2px(15f, this), false));
        releasefValueRv.setLayoutManager(gridLayoutManager);
        friendReleaseAdapter = new FriendReleaseAdapter(this);
        releasefValueRv.setAdapter(friendReleaseAdapter);


        //初始化上传截图的recyclerView
        initImagePicker();
        selImageList = new ArrayList<>();
        screenshotAdapter = new ImagePickerAdapter(LeadTaskWechatActivity.this, selImageList, maxSelectNum);
        screenshotAdapter.setOnItemClickListener(this);
        screenshotAdapter.setLayoutResId(R.layout.layout_item_upload_img, R.drawable.icon_upload_img);
        screenshotRv.setLayoutManager(new GridLayoutManager(LeadTaskWechatActivity.this, 1));
        screenshotRv.setHasFixedSize(true);
        screenshotRv.setAdapter(screenshotAdapter);

    }

    private void initImagePicker() {

        ImagePicker imagePicker = ImagePicker.getInstance();
        imagePicker.setImageLoader(new GlideImageLoader());   //设置图片加载器
        imagePicker.setShowCamera(true);                      //显示拍照按钮
        imagePicker.setCrop(true);                           //允许裁剪（单选才有效）
        imagePicker.setSaveRectangle(true);                   //是否按矩形区域保存
//        imagePicker.setSelectLimit(maxImgCount);              //选中数量限制
        imagePicker.setStyle(CropImageView.Style.RECTANGLE);  //裁剪框的形状
        imagePicker.setFocusWidth(800);                       //裁剪框的宽度。单位像素（圆形自动取宽高最小值）
        imagePicker.setFocusHeight(800);                      //裁剪框的高度。单位像素（圆形自动取宽高最小值）
        imagePicker.setOutPutX(1000);                         //保存文件的宽度。单位像素
        imagePicker.setOutPutY(1000);                         //保存文件的高度。单位像素
    }


    //头像图片选择
    @Override
    public void onItemClick(View view, int position) {
        switch (position) {
            case IMAGE_ITEM_ADD:

                if(!submitTv.isClickable()){
                    showToast("未到时间，暂时不能上传图片");
                    return;
                }


                List<String> names = new ArrayList<>();
                names.add("拍照");
                names.add("相册");
                showDialog(new SelectDialog.SelectDialogListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        switch (position) {
                            case 0: // 直接调起相机
                                /**
                                 * 0.4.7 目前直接调起相机不支持裁剪，如果开启裁剪后不会返回图片，请注意，后续版本会解决
                                 *
                                 * 但是当前直接依赖的版本已经解决，考虑到版本改动很少，所以这次没有上传到远程仓库
                                 *
                                 * 如果实在有所需要，请直接下载源码引用。
                                 */
                                //打开选择,本次允许选择的数量
                                ImagePicker.getInstance().setSelectLimit(maxSelectNum - selectList.size());
                                Intent intent = new Intent(LeadTaskWechatActivity.this, ImageGridActivity.class);
                                intent.putExtra(ImageGridActivity.EXTRAS_TAKE_PICKERS, true); // 是否是直接打开相机
                                startActivityForResult(intent, REQUEST_CODE_SELECT);
                                break;
                            case 1:
                                //打开选择,本次允许选择的数量
                                ImagePicker.getInstance().setSelectLimit(maxSelectNum - selectList.size());
                                Intent intent1 = new Intent(LeadTaskWechatActivity.this, ImageGridActivity.class);
                                /* 如果需要进入选择的时候显示已经选中的图片，
                                 * 详情请查看ImagePickerActivity
                                 * */
                                intent1.putExtra(ImageGridActivity.EXTRAS_IMAGES, images);
                                startActivityForResult(intent1, REQUEST_CODE_SELECT);
                                break;
                            default:
                                break;
                        }

                    }
                }, names);


                break;
            default:
                //打开预览
                Intent intentPreview = new Intent(LeadTaskWechatActivity.this, ImagePreviewDelActivity.class);
                intentPreview.putExtra(ImagePicker.EXTRA_IMAGE_ITEMS, (ArrayList<ImageItem>) screenshotAdapter.getImages());
                intentPreview.putExtra(ImagePicker.EXTRA_SELECTED_IMAGE_POSITION, position);
                intentPreview.putExtra(ImagePicker.EXTRA_FROM_ITEMS, true);
                startActivityForResult(intentPreview, REQUEST_CODE_PREVIEW);
                break;
        }
    }

    private SelectDialog showDialog(SelectDialog.SelectDialogListener listener, List<String> names) {
        SelectDialog dialog = new SelectDialog(this, R.style.transparentFrameWindowStyle, listener, names);
        if (!this.isFinishing()) {
            dialog.show();
        }
        return dialog;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (disposable != null) {
            disposable.dispose();
        }
        if (disposable1 != null) {
            disposable1.dispose();
        }
    }
    private ArrayList<ImageItem> images = null;
    ArrayList<ImageItem>  selImageList; // 当前上传所有图片
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            //添加图片返回
            if (data != null && requestCode == REQUEST_CODE_SELECT) {
                images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                if (images != null) {
                    selImageList.clear();
                    selImageList.addAll(images);
                    screenshotAdapter.setImages(selImageList);

                    // 进行判断
                    upload(images.get(0).path, new UpCompletionHandler() {
                        @Override
                        public void complete(String s, ResponseInfo responseInfo, JSONObject jsonObject) {
                            if (responseInfo.isOK()) {
                                Log.e("135", "complete:" + responseInfo + jsonObject);
                                Log.e("success", "complete:" + responseInfo + jsonObject);

                                try {
                                    String upimg = jsonObject.getString("key");

                                    screenshotUrl = "http://" + "qiniuyun2.ctrlmedia.cn/" + upimg;

                                    Log.e("qiniu++++", "complete: " + jsonObject + screenshotUrl);

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            } else {
                                Log.e("failssss", s + responseInfo + jsonObject + screenshotUrl);
                            }
                        }
                    });

                }

            }
        } else if (resultCode == ImagePicker.RESULT_CODE_BACK) {
            //预览图片返回
            if (data != null && requestCode == REQUEST_CODE_PREVIEW) {

                images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_IMAGE_ITEMS);
                if (images != null) {
                    selImageList.clear();
                    selImageList.addAll(images);
                    screenshotAdapter.setImages(selImageList);
                }
            }
        }
    }

}

