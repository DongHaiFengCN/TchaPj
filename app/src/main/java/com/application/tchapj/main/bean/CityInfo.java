package com.application.tchapj.main.bean;

import java.io.Serializable;

public class CityInfo implements Serializable {

    private int streamnumber;
    private String cityName;

    public int getStreamnumber() {
        return streamnumber;
    }

    public void setStreamnumber(int streamnumber) {
        this.streamnumber = streamnumber;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}
