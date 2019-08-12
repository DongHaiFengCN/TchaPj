package com.application.tchapj.my.view;

import com.application.tchapj.base.BaseMvpView;
import com.application.tchapj.login.bean.LoginResult;
import com.application.tchapj.login.bean.SmsCodeResponse;
import com.application.tchapj.my.bean.MicroInfoBean;
import com.application.tchapj.my.bean.MicroTabBean;

/**
 * Created by 安卓开发 on 2018/8/1.
 */

public interface IMicroView extends BaseMvpView {

    void onGetMicroTabBeanResult(MicroTabBean dicroTabBean);

    // 数据验证码数据
    void onGetSmsCodeResult(SmsCodeResponse loginResultBean);

    // 认证数据
    void onGetMicroInfoBeanResult(MicroInfoBean microInfoBean);

}
