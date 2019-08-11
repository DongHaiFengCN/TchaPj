package com.application.tchapj.utils2.agentweb;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.webkit.ValueCallback;


/**
 * @author cenxiaozhong
 * @date 2017/5/29
 * @since 1.0.0
 */
public interface QuickCallJs {
    @RequiresApi(Build.VERSION_CODES.KITKAT)
    void quickCallJs(String method, ValueCallback<String> callback, String... params);

    void quickCallJs(String method, String... params);

    void quickCallJs(String method);


}
