package com.application.tchapj.my.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018\9\7 0007.
 */

public class MoneyInfoListBean implements Serializable {

    private String method;     //接口名
    private String level;      //接口信息
    private String code;       //接口返回结果

    private MoneyInfoListBeanResult data;

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

    public MoneyInfoListBeanResult getData() {
        return data;
    }

    public void setData(MoneyInfoListBeanResult data) {
        this.data = data;
    }

    public static class MoneyInfoListBeanResult {

        private List<MoneyInfoTaskLogs> taskLogs; // 收益

        private List<MoneyInfoTasks> tasks;       // 花费

        public List<MoneyInfoTaskLogs> getTaskLogs() {
            return taskLogs;
        }

        public void setTaskLogs(List<MoneyInfoTaskLogs> taskLogs) {
            this.taskLogs = taskLogs;
        }

        public List<MoneyInfoTasks> getTasks() {
            return tasks;
        }

        public void setTasks(List<MoneyInfoTasks> tasks) {
            this.tasks = tasks;
        }

        public class MoneyInfoTaskLogs {

            private String name;     // 活动名称
            private String taskType; // 0 朋友圈 1 微博 2 抖音合拍 3 抖音原创 4其他
            private double salary;      // 收益

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getTaskType() {
                return taskType;
            }

            public void setTaskType(String taskType) {
                this.taskType = taskType;
            }

            public double getSalary() {
                return salary;
            }

            public void setSalary(double salary) {
                this.salary = salary;
            }
        }

        public class MoneyInfoTasks {

            private String name;     // 活动名称
            private String taskType; // 0 朋友圈 1 微博 2 抖音合拍 3 抖音原创 4其他
            private double money;      // 付款金额

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getTaskType() {
                return taskType;
            }

            public void setTaskType(String taskType) {
                this.taskType = taskType;
            }

            public double getMoney() {
                return money;
            }

            public void setMoney(double money) {
                this.money = money;
            }
        }

    }

}
