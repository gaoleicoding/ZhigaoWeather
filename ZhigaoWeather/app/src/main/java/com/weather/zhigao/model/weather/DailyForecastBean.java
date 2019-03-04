package com.weather.zhigao.model.weather;

import android.os.Parcel;
import android.os.Parcelable;

public  class DailyForecastBean implements Parcelable {
            /**
             * cond_code_d : 100
             * cond_code_n : 100
             * cond_txt_d : 晴
             * cond_txt_n : 晴
             * date : 2019-02-16
             * hum : 43
             * mr : 13:54
             * ms : 04:02
             * pcpn : 0.0
             * pop : 0
             * pres : 1037
             * sr : 07:04
             * ss : 17:52
             * tmp_max : 2
             * tmp_min : -6
             * uv_index : 3
             * vis : 17
             * wind_deg : 9
             * wind_dir : 北风
             * wind_sc : 3-4
             * wind_spd : 24
             */

            public String cond_code_d;
            public String cond_code_n;
            public String cond_txt_d;
            public String cond_txt_n;
            public String date;
            public String hum;
            public String mr;
            public String ms;
            public String pcpn;
            public String pop;
            public String pres;
            public String sr;
            public String ss;
            public String tmp_max;
            public String tmp_min;
            public String uv_index;
            public String vis;
            public String wind_deg;
            public String wind_dir;
            public String wind_sc;
            public String wind_spd;

            public String getCond_code_d() {
                return cond_code_d;
            }

            public void setCond_code_d(String cond_code_d) {
                this.cond_code_d = cond_code_d;
            }

            public String getCond_code_n() {
                return cond_code_n;
            }

            public void setCond_code_n(String cond_code_n) {
                this.cond_code_n = cond_code_n;
            }

            public String getCond_txt_d() {
                return cond_txt_d;
            }

            public void setCond_txt_d(String cond_txt_d) {
                this.cond_txt_d = cond_txt_d;
            }

            public String getCond_txt_n() {
                return cond_txt_n;
            }

            public void setCond_txt_n(String cond_txt_n) {
                this.cond_txt_n = cond_txt_n;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getHum() {
                return hum;
            }

            public void setHum(String hum) {
                this.hum = hum;
            }

            public String getMr() {
                return mr;
            }

            public void setMr(String mr) {
                this.mr = mr;
            }

            public String getMs() {
                return ms;
            }

            public void setMs(String ms) {
                this.ms = ms;
            }

            public String getPcpn() {
                return pcpn;
            }

            public void setPcpn(String pcpn) {
                this.pcpn = pcpn;
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

            public String getSr() {
                return sr;
            }

            public void setSr(String sr) {
                this.sr = sr;
            }

            public String getSs() {
                return ss;
            }

            public void setSs(String ss) {
                this.ss = ss;
            }

            public String getTmp_max() {
                return tmp_max;
            }

            public void setTmp_max(String tmp_max) {
                this.tmp_max = tmp_max;
            }

            public String getTmp_min() {
                return tmp_min;
            }

            public void setTmp_min(String tmp_min) {
                this.tmp_min = tmp_min;
            }

            public String getUv_index() {
                return uv_index;
            }

            public void setUv_index(String uv_index) {
                this.uv_index = uv_index;
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
                dest.writeString(this.cond_code_d);
                dest.writeString(this.cond_code_n);
                dest.writeString(this.cond_txt_d);
                dest.writeString(this.cond_txt_n);
                dest.writeString(this.date);
                dest.writeString(this.hum);
                dest.writeString(this.mr);
                dest.writeString(this.ms);
                dest.writeString(this.pcpn);
                dest.writeString(this.pop);
                dest.writeString(this.pres);
                dest.writeString(this.sr);
                dest.writeString(this.ss);
                dest.writeString(this.tmp_max);
                dest.writeString(this.tmp_min);
                dest.writeString(this.uv_index);
                dest.writeString(this.vis);
                dest.writeString(this.wind_deg);
                dest.writeString(this.wind_dir);
                dest.writeString(this.wind_sc);
                dest.writeString(this.wind_spd);
            }

            public DailyForecastBean() {
            }

            protected DailyForecastBean(Parcel in) {
                this.cond_code_d = in.readString();
                this.cond_code_n = in.readString();
                this.cond_txt_d = in.readString();
                this.cond_txt_n = in.readString();
                this.date = in.readString();
                this.hum = in.readString();
                this.mr = in.readString();
                this.ms = in.readString();
                this.pcpn = in.readString();
                this.pop = in.readString();
                this.pres = in.readString();
                this.sr = in.readString();
                this.ss = in.readString();
                this.tmp_max = in.readString();
                this.tmp_min = in.readString();
                this.uv_index = in.readString();
                this.vis = in.readString();
                this.wind_deg = in.readString();
                this.wind_dir = in.readString();
                this.wind_sc = in.readString();
                this.wind_spd = in.readString();
            }

            public static final Creator<DailyForecastBean> CREATOR = new Creator<DailyForecastBean>() {
                @Override
                public DailyForecastBean createFromParcel(Parcel source) {
                    return new DailyForecastBean(source);
                }

                @Override
                public DailyForecastBean[] newArray(int size) {
                    return new DailyForecastBean[size];
                }
            };
        }