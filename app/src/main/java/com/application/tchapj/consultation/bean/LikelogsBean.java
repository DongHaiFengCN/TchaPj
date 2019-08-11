package com.application.tchapj.consultation.bean;

import java.io.Serializable;

public class LikelogsBean implements Serializable {

    private String nickName; // 点赞的昵称

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}
