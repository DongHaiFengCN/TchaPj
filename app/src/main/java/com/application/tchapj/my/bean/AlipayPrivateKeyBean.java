package com.application.tchapj.my.bean;

import java.io.Serializable;

/**
 * Created by 安卓开发 on 2018/8/16.
 */

public class AlipayPrivateKeyBean implements Serializable {

    private String method;     //接口名
    private String level;      //接口信息
    private String code;       //接口返回结果
    private String description;       //接口返回内容

    private AlipayPrivateKeyResult data;


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

    public AlipayPrivateKeyResult getData() {
        return data;
    }

    public void setData(AlipayPrivateKeyResult data) {
        this.data = data;
    }

    public static class AlipayPrivateKeyResult {

        private String privatekey;
        private String signtype;

        public String getPrivatekey() {
            return privatekey;
        }

        public void setPrivatekey(String privatekey) {
            this.privatekey = privatekey;
        }

        public String getSigntype() {
            return signtype;
        }

        public void setSigntype(String signtype) {
            this.signtype = signtype;
        }
    }

}
