package com.application.tchapj.utils2;

import android.text.TextUtils;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

// 验证
public class Verification {

    // 验证码
    public static void loadSMS(final TextView tv) {
        CountDownButtonHelper helper = new CountDownButtonHelper(tv, "倒计时", 60, 1);
        helper.setOnFinishListener(new CountDownButtonHelper.OnFinishListener() {
            @Override
            public void finish() {
                tv.setText("再次获取" );
            }
        });
        //helper.setOnFinishListener(() -> mBtnGetSmsCode.setText("再次获取"));
        helper.start();

    }

    // 使用正则表达式验证手机号
    public static boolean verifyTel(String telNumString) {
        if (!TextUtils.isEmpty(telNumString)) {
            String regExp = "(^\\d{11}$)";
            Pattern p = Pattern.compile(regExp);
            Matcher m = p.matcher(telNumString);
            return m.find();
        }

        //手机号验证只验证位数
//        if (!TextUtils.isEmpty(telNumString)) {
//            if(telNumString.length() == 11){
//                return true;
//            }
//        }
        return false;
    }

    // 使用正则表达式验证手机号
    public static boolean verifySfz(String sfzNumString) {
        if (!TextUtils.isEmpty(sfzNumString)) {
            String regExp = "(^\\d{17}([0-9]|X)$)";
            return Pattern.matches(regExp, sfzNumString.toUpperCase());
        }
        return false;
    }
}
