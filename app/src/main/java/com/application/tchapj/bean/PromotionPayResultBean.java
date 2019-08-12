package com.application.tchapj.bean;

import java.io.Serializable;

/**
 * @author 董海峰
 * Create 2019/7/30
 * Description: 用于返回推广金的支付结果
 */
public class PromotionPayResultBean implements Serializable {
    /**
     * method : promotion.app.pay
     * level : Info
     * code : 000
     * description : 推广金支付成功！
     * data : {"proSY":0.36}
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
         * proSY : 0.36
         */

        private double proSY;

        public double getProSY() {
            return proSY;
        }

        public void setProSY(double proSY) {
            this.proSY = proSY;
        }
    }
}
