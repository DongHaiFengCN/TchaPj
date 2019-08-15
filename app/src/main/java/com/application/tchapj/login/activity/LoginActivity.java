/*
package com.application.tchapj.login.activity;

import android.app.Dialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.application.tchapj.App;
import com.application.tchapj.Constants;
import com.application.tchapj.MainActivity;
import com.application.tchapj.R;
import com.application.tchapj.base.BaseMvpActivity;
import com.application.tchapj.bean.UserInfo;
import com.application.tchapj.login.bean.LoginResult;
import com.application.tchapj.login.bean.NewPhoneLoginResult;
import com.application.tchapj.login.presenter.LoginPresenter;
import com.application.tchapj.login.view.ILoginView;
import com.application.tchapj.utils2.AppManager;
import com.application.tchapj.utils2.SharedPreferences;
import com.application.tchapj.utils2.Verification;
import com.application.tchapj.utils2.share.SharedPreferencesUtils;
import com.application.tchapj.widiget.KV;
import com.application.tchapj.widiget.LogUtils;
import com.application.tchapj.widiget.ToolbarHelper;

import butterknife.OnClick;

// 登录
@Deprecated
public class LoginActivity extends BaseMvpActivity<ILoginView, LoginPresenter> implements ILoginView{

    private EditText mEdtUserName;
    private EditText mEdtUserPass;
    private CheckBox mCbRemember;
    private TextView mTvForget;
    private Button mBtnSignIn;
    private TextView mTvRegister;

    private KV kv;                 // 保存缓存数据的对象
    private Dialog pb;             // 进度对话框
    private String loginName, loginPwd,state;

    private LoginResult loginResultBeans;
    private UserInfo userInfols;

    private static final String TAG = "LoginActivity";

    @Override // 得到布局
    public int getRootViewId() {
        return R.layout.activity_login;
    }

    @Override // 初始化状态栏
    protected void initToolbar(ToolbarHelper toolbarHelper) {

    }

    @Override // 初始化页面
    public void initUI() {
        AppManager.getAppManager().addActivity(this);
        kv = new KV(this);             // 保存基础数据
        mEdtUserName = (EditText) findViewById(R.id.login_edt_userName);
        mEdtUserPass = (EditText) findViewById(R.id.login_edt_userPass);
        mCbRemember = (CheckBox) findViewById(R.id.login_cb_remember);
        mTvForget = (TextView) findViewById(R.id.login_tv_forget);
        mBtnSignIn = (Button) findViewById(R.id.login_btn_signIn);
        mTvRegister = (TextView) findViewById(R.id.login_tv_register);
        mEdtUserName.setText(kv.getString(Constants.LOGIN_NAME, "")); // 得到缓存 KV 中的账号数据
        //mEdtUserPass.setText(kv.getString(Constants.LOGIN_PWD, ""));// 得到缓存 KV 中的密码数据
        mCbRemember.setChecked(Boolean.valueOf(kv.getString(Constants.LOGIN_STATE, "")));

        initListener(); // 点击事件
    }

    // 点击事件
    @OnClick({R.id.login_main_back_iv})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_main_back_iv:
                finish();
                break;

        }
    }

    private void initListener() {

        mBtnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                loginName = mEdtUserName.getText().toString().trim();
                loginPwd = mEdtUserPass.getText().toString().trim();
                state = String.valueOf(mCbRemember.isChecked());

                if (loginName.length() <= 0) {
                    Toast.makeText(getApplication(),"请输入账号！", Toast.LENGTH_LONG).show();
                    return;
                }else if (loginPwd.length() <= 0) {
                    Toast.makeText(getApplication(),"请输入密码！", Toast.LENGTH_LONG).show();
                    return;
                }

                if (!Verification.verifyTel(loginName)) {
                    Toast.makeText(getApplication(),"请输入正确手机号！", Toast.LENGTH_LONG).show();
                    return;
                }

                getPresenter().getLoginResult(loginName,loginPwd);
            }
        });

        mTvForget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(LoginActivity.this, PassWordActivity.class));
            }
        });

        mTvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });
    }

    @Override // 初始化数据
    public void initData() {

    }

    @NonNull
    @Override // P层方法获取数据
    public LoginPresenter createPresenter() {
        return new LoginPresenter(getApp());
    }

    @Override // 得到数据 二级包裹的外层数据
    public void onGetLoginResult(LoginResult loginResultBean) {
        loginResultBeans = loginResultBean;
        if(loginResultBeans.getCode() != null&&"000".equals(loginResultBeans.getCode())){
            if(mCbRemember.isChecked()==true){
                kv.put(Constants.LOGIN_NAME, loginName).commit();
                kv.put(Constants.LOGIN_PWD, loginPwd).commit();
                kv.put(Constants.LOGIN_STATE, state).commit();

            }else{
                kv.put(Constants.LOGIN_NAME, "").commit();
                kv.put(Constants.LOGIN_PWD, "").commit();
                kv.put(Constants.LOGIN_STATE, "").commit();
            }

            */
/*kv.put(Constants.LOGIN_NAME, loginName).commit();
            kv.put(Constants.LOGIN_PWD, loginPwd).commit();*//*

            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);

            Toast.makeText(LoginActivity.this,loginResultBeans.getDescription(),Toast.LENGTH_LONG).show();
            LoginActivity.this.finish();

        }else{
            // Toast.makeText(LoginActivity.this,loginResultBeans.getMsg(),Toast.LENGTH_LONG).show();
            Toast.makeText(LoginActivity.this,"请输入正确的用户名和密码！",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onGetLoginResultUsInfo(UserInfo loginInfo) {

        userInfols = loginInfo;
        SharedPreferencesUtils.getInstance().setUserInfo(loginInfo);
       */
/* App.ID = userInfols.getId();
        App.UseName = userInfols.getName();
        App.headimgurl = userInfols.getHeadimgurl();
        App.SEX = userInfols.getSex();
        App.media = userInfols.getMedia();*//*

        App.KF = loginResultBeans.getData().getKF();
        App.Type = loginResultBeans.getData().getType();

        */
/*SharedPreferences.getInstance().getString("id", "");*//*


        kv.put(Constants.USER_ID, userInfols.getId()).commit();

        SharedPreferences.getInstance().setString("id", userInfols.getId());
        SharedPreferences.getInstance().setString(Constants.SERVER_MOBILE,
                loginResultBeans.getData().getKF());

        SharedPreferences.getInstance().setString(Constants.HOME_TYPE,
                loginResultBeans.getData().getType());

    }

    @Override
    public void onGetUpdateResult(LoginResult loginResultBean) {

    }

    @Override
    public void onGetRegisterResult(LoginResult loginResultBean) {

    }

    @Override
    public void onGetSmsCodeResult(LoginResult loginResultBean) {

    }

    @Override
    public void onGetNewPhoneLoginResult(NewPhoneLoginResult newPhoneLoginResult) {

    }

    @Override
    public void onGetThirdLoginResult(NewPhoneLoginResult newPhoneLoginResult) {

    }


    @Override
    public void showProgress() {

    }

    @Override
    public void onCompleted() {

    }

    @Override // 抛出异常
    public void onError(Throwable e) {

        LogUtils.w(e);
    }


}
*/
