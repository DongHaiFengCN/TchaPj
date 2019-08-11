package com.application.tchapj.task.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/8/24.
 */

public class DailyTaskDouyinModel implements Serializable {


    /**
     * method : pm.task.detail
     * level : Info
     * code : 000
     * description : 获取任务详情成功！
     * data : {"task":{"id":"50fbb92166824eafbf25877884c81952","name":"抖音原创不要钱","imgUrl":"http://ou3hks27l.bkt.clouddn.com/2018-08-28_SjfLyXqu.png","startTime":1535532480000,"endTime":1537891200000,"createTime":1535446113000,"circleTypeId":"技术圈 记者圈 夕阳红 ","memberId":"50215135240440d3bc9eeedb0b267fa9","taskType":"3","require":"睡觉睡觉睡觉睡觉","phonenumber":"18363010437","fans":0,"receiveTotal":0,"taskNum":0,"status":"2","unitPrice":200,"nickName":"开创把子肉","index":0,"rowCountPerPage":0,"money":0}}
     */

    private String method;
    private String   level;
    private String   code;
    private String   description;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * task : {"id":"50fbb92166824eafbf25877884c81952","name":"抖音原创不要钱","imgUrl":"http://ou3hks27l.bkt.clouddn.com/2018-08-28_SjfLyXqu.png","startTime":1535532480000,"endTime":1537891200000,"createTime":1535446113000,"circleTypeId":"技术圈 记者圈 夕阳红 ","memberId":"50215135240440d3bc9eeedb0b267fa9","taskType":"3","require":"睡觉睡觉睡觉睡觉","phonenumber":"18363010437","fans":0,"receiveTotal":0,"taskNum":0,"status":"2","unitPrice":200,"nickName":"开创把子肉","index":0,"rowCountPerPage":0,"money":0}
         */

        private TaskBean task;

        public TaskBean getTask() {
            return task;
        }

        public void setTask(TaskBean task) {
            this.task = task;
        }

        public static class TaskBean {
            /**
             * id : 50fbb92166824eafbf25877884c81952
             * name : 抖音原创不要钱
             * imgUrl : http://ou3hks27l.bkt.clouddn.com/2018-08-28_SjfLyXqu.png
             * startTime : 1535532480000
             * endTime : 1537891200000
             * createTime : 1535446113000
             * circleTypeId : 技术圈 记者圈 夕阳红
             * memberId : 50215135240440d3bc9eeedb0b267fa9
             * taskType : 3
             * require : 睡觉睡觉睡觉睡觉
             * phonenumber : 18363010437
             * fans : 0
             * receiveTotal : 0
             * taskNum : 0
             * status : 2
             * unitPrice : 200.0
             * nickName : 开创把子肉
             * index : 0
             * rowCountPerPage : 0
             * money : 0.0
             */

            private String id;
            private String name;
            private String imgUrl;
            private long   startTime;
            private long   endTime;
            private long   createTime;
            private String circleTypeId;
            private String memberId;
            private String taskType;
            private String require;
            private String phonenumber;
            private int    fans;
            private int    receiveTotal;
            private int    taskNum;
            private String status;
            private double unitPrice;
            private String nickName;
            private int    index;
            private int    rowCountPerPage;
            private double money;

            private String taskGuidance;

            public String getTaskImgUrl() {
                return taskImgUrl;
            }

            public void setTaskImgUrl(String taskImgUrl) {
                this.taskImgUrl = taskImgUrl;
            }

            private String taskImgUrl;

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

            public long getStartTime() {
                return startTime;
            }

            public void setStartTime(long startTime) {
                this.startTime = startTime;
            }

            public long getEndTime() {
                return endTime;
            }

            public void setEndTime(long endTime) {
                this.endTime = endTime;
            }

            public long getCreateTime() {
                return createTime;
            }

            public void setCreateTime(long createTime) {
                this.createTime = createTime;
            }

            public String getCircleTypeId() {
                return circleTypeId;
            }

            public void setCircleTypeId(String circleTypeId) {
                this.circleTypeId = circleTypeId;
            }

            public String getMemberId() {
                return memberId;
            }

            public void setMemberId(String memberId) {
                this.memberId = memberId;
            }

            public String getTaskType() {
                return taskType;
            }

            public void setTaskType(String taskType) {
                this.taskType = taskType;
            }

            public String getRequire() {
                return require;
            }

            public void setRequire(String require) {
                this.require = require;
            }

            public String getPhonenumber() {
                return phonenumber;
            }

            public void setPhonenumber(String phonenumber) {
                this.phonenumber = phonenumber;
            }

            public int getFans() {
                return fans;
            }

            public void setFans(int fans) {
                this.fans = fans;
            }

            public int getReceiveTotal() {
                return receiveTotal;
            }

            public void setReceiveTotal(int receiveTotal) {
                this.receiveTotal = receiveTotal;
            }

            public int getTaskNum() {
                return taskNum;
            }

            public void setTaskNum(int taskNum) {
                this.taskNum = taskNum;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public double getUnitPrice() {
                return unitPrice;
            }

            public void setUnitPrice(double unitPrice) {
                this.unitPrice = unitPrice;
            }

            public String getNickName() {
                return nickName;
            }

            public void setNickName(String nickName) {
                this.nickName = nickName;
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

            public double getMoney() {
                return money;
            }

            public void setMoney(double money) {
                this.money = money;
            }

            public String getTaskGuidance() {
                return taskGuidance;
            }

            public void setTaskGuidance(String taskGuidance) {
                this.taskGuidance = taskGuidance;
            }
        }
    }
}
