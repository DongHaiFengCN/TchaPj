package com.application.tchapj.my.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
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

import com.application.tchapj.App;
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
import com.application.tchapj.rxbus.ChangeAnswerEvent;
import com.application.tchapj.rxbus.RxBus;
import com.application.tchapj.utils.ToastUtil;
import com.application.tchapj.utils2.CircleImageView;
import com.application.tchapj.utils2.share.SharedPreferencesUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.king.base.util.LogUtils;
import com.king.base.util.StringUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import me.drakeet.materialdialog.MaterialDialog;
import rx.Subscription;
import rx.functions.Action1;

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


    Subscription mSubscription;
    Unbinder unbinder;

    private String user_id;
    private MaterialDialog dialog;

    private UserInfo user;

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
    public void initUI() {

        user_id = App.getId();

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

                if (user_id == null) {
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
                if (user_id == null) {
                    dialogs();
                } else {
                    Intent intent = new Intent(getContext(), WhbyMemberActivity.class);
                    startActivity(intent);

                }
            }
        });

        my_shenfen_rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (user_id == null) {
                    dialogs();
                } else {
                    Intent intent = new Intent(getContext(), IdentityActivity.class);

                    if (user.getLingState() == null) {
                        intent.putExtra("darenState", "0");
                    } else {
                        intent.putExtra("darenState", user.getLingState());
                    }

                    if (user.getFaState() == null) {
                        intent.putExtra("guanggaozhuState", "0");
                    } else {
                        intent.putExtra("guanggaozhuState", user.getFaState());
                        Log.e("达人状态", "url===" + user.getId());
                    }

                    if (user.getMrState() == null) {
                        intent.putExtra("mingrenState", "0");
                    } else {
                        intent.putExtra("mingrenState", user.getMrState());
                    }

                    if (user.getMtState() == null) {
                        intent.putExtra("meitiState", "0");
                    } else {
                        intent.putExtra("meitiState", user.getMtState());
                    }

                    if (user.getDyState() == null) {
                        intent.putExtra("dyState", "0");
                    } else {
                        intent.putExtra("dyState", user.getDyState());
                    }

                    if (user.getPyqState() == null) {
                        intent.putExtra("pyqState", "0");
                    } else {
                        intent.putExtra("pyqState", user.getPyqState());
                    }

                    if (user.getWbState() == null) {
                        intent.putExtra("wbState", "0");
                    } else {
                        intent.putExtra("wbState", user.getWbState());
                    }

                    if (user.getWsState() == null) {
                        intent.putExtra("wsState", "0");
                    } else {
                        intent.putExtra("wsState", user.getWsState());
                    }

                    startActivity(intent);

                }
            }
        });

        /*my_renzheng_rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(user_id==null){
                    dialogs();
                }else {
                    Intent intent = new Intent(getContext(), MicroNumberActivity.class);

                    startActivity(intent);
                }

            }
        });*/


        my_xiaohb_rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*FeedbackAPI.openFeedbackActivity(); // 意见反馈*/
                if (user_id == null) {
                    dialogs();
                } else {
                    Intent intent = new Intent(getContext(), DanmuActivity.class);

                    intent.putExtra("memberId", user.getId());
                    intent.putExtra("nickName", user.getNickName());

                    startActivity(intent);
                }

            }
        });

        my_system_rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (user_id == null) {
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

        // 添加的
        mSubscription = RxBus.getDefault().toObserverable(ChangeAnswerEvent.class)
                .subscribe(new Action1<ChangeAnswerEvent>() {
                    @Override
                    public void call(ChangeAnswerEvent changeAnswerEvent) {

                        if (changeAnswerEvent.getAnswer().equals("1")) {
                            getPresenter().getUserModelResult(App.getId());
                            getPresenter().getNewsInfo(App.getId());
                        }
                    }

                });

    }

    @Override
    public void initData() {
        getPresenter().getNewsInfo(App.getId());
    }

    @Override
    public MyPresenter createPresenter() {
        return new MyPresenter(getApp());
    }


    @Override // 个人信息
    public void onGetUserModelResult(UserModel userModelBean) {

        if ("000".equals(userModelBean.getCode())) {
            user = userModelBean.getData();

            App.TaskApplyId = user.getTaskApplyId();
            App.DyState = user.getDyState();
            App.PyqState = user.getPyqState();
            App.WbState = user.getWbState();
            App.WsState = user.getWsState();
            App.OtherState = user.getOtherState();

            App.alipay = user.getAlipay();

            App.LingTaskStatus = user.getLingState();

            if (user_id != null) {

                if(user.getIsAuthor() != null && "1".equals(user.getIsAuthor())){
                    my_whbyh_rl.setVisibility(View.VISIBLE);
                }else{
                    my_whbyh_rl.setVisibility(View.GONE);
                }

                my_top_name.setVisibility(View.VISIBLE);
                fragment_my_attentions_fans_ll.setVisibility(View.VISIBLE);
                my_logon_tv.setVisibility(View.GONE);

                if (SharedPreferencesUtils.getInstance().getNickName() == null) {
                    my_top_name.setText(user.getNickName());
                } else {
                    my_top_name.setText(SharedPreferencesUtils.getInstance().getNickName());
                }

                if (!StringUtils.isEmpty(user.getHeadimgurl())) {
                    Glide.with(getActivity())
                            .asBitmap()
                            .apply(headImgOptions)
                            .load(user.getHeadimgurl())
                            .into(new BitmapImageViewTarget(my_top_iv) {
                                @Override
                                protected void setResource(Bitmap resource) {
                                    RoundedBitmapDrawable circularBitmapDrawable =
                                            RoundedBitmapDrawableFactory.
                                                    create(getActivity().getResources(), resource);
                                    //circularBitmapDrawable.setCornerRadius(8); // 圆角
                                    my_top_iv.setImageDrawable(circularBitmapDrawable);
                                }
                            });
                } else {
                    Glide.with(getActivity()).load(R.mipmap.ic_media_head_default).into(my_top_iv);
                }

                if (StringUtils.isEmpty(user.getAttentions())) {
                    fragment_my_attentions_tv.setText("关注：0");
                } else {
                    fragment_my_attentions_tv.setText("关注：" + user.getAttentions());
                }

                if (StringUtils.isEmpty(user.getFans())) {
                    fragment_my_fans_tv.setText("粉丝：0");
                } else {
                    fragment_my_fans_tv.setText("粉丝：" + user.getFans());
                }


                if (SharedPreferencesUtils.getInstance().getUserInfo().getSex() == null) {
                    if (user.getSex().equals("男")) {
                        my_top_sex.setVisibility(View.VISIBLE);
                        my_top_sex2.setVisibility(View.GONE);
                        my_top_sex.setBackgroundResource(R.mipmap.sex);

                    } else {
                        my_top_sex.setVisibility(View.GONE);
                        my_top_sex2.setVisibility(View.VISIBLE);
                        my_top_sex2.setBackgroundResource(R.mipmap.sex2);
                    }
                } else {
                    if (SharedPreferencesUtils.getInstance().getUserInfo().getSex().equals("男")) {
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
    public void onResume() {
        super.onResume();
        getPresenter().getUserModelResult(App.getId());//刷新个人信息
        getPresenter().getNewsInfo(App.getId());
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
            public void onClick(DialogInterface dialog, int whichButton) {
                /*BmobUpdateAgent.setDefault();
                mActivity.finish();
                System.exit(0);*/

                Intent intent = new Intent(getContext(), LoginMainActivity.class);

                    /*intent.putExtra("id", user.getId());
                    intent.putExtra("nickName", user.getNickName());
                    intent.putExtra("sex", user.getSex());
                    intent.putExtra("headimgurl", user.getHeadimgurl());
*/
                startActivity(intent);
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                dialog.dismiss();
            }
        });

        builder.create().show();
    }

    private void dialogs2() {

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage("请绑定支付宝");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {

                Intent intent = new Intent(getContext(), MyjibenActivity.class);
                startActivity(intent);
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
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
                if (user_id == null) {
                    dialogs();
                } else if (App.alipay.equals("0")) {
                    dialogs2();
                } else {
                    Intent intent = new Intent(getContext(), MyMoneyActivity.class);
                    intent.putExtra("headimgurl", user.getHeadimgurl());
                    startActivity(intent);
                }
                break;
            case R.id.fragment_my_content_manager_ll:
                if (user_id == null) {
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
                if (user_id == null) {
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
        }
    }

}
