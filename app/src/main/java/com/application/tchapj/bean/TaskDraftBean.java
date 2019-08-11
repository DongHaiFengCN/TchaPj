package com.application.tchapj.bean;

import com.application.tchapj.utils2.imagepicker.bean.ImageItem;
import com.application.tchapj.utils2.picture.entity.LocalMedia;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Create by zyy on 2019/4/26
 * Description: 保存任务的草稿
 */
public class TaskDraftBean implements Serializable {

    private String activityTitle;//活动标题
    private String startTime;
    private String endTime;
    private String startTimeComplete;//完整的时间包含年的
    private String endTimeComplete;
    private int selectType;//选择的转发形式

    private String taskRequire;//任务要求
    private String linkTitle;//链接标题
    private String linkUrl;//链接url


    private String taskGuide;//活动引导
    private String taskRequireLike;//任务要求点赞
    private String wechatCopywriting;//朋友圈文案
    private List<String> imageUrlList = null;//上传到后台的url
    private List<LocalMedia> selectImageList = null;//adapter展示图片附件/链接图

    private String activityImgUrl;//活动缩略图
    private ArrayList<ImageItem> activityImgList;//adapter活动缩略图

    public List<String> getImageUrlList() {
        return imageUrlList;
    }

    public void setImageUrlList(List<String> imageUrlList) {
        this.imageUrlList = imageUrlList;
    }

    public List<LocalMedia> getSelectImageList() {
        return selectImageList;
    }

    public void setSelectImageList(List<LocalMedia> selectImageList) {
        this.selectImageList = selectImageList;
    }

    public ArrayList<ImageItem> getActivityImgList() {
        return activityImgList;
    }

    public void setActivityImgList(ArrayList<ImageItem> activityImgList) {
        this.activityImgList = activityImgList;
    }

    public String getActivityImgUrl() {
        return activityImgUrl;
    }

    public void setActivityImgUrl(String activityImgUrl) {
        this.activityImgUrl = activityImgUrl;
    }


    public String getActivityTitle() {
        return activityTitle;
    }

    public void setActivityTitle(String activityTitle) {
        this.activityTitle = activityTitle;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public int getSelectType() {
        return selectType;
    }

    public void setSelectType(int selectType) {
        this.selectType = selectType;
    }

    public String getTaskRequire() {
        return taskRequire;
    }

    public void setTaskRequire(String taskRequire) {
        this.taskRequire = taskRequire;
    }

    public String getLinkTitle() {
        return linkTitle;
    }

    public void setLinkTitle(String linkTitle) {
        this.linkTitle = linkTitle;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    public String getTaskGuide() {
        return taskGuide;
    }

    public void setTaskGuide(String taskGuide) {
        this.taskGuide = taskGuide;
    }

    public String getTaskRequireLike() {
        return taskRequireLike;
    }

    public void setTaskRequireLike(String taskRequireLike) {
        this.taskRequireLike = taskRequireLike;
    }

    public String getWechatCopywriting() {
        return wechatCopywriting;
    }

    public void setWechatCopywriting(String wechatCopywriting) {
        this.wechatCopywriting = wechatCopywriting;
    }

    public String getStartTimeComplete() {
        return startTimeComplete;
    }

    public void setStartTimeComplete(String startTimeComplete) {
        this.startTimeComplete = startTimeComplete;
    }

    public String getEndTimeComplete() {
        return endTimeComplete;
    }

    public void setEndTimeComplete(String endTimeComplete) {
        this.endTimeComplete = endTimeComplete;
    }
}
