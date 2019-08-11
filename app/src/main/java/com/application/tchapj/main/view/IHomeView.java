package com.application.tchapj.main.view;

import com.application.tchapj.base.BaseMvpView;
import com.application.tchapj.main.bean.HomeCircleModel;
import com.application.tchapj.main.bean.HomeMediaList;
import com.application.tchapj.main.bean.HomePerson;
import com.application.tchapj.main.bean.HomeTopData;
import com.application.tchapj.my.bean.AlipayPrivateKeyBean;
import com.application.tchapj.my.bean.QiniuBean;

// 主页的View层接口
public interface IHomeView extends BaseMvpView {

    // 得到轮播图
    void onGetHomeBannerResult(HomeTopData homeTopData);

    // 得到找名人
    void onGetHomePersonResult(HomePerson homePerson);

    // 得到找媒体
    void onGetHomeMediaListResult(HomeMediaList homeMediaList);

    // 得到找圈子
    void onGetHomeCircleModelResult(HomeCircleModel homeCircleModel);

    // 得到七牛Token
    void onGetQiniuBeanResult(QiniuBean qiniuBean);

    //获取支付宝私钥
    void onGetAlipayPrivateKeyBeanResult(AlipayPrivateKeyBean alipayPrivateKeyBean);


}
