package com.weather.zhigao.model;

public class CityAddBean {

    public String location, cond_txt, tmp_min, tmp_max;

    public CityAddBean(String location, String cond_txt, String tmp_min, String tmp_max) {
        this.location = location;
        this.cond_txt = cond_txt;
        this.tmp_min = tmp_min;
        this.tmp_max = tmp_max;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCond_txt() {
        return cond_txt;
    }

    public void setCond_txt(String cond_txt) {
        this.cond_txt = cond_txt;
    }

    public String getTmp_min() {
        return tmp_min;
    }

    public void setTmp_min(String tmp_min) {
        this.tmp_min = tmp_min;
    }

    public String getTmp_max() {
        return tmp_max;
    }

    public void setTmp_max(String tmp_max) {
        this.tmp_max = tmp_max;
    }
}
