package com.application.tchapj.main.view;

import com.application.tchapj.base.BaseMvpView;
import com.application.tchapj.main.bean.MessagenotificationBean;

/**
 * Created by 安卓开发 on 2018/8/10.
 */

public interface IMessagenoficationView extends BaseMvpView {

    // 得到小红点消息
    void onGetMessagenotificationResult(MessagenotificationBean messagenotificationBean);

}
