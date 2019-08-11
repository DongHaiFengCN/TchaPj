package com.application.tchapj.widiget;

import android.app.Activity;
import android.graphics.drawable.Drawable;


// 状态栏Helper工具类
class StatusBarHelperImplBase extends StatusBarHelperImpl {

    public StatusBarHelperImplBase(Activity activity) {
        super(activity);
    }

    @Override
    protected void setColor(int color) {
        // do noting
    }

    @Override
    protected void setDrawable(Drawable drawable) {
        // do noting
    }

    @Override
    protected void destroy() {
        // do noting
    }

}
