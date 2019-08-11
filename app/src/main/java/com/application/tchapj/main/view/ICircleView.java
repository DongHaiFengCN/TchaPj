package com.application.tchapj.main.view;

import com.application.tchapj.base.BaseMvpView;
import com.application.tchapj.main.bean.HomeCircleInfoModel;
import com.application.tchapj.main.bean.HomeCircleModel;

/**
 * Created by 安卓开发 on 2018/8/9.
 */

public interface ICircleView extends BaseMvpView {

    // 得到找圈子
    void onGetHomeCircleModelResult(HomeCircleModel homeCircleModel);

    // 得到找圈子
    void onGetHomeCircleInfoModelResult(HomeCircleInfoModel homeCircleInfoModel);

}
