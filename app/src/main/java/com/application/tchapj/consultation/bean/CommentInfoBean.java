package com.application.tchapj.consultation.bean;

import java.io.Serializable;
import java.util.ArrayList;

public class CommentInfoBean implements Serializable {

    private String id;
    private String topicId;
    private String content;
    private String memberId;
    private long createTime;
    private Integer likes = 0;
    private String name;
    private String headimgurl;
    private String state;
    private String author;
    private Integer index;
    private Integer rowCountPerPage;
    private ArrayList<CommentReplyInfoBean> replyList;
    private Integer replyCounts = 0;

    public Integer getReplyCounts() {
        return replyCounts;
    }

    public void setReplyCounts(Integer replyCounts) {
        this.replyCounts = replyCounts;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public ArrayList<CommentReplyInfoBean> getReplyList() {
        return replyList;
    }

    public void setReplyList(ArrayList<CommentReplyInfoBean> replyList) {
        this.replyList = replyList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTopicId() {
        return topicId;
    }

    public void setTopicId(String topicId) {
        this.topicId = topicId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHeadimgurl() {
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
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
