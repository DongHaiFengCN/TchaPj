package com.application.tchapj.widiget;

import java.util.List;

// 标签 单选 多选 监听接口
public interface OnTagSelectListener {
    void onItemSelect(FlowTagLayout parent, int positoin, List<Integer> selectedList);
}
