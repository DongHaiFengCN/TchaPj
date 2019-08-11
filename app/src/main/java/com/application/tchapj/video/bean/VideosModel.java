package com.application.tchapj.video.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/3/27.
 */

public class VideosModel implements Serializable {

    private String method;     //接口名
    private String level;      //接口信息
    private String code;       //接口返回结果
    private String description;//接口返回信息

    private VideosResult data;

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

    public VideosResult getData() {
        return data;
    }

    public void setData(VideosResult data) {
        this.data = data;
    }

    public static class VideosResult {

        private List<Videos> news;

        public List<Videos> getNews() {
            return news;
        }

        public void setNews(List<Videos> news) {
            this.news = news;
        }

        public class Videos {

            private String id;
            private String name;
            private String imgUrl;       // 页面图片
            private String externalUrl;  // 视频路径
            private String r_url;  // 头像路径
            private long createTime;
            private int index;
            private int rowCountPerPage;

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

            public String getExternalUrl() {
                return externalUrl;
            }

            public void setExternalUrl(String externalUrl) {
                this.externalUrl = externalUrl;
            }

            public String getR_url() {
                return r_url;
            }

            public void setR_url(String r_url) {
                this.r_url = r_url;
            }

            public long getCreateTime() {
                return createTime;
            }

            public void setCreateTime(long createTime) {
                this.createTime = createTime;
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
