package com.application.tchapj.task.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/3/27.
 */

public class MyTaskSquareModel implements Serializable {


    /**
     * code : 000
     * data : {"faStatus":"2","lingstatus":"2","tasks":[{"endTime":1535259660000,"id":"4af63734470043cba096c2ef7b0efad7","imgUrl":"http://qiniuyun2.ctrlmedia.cn/FmNLRmJ_qzUHLwBqTAGp13HRtkyQ","index":0,"money":0,"name":"十三","nickName":"十三","rowCountPerPage":0,"startTime":1535448060000,"status":"1","taskType":"0","unitPrice":0.01},{"endTime":1535259660000,"id":"4af63734470043cba096c2ef7b0efad7","imgUrl":"http://qiniuyun2.ctrlmedia.cn/FmNLRmJ_qzUHLwBqTAGp13HRtkyQ","index":0,"money":0,"name":"十三","nickName":"十三","rowCountPerPage":0,"startTime":1535448060000,"status":"1","taskType":"0","unitPrice":0.01},{"endTime":1535259660000,"id":"90277b6630484275bdfb9e661979ea43","imgUrl":"http://qiniuyun2.ctrlmedia.cn/FmNLRmJ_qzUHLwBqTAGp13HRtkyQ","index":0,"money":0,"name":"十三","nickName":"十三","rowCountPerPage":0,"startTime":1535448060000,"status":"1","taskType":"0","unitPrice":0.01},{"endTime":1535259660000,"id":"90277b6630484275bdfb9e661979ea43","imgUrl":"http://qiniuyun2.ctrlmedia.cn/FmNLRmJ_qzUHLwBqTAGp13HRtkyQ","index":0,"money":0,"name":"十三","nickName":"十三","rowCountPerPage":0,"startTime":1535448060000,"status":"1","taskType":"0","unitPrice":0.01},{"endTime":1535259660000,"id":"d14dfcc62ffe4991b7ee0069ffeed6a6","imgUrl":"http://qiniuyun2.ctrlmedia.cn/FmNLRmJ_qzUHLwBqTAGp13HRtkyQ","index":0,"money":0,"name":"十三","nickName":"十三","rowCountPerPage":0,"startTime":1535448060000,"status":"1","taskType":"0","unitPrice":0.01},{"endTime":1535259660000,"id":"d14dfcc62ffe4991b7ee0069ffeed6a6","imgUrl":"http://qiniuyun2.ctrlmedia.cn/FmNLRmJ_qzUHLwBqTAGp13HRtkyQ","index":0,"money":0,"name":"十三","nickName":"十三","rowCountPerPage":0,"startTime":1535448060000,"status":"1","taskType":"0","unitPrice":0.01},{"endTime":1535259720000,"id":"3a65d39d411d4b5b9e29b0df2d12d26b","imgUrl":"http://qiniuyun2.ctrlmedia.cn/FmNLRmJ_qzUHLwBqTAGp13HRtkyQ","index":0,"money":0,"name":"十三","nickName":"十三","rowCountPerPage":0,"startTime":1535448060000,"status":"1","taskType":"1","unitPrice":0.01},{"endTime":1535259720000,"id":"3a65d39d411d4b5b9e29b0df2d12d26b","imgUrl":"http://qiniuyun2.ctrlmedia.cn/FmNLRmJ_qzUHLwBqTAGp13HRtkyQ","index":0,"money":0,"name":"十三","nickName":"十三","rowCountPerPage":0,"startTime":1535448060000,"status":"1","taskType":"1","unitPrice":0.01},{"endTime":1535259780000,"id":"2aa00bbc5f264da0bec10b97bfd097b5","imgUrl":"http://qiniuyun2.ctrlmedia.cn/FmNLRmJ_qzUHLwBqTAGp13HRtkyQ","index":0,"money":0,"name":"十三","nickName":"十三","rowCountPerPage":0,"startTime":1535448060000,"status":"1","taskType":"2","unitPrice":0.01},{"endTime":1535259780000,"id":"2aa00bbc5f264da0bec10b97bfd097b5","imgUrl":"http://qiniuyun2.ctrlmedia.cn/FmNLRmJ_qzUHLwBqTAGp13HRtkyQ","index":0,"money":0,"name":"十三","nickName":"十三","rowCountPerPage":0,"startTime":1535448060000,"status":"1","taskType":"2","unitPrice":0.01}]}
     * description : 获取我的任务列表成功！
     * level : Info
     * method : pm.task.ownlist
     */

    private String code;
    private DataBean data;
    private String   description;
    private String   level;
    private String   method;

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
        /**
         * faStatus : 2
         * lingstatus : 2
         * tasks : [{"endTime":1535259660000,"id":"4af63734470043cba096c2ef7b0efad7","imgUrl":"http://qiniuyun2.ctrlmedia.cn/FmNLRmJ_qzUHLwBqTAGp13HRtkyQ","index":0,"money":0,"name":"十三","nickName":"十三","rowCountPerPage":0,"startTime":1535448060000,"status":"1","taskType":"0","unitPrice":0.01},{"endTime":1535259660000,"id":"4af63734470043cba096c2ef7b0efad7","imgUrl":"http://qiniuyun2.ctrlmedia.cn/FmNLRmJ_qzUHLwBqTAGp13HRtkyQ","index":0,"money":0,"name":"十三","nickName":"十三","rowCountPerPage":0,"startTime":1535448060000,"status":"1","taskType":"0","unitPrice":0.01},{"endTime":1535259660000,"id":"90277b6630484275bdfb9e661979ea43","imgUrl":"http://qiniuyun2.ctrlmedia.cn/FmNLRmJ_qzUHLwBqTAGp13HRtkyQ","index":0,"money":0,"name":"十三","nickName":"十三","rowCountPerPage":0,"startTime":1535448060000,"status":"1","taskType":"0","unitPrice":0.01},{"endTime":1535259660000,"id":"90277b6630484275bdfb9e661979ea43","imgUrl":"http://qiniuyun2.ctrlmedia.cn/FmNLRmJ_qzUHLwBqTAGp13HRtkyQ","index":0,"money":0,"name":"十三","nickName":"十三","rowCountPerPage":0,"startTime":1535448060000,"status":"1","taskType":"0","unitPrice":0.01},{"endTime":1535259660000,"id":"d14dfcc62ffe4991b7ee0069ffeed6a6","imgUrl":"http://qiniuyun2.ctrlmedia.cn/FmNLRmJ_qzUHLwBqTAGp13HRtkyQ","index":0,"money":0,"name":"十三","nickName":"十三","rowCountPerPage":0,"startTime":1535448060000,"status":"1","taskType":"0","unitPrice":0.01},{"endTime":1535259660000,"id":"d14dfcc62ffe4991b7ee0069ffeed6a6","imgUrl":"http://qiniuyun2.ctrlmedia.cn/FmNLRmJ_qzUHLwBqTAGp13HRtkyQ","index":0,"money":0,"name":"十三","nickName":"十三","rowCountPerPage":0,"startTime":1535448060000,"status":"1","taskType":"0","unitPrice":0.01},{"endTime":1535259720000,"id":"3a65d39d411d4b5b9e29b0df2d12d26b","imgUrl":"http://qiniuyun2.ctrlmedia.cn/FmNLRmJ_qzUHLwBqTAGp13HRtkyQ","index":0,"money":0,"name":"十三","nickName":"十三","rowCountPerPage":0,"startTime":1535448060000,"status":"1","taskType":"1","unitPrice":0.01},{"endTime":1535259720000,"id":"3a65d39d411d4b5b9e29b0df2d12d26b","imgUrl":"http://qiniuyun2.ctrlmedia.cn/FmNLRmJ_qzUHLwBqTAGp13HRtkyQ","index":0,"money":0,"name":"十三","nickName":"十三","rowCountPerPage":0,"startTime":1535448060000,"status":"1","taskType":"1","unitPrice":0.01},{"endTime":1535259780000,"id":"2aa00bbc5f264da0bec10b97bfd097b5","imgUrl":"http://qiniuyun2.ctrlmedia.cn/FmNLRmJ_qzUHLwBqTAGp13HRtkyQ","index":0,"money":0,"name":"十三","nickName":"十三","rowCountPerPage":0,"startTime":1535448060000,"status":"1","taskType":"2","unitPrice":0.01},{"endTime":1535259780000,"id":"2aa00bbc5f264da0bec10b97bfd097b5","imgUrl":"http://qiniuyun2.ctrlmedia.cn/FmNLRmJ_qzUHLwBqTAGp13HRtkyQ","index":0,"money":0,"name":"十三","nickName":"十三","rowCountPerPage":0,"startTime":1535448060000,"status":"1","taskType":"2","unitPrice":0.01}]
         */

        private String faStatus;
        private String          lingstatus;
        private List<TasksBean> tasks;

        public String getFaStatus() {
            return faStatus;
        }

        public void setFaStatus(String faStatus) {
            this.faStatus = faStatus;
        }

        public String getLingstatus() {
            return lingstatus;
        }

        public void setLingstatus(String lingstatus) {
            this.lingstatus = lingstatus;
        }

        public List<TasksBean> getTasks() {
            return tasks;
        }

        public void setTasks(List<TasksBean> tasks) {
            this.tasks = tasks;
        }

        public static class TasksBean {
            /**
             * endTime : 1535259660000
             * id : 4af63734470043cba096c2ef7b0efad7
             * imgUrl : http://qiniuyun2.ctrlmedia.cn/FmNLRmJ_qzUHLwBqTAGp13HRtkyQ
             * index : 0
             * money : 0.0
             * name : 十三
             * nickName : 十三
             * rowCountPerPage : 0
             * startTime : 1535448060000
             * status : 1
             * taskType : 0
             * unitPrice : 0.01
             */

            private long endTime;
            private String id;
            private String imgUrl;
            private int    index;
            private double money;
            private String name;
            private String nickName;
            private int    rowCountPerPage;
            private long   startTime;
            private String status;//1已领取 2上传资料审核中 3通过 4 未通过
            private String taskType;
            private double unitPrice;
            private String type;

            public String getType() {
                return this.type == null ? "" : this.type;
            }

            public void setType(String type) {
                this.type = type;
            }



            public long getEndTime() {
                return endTime;
            }

            public void setEndTime(long endTime) {
                this.endTime = endTime;
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
        }
    }
}
