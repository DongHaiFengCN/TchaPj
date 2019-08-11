package com.application.tchapj.consultation.view;

import com.application.tchapj.base.BaseBean;
import com.application.tchapj.base.BaseMvpView;
import com.application.tchapj.consultation.bean.AddNewsCommentResultBean;
import com.application.tchapj.consultation.bean.CommentsResultBean;
import com.application.tchapj.consultation.bean.NewsAttentionResultBean;
import com.application.tchapj.consultation.bean.NewsCommentResultBean;
import com.application.tchapj.consultation.bean.ZanBean;

public interface IFindDetailView extends BaseMvpView {
    void getNewsCommentsResult(NewsCommentResultBean data);

    void getAddNewsCommentsResult(BaseBean<AddNewsCommentResultBean> baseBean);

    void getNewsAttentionResult(BaseBean<NewsAttentionResultBean> baseBean);

    void getNewsCommentsReplyResult(BaseBean<CommentsResultBean> baseBean);

    void onGetZanBeanResult(ZanBean zanBean);
}
