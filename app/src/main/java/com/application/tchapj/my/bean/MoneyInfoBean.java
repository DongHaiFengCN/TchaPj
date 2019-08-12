package com.application.tchapj.my.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2018\9\7 0007.
 */

public class MoneyInfoBean implements Serializable {


    /**
     * method : pm.own.wallet
     * level : Info
     * code : 000
     * description : 获取钱包信息成功！
     * data : {"alipay":"1","transferStatus":"0","alipayId":"2088702155983712","dj":0.1,"salary":0,"sy":0,"occupy":0.29,"proSY":0.3}
     */

    private String method;
    private String level;
    private String code;
    private String description;
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
         * alipay : 1
         * transferStatus : 0
         * alipayId : 2088702155983712
         * dj : 0.1
         * salary : 0
         * sy : 0
         * occupy : 0.29
         * proSY : 0.3
         */

        private String alipay;
        private String transferStatus;
        private String alipayId;
        private String dj;
        private String salary;
        private String sy;
        private String occupy;
        private String proSY;


        public String getAlipay() {
            return alipay;
        }

        public void setAlipay(String alipay) {
            this.alipay = alipay;
        }

        public String getTransferStatus() {
            return transferStatus;
        }

        public void setTransferStatus(String transferStatus) {
            this.transferStatus = transferStatus;
        }

        public String getAlipayId() {
            return alipayId;
        }

        public void setAlipayId(String alipayId) {
            this.alipayId = alipayId;
        }

        public String getDj() {
            return dj;
        }

        public void setDj(String dj) {
            this.dj = dj;
        }

        public String getSalary() {
            return salary;
        }

        public void setSalary(String salary) {
            this.salary = salary;
        }

        public String getSy() {
            return sy;
        }

        public void setSy(String sy) {
            this.sy = sy;
        }

        public String getOccupy() {
            return occupy;
        }

        public void setOccupy(String occupy) {
            this.occupy = occupy;
        }

        public String getProSY() {
            return proSY;
        }

        public void setProSY(String proSY) {
            this.proSY = proSY;
        }
    }
}
