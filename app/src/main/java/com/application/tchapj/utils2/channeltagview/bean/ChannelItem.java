package com.application.tchapj.utils2.channeltagview.bean;

/**
 * 描述：频道bean
 * Created by zhaohl on 2017-3-7.
 */
public class ChannelItem {

    public int id;
    public int iconResid;

    public String titleId;
    public String title;

    public String getTitleId() {
        return titleId;
    }

    public void setTitleId(String titleId) {
        this.titleId = titleId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIconResid() {
        return iconResid;
    }

    public void setIconResid(int iconResid) {
        this.iconResid = iconResid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
