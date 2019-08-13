package com.application.tchapj.login.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.application.tchapj.DataManager;
import com.application.tchapj.R;
import com.application.tchapj.login.activity.PassWordActivity;
import com.application.tchapj.login.activity.RegisterActivity;
import com.application.tchapj.utils2.Verification;

import java.util.Objects;

import static com.application.tchapj.DataManager.getDataManager;

/**
 * Created by 安卓开发 on 2018/7/30.
 *
 * @author 董海峰 重构移除冗余的代码接口，不适用mvp模式 2019/8/10
 */

public class PasswordLoginFragment extends Fragment {
    EditText mEdtUserName;
    EditText mEdtUserPass;
    CheckBox mCbRemember;
    TextView mTvForget;
    Button mBtnSignIn;
    TextView mTvRegister;
    private String loginName, loginPwd;

    private LocalBroadcastManager localBroadcastManager = null;

    private BroadcastReceiver localReceiver;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.password_login_fragment, null);
        mEdtUserName = view.findViewById(R.id.login_edt_userName);
        mEdtUserPass = view.findViewById(R.id.login_edt_userPass);
        mCbRemember = view.findViewById(R.id.login_cb_remember);
        mTvForget = view.findViewById(R.id.login_tv_forget);
        mBtnSignIn = view.findViewById(R.id.login_btn_signIn);
        mTvRegister = view.findViewById(R.id.login_tv_register);
        initListener();
        mEdtUserName.setText("15610170241");
        mEdtUserPass.setText("123456");


        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(DataManager.ACTION_LOGIN);

        localReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {


                if (intent.getBooleanExtra("permission", false)) {
                    Objects.requireNonNull(getActivity()).finish();
                } else {
                    Toast.makeText(getActivity(), intent.getStringExtra("description"), Toast.LENGTH_SHORT).show();
                }
            }
        };
        localBroadcastManager = LocalBroadcastManager.getInstance(Objects.requireNonNull(getActivity()));
        localBroadcastManager.registerReceiver(localReceiver, intentFilter);

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        //注销掉广播
        localBroadcastManager.unregisterReceiver(localReceiver);
        Log.e("DOAING", "密码广播登录已经注销");
    }


    private void initListener() {

        mBtnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                loginName = mEdtUserName.getText().toString().trim();
                loginPwd = mEdtUserPass.getText().toString().trim();
                if (loginName.length() <= 0) {
                    Toast.makeText(getContext(), "请输入账号！", Toast.LENGTH_LONG).show();
                    return;
                } else if (loginPwd.length() <= 0) {
                    Toast.makeText(getContext(), "请输入密码！", Toast.LENGTH_LONG).show();
                    return;
                }

                if (!Verification.verifyTel(loginName)) {
                    Toast.makeText(getContext(), "请输入正确手机号！", Toast.LENGTH_LONG).show();
                    return;
                }
                getDataManager()
                        .initMemberInfo(loginName, loginPwd, true, new DataManager.LoginListener() {
                            @Override
                            public void login(boolean isLogin) {

                                if (isLogin) {

                                    getActivity().finish();
                                    Toast.makeText(getActivity(),"登录成功",Toast.LENGTH_SHORT).show();


                                } else {
                                    Toast.makeText(getActivity(),"登录失败",Toast.LENGTH_SHORT).show();
                                }
                            }
                        });


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

}
