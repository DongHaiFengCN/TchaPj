package com.application.tchapj.task.activity;

import android.content.ActivityNotFoundException;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.ComponentName;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.application.tchapj.task.adapter.FriendReleaseAdapter;
import com.application.tchapj.task.bean.FriendReleaseBean;
import com.application.tchapj.task.bean.TaskSquareInfoModel;
import com.application.tchapj.task.presenter.DailyTaskDouyinFaPresenter;
import com.application.tchapj.task.view.DailyTaskDouyinFaView;
import com.application.tchapj.utils.CommonDialogUtil;
import com.application.tchapj.utils.CommonUtils;
import com.application.tchapj.utils.GlideUtils;
import com.application.tchapj.utils.ToastUtil;
import com.application.tchapj.utils.Utils;
import com.application.tchapj.utils2.FriendReleaseDialog;
import com.application.tchapj.utils2.imagepicker.ImagePicker;
import com.application.tchapj.utils2.imagepicker.bean.ImageItem;
import com.application.tchapj.utils2.qiniu.utils.StringUtils;
import com.application.tchapj.utils2.share.SharedPreferencesUtils;
import com.application.tchapj.widiget.GridSpacingItemDecoration;
import com.application.tchapj.widiget.ToolbarHelper;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import static com.application.tchapj.task.sonic.SonicJavaScriptInterface.PARAM_CLICK_TIME;

// 领任务朋友圈图文/微博
public class FriendReleaseActivity extends BaseMvpActivity<DailyTaskDouyinFaView, DailyTaskDouyinFaPresenter> implements DailyTaskDouyinFaView {

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
    @BindView(R.id.releasefActivitysValueTv1)
    TextView releasefActivitysValueTv1;
    @BindView(R.id.releasefMoneyValueTv)
    TextView releasefMoneyValueTv;
    @BindView(R.id.releasefDownloadValueTv1)
    TextView releasefDownloadValueTv1;
    @BindView(R.id.coypTv)
    TextView coypTv;
    @BindView(R.id.releasefValueRv)
    RecyclerView releasefValueRv;
    @BindView(R.id.hairFriendsBtn)
    Button hairFriendsBtn;
    @BindView(R.id.friendCircleImg)
    ImageView friendCircleImg;
    @BindView(R.id.releasefCopywritingTv)
    TextView releasefCopywritingTv;
    @BindView(R.id.releasefMapTv)
    TextView releasefMapTv;
    @BindView(R.id.releasefContentTv)
    TextView releasefContentTv;
    @BindView(R.id.task_square_info_requirecontent_tv)
    TextView requireTv;

    private FriendReleaseAdapter friendReleaseAdapter;
//    private boolean showDialog;
    private long countdownTime;
    private Disposable disposable;
    private String taskType;
    private Disposable disposable1;
    private String timeStr;
    private String id;
//    private FriendReleaseDialog friendReleaseDialog;

    @Override
    protected void initToolbar(ToolbarHelper toolbarHelper) {
        toolbarHelper.setTitle(getResources().getString(R.string.led_the_task));
    }

    @Override
    public int getRootViewId() {
        return R.layout.activity_friend_release;
    }

    @Override
    public void initUI() {

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
            finish();
            ToastUtil.show(this, "领取成功");
            intentApp();
        }else{
            String toastInfo = "领取失败";
            if(!StringUtils.isNullOrEmpty(baseBean.getDescription())){
                toastInfo = toastInfo + ":" + baseBean.getDescription();
            }
            ToastUtil.show(this, toastInfo);
        }

    }

    @Override
    public void submitAuditData(BaseBean baseBean) {

    }

    @Override
    public void onGetDailyTaskDouyinModels(final TaskSquareInfoModel friendReleaseBean) {

        if (TextUtils.equals(friendReleaseBean.getCode(), "000")) {
            final TaskSquareInfoModel.TaskSquareInfoResult.TaskSquareInfo taskBean = friendReleaseBean.getData().getTask();
            taskType = taskBean.getTaskType();

            /*if (TextUtils.equals(friendReleaseBean.getData().getTaskstatus(), "0")) {
                hairFriendsBtn.setVisibility(View.VISIBLE);
            } else {
                hairFriendsBtn.setVisibility(View.GONE);
            }*/

            if (TextUtils.equals(taskType, "0")) {
                //朋友圈类型
                hairFriendsBtn.setText(getResources().getString(R.string.hair_circle_of_friends));
                releasefCopywritingTv.setText("朋友圈文案");
                releasefMapTv.setText("朋友圈配图");
                releasefContentTv.setText("准备好文案和配图就可以发朋友圈了哦");
                RequestOptions options = new RequestOptions().centerCrop().priority(Priority.HIGH).diskCacheStrategy(DiskCacheStrategy.NONE);

                Glide.with(this).asBitmap().apply(options).load(R.drawable.quzi).into(new BitmapImageViewTarget(friendCircleImg) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        RoundedBitmapDrawable circularBitmapDrawable = RoundedBitmapDrawableFactory.create(FriendReleaseActivity.this.getResources(), resource);
                        //circularBitmapDrawable.setCornerRadius(8); // 圆角
                        friendCircleImg.setImageDrawable(circularBitmapDrawable);
                    }
                });
            } else if (TextUtils.equals(taskType, "1")) {
                //微博
                hairFriendsBtn.setText(getResources().getString(R.string.hair_weibo));
                releasefCopywritingTv.setText("微博文案");
                releasefMapTv.setText("微博配图");
                releasefContentTv.setText("准备好文案和配图就可以发微博了哦");
                RequestOptions options = new RequestOptions().centerCrop().priority(Priority.HIGH).diskCacheStrategy(DiskCacheStrategy.NONE);

                Glide.with(this).asBitmap().apply(options).load(R.drawable.weibo).into(new BitmapImageViewTarget(friendCircleImg) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        RoundedBitmapDrawable circularBitmapDrawable = RoundedBitmapDrawableFactory.create(FriendReleaseActivity.this.getResources(), resource);
                        //circularBitmapDrawable.setCornerRadius(8); // 圆角
                        friendCircleImg.setImageDrawable(circularBitmapDrawable);
                    }
                });
            }

            //任务要求
            if(!StringUtils.isNullOrEmpty(taskBean.getRequire())){
                requireTv.setText(taskBean.getRequire());
            }else{
                requireTv.setText("1.分享的图文至少在朋友圈保留3小时\n2.分享的图文不可带限制条件");
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
                        startActivity(new Intent(FriendReleaseActivity.this, UploadAuditActivity.class).putExtra("ID", id));
                        finish();
                    }
                }
            });
            GlideUtils.loadRoundedImageView(this, taskBean.getImgUrl(), releasefImg, R.color.gainsboro);
            releasefNameTv.setText(taskBean.getName());
            friendCircleTv.setText(taskBean.getNickName());
            releasefDownloadValueTv1.setText(taskBean.getCopywriting());
            releasefActivitysValueTv1.setText(taskBean.getTaskGuidance());
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
                    Intent intentPreview = new Intent(FriendReleaseActivity.this, ImagePreviewCopyActivity.class);
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
                // TODO: handle exception
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

    @OnClick({R.id.coypTv, R.id.hairFriendsBtn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.coypTv:
                ClipboardManager mClipboardManager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                ClipData clipData = ClipData.newPlainText("", releasefDownloadValueTv1.getText().toString().trim());
                mClipboardManager.setPrimaryClip(clipData);
                Toast.makeText(this, "文本复制成功", Toast.LENGTH_SHORT).show();
                break;
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

}
