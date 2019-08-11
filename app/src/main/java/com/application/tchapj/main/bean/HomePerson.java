package com.application.tchapj.main.bean;

import java.io.Serializable;
import java.util.List;

public class HomePerson implements Serializable {

    private String method;     //接口名
    private String level;      //接口信息
    private String code;       //接口返回结果
    private String description;//接口返回信息

    private HomePersonResult data;

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

    public HomePersonResult getData() {
        return data;
    }

    public void setData(HomePersonResult data) {
        this.data = data;
    }

    public static class HomePersonResult{

        private List<HomeMingren> mingrenList;

        public List<HomeMingren> getMingrenList() {
            return mingrenList;
        }

        public void setMingrenList(List<HomeMingren> mingrenList) {
            this.mingrenList = mingrenList;
        }

        public static class HomeMingren {

            private String id;
            private String big_url;
            private String nickName;
            private String content;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getBig_url() {
                return big_url;
            }

            public void setBig_url(String big_url) {
                this.big_url = big_url;
            }

            public String getNickName() {
                return nickName;
            }

            public void setNickName(String nickName) {
                this.nickName = nickName;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

        }


    }


}
