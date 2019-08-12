package com.application.tchapj.bean;

/**
 * Description:
 * @author 董海峰
 */
public class MemberInfo {

    /**
     * method : pm.member.get
     * level : Info
     * code : 000
     * description : 获取用户信息成功！
     * data : {"birthday":"","sex":"","fans":0,"wxId":"0","proSY":599.99,"faState":"2","city":null,"discount":"1","attentions":0,"qqId":"0","id":"d330ba9341704ee1bbf35cde559b21ef","identity":"370123199007063413","balance":0,"authState":"0","lingState":"0","alipay":"1","nickName":"微呼百应会员4306","province":null,"realname":"777","headimgurl":"https://qiniuyun.ctrlmedia.cn/touxiang.png","isAuthor":"0","telephone":"15130514306","mobile":"13345122570"}
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
         * birthday :
         * sex :
         * fans : 0
         * wxId : 0
         * proSY : 599.99
         * faState : 2
         * city : null
         * discount : 1
         * attentions : 0
         * qqId : 0
         * id : d330ba9341704ee1bbf35cde559b21ef
         * identity : 370123199007063413
         * balance : 0
         * authState : 0
         * lingState : 0
         * alipay : 1
         * nickName : 微呼百应会员4306
         * province : null
         * realname : 777
         * headimgurl : https://qiniuyun.ctrlmedia.cn/touxiang.png
         * isAuthor : 0
         * telephone : 15130514306
         * mobile : 13345122570
         */

        private String birthday;
        private String sex;
        private int fans;
        private String wxId;
        private double proSY;
        private String faState;
        private Object city;
        private String discount;
        private int attentions;
        private String qqId;
        private String id;
        private String identity;
        private int balance;
        private String authState;
        private String lingState;
        private String alipay;
        private String nickName;
        private Object province;
        private String realname;
        private String headimgurl;
        private String isAuthor;
        private String telephone;
        private String mobile;

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public int getFans() {
            return fans;
        }

        public void setFans(int fans) {
            this.fans = fans;
        }

        public String getWxId() {
            return wxId;
        }

        public void setWxId(String wxId) {
            this.wxId = wxId;
        }

        public double getProSY() {
            return proSY;
        }

        public void setProSY(double proSY) {
            this.proSY = proSY;
        }

        public String getFaState() {
            return faState;
        }

        public void setFaState(String faState) {
            this.faState = faState;
        }

        public Object getCity() {
            return city;
        }

        public void setCity(Object city) {
            this.city = city;
        }

        public String getDiscount() {
            return discount;
        }

        public void setDiscount(String discount) {
            this.discount = discount;
        }

        public int getAttentions() {
            return attentions;
        }

        public void setAttentions(int attentions) {
            this.attentions = attentions;
        }

        public String getQqId() {
            return qqId;
        }

        public void setQqId(String qqId) {
            this.qqId = qqId;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getIdentity() {
            return identity;
        }

        public void setIdentity(String identity) {
            this.identity = identity;
        }

        public int getBalance() {
            return balance;
        }

        public void setBalance(int balance) {
            this.balance = balance;
        }

        public String getAuthState() {
            return authState;
        }

        public void setAuthState(String authState) {
            this.authState = authState;
        }

        public String getLingState() {
            return lingState;
        }

        public void setLingState(String lingState) {
            this.lingState = lingState;
        }

        public String getAlipay() {
            return alipay;
        }

        public void setAlipay(String alipay) {
            this.alipay = alipay;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public Object getProvince() {
            return province;
        }

        public void setProvince(Object province) {
            this.province = province;
        }

        public String getRealname() {
            return realname;
        }

        public void setRealname(String realname) {
            this.realname = realname;
        }

        public String getHeadimgurl() {
            return headimgurl;
        }

        public void setHeadimgurl(String headimgurl) {
            this.headimgurl = headimgurl;
        }

        public String getIsAuthor() {
            return isAuthor;
        }

        public void setIsAuthor(String isAuthor) {
            this.isAuthor = isAuthor;
        }

        public String getTelephone() {
            return telephone;
        }

        public void setTelephone(String telephone) {
            this.telephone = telephone;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }
    }
}
