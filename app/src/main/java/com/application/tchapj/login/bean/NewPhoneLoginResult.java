package com.application.tchapj.login.bean;

import java.io.Serializable;

/**
 * Created by 安卓开发 on 2018/7/30.
 */

public class NewPhoneLoginResult implements Serializable {

    private String method;     //接口名
    private String level;      //接口信息
    private String code;       //接口返回结果
    private String description;//接口返回信息
    private NewLogindataResult data;

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

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

    public NewLogindataResult getData() {
        return data;
    }

    public void setData(NewLogindataResult data) {
        this.data = data;
    }

    public static class NewLogindataResult {

        private String memberId;              //id

        public String getMemberId() {
            return memberId;
        }

        public void setMemberId(String memberId) {
            this.memberId = memberId;
        }
    }

}
