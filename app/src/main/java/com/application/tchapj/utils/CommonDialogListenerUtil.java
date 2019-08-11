package com.application.tchapj.utils;

public class CommonDialogListenerUtil {

    public interface WechatShareDialogListener{
        void wechat();//微信好友
        void wechatMoments();//微信朋友圈
        void qq();
    }

    public interface ReleaseTaskListener{
        void confirmWechat();
        void confirmQQ();
    }
}
