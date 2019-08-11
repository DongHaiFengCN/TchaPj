package com.application.tchapj.widiget;

import android.view.View;

// 流式标签布局 点击 监听接口
public interface OnTagClickListener {
    void onItemClick(FlowTagLayout parent, View view, int position);
}
