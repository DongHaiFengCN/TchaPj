package com.application.tchapj.my.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.application.tchapj.App;
import com.application.tchapj.DataManager;
import com.application.tchapj.R;
import com.application.tchapj.base.BaseMvpActivity;
import com.application.tchapj.bean.UserInfo;
import com.application.tchapj.main.bean.NewsInfo;
import com.application.tchapj.my.bean.UserModel;
import com.application.tchapj.my.presenter.MyPresenter;
import com.application.tchapj.my.view.IMyView;
import com.application.tchapj.utils2.qiniu.utils.StringUtils;
import com.application.tchapj.utils2.share.SharedPreferencesUtils;
import com.application.tchapj.widiget.KV;
import com.application.tchapj.widiget.ToolbarHelper;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.application.tchapj.DataManager.getDataManager;

// 达人身份认证
public class DarenActivity extends BaseMvpActivity<IMyView, MyPresenter> implements IMyView {

    @BindView(R.id.daren_wanshan_tv)
    TextView daren_wanshan_tv;
    @BindView(R.id.daren_dyadd_ll)
    LinearLayout daren_dyadd_ll;
    @BindView(R.id.daren_pyadd_ll)
    LinearLayout daren_pyadd_ll;
    @BindView(R.id.daren_wbadd_ll)
    LinearLayout daren_wbadd_ll;
    @BindView(R.id.daren_wsadd_ll)
    LinearLayout daren_wsadd_ll;
    @BindView(R.id.daren_otheradd_ll)
    LinearLayout daren_otheradd_ll;
    @BindView(R.id.daren_media_resources_wechat_iv)
    ImageView mediaResourcesWechatIv;
    @BindView(R.id.daren_media_resources_wb_iv)
    ImageView mediaResourcesWbIv;
    @BindView(R.id.daren_media_resources_dy_iv)
    ImageView mediaResourcesDyIv;
    @BindView(R.id.daren_media_resources_ws_iv)
    ImageView mediaResourcesWsIv;
    @BindView(R.id.daren_media_resources_other_iv)
    ImageView mediaResourcesOtherIv;
    @BindView(R.id.daren_media_resources_state_wechat_tv)
    TextView mediaResourcesStateWechatTv;
    @BindView(R.id.daren_media_resources_state_wb_tv)
    TextView mediaResourcesStateWbTv;
    @BindView(R.id.daren_media_resources_state_dy_tv)
    TextView mediaResourcesStateDyTv;
    @BindView(R.id.daren_media_resources_state_ws_tv)
    TextView mediaResourcesStateWsTv;
    @BindView(R.id.daren_media_resources_state_other_tv)
    TextView mediaResourcesStateOtherTv;

    private KV kv;                 // 保存缓存数据的对象
    private String taskApplyId = "";

    private String dyTaskStatus;
    private String pyqTaskStatus;
    private String wbTaskStatus;
    @BindView(R.id.add_tvdy)
    TextView add_tvdy;
    @BindView(R.id.add_tvpyq)
    TextView add_tvpyq;
    @BindView(R.id.add_tvwb)
    TextView add_tvwb;
    @BindView(R.id.add_tvws)
    TextView add_tvws;
    @BindView(R.id.add_tv_other)
    TextView add_tv_other;
    @BindView(R.id.daren_refuse_tv)
    TextView refuseTv;//拒绝原因


    private UserInfo user;

    @Override
    protected void initToolbar(ToolbarHelper toolbarHelper) {

        toolbarHelper.setTitle("达人身份激活");
    }

    @Override
    public int getRootViewId() {
        return R.layout.activity_daren;
    }

    @Override
    public void initUI() {


    }

    @Override
    protected void onResume() {
        super.onResume();

        DataManager.getDataManager().disposeMember(new DataManager.UpDataListener() {
            @Override
            public void upData(boolean getDataSuccess) {

                if(getDataSuccess){


                }

            }
        });

        initViewData();



    }

    private void initViewData() {

        Intent intent = new Intent(DarenActivity.this, DarenDataOneActivity.class);
        startActivity(intent);

        App.LingTaskStatus = getDataManager().quickGetMetaData(R.string.lingState, String.class);


        boolean dyBool = false,
                pyqBool = false,
                wbBool = false,
                wsBool = false,
                otherBool = false;

        if (App.LingTaskStatus.equals("0")
                || "".equals(getDataManager().quickGetMetaData(R.string.lingState, String.class))) {
            // lingState 0-未申请   1-正在审核中  2-已通过  3-未通过
            //未申请达人身份
            setMediaResourcesStateView("", add_tvpyq, mediaResourcesStateWechatTv);
            setMediaResourcesStateView("", add_tvwb, mediaResourcesStateWbTv);
            setMediaResourcesStateView("", add_tvdy, mediaResourcesStateDyTv);
            setMediaResourcesStateView("", add_tvws, mediaResourcesStateWsTv);
            setMediaResourcesStateView("", add_tv_other, mediaResourcesStateOtherTv);

            daren_dyadd_ll.setOnClickListener(applyDarenTipsClick);
            daren_pyadd_ll.setOnClickListener(applyDarenTipsClick);
            daren_wbadd_ll.setOnClickListener(applyDarenTipsClick);
            daren_wsadd_ll.setOnClickListener(applyDarenTipsClick);
            daren_otheradd_ll.setOnClickListener(applyDarenTipsClick);
        } else {
            //0正在审核中，1已通过，2已驳回  null就是未添加

            if (StringUtils.isNullOrEmpty(App.PyqState)) {
                mediaResourcesWechatIv.setImageResource(R.mipmap.ic_py_select_normal);
                pyqBool = true;
            } else if (App.PyqState.equals("0")) {
                mediaResourcesWechatIv.setImageResource(R.mipmap.ic_py_select_normal);
            } else if (App.PyqState.equals("1")) {
                pyqBool = true;
                mediaResourcesWechatIv.setImageResource(R.mipmap.ic_py_select);
            } else if (App.PyqState.equals("2")) {
                mediaResourcesWechatIv.setImageResource(R.mipmap.ic_py_select_normal);
                pyqBool = true;
            }
            setMediaResourcesStateView(App.PyqState, add_tvpyq, mediaResourcesStateWechatTv);

            if (StringUtils.isNullOrEmpty(App.WbState)) {
                mediaResourcesWbIv.setImageResource(R.mipmap.ic_wb_select_normal);
                wbBool = true;
            } else if (App.WbState.equals("0")) {
                mediaResourcesWbIv.setImageResource(R.mipmap.ic_wb_select_normal);
            } else if (App.WbState.equals("1")) {
                wbBool = true;
                mediaResourcesWbIv.setImageResource(R.mipmap.ic_wb_select);
            } else if (App.WbState.equals("2")) {
                mediaResourcesWbIv.setImageResource(R.mipmap.ic_wb_select_normal);
                wbBool = true;
            }
            setMediaResourcesStateView(App.WbState, add_tvwb, mediaResourcesStateWbTv);

            if (StringUtils.isNullOrEmpty(App.DyState)) {
                mediaResourcesDyIv.setImageResource(R.mipmap.ic_dy_select_normal);
                dyBool = true;
            } else if (App.DyState.equals("0")) {
                mediaResourcesDyIv.setImageResource(R.mipmap.ic_dy_select_normal);
            } else if (App.DyState.equals("1")) {
                dyBool = true;
                mediaResourcesDyIv.setImageResource(R.mipmap.ic_dy_select);
            } else if (App.DyState.equals("2")) {
                mediaResourcesDyIv.setImageResource(R.mipmap.ic_dy_select_normal);
                dyBool = true;
            }
            setMediaResourcesStateView(App.DyState, add_tvdy, mediaResourcesStateDyTv);


            if (StringUtils.isNullOrEmpty(App.WsState)) {
                mediaResourcesWsIv.setImageResource(R.mipmap.ic_ws_select_normal);
                wsBool = true;
            } else if (App.WsState.equals("0")) {
                mediaResourcesWsIv.setImageResource(R.mipmap.ic_ws_select_normal);
            } else if (App.WsState.equals("1")) {
                wsBool = true;
                mediaResourcesWsIv.setImageResource(R.mipmap.ic_ws_select);
            } else if (App.WsState.equals("2")) {
                mediaResourcesWsIv.setImageResource(R.mipmap.ic_ws_select_normal);
                wsBool = true;
            }
            setMediaResourcesStateView(App.WsState, add_tvws, mediaResourcesStateWsTv);

            if (StringUtils.isNullOrEmpty(App.OtherState)) {
                mediaResourcesOtherIv.setImageResource(R.mipmap.ic_other_select_normal);
                otherBool = true;
            } else if (App.OtherState.equals("0")) {
                mediaResourcesOtherIv.setImageResource(R.mipmap.ic_other_select_normal);
            } else if (App.OtherState.equals("1")) {
                otherBool = true;
                mediaResourcesOtherIv.setImageResource(R.mipmap.ic_other_select);
            } else if (App.OtherState.equals("2")) {
                mediaResourcesOtherIv.setImageResource(R.mipmap.ic_other_select_normal);
                otherBool = true;
            }
            setMediaResourcesStateView(App.OtherState, add_tv_other, mediaResourcesStateOtherTv);


            if (dyBool) {
                daren_dyadd_ll.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(DarenActivity.this, DarenDyAddActivity.class);
                        intent.putExtra("TaskApplyId", taskApplyId);
                        startActivity(intent);
                    }
                });
            } else {
                daren_dyadd_ll.setOnClickListener(null);
            }

            if (pyqBool) {
                daren_pyadd_ll.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(DarenActivity.this, DarenPyqAddActivity.class);
                        startActivity(intent);
                    }
                });
            } else {
                daren_pyadd_ll.setOnClickListener(null);
            }

            if (wbBool) {
                daren_wbadd_ll.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(DarenActivity.this, DarenWbAddActivity.class);
                        startActivity(intent);
                    }
                });
            } else {
                daren_wbadd_ll.setOnClickListener(null);
            }

            if (wsBool) {
                daren_wsadd_ll.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(DarenActivity.this, DarenWsAddActivity.class);
                        startActivity(intent);
                    }
                });
            } else {
                daren_wsadd_ll.setOnClickListener(null);
            }

            if (otherBool) {
                daren_otheradd_ll.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(DarenActivity.this, DarenOtherAddActivity.class);
                        startActivity(intent);
                    }
                });
            } else {
                daren_otheradd_ll.setOnClickListener(null);
            }

        }
    }

    //设置媒体资源后面点击按钮，根据不同状态显示的样式
    private void setMediaResourcesStateView(String mediaResourceState, TextView grayTextView, TextView textView) {
        //mediaResourceState媒体资源状态，0审核中，1已通过，2审核失败  null就是未添加
        if (!TextUtils.isEmpty(mediaResourceState)) {
            switch (mediaResourceState) {
                case "0":
                    textView.setVisibility(View.GONE);
                    grayTextView.setText("审核中");
                    break;

                case "1":
                    textView.setVisibility(View.VISIBLE);
                    grayTextView.setText("");
                    textView.setBackgroundResource(R.mipmap.bg_daren_media_resourse_success);
                    textView.setText("修改信息");
                    textView.setTextColor(ContextCompat.getColor(this, R.color.white));
                    break;

                case "2":
                    textView.setVisibility(View.VISIBLE);
                    grayTextView.setText("审核失败");
                    textView.setBackgroundResource(R.mipmap.bg_daren_media_resourse_state);
                    textView.setText("重新提交");
                    textView.setTextColor(ContextCompat.getColor(this, R.color.daren_media_resourse_state_tv));
                    break;

            }
        } else {
            textView.setVisibility(View.VISIBLE);
            grayTextView.setText("");
            textView.setBackgroundResource(R.mipmap.bg_daren_media_resourse_state);
            textView.setText("添加信息");
            textView.setTextColor(ContextCompat.getColor(this, R.color.daren_media_resourse_state_tv));
        }


    }

    View.OnClickListener applyDarenTipsClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            showToast("请先申请达人身份");
        }
    };

    @Override
    public void initData() {

    }

    @NonNull
    @Override
    public MyPresenter createPresenter() {
        return new MyPresenter(getApp());
    }

    @Override // 个人信息
    public void onGetUserModelResult(UserModel userModelBean) {

        if ("000".equals(userModelBean.getCode())) {
            user = userModelBean.getData();
            SharedPreferencesUtils.getInstance().setUserInfo(user);
            taskApplyId = user.getTaskApplyId();

            App.LingTaskStatus = user.getLingState();

            App.DyState = user.getDyState();
            App.PyqState = user.getPyqState();
            App.WbState = user.getWbState();
            App.WsState = user.getWsState();
            App.OtherState = user.getOtherState();


            boolean isClick = false;
            if (App.LingTaskStatus.equals("0")) {
                daren_wanshan_tv.setText("申请达人");
                isClick = true;
                refuseTv.setVisibility(View.GONE);
            } else if (App.LingTaskStatus.equals("1")) {
                daren_wanshan_tv.setText("审核中");
                refuseTv.setVisibility(View.GONE);
            } else if (App.LingTaskStatus.equals("2")) {
                daren_wanshan_tv.setText("已通过");
                refuseTv.setVisibility(View.GONE);
            } else {
                daren_wanshan_tv.setText("申请未通过");
                isClick = true;
                if (!TextUtils.isEmpty(user.getRefusal())) {
                    refuseTv.setVisibility(View.VISIBLE);
                    refuseTv.setText("拒绝原因：" + user.getRefusal());
                } else {
                    refuseTv.setVisibility(View.GONE);
                }
            }

            if (isClick) {
                daren_wanshan_tv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(DarenActivity.this, DarenDataOneActivity.class);
                        startActivity(intent);
                    }
                });

            } else {
                daren_wanshan_tv.setOnClickListener(null);
            }





        }

    }

    @Override
    public void onGetUserModelResultResume(UserModel userModelBean) {

    }

    @Override
    public void onGetNewsInfoResult(NewsInfo.NewsInfoResult newsInfo) {

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

    private void dialogs2() {

        AlertDialog.Builder builder = new AlertDialog.Builder(DarenActivity.this);
        builder.setMessage("个人资料已经完善");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {

                dialog.dismiss();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                dialog.dismiss();
            }
        });

        builder.create().show();
    }

}
