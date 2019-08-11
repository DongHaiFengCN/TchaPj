package com.application.tchapj.task.view;

import com.application.tchapj.base.BaseBean;
import com.application.tchapj.base.BaseMvpView;
import com.application.tchapj.task.bean.DailyTaskDouyinModel;
import com.application.tchapj.task.bean.TaskSquareInfoModel;

/**
 * Created by 安卓开发 on 2018/7/25.
 */

public interface DailyTaskDouyinView extends BaseMvpView {

    // 得到咨询数据
    void onGetDailyTaskDouyinModels(TaskSquareInfoModel dailyTaskDouyinModel);

    // 递名片
    void onTaskDouyin(BaseBean baseBean);

}
