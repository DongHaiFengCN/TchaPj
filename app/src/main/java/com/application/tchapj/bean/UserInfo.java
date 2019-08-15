package com.application.tchapj.bean;

import java.io.Serializable;

/**
 * Create by zyy on 2019/5/13
 * Description: 个人信息实体类
 */
public class UserInfo implements Serializable {

    private String id;       // 用户ID
    private String sex;       // 性别
    private String birthday; // 生日
    private String city;
    private Double balance;  // 积分
    private String lingState = "0";  // 达人标示
    private String faState = "0";    // 广告主标示
    private String mrState = "0";  // 名人标示
    private String mtState = "0";    // 媒体标示
    private String authState;  // 小微号认证标示
    private String nickName;  // 姓名
    private String province; //省份
    private String realName; //实名认证状态 0 未认证 1 已认证
    private String headimgurl; // 图片地址
    private String telephone; // 电话
    private String mobile;  // 客服手机

    private String dyState = ""; //抖音标识
    private String pyqState = ""; //朋友圈标识
    private String wbState = ""; //微博标识
    private String wsState = ""; //微视标识
    private String otherState = "";//其他标识

    private String taskApplyId; // 任务认证id
    private String alipay; // 支付宝状态0:未绑定 1:已绑定

    private String attentions;//关注数
    private String fans;//粉丝数
    private String isAuthor;//是否认证微呼百应号 0：未认证 1 已认证
    private String info;//0无新消息 非0 有新消息
    private String refusal;//申请达人，拒绝理由

    private String nick_name;
    private String media;        // 媒体
    private int commision;       // 酬金
    private long createTime;     // 建立时间
    private String alipayId;

    private String faTaskStatus;
    private String lingTaskStatus;
    private String status;
    private String name;         // 用户名
    private String password;     // 密码
    private String code;         //

    public String getMedia() {
        return media;
    }

    public void setMedia(String media) {
        this.media = media;
    }

    public int getCommision() {
        return commision;
    }

    public void setCommision(int commision) {
        this.commision = commision;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public String getAlipayId() {
        return alipayId;
    }

    public void setAlipayId(String alipayId) {
        this.alipayId = alipayId;
    }

    public String getFaTaskStatus() {
        return faTaskStatus;
    }

    public void setFaTaskStatus(String faTaskStatus) {
        this.faTaskStatus = faTaskStatus;
    }

    public String getLingTaskStatus() {
        return lingTaskStatus;
    }

    public void setLingTaskStatus(String lingTaskStatus) {
        this.lingTaskStatus = lingTaskStatus;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getNick_name() {
        return nick_name;
    }

    public void setNick_name(String nick_name) {
        this.nick_name = nick_name;
    }

    public String getRefusal() {
        return refusal;
    }

    public void setRefusal(String refusal) {
        this.refusal = refusal;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getIsAuthor() {
        return isAuthor;
    }

    public void setIsAuthor(String isAuthor) {
        this.isAuthor = isAuthor;
    }

    public String getAttentions() {
        return attentions;
    }

    public void setAttentions(String attentions) {
        this.attentions = attentions;
    }

    public String getFans() {
        return fans;
    }

    public void setFans(String fans) {
        this.fans = fans;
    }

    public String getTaskApplyId() {
        return taskApplyId;
    }

    public void setTaskApplyId(String taskApplyId) {
        this.taskApplyId = taskApplyId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
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

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
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

    public String getLingState() {
        return lingState;
    }

    public void setLingState(String lingState) {
        this.lingState = lingState;
    }

    public String getAuthState() {
        return authState;
    }

    public void setAuthState(String authState) {
        this.authState = authState;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getAlipay() {
        return alipay;
    }

    public void setAlipay(String alipay) {
        this.alipay = alipay;
    }

    public String getMrState() {
        return mrState;
    }

    public void setMrState(String mrState) {
        this.mrState = mrState;
    }

    public String getMtState() {
        return mtState;
    }

    public void setMtState(String mtState) {
        this.mtState = mtState;
    }

    public String getDyState() {
        return dyState;
    }

    public void setDyState(String dyState) {
        this.dyState = dyState;
    }

    public String getPyqState() {
        return pyqState;
    }

    public void setPyqState(String pyqState) {
        this.pyqState = pyqState;
    }

    public String getWbState() {
        return wbState;
    }

    public void setWbState(String wbState) {
        this.wbState = wbState;
    }

    public String getWsState() {
        return wsState;
    }

    public void setWsState(String wsState) {
        this.wsState = wsState;
    }


    public String getOtherState() {
        return otherState;
    }

    public void setOtherState(String otherState) {
        this.otherState = otherState;
    }


    @Override
    public String toString() {
        return "UserInfo{" +
                "id='" + id + '\'' +
                ", sex='" + sex + '\'' +
                ", birthday='" + birthday + '\'' +
                ", city='" + city + '\'' +
                ", balance=" + balance +
                ", lingState='" + lingState + '\'' +
                ", faState='" + faState + '\'' +
                ", mrState='" + mrState + '\'' +
                ", mtState='" + mtState + '\'' +
                ", authState='" + authState + '\'' +
                ", nickName='" + nickName + '\'' +
                ", province='" + province + '\'' +
                ", realName='" + realName + '\'' +
                ", headimgurl='" + headimgurl + '\'' +
                ", telephone='" + telephone + '\'' +
                ", mobile='" + mobile + '\'' +
                ", dyState='" + dyState + '\'' +
                ", pyqState='" + pyqState + '\'' +
                ", wbState='" + wbState + '\'' +
                ", wsState='" + wsState + '\'' +
                ", otherState='" + otherState + '\'' +
                ", taskApplyId='" + taskApplyId + '\'' +
                ", alipay='" + alipay + '\'' +
                ", attentions='" + attentions + '\'' +
                ", fans='" + fans + '\'' +
                ", isAuthor='" + isAuthor + '\'' +
                ", info='" + info + '\'' +
                ", refusal='" + refusal + '\'' +
                ", nick_name='" + nick_name + '\'' +
                ", media='" + media + '\'' +
                ", commision=" + commision +
                ", createTime=" + createTime +
                ", alipayId='" + alipayId + '\'' +
                ", faTaskStatus='" + faTaskStatus + '\'' +
                ", lingTaskStatus='" + lingTaskStatus + '\'' +
                ", status='" + status + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
