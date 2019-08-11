package com.application.tchapj.my.bean;

import com.application.tchapj.main.bean.HomeCircleInfoModel;
import com.application.tchapj.main.bean.PersonMediaModel;

import java.io.Serializable;
import java.util.List;

public class AttentionListBean implements Serializable{

    private List<PersonMediaModel.PersonMediaModelResult.PersonMedia> attentionbymrList;
    private List<PersonMediaModel.PersonMediaModelResult.PersonMedia> attentionbymtList;
    private List<HomeCircleInfoModel.HomeCircleInfoModelResult.HomeInfoCircle> attentionbyQzList;


    public List<PersonMediaModel.PersonMediaModelResult.PersonMedia> getAttentionbymrList() {
        return attentionbymrList;
    }

    public void setAttentionbymrList(List<PersonMediaModel.PersonMediaModelResult.PersonMedia> attentionbymrList) {
        this.attentionbymrList = attentionbymrList;
    }

    public List<PersonMediaModel.PersonMediaModelResult.PersonMedia> getAttentionbymtList() {
        return attentionbymtList;
    }

    public void setAttentionbymtList(List<PersonMediaModel.PersonMediaModelResult.PersonMedia> attentionbymtList) {
        this.attentionbymtList = attentionbymtList;
    }

    public List<HomeCircleInfoModel.HomeCircleInfoModelResult.HomeInfoCircle> getAttentionbyQzList() {
        return attentionbyQzList;
    }

    public void setAttentionbyQzList(List<HomeCircleInfoModel.HomeCircleInfoModelResult.HomeInfoCircle> attentionbyQzList) {
        this.attentionbyQzList = attentionbyQzList;
    }
}
