package com.application.tchapj.login.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.application.tchapj.App;
import com.application.tchapj.R;

import com.application.tchapj.bean.SmsCodeBean;
import com.application.tchapj.login.bean.NewPhoneLoginResult;
import com.application.tchapj.login.bean.SmsCodeResponse;
import com.application.tchapj.utils2.Verification;
import com.iflytek.cloud.thirdparty.S;

import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.wechat.friends.Wechat;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.application.tchapj.DataManager.getDataManager;
import static com.application.tchapj.utils2.Verification.loadSMS;
import static com.application.tchapj.utils2.Verification.verifyTel;
import static com.application.tchapj.utils2.picture.tools.VoiceUtils.release;

/**
 * Created by 安卓开发 on 2018/7/30.
 *
 * @author 董海峰重构 2019 8/10
 */

public class PhoneLogonFragment extends Fragment {

    EditText register_edt_userName;
    EditText register_edt_smsCode;
    TextView register_btn_getSmsCode;
    CheckBox login_cb_remember;
    Button register_btn_signUp;
    ImageView ivWxLogin;
    ImageView ivQqLogin;

    private String userName, smsCode;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.phone_login_fragment, null);
        register_edt_userName = view.findViewById(R.id.register_edt_userName);
        register_edt_smsCode = view.findViewById(R.id.register_edt_smsCode);

        register_btn_getSmsCode = view.findViewById(R.id.register_btn_getSmsCode);
        login_cb_remember = view.findViewById(R.id.login_cb_remember);
        register_btn_signUp = view.findViewById(R.id.register_btn_signUp);

        ivWxLogin = view.findViewById(R.id.iv_wx_login);
        ivQqLogin = view.findViewById(R.id.iv_qq_login);
        initUI();
        return view;
    }


    public void initUI() {
        //是否展示三方登陆icon

        if (ifShowLoginIcon()) {
            ivWxLogin.setVisibility(View.VISIBLE);
            ivQqLogin.setVisibility(View.VISIBLE);
        } else {
            ivWxLogin.setVisibility(View.GONE);
            ivQqLogin.setVisibility(View.GONE);
        }
        login_cb_remember.setChecked(true);
        initListener(); // 点击事件
    }

    private boolean ifShowLoginIcon() {
        long current = System.currentTimeMillis();
        if (current >= 1546050600000L) {
            return true;
        } else {
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


                //获取验证码
                ((App) (getActivity().getApplication()))
                        .getAppComponent()
                        .getAPIService()
                        .getSmsCodeResult(userName, "002", "1.0", "", "JSON") // 得到登录接口
                        .subscribeOn(Schedulers.io()) // 订阅方式
                        .observeOn(AndroidSchedulers.mainThread()) // 指定线程
                        .subscribe(new Observer<SmsCodeResponse>() {  // 将数据绑定到实体类的操作
                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onError(Throwable e) {

                                Log.e("DOAING", e.toString());
                                Toast.makeText(getActivity(), "网络故障，请重新尝试", Toast.LENGTH_SHORT).show();
                            }

                            @Override // 得到数据
                            public void onNext(SmsCodeResponse smsCodeResponse) {
                                Toast.makeText(getActivity(), smsCodeResponse.getDescription(), Toast.LENGTH_SHORT).show();
                                if ("000".equals(smsCodeResponse.getCode())) {
                                    register_edt_smsCode.setText(smsCodeResponse.getData().getCode());
                                }
                            }
                        });
            }
        });

        register_btn_signUp.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                userName = register_edt_userName.getText().toString();
                smsCode = register_edt_smsCode.getText().toString();

                if (userName.length() <= 0) {
                    Toast.makeText(getContext(), "请输入手机号！", Toast.LENGTH_LONG).show();
                    return;
                } else if (smsCode.length() <= 0) {
                    Toast.makeText(getContext(), "请输入验证码！", Toast.LENGTH_LONG).show();
                    return;
                }
                if (!Verification.verifyTel(userName)) {
                    Toast.makeText(getContext(), "请输入正确手机号！", Toast.LENGTH_LONG).show();
                    return;
                }

                //getDataManager().release();

                ((App) (getActivity().getApplication()))
                        .getAppComponent()
                        .getAPIService() // 所有接口对象
                        .getNewPhoneLoginResult(userName, smsCode, "002", "1.0", "", "JSON") // 得到登录接口
                        .subscribeOn(Schedulers.io()) // 订阅方式
                        .observeOn(AndroidSchedulers.mainThread()) // 指定线程
                        .subscribe(new Subscriber<NewPhoneLoginResult>() {  // 将数据绑定到实体类的操作
                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onError(Throwable e) {

                                Toast.makeText(getActivity(), "登录失败！", Toast.LENGTH_SHORT).show();

                            }

                            @Override // 得到数据
                            public void onNext(NewPhoneLoginResult newPhoneLoginResult) {

                                Toast.makeText(getActivity(), newPhoneLoginResult.getDescription(), Toast.LENGTH_SHORT).show();
                                if ("000".equals(newPhoneLoginResult.getCode())) {

                                    getDataManager().setMetaDataById(R.string.id, newPhoneLoginResult.getData().getMemberId(), true);

                                    getDataManager().setMetaDataById(R.string.telephone, userName, true);
                                    getActivity().finish();

                                }

                            }
                        });

            }
        });

        ivWxLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Login(Wechat.NAME);


            }
        });

        ivQqLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Login(QQ.NAME);

            }
        });


    }

    private void Login(final String type) {

       // getDataManager().release();

   /*     Platform plat = ShareSDK.getPlatform(QQ.NAME);
        plat.removeAccount(true); //移除授权状态和本地缓存，下次授权会重新授权
        plat.SSOSetting(false); //SSO授权，传false默认是客户端授权，没有客户端授权或者不支持客户端授权会跳web授权
        plat.setPlatformActionListener(this);//授权回调监听，监听oncomplete，onerror，oncancel三种状态
        if(plat.isClientValid()){
            //判断是否存在授权凭条的客户端，true是有客户端，false是无
        }
        if(plat.isAuthValid()){
//判断是否已经存在授权状态，可以根据自己的登录逻辑设置
            Toast.makeText(this, "已经授权过了", 0).show();
            return;
        }
        ShareSDK.setActivity(this);//抖音登录适配安卓9.0
        plat.showUser(null);    //要数据不要功能，主要体现在不会重复出现授权界面


        */


        final Platform plat = ShareSDK.getPlatform(type);

        //移除授权状态和本地缓存，下次授权会重新授权
        plat.removeAccount(true);

        //SSO授权，传false默认是客户端授权，没有客户端授权或者不支持客户端授权会跳web授权
        plat.SSOSetting(false);

        //授权回调监听，监听oncomplete，onerror，oncancel三种状态
        plat.setPlatformActionListener(new PlatformActionListener() {
            @Override
            public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                platform.getName();

                getDataManager().setMetaDataById(R.string.nickName, platform.getDb().getUserName(), true);
                getDataManager().setMetaDataById(R.string.realname, platform.getDb().getUserName(), true);
                getDataManager().setMetaDataById(R.string.sex, platform.getDb().getUserGender(), true);
                getDataManager().setMetaDataById(R.string.headimgurl, platform.getDb().getUserIcon(), true);

                String logintype = "";
                if (type.equals(QQ.NAME)) {

                    logintype = "1";

                } else if (type.equals(Wechat.NAME)) {
                    logintype = "0";
                }

                ((App) (getActivity().getApplication()))
                        .getAppComponent()
                        .getAPIService()
                        .getThirdLoginResult(platform.getDb().getUserId(), logintype, platform.getDb().getUserName(), platform.getDb().getUserGender(), platform.getDb().getUserIcon(), "002", "1.0", "", "JSON")
                        .subscribeOn(Schedulers.io()) // 订阅方式
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<NewPhoneLoginResult>() {
                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onError(Throwable e) {

                                Toast.makeText(getActivity(), "登录失败!", Toast.LENGTH_SHORT).show();

                            }

                            @Override
                            public void onNext(NewPhoneLoginResult newPhoneLoginResult) {

                                if ("000".equals(newPhoneLoginResult.getCode())) {

                                    getDataManager().setMetaDataById(R.string.id, newPhoneLoginResult.getData().getMemberId(), true);
                                    getActivity().finish();

                                } else {
                                    Toast.makeText(getActivity(), "登录失败!", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

            }

            @Override
            public void onError(Platform platform, int i, Throwable throwable) {
                Toast.makeText(getActivity(), "授权错误！", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancel(Platform platform, int i) {
                Toast.makeText(getActivity(), "取消授权！", Toast.LENGTH_SHORT).show();
            }
        });

        if (plat.isAuthValid()) {
            //判断是否已经存在授权状态，可以根据自己的登录逻辑设置
            Toast.makeText(getActivity(), "已经授权过了", Toast.LENGTH_SHORT).show();
            return;
        }

        //抖音登录适配安卓9.0
        ShareSDK.setActivity(getActivity());

        //要数据不要功能，主要体现在不会重复出现授权界面
        plat.showUser(null);

    }

}
