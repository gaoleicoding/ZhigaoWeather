package com.weather.zhigao.model.weather;

import android.os.Parcel;
import android.os.Parcelable;

public  class LifestyleBean implements Parcelable {
            /**
             * type : comf
             * brf : 较不舒适
             * txt : 白天天气晴好，但仍会使您感觉偏冷，不很舒适，请注意适时添加衣物，以防感冒。
             */

            public String type;
            public String brf;
            public String txt;

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getBrf() {
                return brf;
            }

            public void setBrf(String brf) {
                this.brf = brf;
            }

            public String getTxt() {
                return txt;
            }

            public void setTxt(String txt) {
                this.txt = txt;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.type);
                dest.writeString(this.brf);
                dest.writeString(this.txt);
            }

            public LifestyleBean() {
            }

            protected LifestyleBean(Parcel in) {
                this.type = in.readString();
                this.brf = in.readString();
                this.txt = in.readString();
            }

            public static final Creator<LifestyleBean> CREATOR = new Creator<LifestyleBean>() {
                @Override
                public LifestyleBean createFromParcel(Parcel source) {
                    return new LifestyleBean(source);
                }

                @Override
                public LifestyleBean[] newArray(int size) {
                    return new LifestyleBean[size];
                }
            };
        }
