package com.application.tchapj.consultation.view;

import com.application.tchapj.base.BaseBean;
import com.application.tchapj.base.BaseMvpView;
import com.application.tchapj.bean.UserInfo;
import com.application.tchapj.consultation.bean.ConsultationTopModel;
import com.application.tchapj.consultation.bean.IsAuthorData;
import com.application.tchapj.consultation.bean.UpdateAuthorData;
import com.application.tchapj.my.bean.UserModel;


public interface IConsultationTobView extends BaseMvpView {

    // 得到全部咨询数据
    void onGetConsultationTobResult(ConsultationTopModel consultationTopModel);

    // 查询是否为微呼百应会员
//    void onGetUserIsAuthor(IsAuthorData isAuthorData);

    // 开通微呼百应会员
    void onUpdateAuthor(BaseBean baseBean);

    void onGetMemberInfo(UserInfo userInfo);

}
