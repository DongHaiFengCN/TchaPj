package com.application.tchapj.search.bean;

import com.application.tchapj.consultation.bean.MessageNews;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 安卓开发 on 2018/8/7.
 */

public class SearchBean implements Serializable {

    private String method;     //接口名
    private String level;      //接口信息
    private String code;       //接口返回结果
    private String description;//接口返回信息

    private SearchBeanResult data;

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

    public SearchBeanResult getData() {
        return data;
    }

    public void setData(SearchBeanResult data) {
        this.data = data;
    }

    public static class SearchBeanResult {

        private List<MessageNews> news;

        private List<SearchMingrenList> mingrenList;

        public List<MessageNews> getNews() {
            return news;
        }

        public void setNews(List<MessageNews> news) {
            this.news = news;
        }

        public List<SearchMingrenList> getMingrenList() {
            return mingrenList;
        }

        public void setMingrenList(List<SearchMingrenList> mingrenList) {
            this.mingrenList = mingrenList;
        }

        public static class SearchMingrenList {

            private String id;
            private String headUrl;
            private String nickName;
            private String newsTypeId;
            private String content;
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

            public String getNewsTypeId() {
                return newsTypeId;
            }

            public void setNewsTypeId(String newsTypeId) {
                this.newsTypeId = newsTypeId;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
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
        }


    }

}
