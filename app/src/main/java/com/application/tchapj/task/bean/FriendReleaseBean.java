package com.application.tchapj.task.bean;

public class FriendReleaseBean {

    private String code;
    private DataBean data;
    private String description;
    private String level;
    private String method;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public static class DataBean {

        private String status;
        private TaskBean task;
        private String  taskstatus = "0";

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public TaskBean getTask() {
            return task;
        }

        public void setTask(TaskBean task) {
            this.task = task;
        }

        public String getTaskstatus() {
            return taskstatus;
        }

        public void setTaskstatus(String taskstatus) {
            this.taskstatus = taskstatus;
        }

        public static class TaskBean {

            private String circleTypeId;
            private String copywriting;
            private long createTime;
            private long endTime;
            private int fans;
            private String id;
            private String imgUrl;
            private int index;
            private String iosType;
            private String memberId;
            private double money;
            private String name;
            private String nickName;
            private int receiveTotal;
            private String require;
            private int rowCountPerPage;
            private long startTime;
            private String status;
            private String taskGuidance;
            private String taskImgUrl;
            private int taskNum;
            private String taskType;
            private double unitPrice;

            private String ForwardUrl;

            public String getCircleTypeId() {
                return circleTypeId;
            }

            public void setCircleTypeId(String circleTypeId) {
                this.circleTypeId = circleTypeId;
            }

            public String getCopywriting() {
                return copywriting;
            }

            public void setCopywriting(String copywriting) {
                this.copywriting = copywriting;
            }

            public long getCreateTime() {
                return createTime;
            }

            public void setCreateTime(long createTime) {
                this.createTime = createTime;
            }

            public long getEndTime() {
                return endTime;
            }

            public void setEndTime(long endTime) {
                this.endTime = endTime;
            }

            public int getFans() {
                return fans;
            }

            public void setFans(int fans) {
                this.fans = fans;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getImgUrl() {
                return imgUrl;
            }

            public void setImgUrl(String imgUrl) {
                this.imgUrl = imgUrl;
            }

            public int getIndex() {
                return index;
            }

            public void setIndex(int index) {
                this.index = index;
            }

            public String getIosType() {
                return iosType;
            }

            public void setIosType(String iosType) {
                this.iosType = iosType;
            }

            public String getMemberId() {
                return memberId;
            }

            public void setMemberId(String memberId) {
                this.memberId = memberId;
            }

            public double getMoney() {
                return money;
            }

            public void setMoney(double money) {
                this.money = money;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getNickName() {
                return nickName;
            }

            public void setNickName(String nickName) {
                this.nickName = nickName;
            }

            public int getReceiveTotal() {
                return receiveTotal;
            }

            public void setReceiveTotal(int receiveTotal) {
                this.receiveTotal = receiveTotal;
            }

            public String getRequire() {
                return require;
            }

            public void setRequire(String require) {
                this.require = require;
            }

            public int getRowCountPerPage() {
                return rowCountPerPage;
            }

            public void setRowCountPerPage(int rowCountPerPage) {
                this.rowCountPerPage = rowCountPerPage;
            }

            public long getStartTime() {
                return startTime;
            }

            public void setStartTime(long startTime) {
                this.startTime = startTime;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getTaskGuidance() {
                return taskGuidance;
            }

            public void setTaskGuidance(String taskGuidance) {
                this.taskGuidance = taskGuidance;
            }

            public String getTaskImgUrl() {
                return taskImgUrl;
            }

            public void setTaskImgUrl(String taskImgUrl) {
                this.taskImgUrl = taskImgUrl;
            }

            public int getTaskNum() {
                return taskNum;
            }

            public void setTaskNum(int taskNum) {
                this.taskNum = taskNum;
            }

            public String getTaskType() {
                return taskType;
            }

            public void setTaskType(String taskType) {
                this.taskType = taskType;
            }

            public double getUnitPrice() {
                return unitPrice;
            }

            public void setUnitPrice(double unitPrice) {
                this.unitPrice = unitPrice;
            }

            public String getForwardUrl() {
                return ForwardUrl;
            }

            public void setForwardUrl(String forwardUrl) {
                ForwardUrl = forwardUrl;
            }

        }
    }
}
