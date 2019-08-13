package com.application.tchapj.utils2.share;

import android.app.Activity;
import android.widget.Toast;

import com.mob.MobSDK;

import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;

import cn.sharesdk.sina.weibo.SinaWeibo;

import cn.sharesdk.tencent.qzone.QZone;

import cn.sharesdk.wechat.friends.Wechat;
import cn.sharesdk.wechat.moments.WechatMoments;


//import cn.sharesdk.tencent.qq.QQ;

/**
 * Created by yjin on 2017/6/21.
 */

public class PlatformAuthorizeUserInfoManager {
	private Activity activity;
	private MyPlatformActionListener myPlatformActionListener = null;

	public PlatformAuthorizeUserInfoManager(Activity mACt) {
		this.activity = mACt;
		this.myPlatformActionListener = new MyPlatformActionListener();
	}

	public void WeiXinAuthorize() {
		Platform weiXin = ShareSDK.getPlatform(Wechat.NAME);
		doAuthorize(weiXin);
	}

	public void sinaAuthorize() {
		Platform sina = ShareSDK.getPlatform(SinaWeibo.NAME);
		doAuthorize(sina);
	}


	public void qqZoneAuthorize() {
		Platform qqZone = ShareSDK.getPlatform(QZone.NAME);
		doAuthorize(qqZone);
	}

	public void whatMomentsAuthorize() {
		Platform moments = ShareSDK.getPlatform(WechatMoments.NAME);
		doAuthorize(moments);
	}

/*
	public void wechatFavoriteAuthorize() {
		Platform wechatFavorite = ShareSDK.getPlatform(WechatFavorite.NAME);
		doAuthorize(wechatFavorite);
	}
*/

	public void qqShareAuthorize() {
//		Platform qqShare = ShareSDK.getPlatform(QQ.NAME);
//		doAuthorize(qqShare);
	}


	/**
	 * 授权的代码
	 */
	public void doAuthorize(Platform platform) {
		if (platform != null) {
			platform.setPlatformActionListener(myPlatformActionListener);
			if (platform.isAuthValid()) {
				platform.removeAccount(true);
				return;
			}
			platform.SSOSetting(true);
			platform.authorize();
		}
	}

	/**
	 * 授权的代码
	 */
	public void doAuthorize(Platform platform, PlatformActionListener listener) {
		if (platform != null) {
			platform.setPlatformActionListener(listener);
			platform.removeAccount(true);
			platform.authorize();
		}
	}

	/**
	 * 用户信息的代码
	 */
	public void doUserInfo(Platform platform) {
		if (platform != null) {
			platform.showUser(null);
		}
	}

	/**
	 * 用户信息的代码
	 */
	public void doUserInfo(Platform platform, PlatformActionListener listener) {
		if (platform != null) {
			platform.setPlatformActionListener(listener);
			platform.showUser(null);
		}
	}

	/**
	 *
	 * @param platform 平台名称
	 * @param shareType 分享类型
	 */
	/**
	 * 用户信息的代码
	 */
	public void doUserInfo(Platform platform, String account) {
		if (platform != null) {
			platform.showUser(account);
		}
	}

	/**
	 *
	 * @param platform 平台名称
	 * @param shareType 分享类型
	 */
	/**
	 * 用户信息的代码
	 */
	public void doUserInfo(Platform platform, String account, PlatformActionListener listener) {
		if (platform != null) {
			platform.setPlatformActionListener(listener);
			platform.showUser(account);
		}
	}

	/**
	 * 回调代码
	 */
	class MyPlatformActionListener implements PlatformActionListener {
		@Override
		public void onComplete(final Platform platform, int i, final HashMap<String, Object> hashMap) {
			activity.runOnUiThread(new Runnable() {
				@Override
				public void run() {
					Toast.makeText(MobSDK.getContext(), "Authorize Complete.", Toast.LENGTH_SHORT).show();
					if(platform.getName().equals("ShortMessage") && hashMap != null) {
						Toast.makeText(MobSDK.getContext(), "ShoreMessage Login Info:" + hashMap.toString(), Toast.LENGTH_LONG).show();
					}
				}
			});
		}

		@Override
		public void onError(Platform platform, int i, Throwable throwable) {
			throwable.printStackTrace();
			activity.runOnUiThread(new Runnable() {
				@Override
				public void run() {
					Toast.makeText(MobSDK.getContext(), "Authorize Failure", Toast.LENGTH_SHORT).show();
				}
			});
		}

		@Override
		public void onCancel(Platform platform, int i) {
			Toast.makeText(MobSDK.getContext(), "Cancel Authorize", Toast.LENGTH_SHORT).show();
		}
	}

}
