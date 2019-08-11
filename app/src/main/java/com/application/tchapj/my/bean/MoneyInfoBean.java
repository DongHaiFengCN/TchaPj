package com.application.tchapj.my.bean;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by Administrator on 2018\9\7 0007.
 */

public class MoneyInfoBean implements Serializable {

    private String method;     //接口名
    private String level;      //接口信息
    private String code;       //接口返回结果

    private MoneyInfoBeanResult data;

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

    public MoneyInfoBeanResult getData() {
        return data;
    }

    public void setData(MoneyInfoBeanResult data) {
        this.data = data;
    }

    public static class MoneyInfoBeanResult {

        private BigDecimal salary;  // 总收益
        private BigDecimal sy;      // 余额
        private BigDecimal dj;      // 冻结
        private BigDecimal ktk;     // 可退款
        private String alipay;  // 0:未绑定 1:已绑定
        private String alipayId;// 支付宝ID
        private String transferStatus;

        public String getTransferStatus() {
            return transferStatus;
        }

        public void setTransferStatus(String transferStatus) {
            this.transferStatus = transferStatus;
        }

        public BigDecimal getSalary() {
            return salary;
        }

        public void setSalary(BigDecimal salary) {
            this.salary = salary;
        }

        public BigDecimal getSy() {
            return sy;
        }

        public void setSy(BigDecimal sy) {
            this.sy = sy;
        }

        public BigDecimal getDj() {
            return dj;
        }

        public void setDj(BigDecimal dj) {
            this.dj = dj;
        }

        public BigDecimal getKtk() {
            return ktk;
        }

        public void setKtk(BigDecimal ktk) {
            this.ktk = ktk;
        }

        public String getAlipay() {
            return alipay;
        }

        public void setAlipay(String alipay) {
            this.alipay = alipay;
        }

        public String getAlipayId() {
            return alipayId;
        }

        public void setAlipayId(String alipayId) {
            this.alipayId = alipayId;
        }
    }


}
