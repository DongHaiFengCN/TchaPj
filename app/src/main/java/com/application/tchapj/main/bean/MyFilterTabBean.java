package com.application.tchapj.main.bean;


import com.application.tchapj.utils2.poptabview.base.BaseFilterTabBean;

import java.util.List;

/**
 * Created by chenchangjun on 17/7/26.
 */

public class MyFilterTabBean extends BaseFilterTabBean<MyFilterTabBean.MyChildFilterBean> {


    /*情况1---比如,你需要如下字段*/
    protected String show_id;
    protected String show_name;//展示字段
    protected String channel_name;
    protected String category_ids;
    protected String tag_ids;
    protected String mall_ids;
    protected List<MyChildFilterBean> tabs;

    @Override
    public String getTab_id() {
        return show_id;
    }

    @Override
    public void setTab_id(String tab_id) {
        this.show_id=tab_id;
    }

    @Override
    public String getTab_name() {
        return show_name;
    }

    @Override
    public void setTab_name(String tab_name) {
        this.show_name=tab_name;
    }

    @Override
    public List<MyChildFilterBean> getTabs() {
        return tabs;
    }

    @Override
    public void setTabs(List<MyChildFilterBean> tabs) {
        this.tabs=tabs;
    }


    public static class MyChildFilterBean extends BaseFilterTabBean {


        /*情况1---比如,你需要如下字段*/

        protected String show_id;
        protected String show_name; //展示字段
        protected String channel_name;
        protected String category_ids;
        protected String tag_ids;
        protected String mall_ids;


        @Override
        public String getTab_id() {
            return show_id;
        }

        @Override
        public void setTab_id(String tab_id) {
            this.show_id=tab_id;
        }

        @Override
        public String getTab_name() {
            return show_name;
        }

        @Override
        public void setTab_name(String tab_name) {
            this.show_name=tab_name;
        }

        @Override
        public List getTabs() {
            return null;
        }

        @Override
        public void setTabs(List tabs) {

        }


        public String getChannel_name() {
            return channel_name;
        }

        public void setChannel_name(String channel_name) {
            this.channel_name = channel_name;
        }

        public String getCategory_ids() {
            return category_ids;
        }

        public void setCategory_ids(String category_ids) {
            this.category_ids = category_ids;
        }

        public String getTag_ids() {
            return tag_ids;
        }

        public void setTag_ids(String tag_ids) {
            this.tag_ids = tag_ids;
        }

        public String getMall_ids() {
            return mall_ids;
        }

        public void setMall_ids(String mall_ids) {
            this.mall_ids = mall_ids;
        }


    }


    public String getChannel_name() {
        return channel_name;
    }

    public void setChannel_name(String channel_name) {
        this.channel_name = channel_name;
    }

    public String getCategory_ids() {
        return category_ids;
    }

    public void setCategory_ids(String category_ids) {
        this.category_ids = category_ids;
    }

    public String getTag_ids() {
        return tag_ids;
    }

    public void setTag_ids(String tag_ids) {
        this.tag_ids = tag_ids;
    }

    public String getMall_ids() {
        return mall_ids;
    }

    public void setMall_ids(String mall_ids) {
        this.mall_ids = mall_ids;
    }
}
