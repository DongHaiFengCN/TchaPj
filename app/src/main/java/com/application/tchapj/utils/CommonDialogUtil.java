package com.application.tchapj.utils;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.application.tchapj.App;
import com.application.tchapj.Constants;
import com.application.tchapj.H5UrlData;
import com.application.tchapj.R;
import com.application.tchapj.WebViewActivity;
import com.application.tchapj.consultation.fragment.ConsultationFragment;
import com.application.tchapj.login.activity.LoginMainActivity;
import com.application.tchapj.my.activity.IdentityActivity;
import com.application.tchapj.utils2.share.SharedPreferencesUtils;
import com.application.tchapj.widiget.DensityUtil;

public class CommonDialogUtil {

    public static void showLoginDialog(final Context context) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage("请先进行登录");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {

                Intent intent = new Intent(context, LoginMainActivity.class);

                context.startActivity(intent);
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                dialog.dismiss();
            }
        });

        builder.create().show();
    }

    public static void identityDialog(final Context context, String contentMsg) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(contentMsg);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {

                IdentityActivity.start(context, SharedPreferencesUtils.getInstance().getUserInfo());

            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                dialog.dismiss();
            }
        });

        builder.create().show();
    }


    //确认开通 微呼百应号
    public static void showOpenWhbyNumberDialog(final Context context, final ConsultationFragment.ConsultationFragmentOpenDialogClickListener listener) {
        final Dialog dialog = new Dialog(context,R.style.DialogStyle);
        View view = LayoutInflater.from(context).inflate(R.layout.layout_dialog_consultation_verify_identity, null);
        dialog.setContentView(view);
        final RadioButton radioButton = view.findViewById(R.id.dialog_consultation_verify_identity_rdo_btn);
        final TextView confirmTv = view.findViewById(R.id.dialog_consultation_verify_identity_confirm_tv);
        LinearLayout rdoLl = view.findViewById(R.id.dialog_consultation_verify_identity_rdo_ll);
        TextView tipTv = view.findViewById(R.id.dialog_consultation_verify_identity_tip_tv);
        rdoLl.setVisibility(View.VISIBLE);
        tipTv.setVisibility(View.GONE);
        confirmTv.setBackgroundResource(R.drawable.bg_dialog_consultation_verify_identity_confirm);
        confirmTv.setText("确认开通");

        view.findViewById(R.id.dialog_consultation_verify_identity_fl).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        //协议
        view.findViewById(R.id.dialog_consultation_verify_identity_agreement_tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.agreementClick();

            }
        });

        radioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    confirmTv.setBackgroundResource(R.drawable.bg_dialog_consultation_verify_identity_confirm);
                }else{
                    confirmTv.setBackgroundResource(R.drawable.bg_dialog_consultation_verify_identity_confirm_normal);
                }
            }
        });

        confirmTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                if(radioButton.isChecked()){
                    listener.confirmClick();
                }

            }
        });


        Window dialogWindow = dialog.getWindow();
        WindowManager.LayoutParams layoutParams = dialogWindow.getAttributes();
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);

        layoutParams.width = wm.getDefaultDisplay().getWidth() - DensityUtil.dpTopx(context, 25);
        dialogWindow.setAttributes(layoutParams);
        dialog.show();

    }

    //身份激活
    public static void showIdentityActivateDialog(final Context context, final ConsultationFragment.ConsultationFragmentActivateDialogClickListener listener) {


        final Dialog dialog = new Dialog(context,R.style.DialogStyle);
        View view = LayoutInflater.from(context).inflate(R.layout.layout_dialog_consultation_verify_identity, null);
        dialog.setContentView(view);
        final TextView confirmTv = view.findViewById(R.id.dialog_consultation_verify_identity_confirm_tv);

        LinearLayout rdoLl = view.findViewById(R.id.dialog_consultation_verify_identity_rdo_ll);
        TextView tipTv = view.findViewById(R.id.dialog_consultation_verify_identity_tip_tv);
        rdoLl.setVisibility(View.GONE);
        tipTv.setVisibility(View.VISIBLE);
        confirmTv.setBackgroundResource(R.drawable.bg_dialog_consultation_verify_identity_confirm);
        confirmTv.setText("身份激活");

        view.findViewById(R.id.dialog_consultation_verify_identity_fl).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });


        confirmTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                listener.goActivateClick();

            }
        });


        Window dialogWindow = dialog.getWindow();
        WindowManager.LayoutParams layoutParams = dialogWindow.getAttributes();
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);

        layoutParams.width = wm.getDefaultDisplay().getWidth() - DensityUtil.dpTopx(context, 25);
        dialogWindow.setAttributes(layoutParams);
        dialog.show();

    }

    //更新提示
    public static void showUpdateApkDialog(final Context context, final UpdateManager.UpdateMangerDialogClickListener listener) {
        final Dialog dialog = new Dialog(context,R.style.DialogStyle);
        View view = LayoutInflater.from(context).inflate(R.layout.layout_dialog_update_manager, null);
        dialog.setContentView(view);
        final TextView cancelTv = view.findViewById(R.id.dialog_update_manager_cancel);
        final TextView confirmTv = view.findViewById(R.id.dialog_update_manager_confirm);


        cancelTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                listener.cancelmClick();
            }
        });

        confirmTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                listener.confirmClick();

            }
        });


        Window dialogWindow = dialog.getWindow();
        WindowManager.LayoutParams layoutParams = dialogWindow.getAttributes();
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);

        layoutParams.width = wm.getDefaultDisplay().getWidth();
        dialogWindow.setAttributes(layoutParams);
        dialog.show();

    }

    //内容管理的资讯删除
    public static void consultationDeleteDialog(final Context context, DialogInterface.OnClickListener confirmListener) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage("确定删除吗？");
        builder.setPositiveButton("确定", confirmListener);
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                dialog.dismiss();
            }
        });

        builder.create().show();
    }

    //微信好友、微信朋友圈、QQ分享
    public static void showWechatQQShareDialog(final Context context, final CommonDialogListenerUtil.WechatShareDialogListener listener) {


        final Dialog dialog = new Dialog(context,R.style.DialogStyle);
        View view = LayoutInflater.from(context).inflate(R.layout.layout_dialog_wechat_share, null);
        dialog.setContentView(view);
        final LinearLayout wechatLl = view.findViewById(R.id.dialog_wechat_share_wechat_ll);
        final LinearLayout wechatMomentLl = view.findViewById(R.id.dialog_wechat_share_wechat_moments_ll);
        final LinearLayout qqLl = view.findViewById(R.id.dialog_qq_share_wechat_moments_ll);



        wechatLl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.wechat();
                dialog.dismiss();
            }
        });


        wechatMomentLl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.wechatMoments();
                dialog.dismiss();

            }
        });

        qqLl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.qq();
                dialog.dismiss();

            }
        });

        Window window = dialog.getWindow();
        WindowManager.LayoutParams dialogParams = window.getAttributes();
        dialogParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        dialogParams.gravity = Gravity.BOTTOM;
        window.setAttributes(dialogParams);

        dialog.show();

    }


    //领任务-图文，微信朋友圈、qq空间选择
    public static void showReleaseTaskDialog(final Context context, final CommonDialogListenerUtil.ReleaseTaskListener listener) {


        final Dialog dialog = new Dialog(context,R.style.DialogStyle);
        View view = LayoutInflater.from(context).inflate(R.layout.layout_dialog_wechat_qq, null);
        dialog.setContentView(view);
        final LinearLayout wechatLl = view.findViewById(R.id.dialog_wechat_share_wechat_ll);
        final LinearLayout qqLl = view.findViewById(R.id.dialog_wechat_share_qq_ll);



        wechatLl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.confirmWechat();
                dialog.dismiss();
            }
        });


        qqLl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.confirmQQ();
                dialog.dismiss();

            }
        });

        Window window = dialog.getWindow();
        WindowManager.LayoutParams dialogParams = window.getAttributes();
        dialogParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        dialogParams.gravity = Gravity.BOTTOM;
        window.setAttributes(dialogParams);

        dialog.show();

    }

    /**
     * 显示媒体资源，截图示例dialog
     * @param context
     * @param state 0朋友圈  1微博  2抖音  3微视
     */
    public static void showExampleImgDialog(Context context, String state) {
        final Dialog dialog = new Dialog(context, R.style.DialogStyle);
        ImageView iv = new ImageView(context);
        switch (state){
            case "0":
                iv.setImageResource(R.mipmap.img_daren_data_one_example_wechat);
                break;
            case "1":
                iv.setImageResource(R.mipmap.img_daren_data_one_example_wb);
                break;
            case "2":
                iv.setImageResource(R.mipmap.img_daren_data_one_example_dy);
                break;
            case "3":
                iv.setImageResource(R.mipmap.img_daren_data_one_example_ws);
                break;

        }
        dialog.setContentView(iv);

        Window dialogWindow = dialog.getWindow();
        WindowManager.LayoutParams layoutParams = dialogWindow.getAttributes();

        layoutParams.width = DensityUtil.dpTopx(context, 400);
        layoutParams.height = DensityUtil.dpTopx(context, 600);
        dialogWindow.setAttributes(layoutParams);


        dialog.show();

    }

    //支付订单 todo
    public static void showPayOrderDialog(final Context context) {
        final Dialog dialog = new Dialog(context,R.style.DialogStyle);
        View view = LayoutInflater.from(context).inflate(R.layout.layout_dialog_pay_order, null);
        dialog.setContentView(view);

        Window window = dialog.getWindow();
        WindowManager.LayoutParams dialogParams = window.getAttributes();
        dialogParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        dialogParams.gravity = Gravity.BOTTOM;
        window.setAttributes(dialogParams);

        dialog.show();

    }
}
