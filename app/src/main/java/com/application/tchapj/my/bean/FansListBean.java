package com.application.tchapj.my.bean;

import java.io.Serializable;
import java.util.List;

public class FansListBean implements Serializable {

    List<FansInfoBean> fansList;

    public List<FansInfoBean> getFansList() {
        return fansList;
    }

    public void setFansList(List<FansInfoBean> fansList) {
        this.fansList = fansList;
    }
}
