package com.application.tchapj.login.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.RadioButton;

import com.application.tchapj.R;
import com.application.tchapj.base.BaseActvity;
import com.application.tchapj.login.fragment.PasswordLoginFragment;
import com.application.tchapj.login.fragment.PhoneLogonFragment;
import com.application.tchapj.widiget.ToolbarHelper;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 安卓开发 on 2018/7/30.
 */

public class LoginMainActivity extends BaseActvity {

    // 获得控件
    @BindView(R.id.my_shouji_logon)
    RadioButton my_shouji_logon;
    @BindView(R.id.my_mima_logon)
    RadioButton my_mima_logon;

    private String id;
    private String nickName;
    private String sex;
    private String headimgurl;

    // 得到Fragment对象
    private PhoneLogonFragment phoneLoginFragment;
    private PasswordLoginFragment passwordLoginFragment;

    @Override
    protected void initToolbar(ToolbarHelper toolbarHelper) {

        Intent intent = getIntent();

        id = intent.getStringExtra("id");
        nickName = intent.getStringExtra("nickName");
        sex = intent.getStringExtra("sex");
        headimgurl = intent.getStringExtra("headimgurl");

    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_login_main;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showPhoneLogonFragment();

    }

    // 点击事件
    @OnClick({R.id.my_shouji_logon, R.id.my_mima_logon, R.id.login_main_back_iv})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.my_shouji_logon:
                showPhoneLogonFragment();
                break;
            case R.id.my_mima_logon:
                showPasswordLogonFragment();
                break;
            case R.id.login_main_back_iv:
                finish();
                break;
            default:
                break;

        }
    }

    // 显示手机号
    public void showPhoneLogonFragment() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        hideAllFragment(fragmentTransaction);
        if (phoneLoginFragment == null) {
            phoneLoginFragment = new PhoneLogonFragment();
            fragmentTransaction.add(R.id.my_logon_content, phoneLoginFragment);
        }
        commitShowFragment(fragmentTransaction, phoneLoginFragment);
    }

    // 显示用户名密码
    public void showPasswordLogonFragment() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        hideAllFragment(fragmentTransaction);
        if (passwordLoginFragment == null) {
            passwordLoginFragment = new PasswordLoginFragment();
            fragmentTransaction.add(R.id.my_logon_content, passwordLoginFragment);
        }

        commitShowFragment(fragmentTransaction, passwordLoginFragment);

    }

    // 显示Fragment
    public void commitShowFragment(FragmentTransaction fragmentTransaction, Fragment fragment) {
        fragmentTransaction.show(fragment);
        fragmentTransaction.commit();
    }

    // 隐藏所有Fragment
    public void hideAllFragment(FragmentTransaction fragmentTransaction) {
        hideFragment(fragmentTransaction, phoneLoginFragment);
        hideFragment(fragmentTransaction, passwordLoginFragment);

    }

    // 隐藏Fragment
    private void hideFragment(FragmentTransaction fragmentTransaction, Fragment fragment) {
        if (fragment != null) {
            fragmentTransaction.hide(fragment);
        }
    }


}
