package com.application.tchapj.utils2.share;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

import com.application.tchapj.widiget.LogUtils;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformDb;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.wechat.friends.Wechat;


/**
 * Created by ZhouBin on 2017/8/29.
 * Effect:  分享和登录的帮助类
 */

public class SDKLoginUtils {

    private static final String TAG = "SDKLoginUtils";

    public static final String WX_LOGIN = "wx_login";
    public static final String QQ_LOGIN = "qq_login";
    public static final String WB_LOGIN = "wb_login";

    public static SDKLoginUtils SDKLoginUtils;

    public SDKLoginUtils() {
    }

    public static synchronized SDKLoginUtils getSDKLoginUtils() {
        if (SDKLoginUtils == null) {
            SDKLoginUtils = new SDKLoginUtils();
        }
        return SDKLoginUtils;
    }

    /**
     * 获取授权并登录
     *
     * @param context              上下文
     * @param type                 类型
     * @param confirmLoginListener 登录的回调
     */
    public static void authorLogin(final Context context, String type, final ConfirmLoginListener confirmLoginListener) {
        LoginApi loginApi = new LoginApi();
        if (TextUtils.isEmpty(type)) return;
        if (type.equals(WX_LOGIN)) {  //微信
            loginApi.login(context, Wechat.NAME);
        } else if (type.endsWith(QQ_LOGIN)) { //QQ
            loginApi.login(context, QQ.NAME);
        } else if (type.equals(WB_LOGIN)) {   //微博
            loginApi.login(context, SinaWeibo.NAME);
        }

        loginApi.setOnLoginListener(new OnLoginListener() {
            @Override
            public void authorizeSuccess(Platform platform, PlatformDb platDB) {
                if (confirmLoginListener != null) {

                    confirmLoginListener.confirmLogin(platDB.getToken(), platDB.getUserId(), platDB.getUserName(), platDB.getUserIcon(), platDB.getUserGender(), platDB.getPlatformNname());
                }
            }

            @Override
            public void getProfileError(String info) {
                Toast.makeText(context, "授权失败" + info, Toast.LENGTH_LONG).show();
                LogUtils.e(TAG, "授权失败" + info);
            }
        });
    }

    public interface ConfirmLoginListener {
        void confirmLogin(String userToken, String UserId, String UserName, String UserIcon, String UserGender, String PlatformNname);
    }

    private ConfirmLoginListener confirmLoginListener;

    public void setConfirmLoginListener(ConfirmLoginListener confirmLoginListener) {
        this.confirmLoginListener = confirmLoginListener;
    }
}
