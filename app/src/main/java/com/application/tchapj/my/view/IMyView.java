package com.application.tchapj.my.view;

import com.application.tchapj.base.BaseMvpView;
import com.application.tchapj.main.bean.NewsInfo;
import com.application.tchapj.my.bean.UserModel;

// 我的页面的View层接口
public interface IMyView extends BaseMvpView {



    // 得到用户的二级包裹的外层数据
    void onGetUserModelResult(UserModel userModelBean);

    // 得到用户的二级包裹的外层数据
    void onGetUserModelResultResume(UserModel userModelBean);

    // 得到小红点消息
    void onGetNewsInfoResult(NewsInfo.NewsInfoResult newsInfo);

}
