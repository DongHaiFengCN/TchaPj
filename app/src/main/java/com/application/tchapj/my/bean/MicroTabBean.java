package com.application.tchapj.my.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 安卓开发 on 2018/7/31.
 */

public class MicroTabBean implements Serializable {

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

        private List<MessageNews> newstypeList;

        public List<MessageNews> getNewstypeList() {
            return newstypeList;
        }

        public void setNewstypeList(List<MessageNews> newstypeList) {
            this.newstypeList = newstypeList;
        }

        public class MessageNews {

            private String id;
            private String name;
            private int index;
            private int rowCountPerPage;

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
