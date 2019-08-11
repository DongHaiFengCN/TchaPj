package com.application.tchapj.consultation.bean;

import java.io.Serializable;

public class CommentReplyInfoBean implements Serializable {

    private String id;
    private Integer replyType;
    private String replyContent;
    private String toMemberId;
    private String fromMemberId;
    private String createTime;
    private String toNickName;
    private String toHeaderUrl;
    private String fromNickName;
    private String fromHeaderUrl;
    private String author;
    private Integer index;
    private Integer rowCountPerPage;
    private String state;
    private Integer likes = 0;

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getReplyType() {
        return replyType;
    }

    public void setReplyType(Integer replyType) {
        this.replyType = replyType;
    }

    public String getReplyContent() {
        return replyContent;
    }

    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent;
    }

    public String getToMemberId() {
        return toMemberId;
    }

    public void setToMemberId(String toMemberId) {
        this.toMemberId = toMemberId;
    }

    public String getFromMemberId() {
        return fromMemberId;
    }

    public void setFromMemberId(String fromMemberId) {
        this.fromMemberId = fromMemberId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getToNickName() {
        return toNickName;
    }

    public void setToNickName(String toNickName) {
        this.toNickName = toNickName;
    }

    public String getToHeaderUrl() {
        return toHeaderUrl;
    }

    public void setToHeaderUrl(String toHeaderUrl) {
        this.toHeaderUrl = toHeaderUrl;
    }

    public String getFromNickName() {
        return fromNickName;
    }

    public void setFromNickName(String fromNickName) {
        this.fromNickName = fromNickName;
    }

    public String getFromHeaderUrl() {
        return fromHeaderUrl;
    }

    public void setFromHeaderUrl(String fromHeaderUrl) {
        this.fromHeaderUrl = fromHeaderUrl;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public Integer getRowCountPerPage() {
        return rowCountPerPage;
    }

    public void setRowCountPerPage(Integer rowCountPerPage) {
        this.rowCountPerPage = rowCountPerPage;
    }
}
