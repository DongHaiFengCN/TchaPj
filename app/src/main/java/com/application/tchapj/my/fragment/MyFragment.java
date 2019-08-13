package com.application.tchapj.my.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.application.tchapj.DataManager;
import com.application.tchapj.H5UrlData;
import com.application.tchapj.R;
import com.application.tchapj.WebViewActivity;
import com.application.tchapj.base.BaseMvpFragment;
import com.application.tchapj.bean.UserInfo;
import com.application.tchapj.login.activity.LoginMainActivity;
import com.application.tchapj.main.bean.NewsInfo;
import com.application.tchapj.my.activity.AttentionListActivity;

import com.application.tchapj.my.activity.ContactActivity;
import com.application.tchapj.my.activity.ContentManagerActivity;
import com.application.tchapj.my.activity.DanmuActivity;
import com.application.tchapj.my.activity.FansListActivity;
import com.application.tchapj.my.activity.IdentityActivity;
import com.application.tchapj.my.activity.MyMoneyActivity;
import com.application.tchapj.my.activity.MyjibenActivity;
import com.application.tchapj.my.activity.SettingActivity;
import com.application.tchapj.my.activity.TaskAnalysisActivity;
import com.application.tchapj.my.activity.WhbyMemberActivity;
import com.application.tchapj.my.bean.UserModel;
import com.application.tchapj.my.presenter.MyPresenter;
import com.application.tchapj.my.view.IMyView;
import com.application.tchapj.utils.ToastUtil;
import com.application.tchapj.utils2.CircleImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.king.base.util.LogUtils;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import me.drakeet.materialdialog.MaterialDialog;
import rx.Subscription;

import static com.application.tchapj.DataManager.getDataManager;

// 我的页Fragment
public class MyFragment extends BaseMvpFragment<IMyView, MyPresenter> implements IMyView {

    @BindView(R.id.my_top_iv)
    CircleImageView my_top_iv;
    @BindView(R.id.my_top_name)
    TextView my_top_name;
    @BindView(R.id.my_top_sex)
    ImageView my_top_sex;
    @BindView(R.id.my_top_sex2)
    ImageView my_top_sex2;
    @BindView(R.id.my_logon_tv)
    TextView my_logon_tv;

    @BindView(R.id.my_jiben_rl)
    RelativeLayout my_jiben_rl;
    @BindView(R.id.my_whbyh_rl)
    RelativeLayout my_whbyh_rl;
    @BindView(R.id.my_shenfen_rl)
    RelativeLayout my_shenfen_rl;
    @BindView(R.id.my_renzheng_rl)
    RelativeLayout my_renzheng_rl;
    @BindView(R.id.my_xiaohb_rl)
    RelativeLayout my_xiaohb_rl;

    @BindView(R.id.my_system_rl)
    RelativeLayout my_system_rl;
    @BindView(R.id.my_contact_rl)
    RelativeLayout my_contact_rl;

    @BindView(R.id.my_jiben_iv)
    ImageView my_jiben_iv;
    @BindView(R.id.my_renzheng_iv)
    ImageView my_renzheng_iv;
    @BindView(R.id.my_xiaohb_iv)
    ImageView my_xiaohb_iv;
    @BindView(R.id.my_system_iv)
    ImageView my_system_iv;
    @BindView(R.id.my_contact)
    ImageView my_contact;
    @BindView(R.id.fragment_my_attentions_fans_ll)
    LinearLayout fragment_my_attentions_fans_ll;
    @BindView(R.id.fragment_my_attentions_tv)
    TextView fragment_my_attentions_tv;
    @BindView(R.id.fragment_my_fans_tv)
    TextView fragment_my_fans_tv;

    Unbinder unbinder;

    private String user_id;
    private MaterialDialog dialog;


    RequestOptions headImgOptions = new RequestOptions()
            .centerCrop()
            .priority(Priority.HIGH)
            .placeholder(R.mipmap.ic_media_head_default)
            .error(R.mipmap.ic_media_head_default)
            .diskCacheStrategy(DiskCacheStrategy.NONE);

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
        return R.layout.fragment_my;
    }

    @Override
    public void onResume() {
        super.onResume();

        String nickName = getDataManager().quickGetMetaData(R.string.nickName, String.class);
        String attentions = String.valueOf(getDataManager().quickGetMetaData(R.string.attentions, Integer.class));
        String fans = String.valueOf(getDataManager().quickGetMetaData(R.string.fans, Integer.class));
        String headUrl = getDataManager().quickGetMetaData(R.string.headimgurl, String.class);
        user_id = getDataManager().quickGetMetaData(R.string.id, String.class);


        Log.e("DOAING","NICK_NAME"+nickName);

        if("".equals(nickName)&&"".equals(user_id)){
            my_logon_tv.setText("点击登录");
            my_logon_tv.setClickable(true);

        }else if(!"".equals(nickName)){

            my_logon_tv.setText(nickName);

            my_logon_tv.setClickable(false);

        } else {
            my_logon_tv.setText("游客登录");
            my_logon_tv.setClickable(false);
        }

        if (!"".equals(attentions)) {
            fragment_my_attentions_tv.setText("关注：" + attentions);
        } else {
            fragment_my_attentions_tv.setText("关注：0");
        }

        if (!"".equals(fans)) {
            fragment_my_fans_tv.setText("粉丝：" + fans);
        } else {
            fragment_my_fans_tv.setText("粉丝：0");
        }
        if ("".equals(headUrl)) {
            Glide.with(getActivity()).load(R.mipmap.ic_media_head_default).into(my_top_iv);
        } else {

            Glide.with(getActivity())
                    .asBitmap()
                    .apply(headImgOptions)
                    .load(headUrl)
                    .into(new BitmapImageViewTarget(my_top_iv) {
                        @Override
                        protected void setResource(Bitmap resource) {
                            RoundedBitmapDrawable circularBitmapDrawable =
                                    RoundedBitmapDrawableFactory.
                                            create(getActivity().getResources(), resource);
                            my_top_iv.setImageDrawable(circularBitmapDrawable);
                        }
                    });


        }

        String sex = getDataManager().quickGetMetaData(R.string.sex, String.class);
        if (!"".equals(sex)) {
            if ("m".equals(sex)) {
                my_top_sex.setVisibility(View.VISIBLE);
                my_top_sex2.setVisibility(View.GONE);
                my_top_sex.setBackgroundResource(R.mipmap.sex);

            } else {
                my_top_sex.setVisibility(View.GONE);
                my_top_sex2.setVisibility(View.VISIBLE);
                my_top_sex2.setBackgroundResource(R.mipmap.sex2);
            }
        }
    }



    @Override
    public void initUI() {


        my_logon_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), LoginMainActivity.class);
                startActivity(intent);
            }
        });

        my_jiben_rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (user_id == null || "".equals(user_id)) {
                    dialogs();
                } else {
                    Intent intent = new Intent(getContext(), MyjibenActivity.class);
                    startActivity(intent);
                }

            }
        });

        my_whbyh_rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (user_id == null|| "".equals(user_id)) {
                    dialogs();
                } else {
                    Intent intent = new Intent(getContext(), WhbyMemberActivity.class);
                    startActivity(intent);

                }
            }
        });

        /**
         * 身份管理启动方法
         */
        my_shenfen_rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.e("DOAING",user_id);

                if (user_id == null|| "".equals(user_id)) {
                    dialogs();
                } else {
                    Intent intent = new Intent(getContext(), IdentityActivity.class);
                    startActivity(intent);

                }
            }
        });


        my_xiaohb_rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (user_id == null || "".equals(user_id)) {
                    dialogs();
                } else {
                    Intent intent = new Intent(getContext(), DanmuActivity.class);

                    intent.putExtra("memberId", user_id);
                    intent.putExtra("nickName", getDataManager().quickGetMetaData(R.string.nickName, String.class));

                    startActivity(intent);
                }

            }
        });

        my_system_rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (user_id == null || "".equals(user_id)) {
                    dialogs();
                } else {
                    Intent intent = new Intent(getContext(), SettingActivity.class);

                    startActivity(intent);
                }

            }
        });

        my_contact_rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getContext(), ContactActivity.class);

                startActivity(intent);
            }
        });


    }

    @Override
    public void initData() {
    }

    @Override
    public MyPresenter createPresenter() {
        return new MyPresenter(getApp());
    }


    @Override // 个人信息
    public void onGetUserModelResult(UserModel userModelBean) {

        if ("000".equals(userModelBean.getCode())) {
            //user = userModelBean.getData();

            if (user_id != null) {

                if ("1".equals(getDataManager().quickGetMetaData(R.string.authState, String.class))) {
                    my_whbyh_rl.setVisibility(View.VISIBLE);
                } else {
                    my_whbyh_rl.setVisibility(View.GONE);
                }

                my_top_name.setVisibility(View.VISIBLE);
                fragment_my_attentions_fans_ll.setVisibility(View.VISIBLE);
                my_logon_tv.setVisibility(View.GONE);
                my_top_name.setText(getDataManager().quickGetMetaData(R.string.nickName, String.class));

                String headUrl = getDataManager().quickGetMetaData(R.string.headimgurl, String.class);
                if ("".equals(headUrl)) {
                    Glide.with(getActivity()).load(R.mipmap.ic_media_head_default).into(my_top_iv);
                } else {

                    Glide.with(getActivity())
                            .asBitmap()
                            .apply(headImgOptions)
                            .load(headUrl)
                            .into(new BitmapImageViewTarget(my_top_iv) {
                                @Override
                                protected void setResource(Bitmap resource) {
                                    RoundedBitmapDrawable circularBitmapDrawable =
                                            RoundedBitmapDrawableFactory.
                                                    create(getActivity().getResources(), resource);
                                    my_top_iv.setImageDrawable(circularBitmapDrawable);
                                }
                            });
                }

                String attentions = getDataManager().quickGetMetaData(R.string.attentions, String.class);
                if ("".equals(attentions)) {
                    fragment_my_attentions_tv.setText("关注：0");
                } else {
                    fragment_my_attentions_tv.setText("关注：" + attentions);
                }


                String sex = getDataManager().quickGetMetaData(R.string.sex, String.class);
                if ("".equals(sex)) {

                } else {
                    if ("".equals(sex)) {
                        my_top_sex.setVisibility(View.VISIBLE);
                        my_top_sex2.setVisibility(View.GONE);
                        my_top_sex.setBackgroundResource(R.mipmap.sex);

                    } else {
                        my_top_sex.setVisibility(View.GONE);
                        my_top_sex2.setVisibility(View.VISIBLE);
                        my_top_sex2.setBackgroundResource(R.mipmap.sex2);
                    }
                }

            } else {

                my_top_name.setVisibility(View.GONE);
                fragment_my_attentions_fans_ll.setVisibility(View.GONE);
                my_top_sex.setVisibility(View.GONE);
                my_top_sex2.setVisibility(View.GONE);
                my_logon_tv.setVisibility(View.VISIBLE);
            }

        }
    }

    @Override // 消息
    public void onGetNewsInfoResult(NewsInfo.NewsInfoResult newsInfo) {

    }

    @Override // 重启时获取个人信息
    public void onGetUserModelResultResume(UserModel userModelBean) {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void onCompleted() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();


    }

    @Override
    public void onError(Throwable e) {

        LogUtils.w(e);

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

    private void dialogs() {

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage("请先进行登录");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int whichButton) {


                Intent intent = new Intent(getContext(), LoginMainActivity.class);

                startActivity(intent);
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int whichButton) {
                dialog.dismiss();
            }
        });

        builder.create().show();
    }

    public void startWeb(String url, String title) {

        Intent intent = new Intent(getContext(), WebViewActivity.class);
        intent.putExtra(WebViewActivity.URL_KEY, url);
        intent.putExtra(WebViewActivity.TITLE, title);
        startActivity(intent);
    }

    @OnClick({R.id.fragment_my_wallet_ll, R.id.fragment_my_content_manager_ll, R.id.fragment_my_content_analysis_ll, R.id.fragment_my_task_analysis_ll,
            R.id.fragment_my_attentions_tv, R.id.fragment_my_fans_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fragment_my_wallet_ll:

                if (user_id == null||"".equals(user_id)) {
                    dialogs();
                } else {
                    Intent intent = new Intent(getContext(), MyMoneyActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.fragment_my_content_manager_ll:
                if (user_id == null||"".equals(user_id)) {
                    dialogs();
                } else {
                    Intent intent = new Intent(getContext(), ContentManagerActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.fragment_my_content_analysis_ll:
                ToastUtil.show(getActivity(), "内容分析");
                break;
            case R.id.fragment_my_task_analysis_ll:
                if (user_id == null||"".equals(user_id)) {
                    dialogs();
                } else {
                    Intent intent = new Intent(getContext(), TaskAnalysisActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.fragment_my_attentions_tv:
                //关注
                AttentionListActivity.start(getActivity());
                break;
            case R.id.fragment_my_fans_tv:
                //粉丝
                FansListActivity.start(getActivity());
                break;

            default:
                break;
        }
    }

}
