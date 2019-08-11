package com.application.tchapj.my.view;

import com.application.tchapj.base.BaseBean;
import com.application.tchapj.base.BaseMvpView;
import com.application.tchapj.main.bean.MemberInfoWhbyBean;
import com.application.tchapj.my.bean.AlipayPowerBean;
import com.application.tchapj.my.bean.AlipayPrivateKeyBean;
import com.application.tchapj.my.bean.QiniuBean;
import com.application.tchapj.my.bean.UpMyInfoBean;


// 支付宝授权
public interface IMyAlipayView extends BaseMvpView {

    // 得到七牛Token
    void onGetQiniuBeanResult(QiniuBean qiniuBean);

    void onGetAlipayPrivateKeyBeanResult(AlipayPrivateKeyBean alipayPrivateKeyBean);

    void onGetAlipayPowerBeanResult(AlipayPowerBean alipayPowerBean);

    void onGetUpMyInfoBeanResult(UpMyInfoBean upMyInfoBean);

    void onGetMemberWhbyhResult(MemberInfoWhbyBean infoWhbyBean);//获取微呼百应号信息

    void onUpdateWhbyInfoResult(BaseBean baseBean);//修改微呼百应号信息

}
