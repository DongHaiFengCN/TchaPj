package com.application.tchapj.my.view;

import com.application.tchapj.base.BaseMvpView;
import com.application.tchapj.main.bean.HomeCircleInfoModel;
import com.application.tchapj.main.bean.PersonMediaModel;
import com.application.tchapj.my.bean.FansInfoBean;

import java.util.List;

public interface IAttentionFanView extends BaseMvpView {


    void getAttentionCelebrityListResult(List<PersonMediaModel.PersonMediaModelResult.PersonMedia> personMediaList);//我关注的名人、媒体
    void getAttentionCircleListResult(List<HomeCircleInfoModel.HomeCircleInfoModelResult.HomeInfoCircle> homeInfoCircleList);//我关注的圈子

    void getFansListResult(List<FansInfoBean> fansInfoBeans);//我的粉丝
}
