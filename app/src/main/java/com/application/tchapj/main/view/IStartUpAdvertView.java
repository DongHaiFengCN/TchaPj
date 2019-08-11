package com.application.tchapj.main.view;

import com.application.tchapj.base.BaseBean;
import com.application.tchapj.base.BaseMvpView;
import com.application.tchapj.main.bean.HomeCircleInfoModel;
import com.application.tchapj.main.bean.HomeCircleModel;

/**
 * Created by 安卓开发 on 2018/8/9.
 */

public interface IStartUpAdvertView extends BaseMvpView {

    // 得到图片
    void onGetStartUpAdvertResult(BaseBean baseBean);


}
