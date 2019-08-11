package com.application.tchapj.task.view;

import com.application.tchapj.base.BaseMvpView;
import com.application.tchapj.task.bean.MyTaskSquareModel;

// 我的任务列表的View层接口
public interface MyTaskSquareView extends BaseMvpView {

    // 得到咨询数据
    void onGetMyFaTaskSquareModels(MyTaskSquareModel mytaskSquareModel);

    // 得到咨询数据
    void onGetMyTaskSquareModels(MyTaskSquareModel mytaskSquareModel);

}
