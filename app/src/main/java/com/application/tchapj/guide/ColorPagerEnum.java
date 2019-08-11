package com.application.tchapj.guide;

import com.application.tchapj.R;


public enum ColorPagerEnum {

    RED(R.layout.view_red),
    green(R.layout.view_green),
    BLUE(R.layout.view_blue);

    private int layoutResId;

    ColorPagerEnum(int layoutResId) {
        this.layoutResId = layoutResId;
    }

    public int getLayoutResId() {
        return layoutResId;
    }

}
