package com.application.tchapj.bean;


import java.io.Serializable;

public class Course implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = -9121734039844677432L;
    private int jieci;

    private int day;
    private String des;
    private String CourseName;
    private String TeachPlace;
    private String TeachWeek;
    private String TeachDate;
    private String Section;
    private String TeachName;
    private int spanNum = 2;// 间隔

    private String ClassRoomName;
    private String ClassTypeName;

    public String getTeachDate() {
        return TeachDate;
    }

    public void setTeachDate(String teachDate) {
        TeachDate = teachDate;
    }

    public Course(int jieci, int day, String des) {
        this.jieci = jieci;
        this.day = day;
        this.des = des;
    }

    public Course() {
    }

    public int getJieci() {
        return jieci;
    }

    public void setJieci(int jieci) {
        this.jieci = jieci;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public int getSpanNum() {
        return spanNum;
    }

    public void setSpanNum(int spanNum) {
        this.spanNum = spanNum;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getCourseName() {
        return CourseName;
    }

    public void setCourseName(String courseName) {
        CourseName = courseName;
    }

    public String getTeachPlace() {
        return TeachPlace;
    }

    public void setTeachPlace(String teachPlace) {
        TeachPlace = teachPlace;
    }

    public String getTeachWeek() {
        return TeachWeek;
    }

    public void setTeachWeek(String teachWeek) {
        TeachWeek = teachWeek;
    }

    public String getSection() {
        return Section;
    }

    public void setSection(String section) {
        Section = section;
    }

    public String getTeachName() {
        return TeachName;
    }

    public void setTeachName(String teachName) {
        TeachName = teachName;
    }

    @Override
    public String toString() {
        return "Course [jieci=" + jieci + ", day=" + day + ", des=" + des
                + ", spanNun=" + spanNum + "]";
    }

    public String getClassRoomName() {
        return ClassRoomName;
    }

    public void setClassRoomName(String classRoomName) {
        ClassRoomName = classRoomName;
    }

    public String getClassTypeName() {
        return ClassTypeName;
    }

    public void setClassTypeName(String classTypeName) {
        ClassTypeName = classTypeName;
    }


}
