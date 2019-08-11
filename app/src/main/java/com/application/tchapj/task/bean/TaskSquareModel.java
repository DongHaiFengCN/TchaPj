package com.application.tchapj.task.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/3/27.
 */

public class TaskSquareModel implements Serializable {

    private String method;     //接口名
    private String level;      //接口信息
    private String code;       //接口返回结果
    private String description;//接口返回信息

    private TaskSquareResult data;

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

    public TaskSquareResult getData() {
        return data;
    }

    public void setData(TaskSquareResult data) {
        this.data = data;
    }

    public static class TaskSquareResult {

        private List<TaskModel> lTasks;
        private List<FaTaskSquare> faTasks;
        private List<TaskModel> tasks;

        public List<LingTaskSquare> getLingTasks() {
            if (lingTasks == null) {
                return new ArrayList<>();
            }
            return lingTasks;
        }

        public List<FaTaskSquare> getFaTasks() {
            return faTasks;
        }

        public void setFaTasks(List<FaTaskSquare> faTasks) {
            this.faTasks = faTasks;
        }

        public List<TaskModel> getTasks() {
            return tasks;
        }

        public void setTasks(List<TaskModel> tasks) {
            this.tasks = tasks;
        }

        public void setLingTasks(List<LingTaskSquare> lingTasks) {
            this.lingTasks = lingTasks;
        }

        private List<LingTaskSquare> lingTasks;

        public List<TaskModel> getlTasks() {
            return lTasks;
        }

        public void setlTasks(List<TaskModel> lTasks) {
            this.lTasks = lTasks;
        }


        public List<FaTaskSquare> getFalTasks() {
            return faTasks;
        }

        public void setFalTasks(List<FaTaskSquare> faTasks) {
            this.faTasks = faTasks;
        }

        public class TaskSquare {

            private String id;
            private String name;
            private String imgUrl;       // 页面图片
            private long endTime;
            private String taskType; // vip标识
            private String nickName; // 内容
            private int index;
            private int rowCountPerPage;
            private int money;
            private long startTime;

            public double getUnitPrice() {
                return unitPrice;
            }

            public void setUnitPrice(double unitPrice) {
                this.unitPrice = unitPrice;
            }

            private double unitPrice;

            public String getNickName() {
                return nickName;
            }

            public void setNickName(String nickName) {
                this.nickName = nickName;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getImgUrl() {
                return imgUrl;
            }

            public void setImgUrl(String imgUrl) {
                this.imgUrl = imgUrl;
            }

            public long getEndTime() {
                return endTime;
            }

            public void setEndTime(long endTime) {
                this.endTime = endTime;
            }

            public long getStartTime() {
                return startTime;
            }

            public void setStartTime(long startTime) {
                this.startTime = startTime;
            }

            public String getTaskType() {
                return taskType;
            }

            public void setTaskType(String taskType) {
                this.taskType = taskType;
            }

            public int getIndex() {
                return index;
            }

            public void setIndex(int index) {
                this.index = index;
            }

            public int getRowCountPerPage() {
                return rowCountPerPage;
            }

            public void setRowCountPerPage(int rowCountPerPage) {
                this.rowCountPerPage = rowCountPerPage;
            }

            public int getMoney() {
                return money;
            }

            public void setMoney(int money) {
                this.money = money;
            }
        }

        public class FaTaskSquare {

            private String id;
            private String name;
            private String imgUrl;       // 页面图片
            private long endTime;
            private String taskType; // vip标识
            private String nickName; // 内容
            private int index;
            private int rowCountPerPage;
            private int money;

            public double getUnitPrice() {
                return unitPrice;
            }

            public void setUnitPrice(double unitPrice) {
                this.unitPrice = unitPrice;
            }

            private double unitPrice;

            public String getNickName() {
                return nickName;
            }

            public void setNickName(String nickName) {
                this.nickName = nickName;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getImgUrl() {
                return imgUrl;
            }

            public void setImgUrl(String imgUrl) {
                this.imgUrl = imgUrl;
            }

            public long getEndTime() {
                return endTime;
            }

            public void setEndTime(long endTime) {
                this.endTime = endTime;
            }

            public String getTaskType() {
                return taskType;
            }

            public void setTaskType(String taskType) {
                this.taskType = taskType;
            }

            public int getIndex() {
                return index;
            }

            public void setIndex(int index) {
                this.index = index;
            }

            public int getRowCountPerPage() {
                return rowCountPerPage;
            }

            public void setRowCountPerPage(int rowCountPerPage) {
                this.rowCountPerPage = rowCountPerPage;
            }

            public int getMoney() {
                return money;
            }

            public void setMoney(int money) {
                this.money = money;
            }
        }

        public class LingTaskSquare {

            private String id;
            private String name;
            private String imgUrl;       // 页面图片
            private long endTime;
            private String taskType; // vip标识
            private String nickName; // 内容
            private int index;
            private int rowCountPerPage;
            private int money;

            public double getUnitPrice() {
                return unitPrice;
            }

            public void setUnitPrice(double unitPrice) {
                this.unitPrice = unitPrice;
            }

            private double unitPrice;

            public String getNickName() {
                return nickName;
            }

            public void setNickName(String nickName) {
                this.nickName = nickName;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getImgUrl() {
                return imgUrl;
            }

            public void setImgUrl(String imgUrl) {
                this.imgUrl = imgUrl;
            }

            public long getEndTime() {
                return endTime;
            }

            public void setEndTime(long endTime) {
                this.endTime = endTime;
            }

            public String getTaskType() {
                return taskType;
            }

            public void setTaskType(String taskType) {
                this.taskType = taskType;
            }

            public int getIndex() {
                return index;
            }

            public void setIndex(int index) {
                this.index = index;
            }

            public int getRowCountPerPage() {
                return rowCountPerPage;
            }

            public void setRowCountPerPage(int rowCountPerPage) {
                this.rowCountPerPage = rowCountPerPage;
            }

            public int getMoney() {
                return money;
            }

            public void setMoney(int money) {
                this.money = money;
            }
        }

    }


}
