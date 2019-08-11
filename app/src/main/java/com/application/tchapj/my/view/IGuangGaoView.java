package com.application.tchapj.my.view;

import com.application.tchapj.base.BaseBean;
import com.application.tchapj.base.BaseMvpView;
import com.application.tchapj.bean.SmsCodeBean;
import com.application.tchapj.login.bean.LoginResult;
import com.application.tchapj.my.bean.GuanggaoBean;

/**
 * Created by Administrator on 2018\8\23 0023.
 */

public interface IGuangGaoView extends BaseMvpView {

    // 手机验证码数据
    void onGetSmsCodeResult(BaseBean<SmsCodeBean> baseBean);

    // 上传广告主资料
    void onGetGuangGaoResult(GuanggaoBean guanggaoBean);
}
