package com.application.tchapj.consultation.bean;

import java.io.Serializable;
import java.util.List;

public class NewsCommentResultBean implements Serializable {

    private List<CommentInfoBean> list;
    private Integer counts;
    private Integer isAttention;

    public Integer getIsAttention() {
        return isAttention;
    }

    public void setIsAttention(Integer isAttention) {
        this.isAttention = isAttention;
    }

    public List<CommentInfoBean> getList() {
        return list;
    }

    public void setList(List<CommentInfoBean> list) {
        this.list = list;
    }

    public Integer getCounts() {
        return counts;
    }

    public void setCounts(Integer counts) {
        this.counts = counts;
    }
}
