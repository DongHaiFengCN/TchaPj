package com.application.tchapj.my.bean;

import java.io.Serializable;

/**
 * Created by 安卓开发 on 2018/8/16.
 */

public class AlipayPowerBean implements Serializable {

    private String method;     //接口名
    private String level;      //接口信息
    private String code;       //接口返回结果
    private String description;

    private AlipayBeanResult data;

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

    public AlipayBeanResult getData() {
        return data;
    }

    public void setData(AlipayBeanResult data) {
        this.data = data;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static class AlipayBeanResult {

        private String usrid;

        public String getUsrid() {
            return usrid;
        }

        public void setUsrid(String usrid) {
            this.usrid = usrid;
        }
    }

}
