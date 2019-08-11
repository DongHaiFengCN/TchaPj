package com.application.tchapj.bean;

import java.io.Serializable;
import java.util.List;

// 教学周
public class WeekBean implements Serializable {

    private String code;
    private String msg;
    private String success;
    private List<WeekData> data;

    public List<WeekData> getData() {
        return data;
    }

    public void setData(List<WeekData> data) {
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public static class WeekData{

            private String endDate;
            private String id;
            private String startDate;
            private String week;

        public String getEndDate() {
            return endDate;
        }

        public void setEndDate(String endDate) {
            this.endDate = endDate;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getStartDate() {
            return startDate;
        }

        public void setStartDate(String startDate) {
            this.startDate = startDate;
        }

        public String getWeek() {
            return week;
        }

        public void setWeek(String week) {
            this.week = week;
        }
    }

}
