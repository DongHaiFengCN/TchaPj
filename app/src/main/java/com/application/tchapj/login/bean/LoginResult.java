package com.application.tchapj.login.bean;

import com.application.tchapj.bean.UserInfo;

import java.io.Serializable;

/**
 * Created by monster on 2018/3/28.
 */

public class LoginResult implements Serializable {

    private String method;     //接口名
    private String level;      //接口信息
    private String code;       //接口返回结果
    private String description;//接口返回信息
    private LogindataResult data;

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

    public LogindataResult getData() {
        return data;
    }

    public void setData(LogindataResult data) {
        this.data = data;
    }

    public static class LogindataResult{
        /**
         * loginInfo : {"id":"393b0ed695cc448983ea41ab303be830","openid":"oOw4C08ud4Hufjj4V5tb1Uuqouco","name":"admin","password":"123456","nickname":"张总","headimgurl":"http://wx.qlogo.cn/mmopen/dvlp2NELPaf5Sb8CYCZZSevMGcOsTOx1dawGQR3YgUrUVrQeUU7cZ8p8QyzMNNh3aFzXloV1J2DkScics39GVAeUyiaQO73r8u/0","unionid":"ofyfpvp38IMrF1KWmHdKaXg6spo0","createTime":1502457110000,"point":0,"totalPoint":0,"commision":0,"balance":0,"media":"0"}
         */

        private UserInfo loginInfo;
        private String KF;                //客服电话
        private String type;              //客户类型

        public String getKF() {

            String KFString = "";

            if("".equals(KF)){
                KFString = "13345122570";
            }else {
                KFString = KF;
            }
            return KFString;
        }

        public void setKF(String KF) {
            this.KF = KF;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public UserInfo getLoginInfo() {
            return loginInfo;
        }

        public void setLoginInfo(UserInfo loginInfo) {
            this.loginInfo = loginInfo;
        }
    }

}

