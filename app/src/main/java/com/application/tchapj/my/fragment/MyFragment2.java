/*
package com.application.tchapj.my.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.application.tchapj.App;
import com.application.tchapj.Constants;
import com.application.tchapj.H5UrlData;
import com.application.tchapj.R;
import com.application.tchapj.WebViewActivity;
import com.application.tchapj.base.BaseMvpFragment;
import com.application.tchapj.main.bean.NewsInfo;
import com.application.tchapj.my.activity.SettingActivity;
import com.application.tchapj.my.bean.UserModel;
import com.application.tchapj.my.presenter.MyPresenter;
import com.application.tchapj.my.view.IMyView;
import com.application.tchapj.utils2.CircleImageView;
import com.application.tchapj.utils2.SharedPreferences;
import com.application.tchapj.widiget.LogUtils;
import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.OnClick;
import me.drakeet.materialdialog.MaterialDialog;

*/
/**
 * Created by 安卓开发 on 2018/7/27.
 *//*


public class MyFragment2 extends BaseMvpFragment<IMyView, MyPresenter> implements IMyView {

    @BindView(R.id.iv_head)
    CircleImageView iv_head;
    @BindView(R.id.nick_name)
    TextView nick_name;
    @BindView(R.id.update_info)
    TextView update_info;
    @BindView(R.id.iv_setting)
    ImageView iv_setting;
    @BindView(R.id.media_go)
    ImageView mediaGo;
    @BindView(R.id.message)
    RelativeLayout message;
    @BindView(R.id.yijian_fankui)
    RelativeLayout yijianFankui;
    @BindView(R.id.service_go)
    ImageView serviceGo;
    @BindView(R.id.server_mobile)
    TextView serverMobile;
    @BindView(R.id.jifen)
    TextView jifen;
    @BindView(R.id.media)
    TextView media_renzheng; //个人媒体是否认证
    @BindView(R.id.shiming)
    TextView shiming; //是否实名认证
    @BindView(R.id.rl_jifen)
    RelativeLayout rl_jifen;
    @BindView(R.id.task)
    RelativeLayout task;
    @BindView(R.id.jifen_notes)
    RelativeLayout jifen_notes;
    @BindView(R.id.rl_shiming)
    RelativeLayout rl_shiming;

    @BindView(R.id.ic_spot)
    ImageView ic_spot;

    private String user_id;
    private String mediaId;
    private String isMedia = "0"; //个人自媒体是否认证
    private boolean isShiming; //是否实名认证

    private MaterialDialog dialog;
    private String bankCard;

    // 接收参数
    public static MyFragment newInstance(String param) {
        Bundle args = new Bundle();
        args.putString("args", param);
        MyFragment fragment = new MyFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_my2;
    }

    @Override
    public void initUI() {

        if ("3".equals(App.Type)) {
            rl_jifen.setVisibility(View.GONE);
            task.setVisibility(View.GONE);
            jifen_notes.setVisibility(View.GONE);
            rl_shiming.setVisibility(View.GONE);
        } else if ("4".equals(App.Type)) {
            rl_jifen.setVisibility(View.VISIBLE);
            task.setVisibility(View.VISIBLE);
            jifen_notes.setVisibility(View.VISIBLE);
            rl_shiming.setVisibility(View.VISIBLE);
        }

        user_id = App.getId();

    }

    @OnClick({R.id.update_info, R.id.iv_setting, R.id.media_go, R.id.message, R.id.yijian_fankui,
            R.id.rl_jifen, R.id.rl_media, R.id.task, R.id.jifen_notes, R.id.rl_shiming, R.id.server})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.update_info://修改个人信息
                startWeb(H5UrlData.UPDATE_USERINFO + user_id, "修改个人信息");
                break;
            case R.id.iv_setting:
                Intent intent = new Intent(getActivity(), SettingActivity.class);
                startActivity(intent);
                break;
            case R.id.message://消息中心
                startWeb(H5UrlData.MESSAGE_CENTER + user_id, "");
                break;
            case R.id.yijian_fankui: //意见反馈
                startWeb(H5UrlData.YIJIAN_FANKUI + user_id, "意见反馈");
                break;
            case R.id.rl_jifen://积分兑换
                if (!isShiming) {
                    showInfoDialog();
                } else {
                    startWeb(H5UrlData.JIFEN + user_id + "&bankcard=" + bankCard, "");
                }
                break;
            case R.id.rl_media://个人自媒体认证

                // member : 用户id     personalId : 自媒体id   id：状态
                //           user_id    mediaId          isMedia
                switch (isMedia) {
                    case "0":
                        //去认证
                        startWeb(H5UrlData.MEDIA_RENZHENG + "&id=" + user_id, "身份认证");
                        break;
                    case "1":
                        //审核中
                        startWeb(H5UrlData.MEDIA_RENZHENGzhong + "&member=" + user_id + "&id=" + isMedia + "&personalId=" + mediaId, "身份认证");
                        break;
                    case "2":
                        //通过认证
                        String isReview = SharedPreferences.getInstance().getString("isReview", "");
                        if (isReview.equals("2")) {
                            startWeb(H5UrlData.MEDIA_RENZHENGzhong + "&member=" + user_id + "&id=" + isMedia + "&personalId=" + mediaId, "身份认证");
                            SharedPreferences.getInstance().setString("isReview", "4");
                        } else {
                            startWeb(H5UrlData.MEDIA_yiRENZHENG + "&member=" + user_id + "&id=" + isMedia + "&personalId=" + mediaId, "");
                        }
                        break;
                    case "3":
                        //未通过认证
                        String isView = SharedPreferences.getInstance().getString("isView","");
                        if (TextUtils.isEmpty(isView)){
                            //没有看
                            startWeb(H5UrlData.MEDIA_RENZHENGzhong + "&member=" + user_id + "&id=" + isMedia + "&personalId=" + mediaId, "身份认证");
                            SharedPreferences.getInstance().setString("isView", "view");
                        }else {
                            startWeb(H5UrlData.MEDIA_RENZHENG + "&id=" + user_id, "身份认证");
                        }

                        break;
                    default:
                        break;
                }

                break;
            case R.id.task:
                startWeb(H5UrlData.TASK + user_id, "");
                break;
            case R.id.jifen_notes://积分记录
                startWeb(H5UrlData.JIFEN_NOTES + user_id, "积分记录");
                break;
            case R.id.rl_shiming: //实名认证
                if (!isShiming) {
                    startWeb(H5UrlData.SHIMING_RENZHENG + user_id, "");
                } else {
                    Toast.makeText(getContext(), "已实名认证", Toast.LENGTH_SHORT).show();
                    rl_shiming.setEnabled(false);
                }
                break;
            case R.id.server:
                intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + App.KF));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    @Override
    public void initData() {

        getPresenter().getUserModelResult(App.getId());
        getPresenter().getNewsInfo(App.getId());

    }

    @Override
    public MyPresenter createPresenter() {
        return new MyPresenter(getApp());
    }


    @Override // 个人信息
    public void onGetUserModelResult(UserModel userModelBean) {

        if ("000".equals(userModelBean.getCode())) {
            UserModel.UserInfo user = userModelBean.getData();
            if (user != null) {
                nick_name.setText(user.getNickName());
                Glide.with(getActivity())
                        .load(user.getHeadimgurl())
                        .into(iv_head);
                serverMobile.setText(user.getMobile());

                App.MyKF = user.getMobile();
                App.MyID = user.getId();

                SharedPreferences.getInstance().setString(Constants.SERVER_MOBILE, user.getMobile());
                SharedPreferences.getInstance().setString(Constants.USER_ID, user.getId());

                // 用户状态
                isMedia = user.getMedia();
                mediaId = user.getMediaId();
                bankCard = user.getBankcard();

                //0-未申请   1-正在审核中  2-已通过  3-未通过',
                if ("0".equals(user.getMedia())) {
                    media_renzheng.setText("点击去认证");
                } else if ("1".equals(user.getMedia())) {
                    media_renzheng.setText("正在审核中");
                } else if ("2".equals(user.getMedia())) {
                    String isReview = SharedPreferences.getInstance().getString("isReview", "");
                    if (isReview.equals("") || isReview.length() == 0) {
                        SharedPreferences.getInstance().setString("isReview", "2");
                    }
                    media_renzheng.setText("已通过");
                } else if ("3".equals(user.getMedia())) {
                    media_renzheng.setText("未通过认证");
                }
                jifen.setText("现有" + user.getBalance() + "积分");
                ////实名认证状态 0 未认证 1 已认证
                if ("0".equals(user.getRealName())) {
                    shiming.setText("未认证");
                    shiming.setTextColor(getResources().getColor(R.color.main_text_select));
                    isShiming = false;
                } else {
                    shiming.setText("已认证");
                    shiming.setTextColor(getResources().getColor(R.color.color88));
                    isShiming = true;
                }
            }
        }

    }

    @Override // 消息
    public void onGetNewsInfoResult(NewsInfo.NewsInfoResult newsInfo) {

        int info = newsInfo.getInfo();
        if (0 != info) {
            ic_spot.setVisibility(View.VISIBLE);
        } else {
            ic_spot.setVisibility(View.INVISIBLE);
        }

    }

    @Override // 重启时获取个人信息
    public void onGetUserModelResultResume(UserModel userModelBean) {

        if ("000".equals(userModelBean.getCode())) {
            UserModel.UserInfo user = userModelBean.getData();
            if (user != null) {
                nick_name.setText(user.getNickName());
                Glide.with(getActivity())
                        .load(user.getHeadimgurl())
                        .into(iv_head);
                serverMobile.setText(user.getMobile());

                App.MyKF = user.getMobile();
                App.MyID = user.getId();

                SharedPreferences.getInstance().setString(Constants.SERVER_MOBILE, user.getMobile());
                SharedPreferences.getInstance().setString(Constants.USER_ID, user.getId());
                isMedia = user.getMedia();
                mediaId = user.getMediaId();
                bankCard = user.getBankcard();
                //0-未申请   1-正在审核中  2-已通过  3-未通过',

                if ("0".equals(user.getMedia())) {
                    media_renzheng.setText("点击去认证");
                } else if ("1".equals(user.getMedia())) {
                    media_renzheng.setText("正在审核中");
                } else if ("2".equals(user.getMedia())) {
                    media_renzheng.setText("已通过");
                } else if ("3".equals(user.getMedia())) {
                    media_renzheng.setText("未通过认证");
                }
                jifen.setText("现有" + user.getBalance() + "积分");
                ////实名认证状态 0 未认证 1 已认证
                if ("0".equals(user.getRealName())) {
                    shiming.setText("未认证");
                    shiming.setTextColor(getResources().getColor(R.color.main_text_select));
                    isShiming = false;
                } else {
                    shiming.setText("已认证");
                    shiming.setTextColor(getResources().getColor(R.color.color88));
                    isShiming = true;
                }
            }
        }

    }

    private void showInfoDialog() {
        dialog = new MaterialDialog(getContext())
                .setTitle("提示")
                .setMessage("你还未进行实名认证，为了保护您的积分安全请认证。")
                .setPositiveButton("去认证", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        startWeb(H5UrlData.SHIMING_RENZHENG + user_id, "");
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

    public void startWeb(String url, String title) {
        Intent intent = new Intent(getContext(), WebViewActivity.class);
        intent.putExtra(WebViewActivity.URL_KEY, url);
        intent.putExtra(WebViewActivity.TITLE, title);
        startActivity(intent);
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void onCompleted() {

    }


    @Override // 重新获取一遍信息
    public void onResume() {
        super.onResume();
        getPresenter().getUserModelResultResume(App.getId());
        getPresenter().getNewsInfo(App.getId());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onError(Throwable e) {

        LogUtils.w(e);

    }

}
*/
