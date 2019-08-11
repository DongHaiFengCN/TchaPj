package com.application.tchapj.utils2.download;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.application.tchapj.utils2.agentweb.LogUtils;


/**
 * @author cenxiaozhong
 * @date 2018/2/12
 */
public class NotificationCancelReceiver extends BroadcastReceiver {

	public static final String ACTION = "com.agentweb.cancelled";

	public NotificationCancelReceiver() {
	}

	@Override
	public void onReceive(Context context, Intent intent) {
		String action = intent.getAction();
		if (action.equals(ACTION)) {
			try {
				String url = intent.getStringExtra("TAG");
				CancelDownloadInformer.getInformer().cancelAction(url);
			} catch (Throwable ignore) {
				if (LogUtils.isDebug()) {
					ignore.printStackTrace();
				}
			}

		}
	}
}