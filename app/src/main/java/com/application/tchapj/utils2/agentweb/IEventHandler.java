package com.application.tchapj.utils2.agentweb;

import android.view.KeyEvent;

/**
 * @author cenxiaozhong
 * @since 1.0.0
 */
public interface IEventHandler {

    boolean onKeyDown(int keyCode, KeyEvent event);


    boolean back();
}
