package com.application.tchapj.task.view;

import com.application.tchapj.base.BaseBean;
import com.application.tchapj.base.BaseMvpView;
import com.application.tchapj.task.bean.DailyTaskDouyinModel;
import com.application.tchapj.task.bean.TaskSquareInfoModel;

/**
 * Created by 安卓开发 on 2018/7/25.
 */

public interface DailyTaskDouyinFaView extends BaseMvpView {

    // 得到咨询数据
    void onGetDailyTaskDouyinModels(TaskSquareInfoModel dailyTaskDouyinModel);

    /**
     * 领取任务
     */
    void ledTask(BaseBean baseBean);

    /**
     * 提交审核资料
     * @param baseBean
     */
    void submitAuditData(BaseBean baseBean);

}
