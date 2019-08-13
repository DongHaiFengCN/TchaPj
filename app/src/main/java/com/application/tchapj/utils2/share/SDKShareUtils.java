package com.application.tchapj.utils2.share;

import android.content.Context;
import android.util.Log;

import com.mob.tools.log.MobUncaughtExceptionHandler;

import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.wechat.friends.Wechat;



/**
 * Created by : Z_B on 2017/12/28.
 * Effect :  分享的工具类
 */
@Deprecated
public class SDKShareUtils {

    private static final String TAG = "SDKShareUtils";
//    private static final String TEST_IMAGE = "http://img.mukewang.com/551e470500018dd806000338.jpg";

    /**
     * 一键分享
     *
     * @param context               上下文
     * @param shareTitle            标题
     * @param contentText           分享内容
     * @param shareUrl              分享链接
     * @param shareImage            分享图片
     * @param shareCallBackListener 回调
     */
    public static void oneKeyShareShow(Context context,
                                       final String shareTitle,
                                       final String contentText,
                                       final String shareUrl,
                                       final String shareImage,
                                       final ShareCallBackListener shareCallBackListener) {
        OnekeyShare oks = new OnekeyShare();
        MobUncaughtExceptionHandler.disable();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();
        oks.setShareContentCustomizeCallback(new ShareContentCustomizeCallback() {
            @Override
            public void onShare(Platform platform, Platform.ShareParams paramsToShare) {
                if ("SinaWeibo".equals(platform.getName())) {
                    paramsToShare.setText(contentText);
                    paramsToShare.setImageUrl(shareImage);
                }
                if ("Wechat".equals(platform.getName())) {
                    paramsToShare.setTitle(shareTitle);
                    paramsToShare.setUrl(shareUrl);
                    paramsToShare.setText(contentText);
                    paramsToShare.setImageUrl(shareImage);
                    paramsToShare.setShareType(Platform.SHARE_WEBPAGE);
                }
                if ("WechatMoments".equals(platform.getName())) {
                    paramsToShare.setTitle(shareTitle);
                    paramsToShare.setUrl(shareUrl);
                    paramsToShare.setText(contentText);
                    paramsToShare.setImageUrl(shareImage);
                    paramsToShare.setShareType(Platform.SHARE_WEBPAGE);
                }
                if ("QQ".equals(platform.getName())) {
                    paramsToShare.setTitle(shareTitle);
                    paramsToShare.setTitleUrl(shareUrl);
                    paramsToShare.setText(contentText);
                    paramsToShare.setImageUrl(shareImage);
                }
                if ("QZone".equals(platform.getName())) {
                    paramsToShare.setTitle(shareTitle);
                    paramsToShare.setTitleUrl(shareUrl);
                    paramsToShare.setText(contentText);
                    paramsToShare.setImageUrl(shareImage);
                }
            }
        });
        oks.setCallback(new PlatformActionListener() {
            @Override
            public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                Log.d(TAG, "onComplete ---->  分享成功");
                platform.getName();
                if (shareCallBackListener != null) {
                    shareCallBackListener.onSuccess();
                }
            }

            @Override
            public void onError(Platform platform, int i, Throwable throwable) {
                Log.d(TAG, "onError ---->  失败" + throwable.getStackTrace().toString());
                if (shareCallBackListener != null) {
                    shareCallBackListener.onFailed();
                }
            }

            @Override
            public void onCancel(Platform platform, int i) {
                Log.d(TAG, "onCancel ---->  分享取消");
                if (shareCallBackListener != null) {
                    shareCallBackListener.onCancel();
                }
            }
        });
        // 启动分享GUI
        oks.show(context);
    }


    /**
     * 指定分享使用
     *
     * @param shareType
     * @param title
     * @param message
     * @param imageUrl
     * @param textUrl
     * @param shareCallBackListener
     */
  /*  public static void singleShareShow(String shareType,
                                       String title,
                                       String message,
                                       String imageUrl,
                                       String textUrl,
                                       final ShareCallBackListener shareCallBackListener) {

        Platform.ShareParams shareParams = new Platform.ShareParams();
        shareParams.setTitle(title);
        shareParams.setText(message);
        shareParams.setImageUrl(imageUrl);
        Platform platform = ShareSDK.getPlatform(shareType);
        if (QQ.NAME.equals(shareType)) {//QQ
            platform = ShareSDK.getPlatform(QQ.NAME);
            shareParams.setTitleUrl(textUrl);
        } else if (Wechat.NAME.equals(shareType)) { //微信好友
            platform = ShareSDK.getPlatform(Wechat.NAME);
            shareParams.setUrl(textUrl);
            shareParams.setShareType(Platform.SHARE_WEBPAGE);//分享的是网页
        } else if (WechatMoments.NAME.equals(shareType)) { //微信朋友圈
            platform = ShareSDK.getPlatform(WechatMoments.NAME);
            shareParams.setUrl(textUrl);
            shareParams.setShareType(Platform.SHARE_WEBPAGE);//分享的是网页
        }
        platform.setPlatformActionListener(new PlatformActionListener() {
            @Override
            public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                Log.d(TAG, "onComplete ---->  分享成功");
                if (shareCallBackListener != null) {
                    shareCallBackListener.onSuccess();
                }
            }

            @Override
            public void onError(Platform platform, int i, Throwable throwable) {
                Log.d(TAG, "onError ---->  失败" + throwable.getStackTrace().toString());
                if (shareCallBackListener != null) {
                    shareCallBackListener.onFailed();
                }
            }

            @Override
            public void onCancel(Platform platform, int i) {
                Log.d(TAG, "onCancel ---->  分享取消");
                if (shareCallBackListener != null) {
                    shareCallBackListener.onCancel();
                }
            }
        });
        platform.share(shareParams);

    }*/


    public static class ShareCallBackListener {

        public void onSuccess() {

        }

        public void onFailed() {
        }

        public void onCancel() {
        }

    }

}
