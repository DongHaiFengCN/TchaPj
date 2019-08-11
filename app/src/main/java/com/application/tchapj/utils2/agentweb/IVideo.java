package com.application.tchapj.utils2.agentweb;

import android.view.View;
import android.webkit.WebChromeClient;


/**
 * @author cenxiaozhong
 * @date 2017/6/10
 * @since 2.0.0
 */
public interface IVideo {


    void onShowCustomView(View view, WebChromeClient.CustomViewCallback callback);


    void onHideCustomView();


    boolean isVideoState();

}
