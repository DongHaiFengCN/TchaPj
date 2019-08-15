package com.application.tchapj.login.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.application.tchapj.R;
import com.application.tchapj.base.BaseMvpActivity;
import com.application.tchapj.bean.UserInfo;
import com.application.tchapj.login.bean.LoginResult;
import com.application.tchapj.login.bean.NewPhoneLoginResult;

import com.application.tchapj.login.view.ILoginView;
import com.application.tchapj.widiget.LogUtils;
import com.application.tchapj.widiget.ToolbarHelper;

import static com.application.tchapj.utils2.Verification.loadSMS;
import static com.application.tchapj.utils2.Verification.verifyTel;


// 密码
public class PassWordActivity extends AppCompatActivity {

    private EditText mEdtUserName;
    private TextView mBtnGetSmsCode;
    private EditText mEdtSmsCode;
    private EditText mEdtNewPassword;
    private Button mBtnSubmit;

    private String userName, smsCode, newPassWord;

    private LoginResult loginResultBeans;
    private LoginResult loginResultBeans2;
    private UserInfo userInfols;

    private static final String TAG = "PassWordActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);
        initUI();
    }



    public void initUI() {

        mEdtUserName = (EditText) findViewById(R.id.reset_edt_userName);
        mBtnGetSmsCode = (TextView) findViewById(R.id.reset_btn_getSmsCode);
        mEdtSmsCode = (EditText) findViewById(R.id.reset_edt_smsCode);
        mEdtNewPassword = (EditText) findViewById(R.id.reset_edt_new_password);
        mBtnSubmit = (Button) findViewById(R.id.reset_btn_submit);

        initListener(); // 点击事件
    }

    // 点击事件
    private void initListener() {

        mBtnGetSmsCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                userName=mEdtUserName.getText().toString();

                if (!TextUtils.isEmpty(userName)){
                    boolean isTel = verifyTel(userName);
                    if (isTel){
                        loadSMS(mBtnGetSmsCode);

                    }else {
                        Toast.makeText(getBaseContext(),"手机号不支持",Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(getBaseContext(),"请输入手机号",Toast.LENGTH_SHORT).show();
                }

               // getPresenter().getSmsCodeResult(userName);
            }
        });

        mBtnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                smsCode=mEdtSmsCode.getText().toString();
                newPassWord=mEdtNewPassword.getText().toString();
                userName=mEdtUserName.getText().toString();

                if (userName.length() <= 0) {
                    Toast.makeText(getApplication(),"请输入账号！", Toast.LENGTH_LONG).show();
                    return;
                }else if (newPassWord.length() <= 0) {
                    Toast.makeText(getApplication(),"请输入密码！", Toast.LENGTH_LONG).show();
                    return;
                }else if (smsCode.length() <= 0) {
                    Toast.makeText(getApplication(),"请输入验证码！", Toast.LENGTH_LONG).show();
                    return;
                }

               // getPresenter().getUpdateSwResult(userName,newPassWord,smsCode);

            }
        });

    }

/*
    @Override
    public void initData() {

    }*/
/*
    @NonNull
    @Override
    public LoginPresenter createPresenter() {
        return new LoginPresenter(getApp());
    }

    @Override
    public void onGetLoginResult(LoginResult loginResultBean) {

    }

    @Override
    public void onGetLoginResultUsInfo(UserInfo loginInfo) {

    }

    @Override
    public void onGetUpdateResult(LoginResult loginResultBean) {

        loginResultBeans = loginResultBean;

        if(loginResultBeans.getCode() != null&&"000".equals(loginResultBeans.getCode())){

            Toast.makeText(getBaseContext(),loginResultBeans.getDescription(),Toast.LENGTH_SHORT).show();

        }else {
            Toast.makeText(getBaseContext(),"请输入正确信息!",Toast.LENGTH_SHORT).show();
        }

        Intent intent = new Intent(PassWordActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);  //最关键是这句
        startActivity(intent);

    }*/


}
