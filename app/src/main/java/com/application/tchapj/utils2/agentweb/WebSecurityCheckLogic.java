package com.application.tchapj.utils2.agentweb;

import android.support.v4.util.ArrayMap;
import android.webkit.WebView;

/**
 * @author cenxiaozhong
 */
public interface WebSecurityCheckLogic {
    void dealHoneyComb(WebView view);

    void dealJsInterface(ArrayMap<String, Object> objects, AgentWeb.SecurityType securityType);

}
