package com.application.tchapj.utils2.share;

import android.content.Context;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.mob.MobApplication;
import com.mob.MobSDK;

import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.PlatformDb;
import cn.sharesdk.framework.ShareSDK;

/**
 * 第三方登录
 */
public class LoginApi implements Callback {

    private static final String TAG = "LoginApi";
    private static final int MSG_AUTH_CANCEL = 1;
    private static final int MSG_AUTH_ERROR = 2;
    private static final int MSG_AUTH_COMPLETE = 3;

    private OnLoginListener loginListener;
    private Context context;
    private Handler handler;
    private Platform plat;

    public LoginApi() {
        handler = new Handler(Looper.getMainLooper(), this);
    }

    /**
     * 设置监听
     *
     * @param login
     */
    public void setOnLoginListener(OnLoginListener login) {
        this.loginListener = login;
    }

    /**
     * 登录授权
     *
     * @param context  上下文
     * @param platform 类型
     */
    public void login(Context context, String platform) {
        this.context = context.getApplicationContext();

        Log.e("+++++++1", "登录===" + platform); //拿到登录后的openid

        if (platform == null) {
            return;
        }
        //初始化SDK
        if (!(context instanceof MobApplication)) {
            MobSDK.init(context.getApplicationContext());
        }
        Log.e("+++++++2", "登录===" + platform); //拿到登录后的openid
        plat = ShareSDK.getPlatform(platform);
        /*final Platform plat = ShareSDK.getPlatform(platform);*/

        if (plat == null) {
            return;
        }

        /*plat.setPlatformActionListener(new PlatformActionListener() {
            @Override
            public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                Message msg = new Message();
                msg.what = 1;
                msg.arg2 = i;
                msg.obj = hashMap;
                handler.sendMessage(msg);
                //UIHandler.sendMessage(msg, UserInfoFragment.this);
            }

            @Override
            public void onError(Platform platform, int i, Throwable throwable) {
                Message msg = new Message();
                msg.what = 2;
                msg.arg2 = i;
                msg.obj = throwable;
                handler.sendMessage(msg);
                //UIHandler.sendMessage(msg, UserInfoFragment.this);
            }

            @Override
            public void onCancel(Platform platform, int i) {
                Message msg = new Message();
                msg.what = 3;
                msg.arg2 = i;
                msg.obj = plat;
                handler.sendMessage(msg);
                //UIHandler.sendMessage(msg, UserInfoFragment.this);
            }
        });*/
        
        plat.setPlatformActionListener(new PlatformActionListener() {

            public void onComplete(Platform plat, int action, HashMap<String, Object> res) {
                Log.e("+++++++6", "登录===" + plat+"   "+action+"   "+res); //拿到登录后的openid
                if (action == Platform.ACTION_USER_INFOR) {  // 成功
                    Message msg = new Message();
                    msg.what = MSG_AUTH_COMPLETE;
                    msg.arg2 = action;
                    msg.obj = new Object[]{plat, res};
                    handler.sendMessage(msg);
                    Log.e("+++++++5", "登录===" + plat); //拿到登录后的openid
                }
                Log.e("+++++++6", "登录===" + plat); //拿到登录后的openid
            }

            public void onError(Platform plat, int action, Throwable t) {
                if (action == Platform.ACTION_USER_INFOR) {  // 失败
                    Message msg = new Message();
                    msg.what = MSG_AUTH_ERROR;
                    msg.arg2 = action;
                    msg.obj = t;
                    handler.sendMessage(msg);
                }
                t.printStackTrace();
            }

            public void onCancel(Platform plat, int action) {
                if (action == Platform.ACTION_USER_INFOR) {   // 取消
                    Message msg = new Message();
                    msg.what = MSG_AUTH_CANCEL;
                    msg.arg2 = action;
                    msg.obj = plat;
                    handler.sendMessage(msg);
                }
            }
        });

        plat.authorize();
        plat.showUser(null);//首次授权

    }

    /**
     * 处理操作结果
     */
    public boolean handleMessage(Message msg) {
        switch (msg.what) {
            case MSG_AUTH_COMPLETE: { // 成功
                Object[] objs = (Object[]) msg.obj;
                Platform plat = (Platform) objs[0];
                @SuppressWarnings("unchecked")
                HashMap<String, Object> res = (HashMap<String, Object>) objs[1];
                if (plat.isAuthValid()) {// 判断授权是否有效
                    PlatformDb platDB = plat.getDb();// 获取数平台数据DB
                    if (loginListener != null && platDB != null) {
                        loginListener.authorizeSuccess(plat, platDB);
                    }
                }
                Toast.makeText(context, "登录成功", Toast.LENGTH_SHORT).show();
            }
            break;
            case MSG_AUTH_ERROR: {
                // 失败
                Throwable t = (Throwable) msg.obj;
                String text = "error: " + t.getMessage();
                t.printStackTrace();
                if (loginListener != null) {
                    loginListener.getProfileError(text);
                }
                // 取消
                Toast.makeText(context, "登录失败", Toast.LENGTH_SHORT).show();
            }
            break;
            case MSG_AUTH_CANCEL: {
                // 取消
                Toast.makeText(context, "登录取消", Toast.LENGTH_SHORT).show();
            }
            break;
        }
        return false;
    }

}
