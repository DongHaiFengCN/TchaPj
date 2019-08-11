package com.application.tchapj.my.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/12/4.
 */

public class UpMyInfoBean implements Serializable {

    private String method;     //接口名
    private String level;      //接口信息
    private String code;       //接口返回结果

    /*private UpMyInfoResult data;*/

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

    /*public UpMyInfoResult getData() {
        return data;
    }

    public void setData(UpMyInfoResult data) {
        this.data = data;
    }

    public static class UpMyInfoResult {

        private String uploadToken;

        public String getUploadToken() {
            return uploadToken;
        }

        public void setUploadToken(String uploadToken) {
            this.uploadToken = uploadToken;
        }
    }*/

}
