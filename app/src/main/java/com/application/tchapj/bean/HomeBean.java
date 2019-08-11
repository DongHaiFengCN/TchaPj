package com.application.tchapj.bean;

import java.io.Serializable;
import java.util.List;

// 主页面数据实体类
public class HomeBean implements Serializable {

    private String code;
    private String msg;
    private String success;
    private HomeData data;

    public HomeData getData() {
        return data;
    }

    public void setData(HomeData data) {
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public static class HomeData{

        private List<HomeDateBean> courseList;
        private List<BannerImgBean> bannerImgList;

        public List<HomeDateBean> getCourseList() {
            return courseList;
        }

        public void setCourseList(List<HomeDateBean> courseList) {
            this.courseList = courseList;
        }

        public List<BannerImgBean> getBannerImgList() {
            return bannerImgList;
        }

        public void setBannerImgList(List<BannerImgBean> bannerImgList) {
            this.bannerImgList = bannerImgList;
        }

        public static class HomeDateBean {

            private String courseName;
            private String courseNo;
            private String iconUrl;
            private String semester;

            public String getSemester() {
                return semester;
            }

            public void setSemester(String semester) {
                this.semester = semester;
            }

            public String getCourseName() {
                return courseName;
            }

            public void setCourseName(String courseName) {
                this.courseName = courseName;
            }

            public String getCourseNo() {
                return courseNo;
            }

            public void setCourseNo(String courseNo) {
                this.courseNo = courseNo;
            }

            public String getIconUrl() {
                return iconUrl;
            }

            public void setIconUrl(String iconUrl) {
                this.iconUrl = iconUrl;
            }
        }

        public static class BannerImgBean {

            private String imgUrl;
            private String id;
            private String title;
            private String url;

            public String getImgUrl() {
                return imgUrl;
            }

            public void setImgUrl(String imgUrl) {
                this.imgUrl = imgUrl;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }
    }


}
