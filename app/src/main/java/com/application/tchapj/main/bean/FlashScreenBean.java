package com.application.tchapj.main.bean;

import java.io.Serializable;

public class FlashScreenBean implements Serializable {

    private String OpenPageImg;
    private String href;
    private String name;
    private String litimg;//缩略图
    private String intro;//内容

    public String getLitimg() {
        return litimg;
    }

    public void setLitimg(String litimg) {
        this.litimg = litimg;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOpenPageImg() {
        return OpenPageImg;
    }

    public void setOpenPageImg(String openPageImg) {
        OpenPageImg = openPageImg;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }
}
