package com.application.tchapj.consultation.bean;

import java.io.Serializable;

public class    AddNewsCommentResultBean implements Serializable {

    private String content;
    private String isAuthor;
    private String commentId;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getIsAuthor() {
        return isAuthor;
    }

    public void setIsAuthor(String isAuthor) {
        this.isAuthor = isAuthor;
    }

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }
}
