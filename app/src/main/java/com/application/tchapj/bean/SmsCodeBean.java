package com.application.tchapj.bean;

import java.io.Serializable;

/**
 * Create by zyy on 2019/4/24
 * Description: 验证码实体类
 */
public class SmsCodeBean implements Serializable {

    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
