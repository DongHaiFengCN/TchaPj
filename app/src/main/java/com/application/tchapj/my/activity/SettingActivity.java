package com.application.tchapj.my.activity;

import android.content.Intent;
import android.content.SearchRecentSuggestionsProvider;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.application.tchapj.R;
import com.application.tchapj.base.BaseActvity;
import com.application.tchapj.login.activity.PassWordActivity;
import com.application.tchapj.utils.Utils;
import com.application.tchapj.widiget.ToolbarHelper;

import butterknife.BindView;


import static com.application.tchapj.DataManager.getDataManager;


// 设置界面
public class SettingActivity extends BaseActvity implements View.OnClickListener {

    @BindView(R.id.setting_mima_tv)
    TextView setting_mima_tv;

    @BindView(R.id.setting_tv_exit)
    Button mTvExit;

    @BindView(R.id.versionName_tv)
    TextView versionNameTv;


    @BindView(R.id.qq_tv)
    TextView qqTv;

    @BindView(R.id.wechat_tv)
    TextView weTv;

    @BindView(R.id.telephone_tv)
    TextView telephoneTv;


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
                getDataManager().release();
                finish();
            }
        });


        telephoneTv.setText(getDataManager().quickGetMetaData(R.string.telephone,String.class));

        versionNameTv.setText(Utils.getVersionName(getApplicationContext()));


        if ("1".equals(getDataManager().quickGetMetaData(R.string.qqId, String.class))) {

            qqTv.setText("已经绑定");
        } else {
            qqTv.setText("未绑定");
        }


        if ("1".equals(getDataManager().quickGetMetaData(R.string.wxId, String.class))) {

            weTv.setText("已经绑定");
        } else {
            weTv.setText("未绑定");
        }
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


}
