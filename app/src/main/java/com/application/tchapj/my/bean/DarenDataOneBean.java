package com.application.tchapj.my.bean;

import java.io.Serializable;

/**
 * Created by 安卓开发 on 2018/8/21.
 */

public class DarenDataOneBean implements Serializable {

    private String method;     //接口名
    private String level;      //接口信息
    private String code;       //接口返回结果
    private String description;

    private DarenDataOneBeanResult data;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public DarenDataOneBeanResult getData() {
        return data;
    }

    public void setData(DarenDataOneBeanResult data) {
        this.data = data;
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

    public static class DarenDataOneBeanResult {

        private String taskApplyId;

        public String getTaskApplyId() {
            return taskApplyId;
        }

        public void setTaskApplyId(String taskApplyId) {
            this.taskApplyId = taskApplyId;
        }
    }


}
