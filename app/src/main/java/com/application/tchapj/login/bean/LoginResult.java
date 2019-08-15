package com.application.tchapj.login.bean;

/**
 * Created by monster on 2018/3/28.
 */

public class LoginResult  {


    /**
     * method : pm.login.member
     * level : Info
     * code : 000
     * description : 登录信息获取成功！
     * data : {"loginInfo":{"id":"92893057595c48e9a2b11504d7532b8d","name":"13483017435","password":"123456","code":"739928","status":"0","authState":"0","lingTaskStatus":"0","faTaskStatus":"2","income":0,"getMoney":0,"isAuthor":"0","index":0,"rowCountPerPage":0,"promotionRecharge":102,"headimgurl":"https://qiniuyun.ctrlmedia.cn/touxiang.png","mobile":"13483017435","createTime":1555913989000,"commision":0,"balance":0,"media":"0","nick_name":"微呼百应会员7435"},"KF":"13345122570","type":"3"}
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
         * loginInfo : {"id":"92893057595c48e9a2b11504d7532b8d","name":"13483017435","password":"123456","code":"739928","status":"0","authState":"0","lingTaskStatus":"0","faTaskStatus":"2","income":0,"getMoney":0,"isAuthor":"0","index":0,"rowCountPerPage":0,"promotionRecharge":102,"headimgurl":"https://qiniuyun.ctrlmedia.cn/touxiang.png","mobile":"13483017435","createTime":1555913989000,"commision":0,"balance":0,"media":"0","nick_name":"微呼百应会员7435"}
         * KF : 13345122570
         * type : 3
         */

        private LoginInfoBean loginInfo;
        private String KF;
        private String type;

        public LoginInfoBean getLoginInfo() {
            return loginInfo;
        }

        public void setLoginInfo(LoginInfoBean loginInfo) {
            this.loginInfo = loginInfo;
        }

        public String getKF() {
            return KF;
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

        public static class LoginInfoBean {
            /**
             * id : 92893057595c48e9a2b11504d7532b8d
             * name : 13483017435
             * password : 123456
             * code : 739928
             * status : 0
             * authState : 0
             * lingTaskStatus : 0
             * faTaskStatus : 2
             * income : 0.0
             * getMoney : 0.0
             * isAuthor : 0
             * index : 0
             * rowCountPerPage : 0
             * promotionRecharge : 102.0
             * headimgurl : https://qiniuyun.ctrlmedia.cn/touxiang.png
             * mobile : 13483017435
             * createTime : 1555913989000
             * commision : 0.0
             * balance : 0.0
             * media : 0
             * nick_name : 微呼百应会员7435
             */

            private String id;
            private String name;
            private String password;
            private String code;
            private String status;
            private String authState;
            private String lingTaskStatus;
            private String faTaskStatus;
            private double income;
            private double getMoney;
            private String isAuthor;
            private int index;
            private int rowCountPerPage;
            private double promotionRecharge;
            private String headimgurl;
            private String mobile;
            private long createTime;
            private double commision;
            private double balance;
            private String media;
            private String nick_name;

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

            public String getPassword() {
                return password;
            }

            public void setPassword(String password) {
                this.password = password;
            }

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getAuthState() {
                return authState;
            }

            public void setAuthState(String authState) {
                this.authState = authState;
            }

            public String getLingTaskStatus() {
                return lingTaskStatus;
            }

            public void setLingTaskStatus(String lingTaskStatus) {
                this.lingTaskStatus = lingTaskStatus;
            }

            public String getFaTaskStatus() {
                return faTaskStatus;
            }

            public void setFaTaskStatus(String faTaskStatus) {
                this.faTaskStatus = faTaskStatus;
            }

            public double getIncome() {
                return income;
            }

            public void setIncome(double income) {
                this.income = income;
            }

            public double getGetMoney() {
                return getMoney;
            }

            public void setGetMoney(double getMoney) {
                this.getMoney = getMoney;
            }

            public String getIsAuthor() {
                return isAuthor;
            }

            public void setIsAuthor(String isAuthor) {
                this.isAuthor = isAuthor;
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

            public double getPromotionRecharge() {
                return promotionRecharge;
            }

            public void setPromotionRecharge(double promotionRecharge) {
                this.promotionRecharge = promotionRecharge;
            }

            public String getHeadimgurl() {
                return headimgurl;
            }

            public void setHeadimgurl(String headimgurl) {
                this.headimgurl = headimgurl;
            }

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public long getCreateTime() {
                return createTime;
            }

            public void setCreateTime(long createTime) {
                this.createTime = createTime;
            }

            public double getCommision() {
                return commision;
            }

            public void setCommision(double commision) {
                this.commision = commision;
            }

            public double getBalance() {
                return balance;
            }

            public void setBalance(double balance) {
                this.balance = balance;
            }

            public String getMedia() {
                return media;
            }

            public void setMedia(String media) {
                this.media = media;
            }

            public String getNick_name() {
                return nick_name;
            }

            public void setNick_name(String nick_name) {
                this.nick_name = nick_name;
            }
        }
    }
}

