package com.weather.zhigao.model.weather;

import android.os.Parcel;
import android.os.Parcelable;

public  class BasicBean implements Parcelable {
            /**
             * cid : CN101010300
             * location : 朝阳
             * parent_city : 北京
             * admin_area : 北京
             * cnty : 中国
             * lat : 39.92148972
             * lon : 116.48641205
             * tz : +8.00
             */

            public String cid;
            public String location;
            public String parent_city;
            public String admin_area;
            public String cnty;
            public String lat;
            public String lon;
            public String tz;

            public String getCid() {
                return cid;
            }

            public void setCid(String cid) {
                this.cid = cid;
            }

            public String getLocation() {
                return location;
            }

            public void setLocation(String location) {
                this.location = location;
            }

            public String getParent_city() {
                return parent_city;
            }

            public void setParent_city(String parent_city) {
                this.parent_city = parent_city;
            }

            public String getAdmin_area() {
                return admin_area;
            }

            public void setAdmin_area(String admin_area) {
                this.admin_area = admin_area;
            }

            public String getCnty() {
                return cnty;
            }

            public void setCnty(String cnty) {
                this.cnty = cnty;
            }

            public String getLat() {
                return lat;
            }

            public void setLat(String lat) {
                this.lat = lat;
            }

            public String getLon() {
                return lon;
            }

            public void setLon(String lon) {
                this.lon = lon;
            }

            public String getTz() {
                return tz;
            }

            public void setTz(String tz) {
                this.tz = tz;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.cid);
                dest.writeString(this.location);
                dest.writeString(this.parent_city);
                dest.writeString(this.admin_area);
                dest.writeString(this.cnty);
                dest.writeString(this.lat);
                dest.writeString(this.lon);
                dest.writeString(this.tz);
            }

            public BasicBean() {
            }

            protected BasicBean(Parcel in) {
                this.cid = in.readString();
                this.location = in.readString();
                this.parent_city = in.readString();
                this.admin_area = in.readString();
                this.cnty = in.readString();
                this.lat = in.readString();
                this.lon = in.readString();
                this.tz = in.readString();
            }

            public static final Creator<BasicBean> CREATOR = new Creator<BasicBean>() {
                @Override
                public BasicBean createFromParcel(Parcel source) {
                    return new BasicBean(source);
                }

                @Override
                public BasicBean[] newArray(int size) {
                    return new BasicBean[size];
                }
            };
        }