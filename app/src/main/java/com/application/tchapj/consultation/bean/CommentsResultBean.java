package com.application.tchapj.consultation.bean;

import java.io.Serializable;
import java.util.ArrayList;

public class CommentsResultBean implements Serializable {


    private int counts;//回复数
    private ArrayList<CommentReplyInfoBean> list;

    public int getCounts() {
        return counts;
    }

    public void setCounts(int counts) {
        this.counts = counts;
    }

    public ArrayList<CommentReplyInfoBean> getList() {
        return list;
    }

    public void setList(ArrayList<CommentReplyInfoBean> list) {
        this.list = list;
    }
}
