package com.weather.zhigao.model.weather;

import android.os.Parcel;
import android.os.Parcelable;

public  class HourlyBean implements Parcelable {
            /**
             * cloud : 2
             * cond_code : 100
             * cond_txt : 晴
             * dew : -20
             * hum : 75
             * pop : 0
             * pres : 1036
             * time : 2019-02-16 22:00
             * tmp : -2
             * wind_deg : 317
             * wind_dir : 西北风
             * wind_sc : 3-4
             * wind_spd : 17
             */

            public String cloud;
            public String cond_code;
            public String cond_txt;
            public String dew;
            public String hum;
            public String pop;
            public String pres;
            public String time;
            public String tmp;
            public String wind_deg;
            public String wind_dir;
            public String wind_sc;
            public String wind_spd;

            public String getCloud() {
                return cloud;
            }

            public void setCloud(String cloud) {
                this.cloud = cloud;
            }

            public String getCond_code() {
                return cond_code;
            }

            public void setCond_code(String cond_code) {
                this.cond_code = cond_code;
            }

            public String getCond_txt() {
                return cond_txt;
            }

            public void setCond_txt(String cond_txt) {
                this.cond_txt = cond_txt;
            }

            public String getDew() {
                return dew;
            }

            public void setDew(String dew) {
                this.dew = dew;
            }

            public String getHum() {
                return hum;
            }

            public void setHum(String hum) {
                this.hum = hum;
            }

            public String getPop() {
                return pop;
            }

            public void setPop(String pop) {
                this.pop = pop;
            }

            public String getPres() {
                return pres;
            }

            public void setPres(String pres) {
                this.pres = pres;
            }

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }

            public String getTmp() {
                return tmp;
            }

            public void setTmp(String tmp) {
                this.tmp = tmp;
            }

            public String getWind_deg() {
                return wind_deg;
            }

            public void setWind_deg(String wind_deg) {
                this.wind_deg = wind_deg;
            }

            public String getWind_dir() {
                return wind_dir;
            }

            public void setWind_dir(String wind_dir) {
                this.wind_dir = wind_dir;
            }

            public String getWind_sc() {
                return wind_sc;
            }

            public void setWind_sc(String wind_sc) {
                this.wind_sc = wind_sc;
            }

            public String getWind_spd() {
                return wind_spd;
            }

            public void setWind_spd(String wind_spd) {
                this.wind_spd = wind_spd;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.cloud);
                dest.writeString(this.cond_code);
                dest.writeString(this.cond_txt);
                dest.writeString(this.dew);
                dest.writeString(this.hum);
                dest.writeString(this.pop);
                dest.writeString(this.pres);
                dest.writeString(this.time);
                dest.writeString(this.tmp);
                dest.writeString(this.wind_deg);
                dest.writeString(this.wind_dir);
                dest.writeString(this.wind_sc);
                dest.writeString(this.wind_spd);
            }

            public HourlyBean() {
            }

            protected HourlyBean(Parcel in) {
                this.cloud = in.readString();
                this.cond_code = in.readString();
                this.cond_txt = in.readString();
                this.dew = in.readString();
                this.hum = in.readString();
                this.pop = in.readString();
                this.pres = in.readString();
                this.time = in.readString();
                this.tmp = in.readString();
                this.wind_deg = in.readString();
                this.wind_dir = in.readString();
                this.wind_sc = in.readString();
                this.wind_spd = in.readString();
            }

            public static final Creator<HourlyBean> CREATOR = new Creator<HourlyBean>() {
                @Override
                public HourlyBean createFromParcel(Parcel source) {
                    return new HourlyBean(source);
                }

                @Override
                public HourlyBean[] newArray(int size) {
                    return new HourlyBean[size];
                }
            };
        }