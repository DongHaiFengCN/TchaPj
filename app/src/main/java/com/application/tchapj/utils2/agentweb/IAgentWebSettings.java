package com.application.tchapj.utils2.agentweb;

import android.webkit.WebView;

/**
 * @author cenxiaozhong
 * @since 1.0.0
 */

public interface IAgentWebSettings<T extends android.webkit.WebSettings> {

    IAgentWebSettings toSetting(WebView webView);

    T getWebSettings();

}
