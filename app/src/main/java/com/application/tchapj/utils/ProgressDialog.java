package com.application.tchapj.utils;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.widget.TextView;

import com.application.tchapj.R;
import com.application.tchapj.widiget.AnimationLoader;


// 进度条对话框
public class ProgressDialog extends Dialog {

    private View mDialogView;
    private AnimationSet mModalInAnim;
    private AnimationSet mModalOutAnim;
    private boolean mCloseFromCancel;
    private TextView mLoadingTv;
    private String mLoadingText;

    public ProgressDialog(Context context) {
        super(context, R.style.alert_dialog);
        //默认返回键可以取消
        setCancelable(true);
        //其他区域可取消
        setCanceledOnTouchOutside(true);
        mModalInAnim = (AnimationSet) AnimationLoader.loadAnimation(getContext(), R.anim.modal_in);
        mModalOutAnim = (AnimationSet) AnimationLoader.loadAnimation(getContext(), R.anim.modal_out);

        mModalOutAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }
            @Override
            public void onAnimationEnd(Animation animation) {
                mDialogView.post(new Runnable() {
                    @Override
                    public void run() {
                        if (mCloseFromCancel) {
                            ProgressDialog.super.cancel();
                        } else {
                            ProgressDialog.super.dismiss();
                        }
                    }
                });
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_layout);
        mDialogView = getWindow().getDecorView().findViewById(android.R.id.content);
        mLoadingTv = (TextView) findViewById(R.id.tv_load_dialog);
//        setLoadingText(mLoadingText);
        initView();
    }
    private void initView(){
        if(mLoadingText!=null){
            mLoadingTv.setText(mLoadingText);
        }
    }

    @Override
    protected void onStart() {
        mDialogView.startAnimation(mModalInAnim);
    }

    @Override
    public void cancel() {
        dismissWithAnimation(true);
    }

    private void dismissWithAnimation(boolean fromCancel) {
        mCloseFromCancel = fromCancel;
        mDialogView.startAnimation(mModalOutAnim);
    }

    /**
     * 设置加载中文字
     * @param loadingText
     */
    public void setLoadingText(String loadingText) {
        if(!TextUtils.isEmpty(loadingText)){
            mLoadingText = loadingText;
        }
    }
}
