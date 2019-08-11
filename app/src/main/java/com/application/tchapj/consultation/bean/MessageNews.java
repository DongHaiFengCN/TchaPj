package com.application.tchapj.consultation.bean;

import java.io.Serializable;
import java.util.List;

public class MessageNews implements Serializable {


    private String id;
    private String tid;
    private String name;
    private String imgUrl;
    private String conent;
    private String externalUrl;
    private long createTime;
    private int readTotal;
    private int likeTotal;//赞总量
    private String resourcesId;
    private String isTop;   //是否热门
    private String newstype_id;
    private String new_model;  //资讯模式: 0普通 1三图 2个人 3视频
    private String advertisers_id;
    private String title;
    private String r_url;
    private String r_name;
    private String con;
    private int index;
    private String ctype;
    private int rowCountPerPage;
    private int isLike; // 判断是否点赞 0未点赞 1已经点赞
    private String top;//是否置顶
    private int commentCounts;//评论总量

    public int getCommentCounts() {
        return commentCounts;
    }

    public void setCommentCounts(int commentCounts) {
        this.commentCounts = commentCounts;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    private List<CommentlogsBean> commentlogs;

    private List<LikelogsBean> likelogs;

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

    public String getConent() {
        return conent;
    }

    public void setConent(String conent) {
        this.conent = conent;
    }

    public String getExternalUrl() {
        return externalUrl;
    }

    public void setExternalUrl(String externalUrl) {
        this.externalUrl = externalUrl;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public int getReadTotal() {
        return readTotal;
    }

    public void setReadTotal(int readTotal) {
        this.readTotal = readTotal;
    }

    public int getLikeTotal() {
        return likeTotal;
    }

    public void setLikeTotal(int likeTotal) {
        this.likeTotal = likeTotal;
    }

    public String getResourcesId() {
        return resourcesId;
    }

    public void setResourcesId(String resourcesId) {
        this.resourcesId = resourcesId;
    }

    public String getIsTop() {
        return isTop;
    }

    public void setIsTop(String isTop) {
        this.isTop = isTop;
    }

    public String getNewstype_id() {
        return newstype_id;
    }

    public void setNewstype_id(String newstype_id) {
        this.newstype_id = newstype_id;
    }

    public String getNew_model() {
        return new_model;
    }

    public void setNew_model(String new_model) {
        this.new_model = new_model;
    }

    public String getAdvertisers_id() {
        return advertisers_id;
    }

    public void setAdvertisers_id(String advertisers_id) {
        this.advertisers_id = advertisers_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getR_url() {
        return r_url;
    }

    public void setR_url(String r_url) {
        this.r_url = r_url;
    }

    public String getR_name() {
        return r_name;
    }

    public void setR_name(String r_name) {
        this.r_name = r_name;
    }

    public String getCon() {
        return con;
    }

    public void setCon(String con) {
        this.con = con;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getCtype() {
        return ctype;
    }

    public void setCtype(String ctype) {
        this.ctype = ctype;
    }

    public int getRowCountPerPage() {
        return rowCountPerPage;
    }

    public void setRowCountPerPage(int rowCountPerPage) {
        this.rowCountPerPage = rowCountPerPage;
    }

    public int getIsLike() {
        return isLike;
    }

    public void setIsLike(int isLike) {
        this.isLike = isLike;
    }

    public List<CommentlogsBean> getCommentlogs() {
        return commentlogs;
    }

    public void setCommentlogs(List<CommentlogsBean> commentlogs) {
        this.commentlogs = commentlogs;
    }

    public List<LikelogsBean> getLikelogs() {
        return likelogs;
    }

    public void setLikelogs(List<LikelogsBean> likelogs) {
        this.likelogs = likelogs;
    }

    public String getTop() {
        return top;
    }

    public void setTop(String top) {
        this.top = top;
    }
}
