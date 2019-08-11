package com.application.tchapj.main.bean;

import java.util.List;

public class HomeCircleModel {

    private String method;     //接口名
    private String level;      //接口信息
    private String code;       //接口返回结果
    private String description;//接口返回信息

    private HomeCircleModelResult data;

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

    public HomeCircleModelResult getData() {
        return data;
    }

    public void setData(HomeCircleModelResult data) {
        this.data = data;
    }

    public static class HomeCircleModelResult{


        private List<HomeCircle> circleTypeList;

        public List<HomeCircle> getCircleTypeList() {
            return circleTypeList;
        }

        public void setCircleTypeList(List<HomeCircle> circleTypeList) {
            this.circleTypeList = circleTypeList;
        }

        public static class HomeCircle{

            private String id;
            private String name;
            private String imgUrl;

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

            public String getImgUrl() {
                return imgUrl;
            }

            public void setImgUrl(String imgUrl) {
                this.imgUrl = imgUrl;
            }
        }

    }

}
