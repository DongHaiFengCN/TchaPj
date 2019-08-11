package com.application.tchapj.task.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2018\8\25 0025.
 */

public class FaTaskBean implements Serializable {

    private String method;     //接口名
    private String level;      //接口信息
    private String code;       //接口返回结果
    private String description;

    private FaTaskBeanResult data;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

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

    public FaTaskBeanResult getData() {
        return data;
    }

    public void setData(FaTaskBeanResult data) {
        this.data = data;
    }

    public static class FaTaskBeanResult {

        private String taskId;

        public String getTaskId() {
            return taskId;
        }

        public void setTaskId(String taskId) {
            this.taskId = taskId;
        }
    }
}
