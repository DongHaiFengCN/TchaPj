package com.application.tchapj.my.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.application.tchapj.App;
import com.application.tchapj.MainActivity;
import com.application.tchapj.R;
import com.application.tchapj.base.BaseActvity;
import com.application.tchapj.login.activity.PassWordActivity;
import com.application.tchapj.utils2.share.SharedPreferencesUtils;
import com.application.tchapj.widiget.ToolbarHelper;

import butterknife.BindView;
import me.drakeet.materialdialog.MaterialDialog;


// 设置界面
public class SettingActivity extends BaseActvity implements View.OnClickListener {

    @BindView(R.id.setting_mima_tv)
    TextView setting_mima_tv;

    @BindView(R.id.setting_tv_exit)
    Button mTvExit;

    private MaterialDialog exitDialog;

    @Override
    protected void initToolbar(ToolbarHelper toolbarHelper) {
        toolbarHelper.setTitle("系统设置");
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_setting;
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setting_mima_tv.setOnClickListener(this);
        mTvExit.setOnClickListener(this);

        mTvExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exitApp();
            }
        });

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.setting_mima_tv:
                startActivity(new Intent(this, PassWordActivity.class));
                break;
            default:
                break;
        }
    }

    private void dialogs() {

        AlertDialog.Builder builder = new AlertDialog.Builder(SettingActivity.this);
        builder.setMessage("确定要退出应用吗？");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                /*BmobUpdateAgent.setDefault();
                mActivity.finish();
                System.exit(0);*/

                /*SharedPreferences.getInstance().setString("USER_ID","");
                SharedPreferences.getInstance().setString("id","");
                SharedPreferences.getInstance().setString("SERVER_MOBILE","");
*/
             exitApp();

            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }

    private void exitApp() {
        SharedPreferencesUtils.getInstance().setNickName(null);
        SharedPreferencesUtils.getInstance().setUserInfo(null);
        Intent intent = new Intent(SettingActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);  //最关键是这句
        startActivity(intent);
    }

}
