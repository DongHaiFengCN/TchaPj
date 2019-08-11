package com.application.tchapj.task.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2018\8\27 0027.
 */

public class FaTaskSuccessBean implements Serializable {

    private String method;     //接口名
    private String level;      //接口信息
    private String code;       //接口返回结果

    private FaTaskSuccessBeanResult data;

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

    public FaTaskSuccessBeanResult getData() {
        return data;
    }

    public void setData(FaTaskSuccessBeanResult data) {
        this.data = data;
    }

    public static class FaTaskSuccessBeanResult {

        private String orderString;

        public String getOrderString() {
            return orderString;
        }

        public void setOrderString(String orderString) {
            this.orderString = orderString;
        }
    }

}
