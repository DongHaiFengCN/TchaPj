package com.application.tchapj.main.bean;

import java.io.Serializable;
import java.util.List;

public class StartInitiationDataModel implements Serializable {

    private List<CityInfo> citys;
    private VersionInfoModel versionInfo;
    private String discount;//发任务折扣

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public List<CityInfo> getCitys() {
        return citys;
    }

    public void setCitys(List<CityInfo> citys) {
        this.citys = citys;
    }

    public VersionInfoModel getVersionInfo() {
        return versionInfo;
    }

    public void setVersionInfo(VersionInfoModel versionInfo) {
        this.versionInfo = versionInfo;
    }

    public class VersionInfoModel implements Serializable{
        private String id;
        private String apkName;
        private String apkVersion;
        private String apkUrl;
        private String isNew;
        private String adminId;
        private String remark;
        private String updateTime;
        private String clientType;
        private String osType;
        private int versionCode;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getApkName() {
            return apkName;
        }

        public void setApkName(String apkName) {
            this.apkName = apkName;
        }

        public String getApkVersion() {
            return apkVersion;
        }

        public void setApkVersion(String apkVersion) {
            this.apkVersion = apkVersion;
        }

        public String getApkUrl() {
            return apkUrl;
        }

        public void setApkUrl(String apkUrl) {
            this.apkUrl = apkUrl;
        }

        public String getIsNew() {
            return isNew;
        }

        public void setIsNew(String isNew) {
            this.isNew = isNew;
        }

        public String getAdminId() {
            return adminId;
        }

        public void setAdminId(String adminId) {
            this.adminId = adminId;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public String getClientType() {
            return clientType;
        }

        public void setClientType(String clientType) {
            this.clientType = clientType;
        }

        public String getOsType() {
            return osType;
        }

        public void setOsType(String osType) {
            this.osType = osType;
        }

        public int getVersionCode() {
            return versionCode;
        }

        public void setVersionCode(int versionCode) {
            this.versionCode = versionCode;
        }
    }
}
