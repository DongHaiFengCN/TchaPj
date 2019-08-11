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

//        public static class SearchNews {
//
//            private String id;
//            private String name;
//            private String imgUrl;
//            private String conent;
//            private long createTime;
//            private int likeTotal;
//            private String resourcesId;
//            private String isTop;
//            private String newstype_id;
//            private String new_model;
//            private int index;
//            private int rowCountPerPage;
//
//            public String getId() {
//                return id;
//            }
//
//            public void setId(String id) {
//                this.id = id;
//            }
//
//            public String getName() {
//                return name;
//            }
//
//            public void setName(String name) {
//                this.name = name;
//            }
//
//            public String getImgUrl() {
//                return imgUrl;
//            }
//
//            public void setImgUrl(String imgUrl) {
//                this.imgUrl = imgUrl;
//            }
//
//            public String getConent() {
//                return conent;
//            }
//
//            public void setConent(String conent) {
//                this.conent = conent;
//            }
//
//            public long getCreateTime() {
//                return createTime*1000L;
//            }
//
//            public void setCreateTime(long createTime) {
//                this.createTime = createTime;
//            }
//
//            public int getLikeTotal() {
//                return likeTotal;
//            }
//
//            public void setLikeTotal(int likeTotal) {
//                this.likeTotal = likeTotal;
//            }
//
//            public String getResourcesId() {
//                return resourcesId;
//            }
//
//            public void setResourcesId(String resourcesId) {
//                this.resourcesId = resourcesId;
//            }
//
//            public String getIsTop() {
//                return isTop;
//            }
//
//            public void setIsTop(String isTop) {
//                this.isTop = isTop;
//            }
//
//            public String getNewstype_id() {
//                return newstype_id;
//            }
//
//            public void setNewstype_id(String newstype_id) {
//                this.newstype_id = newstype_id;
//            }
//
//            public String getNew_model() {
//                return new_model;
//            }
//
//            public void setNew_model(String new_model) {
//                this.new_model = new_model;
//            }
//
//            public int getIndex() {
//                return index;
//            }
//
//            public void setIndex(int index) {
//                this.index = index;
//            }
//
//            public int getRowCountPerPage() {
//                return rowCountPerPage;
//            }
//
//            public void setRowCountPerPage(int rowCountPerPage) {
//                this.rowCountPerPage = rowCountPerPage;
//            }
//        }

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
