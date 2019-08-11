package com.application.tchapj.login.fragment;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
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
import com.application.tchapj.base.BaseMvpFragment;
import com.application.tchapj.bean.UserInfo;
import com.application.tchapj.login.activity.PassWordActivity;
import com.application.tchapj.login.activity.RegisterActivity;
import com.application.tchapj.login.bean.LoginResult;
import com.application.tchapj.login.bean.NewPhoneLoginResult;
import com.application.tchapj.login.presenter.LoginPresenter;
import com.application.tchapj.login.view.ILoginView;
import com.application.tchapj.utils2.SharedPreferences;
import com.application.tchapj.utils2.Verification;
import com.application.tchapj.utils2.share.SharedPreferencesUtils;
import com.application.tchapj.widiget.KV;

import butterknife.BindView;

/**
 * Created by 安卓开发 on 2018/7/30.
 */

public class PasswordLogonFragment extends BaseMvpFragment<ILoginView, LoginPresenter> implements ILoginView{

    @BindView(R.id.login_edt_userName)
    EditText mEdtUserName;
    @BindView(R.id.login_edt_userPass)
    EditText mEdtUserPass;
    @BindView(R.id.login_cb_remember)
    CheckBox mCbRemember;
    @BindView(R.id.login_tv_forget)
    TextView mTvForget;
    @BindView(R.id.login_btn_signIn)
    Button mBtnSignIn;
    @BindView(R.id.login_tv_register)
    TextView mTvRegister;

    private KV kv;                 // 保存缓存数据的对象
    private Dialog pb;             // 进度对话框
    private String loginName, loginPwd,state;
    private LoginResult loginResultBeans;
    private UserInfo userInfols;

    private static final String TAG = "LoginActivity";

    // 接收参数
    public static PasswordLogonFragment newInstance(String param) {
        Bundle args = new Bundle();
        args.putString("args", param);
        PasswordLogonFragment fragment = new PasswordLogonFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.password_logon_fragment;
    }

    @Override
    public void initUI() {

        kv = new KV(getContext());             // 保存基础数据
        mEdtUserName.setText(kv.getString(Constants.LOGIN_NAME, "")); // 得到缓存 KV 中的账号数据
        //mEdtUserPass.setText(kv.getString(Constants.LOGIN_PWD, ""));// 得到缓存 KV 中的密码数据
        mCbRemember.setChecked(Boolean.valueOf(kv.getString(Constants.LOGIN_STATE, "")));

        initListener(); // 点击事件

    }

    private void initListener() {

        mBtnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                loginName = mEdtUserName.getText().toString().trim();
                loginPwd = mEdtUserPass.getText().toString().trim();
                state = String.valueOf(mCbRemember.isChecked());

                if (loginName.length() <= 0) {
                    Toast.makeText(getContext(),"请输入账号！", Toast.LENGTH_LONG).show();
                    return;
                }else if (loginPwd.length() <= 0) {
                    Toast.makeText(getContext(),"请输入密码！", Toast.LENGTH_LONG).show();
                    return;
                }

                if (!Verification.verifyTel(loginName)) {
                    Toast.makeText(getContext(),"请输入正确手机号！", Toast.LENGTH_LONG).show();
                    return;
                }

                getPresenter().getLoginResult(loginName,loginPwd);
            }
        });

        mTvForget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getContext(), PassWordActivity.class));
            }
        });

        mTvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getContext(), RegisterActivity.class));
            }
        });
    }

    @Override
    public void initData() {

    }

    @Override
    public LoginPresenter createPresenter() {

        return new LoginPresenter(getApp());
    }

    @Override
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

            /*kv.put(Constants.LOGIN_NAME, loginName).commit();
            kv.put(Constants.LOGIN_PWD, loginPwd).commit();*/
            Intent intent = new Intent(getContext(), MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);  //最关键是这句
            startActivity(intent);
            //App.getInstance().exit();

            Toast.makeText(getContext(),loginResultBeans.getDescription(),Toast.LENGTH_LONG).show();


        }else{
            // Toast.makeText(LoginActivity.this,loginResultBeans.getMsg(),Toast.LENGTH_LONG).show();
            Toast.makeText(getContext(),"请输入正确的用户名和密码！",Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onGetLoginResultUsInfo(UserInfo loginInfo) {

        userInfols = loginInfo;
        SharedPreferencesUtils.getInstance().setUserInfo(loginInfo);
       /* App.ID = userInfols.getId();
        App.UseName = userInfols.getName();
        App.headimgurl = userInfols.getHeadimgurl();
        App.SEX = userInfols.getSex();
        App.media = userInfols.getMedia();*/
        App.KF = loginResultBeans.getData().getKF();
        App.Type = loginResultBeans.getData().getType();

        SharedPreferencesUtils.getInstance().setNickName(userInfols.getNick_name());

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

    @Override
    public void onError(Throwable e) {

    }


}
