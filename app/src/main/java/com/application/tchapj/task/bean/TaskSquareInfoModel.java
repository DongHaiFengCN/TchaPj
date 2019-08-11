package com.application.tchapj.task.bean;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by 安卓开发 on 2018/7/25.
 */

public class TaskSquareInfoModel implements Serializable {

    private String method;     //接口名
    private String level;      //接口信息
    private String code;       //接口返回结果
    private String description;//接口返回信息

    private TaskSquareInfoResult data;

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

    public TaskSquareInfoResult getData() {
        return data;
    }

    public void setData(TaskSquareInfoResult data) {
        this.data = data;
    }

    public static class TaskSquareInfoResult {

        private String status;
        private String taskstatus = "0";
        private long startTime;
        private long countDownTime;
        private int haveReceivedCount;//已经多少人参与
        private int receiveTotal;//剩余审核名额
        private String refusal;//任务审核，拒绝理由
        private String isDoTask;//0：已经提醒  1;需要提醒：说明没领过任务。（获取赏金名额有限，领任务后请尽快提交审核）
        private TaskSquareInfo task;

        public String getIsDoTask() {
            return isDoTask;
        }

        public void setIsDoTask(String isDoTask) {
            this.isDoTask = isDoTask;
        }
        public String getRefusal() {
            return refusal;
        }

        public void setRefusal(String refusal) {
            this.refusal = refusal;
        }
        public int getHaveReceivedCount() {
            return haveReceivedCount;
        }

        public void setHaveReceivedCount(int haveReceivedCount) {
            this.haveReceivedCount = haveReceivedCount;
        }

        public int getReceiveTotal() {
            return receiveTotal;
        }

        public void setReceiveTotal(int receiveTotal) {
            this.receiveTotal = receiveTotal;
        }

        public long getCountDownTime() {
            return countDownTime;
        }

        public void setCountDownTime(long countDownTime) {
            this.countDownTime = countDownTime;
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

        public String getTaskstatus() {
            return taskstatus;
        }

        public void setTaskstatus(String taskstatus) {
            this.taskstatus = taskstatus;
        }

        public TaskSquareInfo getTask() {
            return task;
        }

        public void setTask(TaskSquareInfo task) {
            this.task = task;
        }

        public class TaskSquareInfo {

            private String id;
            private String name;
            private String imgUrl;       // 页面图片
            private long startTime;
            private long endTime;
            private long createTime;

            private String tfPeoples;

            private String taskGuidance;   // vip标识
            private String memberId;     // vip标识
            private String taskImgUrl;      // vip标识
            private String taskType;       // 0 朋友圈 1 微博 2 抖音跟拍 3 抖音原创 4其他5朋友圈转发链接6微视合拍7微视原创
            private String require;           // vip标识
            private String copywriting;   // vip标识
            private String phonenumber;   // vip标识
            private int fans;
            private int receiveTotal;
            private int taskNum;//任务总量
            private String status;       // vip标识
            private BigDecimal unitPrice;
            private String nickName;      // vip标识
            private int index;
            private int rowCountPerPage;
            private int money;
            private String forwardUrl;
            private String circleTypeId;

            public String getCircleTypeId() {
                return circleTypeId;
            }

            public void setCircleTypeId(String circleTypeId) {
                this.circleTypeId = circleTypeId;
            }

            public String getForwardUrl() {
                return forwardUrl;
            }

            public void setForwardUrl(String forwardUrl) {
                this.forwardUrl = forwardUrl;
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

            public String getTfPeoples() {
                return tfPeoples;
            }

            public void setTfPeoples(String tfPeoples) {
                this.tfPeoples = tfPeoples;
            }

            public String getTaskGuidance() {
                return taskGuidance;
            }

            public void setTaskGuidance(String taskGuidance) {
                this.taskGuidance = taskGuidance;
            }

            public String getMemberId() {
                return memberId;
            }

            public void setMemberId(String memberId) {
                this.memberId = memberId;
            }

            public String getTaskImgUrl() {
                return taskImgUrl;
            }

            public void setTaskImgUrl(String taskImgUrl) {
                this.taskImgUrl = taskImgUrl;
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

            public String getCopywriting() {
                return copywriting;
            }

            public void setCopywriting(String copywriting) {
                this.copywriting = copywriting;
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

            public BigDecimal getUnitPrice() {
                return unitPrice;
            }

            public void setUnitPrice(BigDecimal unitPrice) {
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

            public int getMoney() {
                return money;
            }

            public void setMoney(int money) {
                this.money = money;
            }
        }

    }
}
