package com.application.tchapj.my.bean;

import java.io.Serializable;
import java.math.BigDecimal;

public class FrozenInfoBean implements Serializable {

    private String id;
    private String name;
    private BigDecimal money;
    private String frozenOutStatus;
    private String status;

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

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public String getFrozenOutStatus() {
        return frozenOutStatus;
    }

    public void setFrozenOutStatus(String frozenOutStatus) {
        this.frozenOutStatus = frozenOutStatus;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
