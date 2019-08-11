package com.application.tchapj.login.view;

import com.application.tchapj.base.BaseMvpView;
import com.application.tchapj.bean.UserInfo;
import com.application.tchapj.login.bean.LoginResult;
import com.application.tchapj.login.bean.NewPhoneLoginResult;

// 登录的View层接口
public interface ILoginView extends BaseMvpView {

    // 得到用户的二级包裹的外层数据
    void onGetLoginResult(LoginResult loginResultBean);

    // 得到用户的二级包裹的里层数据
    void onGetLoginResultUsInfo(UserInfo loginInfo);

    // 得到用户的二级包裹的外层数据密码数据
    void onGetUpdateResult(LoginResult loginResultBean);

    // 得到用户的二级包裹的外层数据注册数据
    void onGetRegisterResult(LoginResult loginResultBean);

    // 得到用户的二级包裹的外层数据验证码数据
    void onGetSmsCodeResult(LoginResult loginResultBean);

    // 得到用户的二级包裹的外层数据验证码数据
    void onGetNewPhoneLoginResult(NewPhoneLoginResult newPhoneLoginResult);

    // 得到用户的二级包裹的外层数据验证码数据
    void onGetThirdLoginResult(NewPhoneLoginResult newPhoneLoginResult);

}
