package com.application.tchapj.login.view;

import com.application.tchapj.base.BaseBean;
import com.application.tchapj.base.BaseMvpView;
import com.application.tchapj.bean.BindingPhoneBean;
import com.application.tchapj.bean.SmsCodeBean;
import com.application.tchapj.login.bean.LoginResult;

/**
 * Create by zyy on 2019/4/24
 * Description:
 */
public interface IBindingPhoneView  extends BaseMvpView {

    // 三方登录绑定手机号
    void onThirdLoginBindingResult(BaseBean<BindingPhoneBean> loginResultBean);

    // 得到用户的二级包裹的外层数据验证码数据
    void onGetSmsCodeResult(BaseBean<SmsCodeBean> codeResultBean);


}
