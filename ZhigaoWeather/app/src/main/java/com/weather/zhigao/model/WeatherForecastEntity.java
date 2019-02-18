package com.weather.zhigao.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class WeatherForecastEntity implements Parcelable{


    public List<HeWeather6Bean> HeWeather6;

    public List<HeWeather6Bean> getHeWeather6() {
        return HeWeather6;
    }

    public void setHeWeather6(List<HeWeather6Bean> HeWeather6) {
        this.HeWeather6 = HeWeather6;
    }

    public static class HeWeather6Bean implements Parcelable{
        /**
         * basic : {"cid":"CN101010300","location":"朝阳","parent_city":"北京","admin_area":"北京","cnty":"中国","lat":"39.92148972","lon":"116.48641205","tz":"+8.00"}
         * update : {"loc":"2019-02-16 20:56","utc":"2019-02-16 12:56"}
         * status : ok
         * now : {"cloud":"0","cond_code":"100","cond_txt":"晴","fl":"-5","hum":"22","pcpn":"0.0","pres":"1035","tmp":"0","vis":"30","wind_deg":"289","wind_dir":"西北风","wind_sc":"3","wind_spd":"14"}
         * daily_forecast : [{"cond_code_d":"100","cond_code_n":"100","cond_txt_d":"晴","cond_txt_n":"晴","date":"2019-02-16","hum":"43","mr":"13:54","ms":"04:02","pcpn":"0.0","pop":"0","pres":"1037","sr":"07:04","ss":"17:52","tmp_max":"2","tmp_min":"-6","uv_index":"3","vis":"17","wind_deg":"9","wind_dir":"北风","wind_sc":"3-4","wind_spd":"24"},{"cond_code_d":"100","cond_code_n":"101","cond_txt_d":"晴","cond_txt_n":"多云","date":"2019-02-17","hum":"40","mr":"14:58","ms":"05:04","pcpn":"0.0","pop":"0","pres":"1035","sr":"07:03","ss":"17:53","tmp_max":"6","tmp_min":"-5","uv_index":"3","vis":"20","wind_deg":"201","wind_dir":"西南风","wind_sc":"1-2","wind_spd":"6"},{"cond_code_d":"101","cond_code_n":"104","cond_txt_d":"多云","cond_txt_n":"阴","date":"2019-02-18","hum":"35","mr":"16:10","ms":"06:00","pcpn":"0.0","pop":"0","pres":"1027","sr":"07:01","ss":"17:54","tmp_max":"5","tmp_min":"-3","uv_index":"2","vis":"20","wind_deg":"184","wind_dir":"南风","wind_sc":"1-2","wind_spd":"2"},{"cond_code_d":"101","cond_code_n":"100","cond_txt_d":"多云","cond_txt_n":"晴","date":"2019-02-19","hum":"40","mr":"17:25","ms":"06:49","pcpn":"0.0","pop":"0","pres":"1024","sr":"07:00","ss":"17:55","tmp_max":"7","tmp_min":"-3","uv_index":"2","vis":"19","wind_deg":"359","wind_dir":"北风","wind_sc":"1-2","wind_spd":"9"},{"cond_code_d":"100","cond_code_n":"100","cond_txt_d":"晴","cond_txt_n":"晴","date":"2019-02-20","hum":"42","mr":"18:41","ms":"07:32","pcpn":"0.0","pop":"0","pres":"1032","sr":"06:59","ss":"17:56","tmp_max":"9","tmp_min":"-4","uv_index":"4","vis":"20","wind_deg":"356","wind_dir":"北风","wind_sc":"1-2","wind_spd":"10"},{"cond_code_d":"100","cond_code_n":"101","cond_txt_d":"晴","cond_txt_n":"多云","date":"2019-02-21","hum":"41","mr":"19:56","ms":"08:09","pcpn":"0.0","pop":"0","pres":"1027","sr":"06:57","ss":"17:57","tmp_max":"10","tmp_min":"-3","uv_index":"4","vis":"20","wind_deg":"0","wind_dir":"北风","wind_sc":"1-2","wind_spd":"5"},{"cond_code_d":"101","cond_code_n":"101","cond_txt_d":"多云","cond_txt_n":"多云","date":"2019-02-22","hum":"36","mr":"21:08","ms":"08:44","pcpn":"0.0","pop":"2","pres":"1025","sr":"06:56","ss":"17:58","tmp_max":"9","tmp_min":"-2","uv_index":"1","vis":"19","wind_deg":"175","wind_dir":"南风","wind_sc":"1-2","wind_spd":"8"}]
         * hourly : [{"cloud":"2","cond_code":"100","cond_txt":"晴","dew":"-20","hum":"75","pop":"0","pres":"1036","time":"2019-02-16 22:00","tmp":"-2","wind_deg":"317","wind_dir":"西北风","wind_sc":"3-4","wind_spd":"17"},{"cloud":"2","cond_code":"100","cond_txt":"晴","dew":"-21","hum":"81","pop":"0","pres":"1037","time":"2019-02-17 01:00","tmp":"-4","wind_deg":"358","wind_dir":"北风","wind_sc":"3-4","wind_spd":"17"},{"cloud":"3","cond_code":"100","cond_txt":"晴","dew":"-21","hum":"83","pop":"0","pres":"1031","time":"2019-02-17 04:00","tmp":"-5","wind_deg":"1","wind_dir":"北风","wind_sc":"3-4","wind_spd":"14"},{"cloud":"1","cond_code":"100","cond_txt":"晴","dew":"-20","hum":"82","pop":"0","pres":"1027","time":"2019-02-17 07:00","tmp":"-4","wind_deg":"197","wind_dir":"西南风","wind_sc":"3-4","wind_spd":"23"},{"cloud":"19","cond_code":"100","cond_txt":"晴","dew":"-21","hum":"60","pop":"0","pres":"1027","time":"2019-02-17 10:00","tmp":"0","wind_deg":"174","wind_dir":"南风","wind_sc":"1-2","wind_spd":"11"},{"cloud":"15","cond_code":"100","cond_txt":"晴","dew":"-24","hum":"41","pop":"0","pres":"1031","time":"2019-02-17 13:00","tmp":"3","wind_deg":"19","wind_dir":"东北风","wind_sc":"1-2","wind_spd":"7"},{"cloud":"2","cond_code":"100","cond_txt":"晴","dew":"-24","hum":"49","pop":"0","pres":"1032","time":"2019-02-17 16:00","tmp":"4","wind_deg":"94","wind_dir":"东风","wind_sc":"1-2","wind_spd":"10"},{"cloud":"49","cond_code":"100","cond_txt":"晴","dew":"-24","hum":"74","pop":"0","pres":"1031","time":"2019-02-17 19:00","tmp":"0","wind_deg":"81","wind_dir":"东风","wind_sc":"1-2","wind_spd":"5"}]
         * lifestyle : [{"type":"comf","brf":"较不舒适","txt":"白天天气晴好，但仍会使您感觉偏冷，不很舒适，请注意适时添加衣物，以防感冒。"},{"type":"drsg","brf":"冷","txt":"天气冷，建议着棉服、羽绒服、皮夹克加羊毛衫等冬季服装。年老体弱者宜着厚棉衣、冬大衣或厚羽绒服。"},{"type":"flu","brf":"较易发","txt":"天气较凉，较易发生感冒，请适当增加衣服。体质较弱的朋友尤其应该注意防护。"},{"type":"sport","brf":"较不宜","txt":"天气较好，但考虑天气寒冷，风力较强，推荐您进行室内运动，若户外运动请注意保暖并做好准备活动。"},{"type":"trav","brf":"一般","txt":"天气较好，温度稍低，而且风稍大，让您感觉有些冷，会对外出有一定影响，外出注意防风保暖。"},{"type":"uv","brf":"弱","txt":"紫外线强度较弱，建议出门前涂擦SPF在12-15之间、PA+的防晒护肤品。"},{"type":"cw","brf":"较适宜","txt":"较适宜洗车，未来一天无雨，风力较小，擦洗一新的汽车至少能保持一天。"},{"type":"air","brf":"较差","txt":"气象条件较不利于空气污染物稀释、扩散和清除，请适当减少室外活动时间。"}]
         */

        public BasicBean basic;
        public UpdateBean update;
        public String status;
        public NowBean now;
        public List<DailyForecastBean> daily_forecast;
        public List<HourlyBean> hourly;
        public List<LifestyleBean> lifestyle;

        public BasicBean getBasic() {
            return basic;
        }

        public void setBasic(BasicBean basic) {
            this.basic = basic;
        }

        public UpdateBean getUpdate() {
            return update;
        }

        public void setUpdate(UpdateBean update) {
            this.update = update;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public NowBean getNow() {
            return now;
        }

        public void setNow(NowBean now) {
            this.now = now;
        }

        public List<DailyForecastBean> getDaily_forecast() {
            return daily_forecast;
        }

        public void setDaily_forecast(List<DailyForecastBean> daily_forecast) {
            this.daily_forecast = daily_forecast;
        }

        public List<HourlyBean> getHourly() {
            return hourly;
        }

        public void setHourly(List<HourlyBean> hourly) {
            this.hourly = hourly;
        }

        public List<LifestyleBean> getLifestyle() {
            return lifestyle;
        }

        public void setLifestyle(List<LifestyleBean> lifestyle) {
            this.lifestyle = lifestyle;
        }

        public static class BasicBean implements Parcelable{
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

        public static class UpdateBean implements Parcelable{
            /**
             * loc : 2019-02-16 20:56
             * utc : 2019-02-16 12:56
             */

            public String loc;
            public String utc;

            public String getLoc() {
                return loc;
            }

            public void setLoc(String loc) {
                this.loc = loc;
            }

            public String getUtc() {
                return utc;
            }

            public void setUtc(String utc) {
                this.utc = utc;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.loc);
                dest.writeString(this.utc);
            }

            public UpdateBean() {
            }

            protected UpdateBean(Parcel in) {
                this.loc = in.readString();
                this.utc = in.readString();
            }

            public static final Creator<UpdateBean> CREATOR = new Creator<UpdateBean>() {
                @Override
                public UpdateBean createFromParcel(Parcel source) {
                    return new UpdateBean(source);
                }

                @Override
                public UpdateBean[] newArray(int size) {
                    return new UpdateBean[size];
                }
            };
        }

        public static class NowBean implements Parcelable{
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

        public static class DailyForecastBean implements Parcelable{
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

        public static class HourlyBean implements Parcelable{
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

        public static class LifestyleBean implements Parcelable{
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

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeParcelable(this.basic, flags);
            dest.writeParcelable(this.update, flags);
            dest.writeString(this.status);
            dest.writeParcelable(this.now, flags);
            dest.writeTypedList(this.daily_forecast);
            dest.writeTypedList(this.hourly);
            dest.writeTypedList(this.lifestyle);
        }

        public HeWeather6Bean() {
        }

        protected HeWeather6Bean(Parcel in) {
            this.basic = in.readParcelable(BasicBean.class.getClassLoader());
            this.update = in.readParcelable(UpdateBean.class.getClassLoader());
            this.status = in.readString();
            this.now = in.readParcelable(NowBean.class.getClassLoader());
            this.daily_forecast = in.createTypedArrayList(DailyForecastBean.CREATOR);
            this.hourly = in.createTypedArrayList(HourlyBean.CREATOR);
            this.lifestyle = in.createTypedArrayList(LifestyleBean.CREATOR);
        }

        public static final Creator<HeWeather6Bean> CREATOR = new Creator<HeWeather6Bean>() {
            @Override
            public HeWeather6Bean createFromParcel(Parcel source) {
                return new HeWeather6Bean(source);
            }

            @Override
            public HeWeather6Bean[] newArray(int size) {
                return new HeWeather6Bean[size];
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.HeWeather6);
    }

    public WeatherForecastEntity() {
    }

    protected WeatherForecastEntity(Parcel in) {
        this.HeWeather6 = in.createTypedArrayList(HeWeather6Bean.CREATOR);
    }

    public static final Creator<WeatherForecastEntity> CREATOR = new Creator<WeatherForecastEntity>() {
        @Override
        public WeatherForecastEntity createFromParcel(Parcel source) {
            return new WeatherForecastEntity(source);
        }

        @Override
        public WeatherForecastEntity[] newArray(int size) {
            return new WeatherForecastEntity[size];
        }
    };
}
