package com.weather.zhigao.model.weather;

import android.os.Parcel;
import android.os.Parcelable;

public  class NowBean implements Parcelable {
            /**
             * cloud : 0
             * cond_code : 100
             * cond_txt : 晴
             * fl : -5
             * hum : 22
             * pcpn : 0.0
             * pres : 1035
             * tmp : 0
             * vis : 30
             * wind_deg : 289
             * wind_dir : 西北风
             * wind_sc : 3
             * wind_spd : 14
             */

            public String cloud;
            public String cond_code;
            public String cond_txt;
            public String fl;
            public String hum;
            public String pcpn;
            public String pres;
            public String tmp;
            public String vis;
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

            public String getFl() {
                return fl;
            }

            public void setFl(String fl) {
                this.fl = fl;
            }

            public String getHum() {
                return hum;
            }

            public void setHum(String hum) {
                this.hum = hum;
            }

            public String getPcpn() {
                return pcpn;
            }

            public void setPcpn(String pcpn) {
                this.pcpn = pcpn;
            }

            public String getPres() {
                return pres;
            }

            public void setPres(String pres) {
                this.pres = pres;
            }

            public String getTmp() {
                return tmp;
            }

            public void setTmp(String tmp) {
                this.tmp = tmp;
            }

            public String getVis() {
                return vis;
            }

            public void setVis(String vis) {
                this.vis = vis;
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
                dest.writeString(this.fl);
                dest.writeString(this.hum);
                dest.writeString(this.pcpn);
                dest.writeString(this.pres);
                dest.writeString(this.tmp);
                dest.writeString(this.vis);
                dest.writeString(this.wind_deg);
                dest.writeString(this.wind_dir);
                dest.writeString(this.wind_sc);
                dest.writeString(this.wind_spd);
            }

            public NowBean() {
            }

            protected NowBean(Parcel in) {
                this.cloud = in.readString();
                this.cond_code = in.readString();
                this.cond_txt = in.readString();
                this.fl = in.readString();
                this.hum = in.readString();
                this.pcpn = in.readString();
                this.pres = in.readString();
                this.tmp = in.readString();
                this.vis = in.readString();
                this.wind_deg = in.readString();
                this.wind_dir = in.readString();
                this.wind_sc = in.readString();
                this.wind_spd = in.readString();
            }

            public static final Creator<NowBean> CREATOR = new Creator<NowBean>() {
                @Override
                public NowBean createFromParcel(Parcel source) {
                    return new NowBean(source);
                }

                @Override
                public NowBean[] newArray(int size) {
                    return new NowBean[size];
                }
            };
        }
