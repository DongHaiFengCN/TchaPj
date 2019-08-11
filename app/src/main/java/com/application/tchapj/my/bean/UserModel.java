package com.application.tchapj.my.bean;

import com.application.tchapj.bean.UserInfo;

import java.io.Serializable;

/**
 * Created by tanger on 2018/3/28.
 */

public class UserModel implements Serializable {
    private String code;
    private String description;
    private UserInfo data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UserInfo getData() {
        return data;
    }

    public void setData(UserInfo data) {
        this.data = data;
    }


}
