package com.application.tchapj.task.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class NewUserModel implements Parcelable {

    /**
     * method : pm.member.get
     * level : Info
     * code : 000
     * description : 获取用户信息成功！
     * data : {"birthday":"","sex":"男","dyState":"1","taskApplyId":"5a0792d5eec345fa99cab7f307442015","faState":"2","city":null,"id":"5a0792d5eec345fa99cab7f307442015","balance":0,"authState":"0","lingState":"2","alipay":"1","nickName":"微呼百应会员","province":null,"realName":"0","headimgurl":"http://p1at4qo4h.bkt.clouddn.com/o_1c1rtldnf1gdv17cuvar11orfnha.png","telephone":"15345312085","mobile":"13345122570"}
     */

    private String   method;
    private String   level;
    private String   code;
    private String   description;
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

    public static class DataBean implements Parcelable {
        /**
         * birthday :
         * sex : 男
         * dyState : 1
         * taskApplyId : 5a0792d5eec345fa99cab7f307442015
         * faState : 2
         * city : null
         * id : 5a0792d5eec345fa99cab7f307442015
         * balance : 0.0
         * authState : 0
         * lingState : 2
         * alipay : 1
         * nickName : 微呼百应会员
         * province : null
         * realName : 0
         * headimgurl : http://p1at4qo4h.bkt.clouddn.com/o_1c1rtldnf1gdv17cuvar11orfnha.png
         * telephone : 15345312085
         * mobile : 13345122570
         */

        private String birthday;
        private String sex;
        private String dyState;
        private String taskApplyId;
        private String faState;
        private String city;
        private String id;
        private double balance;
        private String authState;
        private String lingState;
        private String alipay;
        private String nickName;
        private String province;
        private String realName;
        private String headimgurl;
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

        public String getDyState() {
            return dyState;
        }

        public void setDyState(String dyState) {
            this.dyState = dyState;
        }

        public String getTaskApplyId() {
            return taskApplyId;
        }

        public void setTaskApplyId(String taskApplyId) {
            this.taskApplyId = taskApplyId;
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

        public void setCity(String city) {
            this.city = city;
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

        public void setProvince(String province) {
            this.province = province;
        }

        public String getRealName() {
            return realName;
        }

        public void setRealName(String realName) {
            this.realName = realName;
        }

        public String getHeadimgurl() {
            return headimgurl;
        }

        public void setHeadimgurl(String headimgurl) {
            this.headimgurl = headimgurl;
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
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.birthday);
            dest.writeString(this.sex);
            dest.writeString(this.dyState);
            dest.writeString(this.taskApplyId);
            dest.writeString(this.faState);
            dest.writeString(this.city);
            dest.writeString(this.id);
            dest.writeDouble(this.balance);
            dest.writeString(this.authState);
            dest.writeString(this.lingState);
            dest.writeString(this.alipay);
            dest.writeString(this.nickName);
            dest.writeString(this.province);
            dest.writeString(this.realName);
            dest.writeString(this.headimgurl);
            dest.writeString(this.telephone);
            dest.writeString(this.mobile);
        }

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.birthday = in.readString();
            this.sex = in.readString();
            this.dyState = in.readString();
            this.taskApplyId = in.readString();
            this.faState = in.readString();
            this.city = in.readParcelable(Object.class.getClassLoader());
            this.id = in.readString();
            this.balance = in.readDouble();
            this.authState = in.readString();
            this.lingState = in.readString();
            this.alipay = in.readString();
            this.nickName = in.readString();
            this.province = in.readParcelable(Object.class.getClassLoader());
            this.realName = in.readString();
            this.headimgurl = in.readString();
            this.telephone = in.readString();
            this.mobile = in.readString();
            this.city = in.readString();
            this.province = in.readString();
        }

        public static final Creator<DataBean> CREATOR = new Creator<DataBean>() {
            @Override
            public DataBean createFromParcel(Parcel source) {
                return new DataBean(source);
            }

            @Override
            public DataBean[] newArray(int size) {
                return new DataBean[size];
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.method);
        dest.writeString(this.level);
        dest.writeString(this.code);
        dest.writeString(this.description);
        dest.writeParcelable(this.data, flags);
    }

    public NewUserModel() {
    }

    protected NewUserModel(Parcel in) {
        this.method = in.readString();
        this.level = in.readString();
        this.code = in.readString();
        this.description = in.readString();
        this.data = in.readParcelable(DataBean.class.getClassLoader());
    }

    public static final Creator<NewUserModel> CREATOR = new Creator<NewUserModel>() {
        @Override
        public NewUserModel createFromParcel(Parcel source) {
            return new NewUserModel(source);
        }

        @Override
        public NewUserModel[] newArray(int size) {
            return new NewUserModel[size];
        }
    };
}
