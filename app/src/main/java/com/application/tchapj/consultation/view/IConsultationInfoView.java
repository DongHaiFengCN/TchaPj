package com.application.tchapj.consultation.view;

import com.application.tchapj.base.BaseModel;
import com.application.tchapj.base.BaseMvpView;
import com.application.tchapj.consultation.bean.ConsultationNewsModel;
import com.application.tchapj.consultation.bean.InsertComments;
import com.application.tchapj.consultation.bean.ZanBean;
import com.application.tchapj.my.bean.UserModel;

// 推荐信息的View层接口
public interface IConsultationInfoView extends BaseMvpView {

    // 得到全部发现数据
    void onGetConsultationInfoResultAll(ConsultationNewsModel consultationNewsModel);

    // 得到其他的发现数据
    void onGetConsultationInfoResult(ConsultationNewsModel consultationNewsModel);

    // 得到发评论数据
    void onGetInsertCommentsResult(InsertComments insertComments);

    // 得到点赞数据
    void onGetZanBeanResult(ZanBean zanBean);

    // 得到用户的二级包裹的外层数据
    void onGetUserModelResult(UserModel userModelBean);

    //删除文章
    void onDeleteNewsRespon(BaseModel baseModel);

}
