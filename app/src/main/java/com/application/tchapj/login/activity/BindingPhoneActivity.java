package com.application.tchapj.login.activity;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.application.tchapj.App;
import com.application.tchapj.R;
import com.application.tchapj.base.BaseBean;
import com.application.tchapj.base.BaseMvpActivity;
import com.application.tchapj.bean.BindingPhoneBean;
import com.application.tchapj.bean.SmsCodeBean;
import com.application.tchapj.login.bean.LoginResult;
import com.application.tchapj.login.bean.NewPhoneLoginResult;
import com.application.tchapj.login.presenter.BindingPhonePresenter;
import com.application.tchapj.login.presenter.LoginPresenter;
import com.application.tchapj.login.view.IBindingPhoneView;
import com.application.tchapj.login.view.ILoginView;
import com.application.tchapj.utils2.SharedPreferences;
import com.application.tchapj.utils2.qiniu.utils.StringUtils;
import com.application.tchapj.utils2.share.SharedPreferencesUtils;
import com.application.tchapj.widiget.LogUtils;
import com.application.tchapj.widiget.ToolbarHelper;

import static com.application.tchapj.utils2.Verification.loadSMS;
import static com.application.tchapj.utils2.Verification.verifyTel;


// 绑定手机号
public class BindingPhoneActivity extends BaseMvpActivity<IBindingPhoneView, BindingPhonePresenter> implements IBindingPhoneView{

    private static final String TAG = BindingPhoneActivity.class.getSimpleName();

    private EditText mEdtUserPhone;
    private TextView mBtnGetSmsCode;
    private EditText mEdtSmsCode;
    private Button mBtnSubmit;

    private String userPhone, smsCode;
    private String responseSmsCode = "";//验证码接口返回的



    @Override
    public int getRootViewId() {
        return R.layout.activity_binding_phone;
    }

    @Override
    protected void initToolbar(ToolbarHelper toolbarHelper) {
        toolbarHelper.setTitle("绑定手机号");
    }

    @Override
    public void initUI() {

        mEdtUserPhone = (EditText) findViewById(R.id.reset_edt_userPhone);
        mBtnGetSmsCode = (TextView) findViewById(R.id.reset_btn_getSmsCode);
        mEdtSmsCode = (EditText) findViewById(R.id.reset_edt_smsCode);
        mBtnSubmit = (Button) findViewById(R.id.reset_btn_submit);

        initListener(); // 点击事件
    }

    // 点击事件
    private void initListener() {

        mBtnGetSmsCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                userPhone=mEdtUserPhone.getText().toString();

                if (!TextUtils.isEmpty(userPhone)){
                    boolean isTel = verifyTel(userPhone);
                    if (isTel){
                        loadSMS(mBtnGetSmsCode);

                    }else {
                        Toast.makeText(getBaseContext(),"手机号不支持",Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(getBaseContext(),"请输入手机号",Toast.LENGTH_SHORT).show();
                }

                responseSmsCode = "";
                getPresenter().getSmsCodeResult(userPhone);
            }
        });

        mBtnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                smsCode=mEdtSmsCode.getText().toString();
                userPhone=mEdtUserPhone.getText().toString();

                if (userPhone.length() <= 0) {
                    Toast.makeText(getApplication(),"请输入手机号", Toast.LENGTH_LONG).show();
                    return;
                }else if (smsCode.length() <= 0) {
                    Toast.makeText(getApplication(),"请输入验证码", Toast.LENGTH_LONG).show();
                    return;
                }else if(!responseSmsCode.equals(smsCode)){
                    Toast.makeText(getApplication(),"验证码不正确", Toast.LENGTH_LONG).show();
                    return;
                }

                getPresenter().onThirdLoginBindingResult(userPhone);

            }
        });

    }

    @Override
    public void initData() {

    }

    @NonNull
    @Override
    public BindingPhonePresenter createPresenter() {
        return new BindingPhonePresenter(getApp());
    }


    @Override
    public void onThirdLoginBindingResult(BaseBean<BindingPhoneBean> loginResultBean) {
        if(loginResultBean != null && loginResultBean.getCode().equals("000")){
            if(loginResultBean.getData() != null && !StringUtils.isNullOrEmpty(loginResultBean.getData().getMemberid())){
                getPresenter().refreshUserInfo(loginResultBean.getData().getMemberid());
            }
            showToast("绑定成功");
            finish();
        }else{
            if(loginResultBean != null && !TextUtils.isEmpty(loginResultBean.getDescription())){
                showToast("绑定失败，" + loginResultBean.getDescription());
            }else{
                showToast("绑定失败");
            }
        }

    }

    @Override
    public void onGetSmsCodeResult(BaseBean<SmsCodeBean> codeResultBean) {
        if(codeResultBean != null && codeResultBean.getData() != null && !TextUtils.isEmpty(codeResultBean.getData().getCode())){
            responseSmsCode = codeResultBean.getData().getCode();
        }

    }

    public static void start(Context context) {
        Intent starter = new Intent(context, BindingPhoneActivity.class);
        context.startActivity(starter);
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
