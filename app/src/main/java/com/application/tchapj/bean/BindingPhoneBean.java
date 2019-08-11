package com.application.tchapj.bean;

import com.application.tchapj.login.bean.LoginResult;
import com.application.tchapj.my.bean.UserModel;

import java.io.Serializable;

/**
 * Create by zyy on 2019/4/24
 * Description:
 */
public class BindingPhoneBean implements Serializable {

    private String memberId;

    public String getMemberid() {
        return memberId;
    }

    public void setMemberid(String memberid) {
        this.memberId = memberid;
    }
}
