package com.application.tchapj.consultation.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/3/27.
 */

public class ConsultationNewsModel implements Serializable {

    private String method;     //接口名
    private String level;      //接口信息
    private String code;       //接口返回结果
    private String description;//接口返回信息

    private MessageNewsResult data;

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

    public MessageNewsResult getData() {
        return data;
    }

    public void setData(MessageNewsResult data) {
        this.data = data;
    }

    public static class MessageNewsResult {

        private List<MessageNews> news;

        public List<MessageNews> getNews() {
            return news;
        }

        public void setNews(List<MessageNews> news) {
            this.news = news;
        }

//        public static class MessageNews {
//
//            private String id;
//            private String name;
//            private String imgUrl;
//            private String conent;
//            private String externalUrl;
//            private long createTime;
//            private int readTotal;
//            private int likeTotal;
//            private String resourcesId;
//            private String isTop;   //是否热门
//            private String newstype_id;
//            private String new_model;  //资讯模式: 0普通 1三图 2个人 3视频
//            private String advertisers_id;
//            private String title;
//            private String r_url;
//            private String r_name;
//            private String con;
//            private int index;
//            private String ctype;
//            private int rowCountPerPage;
//            private int isLike; // 判断是否点赞 0未点赞 1已经点赞
//
//            private List<CommentlogsBean> commentlogs;
//
//            private List<LikelogsBean> likelogs;
//
//            public String getCtype() {
//                return ctype;
//            }
//
//            public void setCtype(String ctype) {
//                this.ctype = ctype;
//            }
//
//            public String getExternalUrl() {
//                return externalUrl;
//            }
//
//            public void setExternalUrl(String externalUrl) {
//                this.externalUrl = externalUrl;
//            }
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
//            public int getReadTotal() {
//                return readTotal;
//            }
//
//            public void setReadTotal(int readTotal) {
//                this.readTotal = readTotal;
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
//            public String getAdvertisers_id() {
//                return advertisers_id;
//            }
//
//            public void setAdvertisers_id(String advertisers_id) {
//                this.advertisers_id = advertisers_id;
//            }
//
//            public String getTitle() {
//                return title;
//            }
//
//            public void setTitle(String title) {
//                this.title = title;
//            }
//
//            public String getR_url() {
//                return r_url;
//            }
//
//            public void setR_url(String r_url) {
//                this.r_url = r_url;
//            }
//
//            public String getR_name() {
//                return r_name;
//            }
//
//            public void setR_name(String r_name) {
//                this.r_name = r_name;
//            }
//
//            public String getCon() {
//                return con;
//            }
//
//            public void setCon(String con) {
//                this.con = con;
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
//
//            public int getIsLike() {
//                return isLike;
//            }
//
//            public void setIsLike(int isLike) {
//                this.isLike = isLike;
//            }
//
//            public List<CommentlogsBean> getCommentlogs() {
//                return commentlogs;
//            }
//
//            public void setCommentlogs(List<CommentlogsBean> commentlogs) {
//                this.commentlogs = commentlogs;
//            }
//
//            public List<LikelogsBean> getLikelogs() {
//                return likelogs;
//            }
//
//            public void setLikelogs(List<LikelogsBean> likelogs) {
//                this.likelogs = likelogs;
//            }
//            public static class CommentlogsBean {
//
//                private String content;  // 评论内容
//                private String memberId; // 评论昵称
//                private long createTime;
//                private int likes;
//                private int index;
//                private int rowCountPerPage;
//
//                public String getContent() {
//                    return content;
//                }
//
//                public void setContent(String content) {
//                    this.content = content;
//                }
//
//                public String getMemberId() {
//                    return memberId;
//                }
//
//                public void setMemberId(String memberId) {
//                    this.memberId = memberId;
//                }
//
//                public long getCreateTime() {
//                    return createTime;
//                }
//
//                public void setCreateTime(long createTime) {
//                    this.createTime = createTime;
//                }
//
//                public int getLikes() {
//                    return likes;
//                }
//
//                public void setLikes(int likes) {
//                    this.likes = likes;
//                }
//
//                public int getIndex() {
//                    return index;
//                }
//
//                public void setIndex(int index) {
//                    this.index = index;
//                }
//
//                public int getRowCountPerPage() {
//                    return rowCountPerPage;
//                }
//
//                public void setRowCountPerPage(int rowCountPerPage) {
//                    this.rowCountPerPage = rowCountPerPage;
//                }
//            }
//
//            public static class LikelogsBean {
//
//                private String nickName; // 点赞的昵称
//
//                public String getNickName() {
//                    return nickName;
//                }
//
//                public void setNickName(String nickName) {
//                    this.nickName = nickName;
//                }
//            }
//
//        }

    }


}
