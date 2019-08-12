package com.application.tchapj.my.view;

import com.application.tchapj.base.BaseMvpView;
import com.application.tchapj.login.bean.LoginResult;
import com.application.tchapj.login.bean.SmsCodeResponse;
import com.application.tchapj.my.bean.DarenDataBean;
import com.application.tchapj.my.bean.DarenDataOneBean;

/**
 * Created by 安卓开发 on 2018/8/20.
 */

public interface IDarenOneView extends BaseMvpView {

    // 手机验证码数据
    void onGetSmsCodeResult(SmsCodeResponse loginResultBean);

    // 上传达人资料数据
    void onGetDarenDataOneBeanResult(DarenDataOneBean darenDataOneBean);

    // 上传达人频道数据
    void onGetDarenDataBeanResult(DarenDataBean darenDataBean);

}
