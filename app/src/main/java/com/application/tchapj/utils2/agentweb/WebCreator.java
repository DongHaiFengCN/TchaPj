package com.application.tchapj.utils2.agentweb;

import android.webkit.WebView;
import android.widget.FrameLayout;

/**
 * @author cenxiaozhong
 * @since 1.0.0
 */
public interface WebCreator extends IWebIndicator {

    WebCreator create();

    WebView getWebView();

    FrameLayout getWebParentLayout();
}
