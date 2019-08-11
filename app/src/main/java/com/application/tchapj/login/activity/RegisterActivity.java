package com.application.tchapj.login.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.application.tchapj.R;
import com.application.tchapj.WebViewActivity;
import com.application.tchapj.base.BaseMvpActivity;
import com.application.tchapj.bean.UserInfo;
import com.application.tchapj.login.bean.LoginResult;
import com.application.tchapj.login.bean.NewPhoneLoginResult;
import com.application.tchapj.login.presenter.LoginPresenter;
import com.application.tchapj.login.view.ILoginView;
import com.application.tchapj.utils2.Verification;
import com.application.tchapj.widiget.LogUtils;
import com.application.tchapj.widiget.ToolbarHelper;

import static com.application.tchapj.utils2.Verification.loadSMS;
import static com.application.tchapj.utils2.Verification.verifyTel;



// 注册
public class RegisterActivity extends BaseMvpActivity<ILoginView, LoginPresenter> implements ILoginView{

    private EditText mEdtUserName;
    private EditText mEdtSms;
    private EditText mEdtPassWord;
    private TextView mBtnGetSmsCode;
    private Button mBtnSignUp;

    private String userName, smsCode, passWord;

    private LoginResult loginResultBeans;
    private LoginResult loginResultBeans2;
    private LoginResult loginResultBeans3;
    private UserInfo userInfols;

    private static final String TAG = "RegisterActivity";

    @Override
    protected void initToolbar(ToolbarHelper toolbarHelper) {
        toolbarHelper.setTitle("注册");
    }

    @Override
    public int getRootViewId() {

        return R.layout.activity_register;

    }



    @Override
    public void initUI() {

        mEdtUserName = (EditText) findViewById(R.id.register_edt_userName);
        mEdtSms = (EditText) findViewById(R.id.register_edt_smsCode);
        mEdtPassWord = (EditText) findViewById(R.id.register_edt_new_password);
        mBtnGetSmsCode = (TextView) findViewById(R.id.register_btn_getSmsCode);
        mBtnSignUp = (Button) findViewById(R.id.register_btn_signUp);

        initListener(); // 点击事件
    }

    // 点击事件
    private void initListener() {

        mBtnGetSmsCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                userName = mEdtUserName.getText().toString();

                if (!TextUtils.isEmpty(userName)) {
                    boolean isTel = verifyTel(userName);
                    if (isTel) {
                        loadSMS(mBtnGetSmsCode);
                    } else {
                        Toast.makeText(RegisterActivity.this, "请正确填写手机号", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(RegisterActivity.this, "请输入手机号", Toast.LENGTH_SHORT).show();
                }

                getPresenter().getSmsCodeResult(userName);
            }
        });

        mBtnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                userName = mEdtUserName.getText().toString();
                passWord = mEdtPassWord.getText().toString();
                smsCode = mEdtSms.getText().toString();

                if (userName.length() <= 0) {
                    Toast.makeText(getApplication(),"请输入手机号！", Toast.LENGTH_LONG).show();
                    return;
                }else if (smsCode.length() <= 0) {
                    Toast.makeText(getApplication(),"请输入验证码！", Toast.LENGTH_LONG).show();
                    return;
                } else if (passWord.length() <= 0) {
                    Toast.makeText(getApplication(),"请输入新密码！", Toast.LENGTH_LONG).show();
                    return;
                }


                if (!Verification.verifyTel(userName)) {
                    Toast.makeText(getApplication(),"请输入正确手机号！", Toast.LENGTH_LONG).show();
                    return;
                }


                getPresenter().getRegisterResult(userName,passWord,smsCode);

            }
        });

    }


    @Override
    public void initData() {

    }

    @NonNull
    @Override
    public LoginPresenter createPresenter() {
        return new LoginPresenter(getApp());
    }

    @Override
    public void onGetLoginResult(LoginResult loginResultBean) {
        loginResultBeans3 = loginResultBean;

        /*// 修改个人资料H5调整
        if (loginResultBeans3.getCode() != null && "000".equals(loginResultBeans3.getCode())) {

            //修改个人资料
            startWeb(H5UrlData.UPDATE_USERINFO + loginResultBeans3.getData().getLoginInfo().getId(), "修改个人信息");

        } else {
            Toast.makeText(RegisterActivity.this, "" + loginResultBeans3.getDescription(), Toast.LENGTH_SHORT).show();
        }*/

    }

    @Override
    public void onGetLoginResultUsInfo(UserInfo loginInfo) {

    }

    @Override
    public void onGetUpdateResult(LoginResult loginResultBean) {

    }

    @Override
    public void onGetRegisterResult(LoginResult loginResultBean) {
        loginResultBeans = loginResultBean;

        if(loginResultBeans.getCode() != null&&"000".equals(loginResultBeans.getCode())){

            Toast.makeText(getBaseContext(),loginResultBeans.getDescription(),Toast.LENGTH_SHORT).show();
            ToPersonalInfo(userName,passWord);
            finish();
            Intent intent = new Intent(RegisterActivity.this, LoginMainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);  //最关键是这句
            startActivity(intent);

        }else {
            Toast.makeText(getBaseContext(),"注册失败!",Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public void onGetSmsCodeResult(LoginResult loginResultBean) {
        loginResultBeans2 = loginResultBean;
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
        LogUtils.w(e);
    }

    private void ToPersonalInfo(String userName, String passWord) {

        getPresenter().getLoginResult(userName,passWord);

    }


    public void startWeb(String url, String title) {
        Intent intent = new Intent(RegisterActivity.this, WebViewActivity.class);
        intent.putExtra(WebViewActivity.URL_KEY, url);
        intent.putExtra(WebViewActivity.TITLE, title);
        startActivity(intent);
    }

}
