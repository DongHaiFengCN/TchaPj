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
     * data : {"birthday":"","sex":"1","fans":0,"wxId":"1","proSY":"0.00","faState":"0","city":"","discount":"1","attentions":0,"qqId":"0","id":"2299f39bf07a40c5a644fabde5b21d7d","balance":0,"identity":"","authState":"0","lingState":"0","alipay":"0","nickName":"董海峰","province":"","realName":"","realname":"","headimgurl":"http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLoOWZM3NMkRYFlalBGeiaNVMvGuHw4yGaLrkVgq1Vmcw7hKqwZEEWibEstLsMCRLkmbP9NLFZEVq5g/132","isAuthor":"0","telephone":"","mobile":"13345122570"}
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

    @Override
    public String toString() {
        return "MemberInfo{" +
                "method='" + method + '\'' +
                ", level='" + level + '\'' +
                ", code='" + code + '\'' +
                ", description='" + description + '\'' +
                ", data=" + data +
                '}';
    }

    public static class DataBean {
        /**
         * birthday :
         * sex : 1
         * fans : 0
         * wxId : 1
         * proSY : 0.00
         * faState : 0
         * city :
         * discount : 1
         * attentions : 0
         * qqId : 0
         * id : 2299f39bf07a40c5a644fabde5b21d7d
         * balance : 0.0
         * identity :
         * authState : 0
         * lingState : 0
         * alipay : 0
         * nickName : 董海峰
         * province :
         * realName :
         * realname :
         * headimgurl : http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLoOWZM3NMkRYFlalBGeiaNVMvGuHw4yGaLrkVgq1Vmcw7hKqwZEEWibEstLsMCRLkmbP9NLFZEVq5g/132
         * isAuthor : 0
         * telephone :
         * mobile : 13345122570
         */

        private String birthday;
        private String sex;
        private int fans;
        private String wxId;
        private String proSY;
        private String faState;
        private String city;
        private String discount;
        private int attentions;
        private String qqId;
        private String id;
        private double balance;
        private String identity;
        private String authState;
        private String lingState;
        private String alipay;
        private String nickName;
        private String province;
        private String realName;
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

        public String getProSY() {
            return proSY;
        }

        public void setProSY(String proSY) {
            this.proSY = proSY;
        }

        public String getFaState() {
            return faState;
        }

        public void setFaState(String faState) {
            this.faState = faState;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
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

        public double getBalance() {
            return balance;
        }

        public void setBalance(double balance) {
            this.balance = balance;
        }

        public String getIdentity() {
            return identity;
        }

        public void setIdentity(String identity) {
            this.identity = identity;
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

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getRealName() {
            return realName;
        }

        public void setRealName(String realName) {
            this.realName = realName;
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

        @Override
        public String toString() {
            return "DataBean{" +
                    "birthday='" + birthday + '\'' +
                    ", sex='" + sex + '\'' +
                    ", fans=" + fans +
                    ", wxId='" + wxId + '\'' +
                    ", proSY='" + proSY + '\'' +
                    ", faState='" + faState + '\'' +
                    ", city='" + city + '\'' +
                    ", discount='" + discount + '\'' +
                    ", attentions=" + attentions +
                    ", qqId='" + qqId + '\'' +
                    ", id='" + id + '\'' +
                    ", balance=" + balance +
                    ", identity='" + identity + '\'' +
                    ", authState='" + authState + '\'' +
                    ", lingState='" + lingState + '\'' +
                    ", alipay='" + alipay + '\'' +
                    ", nickName='" + nickName + '\'' +
                    ", province='" + province + '\'' +
                    ", realName='" + realName + '\'' +
                    ", realname='" + realname + '\'' +
                    ", headimgurl='" + headimgurl + '\'' +
                    ", isAuthor='" + isAuthor + '\'' +
                    ", telephone='" + telephone + '\'' +
                    ", mobile='" + mobile + '\'' +
                    '}';
        }
    }
}
