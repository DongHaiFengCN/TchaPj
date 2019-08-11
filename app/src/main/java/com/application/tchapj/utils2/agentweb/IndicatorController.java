package com.application.tchapj.utils2.agentweb;

import android.webkit.WebView;

/**
 * @author cenxiaozhong
 * @update 4.0.0
 * @since 1.0.0
 */

public interface IndicatorController {

    void progress(WebView v, int newProgress);

    BaseIndicatorSpec offerIndicator();

    void showIndicator();

    void setProgress(int newProgress);

    void finish();
}
