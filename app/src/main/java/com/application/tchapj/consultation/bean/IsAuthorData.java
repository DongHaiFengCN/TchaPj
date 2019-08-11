package com.application.tchapj.consultation.bean;

import com.application.tchapj.base.BaseModel;

public class IsAuthorData extends BaseModel {

    private String isAuthor;//0：未认证 1 已认证

    public String getIsAuthor() {
        return isAuthor;
    }

    public void setIsAuthor(String isAuthor) {
        this.isAuthor = isAuthor;
    }
}
