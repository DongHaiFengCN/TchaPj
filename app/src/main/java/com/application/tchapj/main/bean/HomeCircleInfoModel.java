package com.application.tchapj.main.bean;

import java.util.List;

/**
 * Created by 安卓开发 on 2018/8/9.
 */

public class HomeCircleInfoModel {

    private String method;     //接口名
    private String level;      //接口信息
    private String code;       //接口返回结果
    private String description;//接口返回信息

    private HomeCircleInfoModelResult data;

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

    public HomeCircleInfoModelResult getData() {
        return data;
    }

    public void setData(HomeCircleInfoModelResult data) {
        this.data = data;
    }

    public static class HomeCircleInfoModelResult{


        private List<HomeInfoCircle> media_own;

        public List<HomeInfoCircle> getMedia_own() {
            return media_own;
        }

        public void setMedia_own(List<HomeInfoCircle> media_own) {
            this.media_own = media_own;
        }

        public static class HomeInfoCircle{

            private String id;
            private String name;
            private String memberId;
            private int age;
            private int sex;
            private String phoneNumber;
            private String code;
            private String circleTypeId;
            private String industry;
            private String applyStatus;
            private long createTime;
            private String headimageUrl;
            private String conpanyImgUrl;
            private String content;
            private String pyqStatus;
            private String dyStatus;
            private String wbStatus;
            private String wsStatus;
            private String otherStatus;
            private int index;
            private int rowCountPerPage;
            private String nickName;

            public String getNickName() {
                return nickName;
            }

            public void setNickName(String nickName) {
                this.nickName = nickName;
            }

            public String getWsStatus() {
                return wsStatus;
            }

            public void setWsStatus(String wsStatus) {
                this.wsStatus = wsStatus;
            }

            public String getOtherStatus() {
                return otherStatus;
            }

            public void setOtherStatus(String otherStatus) {
                this.otherStatus = otherStatus;
            }

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

            public String getMemberId() {
                return memberId;
            }

            public void setMemberId(String memberId) {
                this.memberId = memberId;
            }

            public int getAge() {
                return age;
            }

            public void setAge(int age) {
                this.age = age;
            }

            public int getSex() {
                return sex;
            }

            public void setSex(int sex) {
                this.sex = sex;
            }

            public String getPhoneNumber() {
                return phoneNumber;
            }

            public void setPhoneNumber(String phoneNumber) {
                this.phoneNumber = phoneNumber;
            }

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public String getCircleTypeId() {
                return circleTypeId;
            }

            public void setCircleTypeId(String circleTypeId) {
                this.circleTypeId = circleTypeId;
            }

            public String getIndustry() {
                return industry;
            }

            public void setIndustry(String industry) {
                this.industry = industry;
            }

            public String getApplyStatus() {
                return applyStatus;
            }

            public void setApplyStatus(String applyStatus) {
                this.applyStatus = applyStatus;
            }

            public long getCreateTime() {
                return createTime;
            }

            public void setCreateTime(long createTime) {
                this.createTime = createTime;
            }

            public String getHeadimageUrl() {
                return headimageUrl;
            }

            public void setHeadimageUrl(String headimageUrl) {
                this.headimageUrl = headimageUrl;
            }

            public String getConpanyImgUrl() {
                return conpanyImgUrl;
            }

            public void setConpanyImgUrl(String conpanyImgUrl) {
                this.conpanyImgUrl = conpanyImgUrl;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getPyqStatus() {
                return pyqStatus;
            }

            public void setPyqStatus(String pyqStatus) {
                this.pyqStatus = pyqStatus;
            }

            public String getDyStatus() {
                return dyStatus;
            }

            public void setDyStatus(String dyStatus) {
                this.dyStatus = dyStatus;
            }

            public String getWbStatus() {
                return wbStatus;
            }

            public void setWbStatus(String wbStatus) {
                this.wbStatus = wbStatus;
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
        }

    }


}
