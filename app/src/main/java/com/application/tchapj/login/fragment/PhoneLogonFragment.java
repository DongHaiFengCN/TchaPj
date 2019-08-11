package com.application.tchapj.login.fragment;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.application.tchapj.App;
import com.application.tchapj.Constants;
import com.application.tchapj.MainActivity;
import com.application.tchapj.R;
import com.application.tchapj.base.BaseMvpFragment;
import com.application.tchapj.bean.UserInfo;
import com.application.tchapj.login.bean.LoginResult;
import com.application.tchapj.login.bean.NewPhoneLoginResult;
import com.application.tchapj.login.presenter.LoginPresenter;
import com.application.tchapj.login.view.ILoginView;
import com.application.tchapj.utils.ToastUtil;
import com.application.tchapj.utils2.Verification;
import com.application.tchapj.utils2.pickers.util.LogUtils;
import com.application.tchapj.utils2.qiniu.utils.StringUtils;
import com.application.tchapj.utils2.share.SDKLoginUtils;
import com.application.tchapj.utils2.share.SharedPreferencesUtils;
import com.iflytek.cloud.thirdparty.A;
import com.iflytek.cloud.thirdparty.B;
import com.mob.tools.utils.Data;

import java.util.Calendar;
import java.util.TreeMap;

import butterknife.BindView;

import static com.application.tchapj.utils2.Verification.loadSMS;
import static com.application.tchapj.utils2.Verification.verifyTel;

/**
 * Created by 安卓开发 on 2018/7/30.
 */

public class PhoneLogonFragment extends BaseMvpFragment<ILoginView, LoginPresenter> implements ILoginView {

    @BindView(R.id.register_edt_userName)
    EditText register_edt_userName;
    @BindView(R.id.register_edt_smsCode)
    EditText register_edt_smsCode;
    @BindView(R.id.register_btn_getSmsCode)
    TextView register_btn_getSmsCode;
    @BindView(R.id.login_cb_remember)
    CheckBox login_cb_remember;
    @BindView(R.id.register_btn_signUp)
    Button register_btn_signUp;

    @BindView(R.id.iv_wx_login)
    ImageView ivWxLogin;
    @BindView(R.id.iv_qq_login)
    ImageView ivQqLogin;

    private ProgressDialog progressDialog;
    private static final int MSG_ACTION_CCALLBACK = 0;

    private String userName, smsCode, passWord;

    private NewPhoneLoginResult newPhoneLogins;
    private LoginResult loginResultBeans3;

    private String id;
    private String nickName;
    private String sex;
    private String headimgurl;

    String picurl = "http://pic33.nipic.com/20130916/3420027_192919547000_2.jpg";//测试图片地址

    private int SharesdkType;

    /*Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String userIcon = (String) msg.obj;//发送空消息也行，只起到提示的作用

            text.setText(userName);
        }
    };*/

    // 接收参数
    public static PhoneLogonFragment newInstance(String Id, String NickName,String Sex,String Headimgurl) {
        Bundle args = new Bundle();

        PhoneLogonFragment fragment = new PhoneLogonFragment();
        fragment.id = Id;
        fragment.nickName = NickName;
        fragment.sex = Sex;
        fragment.headimgurl = Headimgurl;
        fragment.setArguments(args);

        return fragment;
    }


    @Override
    public int getRootViewId() {
        return R.layout.phone_login_fragment;
    }

    @Override
    public void initUI() {

        if(ifShowLoginIcon()) {//是否展示三方登陆icon
            ivWxLogin.setVisibility(View.VISIBLE);
            ivQqLogin.setVisibility(View.VISIBLE);
        }else{
            ivWxLogin.setVisibility(View.GONE);
            ivQqLogin.setVisibility(View.GONE);
        }

        registerReceiver();

        login_cb_remember.setChecked(true);

        initListener(); // 点击事件
    }

    private boolean ifShowLoginIcon() {
//        Calendar c = Calendar.getInstance();
//
//        int mYear = c.get(Calendar.YEAR); // 获取当前年份
//        int mMonth = c.get(Calendar.MONTH) + 1;// 获取当前月份
//        int mDay = c.get(Calendar.DAY_OF_MONTH);// 获取当日期
//        int mHour = c.get(Calendar.HOUR_OF_DAY);//时
//        if(mYear == 2018 && mMonth == 11 && mDay == 29 && mHour == 10){
//
//        }

        long current = System.currentTimeMillis();
        if(current >= 1546050600000L){
            return true;
        }else{
            return false;
        }

    }

    // 点击事件
    private void initListener() {

        register_btn_getSmsCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                userName = register_edt_userName.getText().toString();

                if (!TextUtils.isEmpty(userName)) {
                    boolean isTel = verifyTel(userName);
                    if (isTel) {
                        loadSMS(register_btn_getSmsCode);
                    } else {
                        Toast.makeText(getContext(), "请正确填写手机号", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(getContext(), "请输入手机号", Toast.LENGTH_SHORT).show();
                }

                getPresenter().getSmsCodeResult(userName);
            }
        });

        register_btn_signUp.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                userName = register_edt_userName.getText().toString();
                smsCode = register_edt_smsCode.getText().toString();

                if (userName.length() <= 0) {
                    Toast.makeText(getContext(),"请输入手机号！", Toast.LENGTH_LONG).show();
                    return;
                }else if (smsCode.length() <= 0) {
                    Toast.makeText(getContext(),"请输入验证码！", Toast.LENGTH_LONG).show();
                    return;
                }
                if (!Verification.verifyTel(userName)) {
                    Toast.makeText(getContext(),"请输入正确手机号！", Toast.LENGTH_LONG).show();
                    return;
                }
                getPresenter().getNewPhoneLoginResult(userName,smsCode);


            }
        });

        ivWxLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Login(SDKLoginUtils.WX_LOGIN);

                /*if(WXEntryActivity.wxopenid!=null){

                    Log.e("微信+++++++", "getUserId===" + WXEntryActivity.wxopenid); //拿到登录后的openid
                    Log.e("微信+++++++", "getUserName===" + WXEntryActivity.wxnickname); //拿到登录后的昵称
                    Log.e("微信+++++++", "性别===" + WXEntryActivity.wxsex); //登录方式
                    Log.e("微信+++++++", "getUserIcon===" + WXEntryActivity.wxheadimgurl); //登录头像

                getPresenter().getThirdLoginResult(WXEntryActivity.wxopenid,
                        0+"",WXEntryActivity.wxnickname
                        ,WXEntryActivity.wxsex,WXEntryActivity.wxheadimgurl);
                }else {
                    Toast.makeText(getContext(),"获取用户信息失败，请重新登录",Toast.LENGTH_SHORT).show();
                }*/



            }
        });

        ivQqLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Login(SDKLoginUtils.QQ_LOGIN);

            }
        });


    }

    private void Login(String type) {
        SDKLoginUtils.authorLogin(getContext(), type, new SDKLoginUtils.ConfirmLoginListener() {
            @Override
            public void confirmLogin(String userToken, String UserId, String UserName, String UserIcon, String UserGender,String PlatformNname) {
                if (!TextUtils.isEmpty(UserId)) {
                    toastShow("获取数据成功");
                }

                Log.e("QQ+++++++", "getUserId===" + UserId); //拿到登录后的openid
                Log.e("QQ+++++++", "getUserName===" + UserName); //拿到登录后的昵称
                Log.e("QQ+++++++", "getUserIcon===" + UserIcon); //登录头像
                Log.e("QQ+++++++", "getUserIcon===" + PlatformNname); //登录方式
                Log.e("QQ+++++++", "性别===" + UserGender); //登录方式

                if(UserGender.equals("m")){
                    getPresenter().getThirdLoginResult(UserId, 1+"",UserName
                            ,"0",UserIcon);
                }else {
                    getPresenter().getThirdLoginResult(UserId, 1+"",UserName
                            ,"1",UserIcon);
                }

            }
        });
    }

    public void toastShow(String text) {
        Toast.makeText(getContext(), text, Toast.LENGTH_LONG).show();
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

    }

    @Override
    public void onGetLoginResultUsInfo(UserInfo loginInfo) {

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

    // 新手机验证登录
    @Override
    public void onGetNewPhoneLoginResult(NewPhoneLoginResult newPhoneLoginResult) {

        newPhoneLogins = newPhoneLoginResult;


        if("000".equals(newPhoneLogins.getCode())){

            Intent intent = new Intent(getContext(), MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);  //最关键是这句
            startActivity(intent);
            //App.getInstance().exit();
            finish();
            //SharedPreferences.getInstance().setString("id", newPhoneLogins.getData().getMemberId());
            App.setId(newPhoneLoginResult.getData().getMemberId());
            Log.e("用户ID：", "url===" + App.getId());

            Toast.makeText(getActivity(),"登录成功",Toast.LENGTH_SHORT).show();

        }else if(newPhoneLoginResult != null && !StringUtils.isNullOrEmpty(newPhoneLoginResult.getDescription())){

            Toast.makeText(getActivity(),newPhoneLoginResult.getDescription(),Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getActivity(),"登录失败!",Toast.LENGTH_SHORT).show();
        }

    }

    @Override // 第三方登录
    public void onGetThirdLoginResult(NewPhoneLoginResult newPhoneLoginResult) {

        newPhoneLogins = newPhoneLoginResult;


        if("000".equals(newPhoneLoginResult.getCode())){

            ToastUtil.show(getActivity(), "登录成功");
            App.setId(newPhoneLoginResult.getData().getMemberId());
            Log.e("用户ID：", "url===" + App.getId());
//            App.getInstance().exit();

            Intent intent = new Intent(getContext(), MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//最关键是这句
            startActivity(intent);
            finish();



        }else if(newPhoneLoginResult != null && !StringUtils.isNullOrEmpty(newPhoneLoginResult.getDescription())){

            Toast.makeText(getContext(),newPhoneLoginResult.getDescription(),Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(getActivity(),"登录失败!",Toast.LENGTH_SHORT).show();
        }
        LogUtils.error("PhoneLogonFragment", "onGetThirdLoginResult-end ");

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

    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if(Constants.PHONE_LOGON_FRAGMENT_BROADCAST_NAME.equals(intent.getAction())){

                String wxopenid = App.getShared().getString("openid","");
                String wxnickname = App.getShared().getString("nickname","");
                int wxsex = App.getShared().getInt("sex");
                String wxheadimgurl = App.getShared().getString("headimgurl","");

                Log.e("微信+++++++", "getUserId===" + wxopenid); //拿到登录后的openid
                Log.e("微信+++++++", "getUserName===" + wxnickname); //拿到登录后的昵称
                Log.e("微信+++++++", "性别===" + wxsex); //登录方式
                Log.e("微信+++++++", "getUserIcon===" + wxheadimgurl); //登录头像

                if(!StringUtils.isNullOrEmpty(wxopenid)){
                    getPresenter().getThirdLoginResult(wxopenid,
                            0 + "", wxnickname
                            , wxsex + "", wxheadimgurl);
                }

            }
        }
    };

    public void registerReceiver() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(Constants.PHONE_LOGON_FRAGMENT_BROADCAST_NAME);
        filter.setPriority(Integer.MAX_VALUE);
        getActivity().registerReceiver(mReceiver, filter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mReceiver != null){
            getActivity().unregisterReceiver(mReceiver);
        }
    }
}
