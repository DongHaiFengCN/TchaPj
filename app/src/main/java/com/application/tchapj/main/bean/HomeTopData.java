package com.application.tchapj.main.bean;

import java.io.Serializable;
import java.util.List;

public class HomeTopData implements Serializable {

    private String method;     //接口名
    private String level;      //接口信息
    private String code;       //接口返回结果
    private String description;//接口返回信息

    private HomeTopDataResult data;

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

    public HomeTopDataResult getData() {
        return data;
    }

    public void setData(HomeTopDataResult data) {
        this.data = data;
    }

    public static class HomeTopDataResult{

        private String count;
        private List<HomeBanner> ladv;
        private List<HomeTopNews> news;

        public String getCount() {
            return count;
        }

        public void setCount(String count) {
            this.count = count;
        }

        public List<HomeBanner> getLadv() {
            return ladv;
        }

        public void setLadv(List<HomeBanner> ladv) {
            this.ladv = ladv;
        }

        public List<HomeTopNews> getNews() {
            return news;
        }

        public void setNews(List<HomeTopNews> news) {
            this.news = news;
        }

        public static class HomeBanner {
            private String id;
            private String name;
            private String image;
            private String type;
            private int porder;
            private String href;
            private String activity;
            private String bannerImage;
            private String intro;
            private String litimg;

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

            public String getHref() {
                return href;
            }

            public void setHref(String href) {
                this.href = href;
            }

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

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public int getPorder() {
                return porder;
            }

            public void setPorder(int porder) {
                this.porder = porder;
            }

            public String getActivity() {
                return activity;
            }

            public void setActivity(String activity) {
                this.activity = activity;
            }

            public String getBannerImage() {
                return bannerImage;
            }

            public void setBannerImage(String bannerImage) {
                this.bannerImage = bannerImage;
            }
        }

        public static class HomeTopNews {


            private String id;
            private String imgUrl;
            private String newstype_id;
            private String title;
            private String r_name;

            private int ctype;
            private int index;
            private int rowCountPerPage;

            public String getImgUrl() {
                return imgUrl;
            }

            public void setImgUrl(String imgUrl) {
                this.imgUrl = imgUrl;
            }

            public String getNewstype_id() {
                return newstype_id;
            }

            public void setNewstype_id(String newstype_id) {
                this.newstype_id = newstype_id;
            }

            public String getR_name() {
                return r_name;
            }

            public void setR_name(String r_name) {
                this.r_name = r_name;
            }

            public int getCtype() {
                return ctype;
            }

            public void setCtype(int ctype) {
                this.ctype = ctype;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
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
