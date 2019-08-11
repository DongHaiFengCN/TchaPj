package com.application.tchapj.main.bean;

import java.io.Serializable;
import java.util.List;


public class HomeMediaList implements Serializable{

    private String method;     //接口名
    private String level;      //接口信息
    private String code;       //接口返回结果
    private String description;//接口返回信息

    private HomeMediaListResult data;

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

    public HomeMediaListResult getData() {
        return data;
    }

    public void setData(HomeMediaListResult data) {
        this.data = data;
    }

    public static class HomeMediaListResult{

        private List<HomeMediaModel> mediaList;

        public List<HomeMediaModel> getMediaList() {
            return mediaList;
        }

        public void setMediaList(List<HomeMediaModel> mediaList) {
            this.mediaList = mediaList;
        }

        public static class HomeMediaModel  {

            private String id;
            private String headUrl;
            private String nickName;
            private String fansTotal;
            private String content;
            private String cat_type;
            private String service_label;
            private String index;
            private String rowCountPerPage;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getHeadUrl() {
                return headUrl;
            }

            public void setHeadUrl(String headUrl) {
                this.headUrl = headUrl;
            }

            public String getNickName() {
                return nickName;
            }

            public void setNickName(String nickName) {
                this.nickName = nickName;
            }

            public String getCat_type() {
                return cat_type;
            }

            public void setCat_type(String cat_type) {
                this.cat_type = cat_type;
            }

            public String getService_label() {
                return service_label;
            }

            public void setService_label(String service_label) {
                this.service_label = service_label;
            }

            public String getIndex() {
                return index;
            }

            public void setIndex(String index) {
                this.index = index;
            }

            public String getRowCountPerPage() {
                return rowCountPerPage;
            }

            public void setRowCountPerPage(String rowCountPerPage) {
                this.rowCountPerPage = rowCountPerPage;
            }

            public String getFansTotal() {
                return fansTotal;
            }

            public void setFansTotal(String fansTotal) {
                this.fansTotal = fansTotal;
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
