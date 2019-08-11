package com.application.tchapj.task.view;

import com.application.tchapj.base.BaseMvpView;
import com.application.tchapj.my.bean.QiniuBean;
import com.application.tchapj.task.bean.FriendReleaseBean;
import com.application.tchapj.task.bean.TaskSquareInfoModel;
import com.application.tchapj.task.bean.TaskSquareModel;

// 任务广场的View层接口
public interface ITaskSquareView extends BaseMvpView {

    // 得到七牛Token
    void onGetQiniuBeanResult(QiniuBean qiniuBean);

    // 得到任务广场数据
    void onGetTaskSquareModels(TaskSquareModel taskSquareModel);

    /**
     * 任务详情
     *
     * @param friendReleaseBean
     */
    void onGetFriendReleaseData(TaskSquareInfoModel friendReleaseBean);

}
