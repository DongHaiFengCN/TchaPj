package com.application.tchapj.consultation.bean;

import java.io.Serializable;

public class CommentlogsBean implements Serializable {


    private String content;  // 评论内容
    private String memberId; // 评论昵称
    private long createTime;
    private int likes;
    private int index;
    private int rowCountPerPage;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getRowCountPerPage() {
        return rowCountPerPage;
    }

    public void setRowCountPerPage(int rowCountPerPage) {
        this.rowCountPerPage = rowCountPerPage;
    }
}
