package com.application.tchapj.main.bean;

/**
 * Created by 安卓开发 on 2018/8/10.
 */

public class MessagenotificationBean {

    private String method;     //接口名
    private String level;      //接口信息
    private String code;       //接口返回结果
    private String description;//接口返回信息

    private MessagenotificationBeanResult data;

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

    public MessagenotificationBeanResult getData() {
        return data;
    }

    public void setData(MessagenotificationBeanResult data) {
        this.data = data;
    }

    public static class MessagenotificationBeanResult{

        private String createTime;
        private String title;

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }

}
