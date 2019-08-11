package com.application.tchapj.utils2.qiniu.utils;

import android.os.Handler;
import android.os.Looper;

/**
 * Created by bailong on 14/10/22.
 */
public final class AsyncRun {
    public static void runInMain(Runnable r) {
        Handler h = new Handler(Looper.getMainLooper());
        h.post(r);
    }

    public static void runInBack(Runnable r) {

    }
}
