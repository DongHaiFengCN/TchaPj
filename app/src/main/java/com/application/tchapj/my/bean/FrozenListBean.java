package com.application.tchapj.my.bean;

import java.io.Serializable;
import java.util.List;

public class FrozenListBean implements Serializable {

    private List<FrozenInfoBean> frozenlist;

    public List<FrozenInfoBean> getFrozenlist() {
        return frozenlist;
    }

    public void setFrozenlist(List<FrozenInfoBean> frozenlist) {
        this.frozenlist = frozenlist;
    }
}
