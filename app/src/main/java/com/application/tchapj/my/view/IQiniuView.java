package com.application.tchapj.my.view;

import com.application.tchapj.base.BaseMvpView;
import com.application.tchapj.my.bean.QiniuBean;
import com.application.tchapj.my.bean.UserModel;

/**
 * Created by 安卓开发 on 2018/8/1.
 */

public interface IQiniuView extends BaseMvpView {

    void onGetQiniuBeanResult(QiniuBean qiniuBean);

    void onGetUserModelResult(UserModel userModelBean);

}
