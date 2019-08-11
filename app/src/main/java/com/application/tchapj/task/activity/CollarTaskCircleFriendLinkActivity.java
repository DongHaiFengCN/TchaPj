package com.application.tchapj.task.activity;

import android.content.ActivityNotFoundException;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.ComponentName;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.GridLayoutManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.application.tchapj.App;
import com.application.tchapj.R;
import com.application.tchapj.WebViewActivity;
import com.application.tchapj.base.BaseBean;
import com.application.tchapj.base.BaseMvpActivity;
import com.application.tchapj.main.activity.StartUpAdvertActivity;
import com.application.tchapj.task.adapter.FriendReleaseAdapter;
import com.application.tchapj.task.bean.TaskSquareInfoModel;
import com.application.tchapj.task.presenter.DailyTaskDouyinFaPresenter;
import com.application.tchapj.task.view.DailyTaskDouyinFaView;
import com.application.tchapj.utils.CommonDialogListenerUtil;
import com.application.tchapj.utils.CommonDialogUtil;
import com.application.tchapj.utils.CommonUtils;
import com.application.tchapj.utils.GlideUtils;
import com.application.tchapj.utils.ToastUtil;
import com.application.tchapj.utils2.ShareSDKUtils;
import com.application.tchapj.utils2.imagepicker.ImagePicker;
import com.application.tchapj.utils2.imagepicker.bean.ImageItem;
import com.application.tchapj.utils2.qiniu.utils.StringUtils;
import com.application.tchapj.utils2.share.OnekeyShare;
import com.application.tchapj.utils2.share.SharedPreferencesUtils;
import com.application.tchapj.widiget.ToolbarHelper;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.iflytek.cloud.thirdparty.P;
import com.just.agentweb.AgentWeb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.tencent.qzone.QZone;
import cn.sharesdk.wechat.favorite.WechatFavorite;
import cn.sharesdk.wechat.friends.Wechat;
import cn.sharesdk.wechat.moments.WechatMoments;
import io.reactivex.disposables.Disposable;


/**
 * 领任务朋友圈链接
 */
public class CollarTaskCircleFriendLinkActivity extends BaseMvpActivity<DailyTaskDouyinFaView, DailyTaskDouyinFaPresenter> implements DailyTaskDouyinFaView{

    @BindView(R.id.collar_task_circle_friend_task_iv)
    ImageView taskImgIv;
    @BindView(R.id.collar_task_circle_friend_task_name_tv)
    TextView taskNameTv;
    @BindView(R.id.collar_task_circle_friend_task_nick_name_tv)
    TextView nickNameTv;
    @BindView(R.id.collar_task_circle_friend_task_countdown_tv)
    TextView countdownTimeTv;
    @BindView(R.id.collar_task_circle_friend_task_requirecontent_tv)
    TextView requireContentTv;
    @BindView(R.id.collar_task_circle_friend_task_link_url_click_tv)
    TextView linkClickTv;
    @BindView(R.id.collar_task_circle_friend_task_info_first_ll)
    LinearLayout taskInfoFirstLl;
    @BindView(R.id.collar_task_circle_friend_task_info_second_ll)
    LinearLayout taskInfoSecondLl;

    private String id;

    String shareTitle = "";
    String shareContent = "";
    String shareImgUrl = "";
    String shareWebUrl = "";

    @Override
    protected void initToolbar(ToolbarHelper toolbarHelper) {
        toolbarHelper.setTitle("领任务");
    }

    @Override
    public int getRootViewId() {
        return R.layout.activity_collar_task_circle_friend_link;
    }

    @Override
    public void initUI() {
        Intent intent = getIntent();
        id = intent.getStringExtra("ID");
        presenter.getTaskSquareInfo(App.getId(), id);
        initData();
    }


    @NonNull
    @Override
    public DailyTaskDouyinFaPresenter createPresenter() {
        return new DailyTaskDouyinFaPresenter(getApp());
    }


    @Override
    public void onGetDailyTaskDouyinModels(final TaskSquareInfoModel friendReleaseBean) {

        if (TextUtils.equals(friendReleaseBean.getCode(), "000")) {
            if(!StringUtils.isNullOrEmpty(friendReleaseBean.getData().getTaskstatus()) && !friendReleaseBean.getData().getTaskstatus().equals("3")){
                taskInfoFirstLl.setVisibility(View.VISIBLE);
                taskInfoSecondLl.setVisibility(View.GONE);

                final TaskSquareInfoModel.TaskSquareInfoResult.TaskSquareInfo taskBean = friendReleaseBean.getData().getTask();

                shareTitle = taskBean.getTaskGuidance();
                shareImgUrl = taskBean.getTaskImgUrl();
                shareWebUrl = taskBean.getForwardUrl();


                linkClickTv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        WebViewActivity.start(CollarTaskCircleFriendLinkActivity.this,
                                "All", taskBean.getForwardUrl(),false, true);
                    }
                });

                RequestOptions options = new RequestOptions().centerCrop().priority(Priority.HIGH).diskCacheStrategy(DiskCacheStrategy.NONE);


                GlideUtils.loadRoundedImageView(this, taskBean.getImgUrl(), taskImgIv, R.color.gainsboro);
                taskNameTv.setText(taskBean.getName());
                nickNameTv.setText(taskBean.getNickName());
                String timeStr = CommonUtils.getTimeStampDiffer(taskBean.getEndTime() - System.currentTimeMillis());
                countdownTimeTv.setText(timeStr);

                if(!StringUtils.isNullOrEmpty(taskBean.getRequire())){
                    requireContentTv.setText(taskBean.getRequire());
                }else{
                    requireContentTv.setText("1.分享的链接至少在朋友圈保留3小时\n2.分享的链接不可带限制条件");
                }


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

            }else{
                taskInfoFirstLl.setVisibility(View.GONE);
                taskInfoSecondLl.setVisibility(View.VISIBLE);
            }

        }
    }

    @Override
    public void ledTask(BaseBean baseBean) {
        if(baseBean != null && baseBean.getCode().equals("000")){

            CommonDialogUtil.showWechatQQShareDialog(this, new CommonDialogListenerUtil.WechatShareDialogListener() {
                @Override
                public void wechat() {
                    ShareSDKUtils.showShare(CollarTaskCircleFriendLinkActivity.this, shareTitle, shareContent, shareImgUrl, shareWebUrl, Wechat.NAME);
                    finish();
                }

                @Override
                public void wechatMoments() {
                    ShareSDKUtils.showShare(CollarTaskCircleFriendLinkActivity.this, shareTitle, shareContent, shareImgUrl, shareWebUrl, WechatMoments.NAME);
                    finish();
                }

                @Override
                public void qq() {
                    ShareSDKUtils.showShare(CollarTaskCircleFriendLinkActivity.this, shareTitle, shareContent, shareImgUrl, shareWebUrl, QQ.NAME);
                    finish();
                }
            });
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


    public void intentApp() {
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

    @OnClick({R.id.collar_task_circle_friend_task_link_share_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.collar_task_circle_friend_task_link_share_btn:
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
//                Intent intent = new Intent(Intent.ACTION_MAIN);
//                ComponentName cmp = new ComponentName("com.tencent.mm", "com.tencent.mm.ui.LauncherUI");
//                intent.addCategory(Intent.CATEGORY_LAUNCHER);
//                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                intent.setComponent(cmp);
//                startActivity(intent);

                break;
        }
    }


    // 分享的返回结果
    class WechatPlatformActionListener implements PlatformActionListener {

        protected AgentWeb mAgentWeb;              // AgentWeb 是一个高度封装的 WebView

        public void onComplete(Platform plat, int action,
                               HashMap res) {
            System.out.println(res.toString());
            // 在这里添加分享成功的处理代码
            // 无参数调用
            mAgentWeb.getJsEntraceAccess().quickCallJs("shareSuccess");
        }

        public void onError(Platform plat, int action, Throwable t) {
            t.printStackTrace();
            // 在这里添加分享失败的处理代码
            // 无参数调用
            mAgentWeb.getJsEntraceAccess().quickCallJs("shareError('" + "分享失败" + "')");
//            mAgentWeb.loadUrl("javascript:shareError('" +"分享失败"+"')");
        }

        public void onCancel(Platform plat, int action) {
            // 在这里添加取消分享的处理代码
            mAgentWeb.getJsEntraceAccess().quickCallJs("shareCancel");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void initData() {

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

