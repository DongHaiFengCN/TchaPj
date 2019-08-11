package com.application.tchapj.main.bean;

import java.io.Serializable;
import java.util.List;


public class PersonMediaModel implements Serializable {

    private String method;     //接口名
    private String level;      //接口信息
    private String code;       //接口返回结果
    private String description;//接口返回信息

    private PersonMediaModelResult data;

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

    public PersonMediaModelResult getData() {
        return data;
    }

    public void setData(PersonMediaModelResult data) {
        this.data = data;
    }

    public static class PersonMediaModelResult {

        private String memberId;
        private int info;
        private List<PersonMedia> classnewsList;

        private List<HuoYue> huoyueList;

        public String getMemberId() {
            return memberId;
        }

        public void setMemberId(String memberId) {
            this.memberId = memberId;
        }

        public int getInfo() {
            return info;
        }

        public void setInfo(int info) {
            this.info = info;
        }



        public List<PersonMedia> getClassnewsList() {
            return classnewsList;
        }

        public void setClassnewsList(List<PersonMedia> classnewsList) {
            this.classnewsList = classnewsList;
        }

        public List<HuoYue> getHuoyueList() {
            return huoyueList;
        }

        public void setHuoyueList(List<HuoYue> huoyueList) {
            this.huoyueList = huoyueList;
        }

        public static class PersonMedia {

            private String id;
            private String headUrl;
            private String nickName;
            private String cat_type;  //0_名人   1-媒体
            private String content;
            private int index;
            private int rowCountPerPage;
            private String service_label;
            private String zixunName; //分类名称

            public String getCat_type() {
                return cat_type;
            }

            public void setCat_type(String cat_type) {
                this.cat_type = cat_type;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getHeadUrl() {
                return headUrl;
            }

            public void setHeadUrl(String headUrl) {
                this.headUrl = headUrl;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public int getIndex() {
                return index;
            }

            public void setIndex(int index) {
                this.index = index;
            }

            public String getNickName() {
                return nickName;
            }

            public void setNickName(String nickName) {
                this.nickName = nickName;
            }

            public int getRowCountPerPage() {
                return rowCountPerPage;
            }

            public void setRowCountPerPage(int rowCountPerPage) {
                this.rowCountPerPage = rowCountPerPage;
            }

            public String getService_label() {
                return service_label;
            }

            public void setService_label(String service_label) {
                this.service_label = service_label;
            }

            public String getZixunName() {
                return zixunName;
            }

            public void setZixunName(String zixunName) {
                this.zixunName = zixunName;
            }
        }

        public static class HuoYue {

            private String id;
            private String headUrl;
            private String nickName;
            private String activity;
            private int index;
            private int rowCountPerPage;

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

            public String getActivity() {
                return activity;
            }

            public void setActivity(String activity) {
                this.activity = activity;
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
