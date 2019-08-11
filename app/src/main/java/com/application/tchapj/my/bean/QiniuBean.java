package com.application.tchapj.my.bean;

import java.io.Serializable;

/**
 * Created by 安卓开发 on 2018/8/1.
 */

public class QiniuBean implements Serializable {

    private String method;     //接口名
    private String level;      //接口信息
    private String code;       //接口返回结果
    private String description;//接口返回信息

    private QiniuBeanResult data;

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

    public QiniuBeanResult getData() {
        return data;
    }

    public void setData(QiniuBeanResult data) {
        this.data = data;
    }

    public static class QiniuBeanResult {

        private String uploadToken;

        public String getUploadToken() {
            return uploadToken;
        }

        public void setUploadToken(String uploadToken) {
            this.uploadToken = uploadToken;
        }
    }

}
