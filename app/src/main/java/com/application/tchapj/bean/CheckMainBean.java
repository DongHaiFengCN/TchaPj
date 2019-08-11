package com.application.tchapj.bean;

import java.io.Serializable;
import java.util.List;

public class CheckMainBean implements Serializable {

    private String code;
    private String msg;
    private boolean success;
    private CheckMainData data; // 查询实体类的二级实体类对象

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public CheckMainData getData() {
        return data;
    }

    public void setData(CheckMainData data) {
        this.data = data;
    }

    public static class CheckMainData{

        private List<AemesterList> semesterList; // 实体类的三级实体类对象
        private List<AttendanceStatuses> attendanceStatuses; // 实体类的三级实体类对象

        public List<AemesterList> getSemesterList() {
            return semesterList;
        }

        public void setSemesterList(List<AemesterList> semesterList) {
            this.semesterList = semesterList;
        }

        public List<AttendanceStatuses> getAttendanceStatuses() {
            return attendanceStatuses;
        }

        public void setAttendanceStatuses(List<AttendanceStatuses> attendanceStatuses) {
            this.attendanceStatuses = attendanceStatuses;
        }

        public static class AemesterList {

            private String current;
            private String semester;

            public AemesterList(String current, String semester){
                this.current = current;
                this.semester = semester;
            }

            public String getCurrent() {
                return current;
            }

            public void setCurrent(String current) {
                this.current = current;
            }

            public String getSemester() {
                return semester;
            }

            public void setSemester(String semester) {
                this.semester = semester;
            }
        }

        public static class AttendanceStatuses {

            private int createBy;
//            private Date createTime;
            private boolean deleted;
            private String description;
            private double id;
            private double itemSort;
            private String label;
            private int lastUpdateBy;
//            private Date lastUpdateTime;
            private String name;
            private int page;
            private int pageNum;
            private int pageSize;
            private int rows;
            private String type;
            private double typeSort;
            private String value;

            public int getCreateBy() {
                return createBy;
            }

            public void setCreateBy(int createBy) {
                this.createBy = createBy;
            }

//            public Date getCreateTime() {
//                return createTime;
//            }
//
//            public void setCreateTime(Date createTime) {
//                this.createTime = createTime;
//            }

            public boolean isDeleted() {
                return deleted;
            }

            public void setDeleted(boolean deleted) {
                this.deleted = deleted;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public double getId() {
                return id;
            }

            public void setId(double id) {
                this.id = id;
            }

            public double getItemSort() {
                return itemSort;
            }

            public void setItemSort(double itemSort) {
                this.itemSort = itemSort;
            }

            public String getLabel() {
                return label;
            }

            public void setLabel(String label) {
                this.label = label;
            }

            public int getLastUpdateBy() {
                return lastUpdateBy;
            }

            public void setLastUpdateBy(int lastUpdateBy) {
                this.lastUpdateBy = lastUpdateBy;
            }

//            public Date getLastUpdateTime() {
//                return lastUpdateTime;
//            }
//
//            public void setLastUpdateTime(Date lastUpdateTime) {
//                this.lastUpdateTime = lastUpdateTime;
//            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getPage() {
                return page;
            }

            public void setPage(int page) {
                this.page = page;
            }

            public int getPageNum() {
                return pageNum;
            }

            public void setPageNum(int pageNum) {
                this.pageNum = pageNum;
            }

            public int getPageSize() {
                return pageSize;
            }

            public void setPageSize(int pageSize) {
                this.pageSize = pageSize;
            }

            public int getRows() {
                return rows;
            }

            public void setRows(int rows) {
                this.rows = rows;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public double getTypeSort() {
                return typeSort;
            }

            public void setTypeSort(double typeSort) {
                this.typeSort = typeSort;
            }

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }
        }
    }


}
