package com.application.tchapj.task.view;

import com.application.tchapj.base.BaseMvpView;
import com.application.tchapj.task.bean.TaskSquareInfoModel;

/**
 * Created by 安卓开发 on 2018/7/25.
 */

public interface ITaskSquareInfoView extends BaseMvpView {

    // 得到咨询数据
    void onGetTaskSquareInfoModels(TaskSquareInfoModel taskSquareInfoModel);
}
