package com.application.tchapj.main.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/3/27.
 */

public class PersonSelectModel implements Serializable {

    private String method;     //接口名
    private String level;      //接口信息
    private String code;       //接口返回结果
    private String description;//接口返回信息

    private PersonSelectModelResult data;

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

    public PersonSelectModelResult getData() {
        return data;
    }

    public void setData(PersonSelectModelResult data) {
        this.data = data;
    }

    public static class PersonSelectModelResult {

        private List<NewStypeSelect> newstypeList;

        private List<ServiceItemSelect> serviceItemList;

        public List<NewStypeSelect> getNewstypeList() {
            return newstypeList;
        }

        public void setNewstypeList(List<NewStypeSelect> newstypeList) {
            this.newstypeList = newstypeList;
        }

        public List<ServiceItemSelect> getServiceItemList() {
            return serviceItemList;
        }

        public void setServiceItemList(List<ServiceItemSelect> serviceItemList) {
            this.serviceItemList = serviceItemList;
        }

        public static class NewStypeSelect {
            private String id;
            private int index;
            private String name;
            private int rowCountPerPage;

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

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getRowCountPerPage() {
                return rowCountPerPage;
            }

            public void setRowCountPerPage(int rowCountPerPage) {
                this.rowCountPerPage = rowCountPerPage;
            }
        }

        public static class ServiceItemSelect {
            private String id;

            private String name;


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
        }

    }


}
