package com.weather.zhigao.model;

import com.google.gson.Gson;

import java.util.List;

public class WeatherBroadcast {


    public List<HeWeather6Bean> HeWeather6;

    public static WeatherBroadcast objectFromData(String str) {

        return new Gson().fromJson(str, WeatherBroadcast.class);
    }

    public List<HeWeather6Bean> getHeWeather6() {
        return HeWeather6;
    }

    public void setHeWeather6(List<HeWeather6Bean> HeWeather6) {
        this.HeWeather6 = HeWeather6;
    }

    public static class HeWeather6Bean {
        /**
         * basic : {"cid":"CN101010300","location":"朝阳","parent_city":"北京","admin_area":"北京","cnty":"中国","lat":"39.92148972","lon":"116.48641205","tz":"+8.00"}
         * update : {"loc":"2019-02-15 15:56","utc":"2019-02-15 07:56"}
         * status : ok
         * daily_forecast : [{"cond_code_d":"100","cond_code_n":"100","cond_txt_d":"晴","cond_txt_n":"晴","date":"2019-02-15","hum":"38","mr":"12:59","ms":"02:57","pcpn":"0.0","pop":"0","pres":"1031","sr":"07:05","ss":"17:50","tmp_max":"2","tmp_min":"-6","uv_index":"3","vis":"19","wind_deg":"288","wind_dir":"西北风","wind_sc":"4-5","wind_spd":"32"},{"cond_code_d":"100","cond_code_n":"100","cond_txt_d":"晴","cond_txt_n":"晴","date":"2019-02-16","hum":"35","mr":"13:54","ms":"04:02","pcpn":"0.0","pop":"0","pres":"1037","sr":"07:04","ss":"17:52","tmp_max":"4","tmp_min":"-6","uv_index":"3","vis":"20","wind_deg":"317","wind_dir":"西北风","wind_sc":"3-4","wind_spd":"21"},{"cond_code_d":"100","cond_code_n":"101","cond_txt_d":"晴","cond_txt_n":"多云","date":"2019-02-17","hum":"36","mr":"14:58","ms":"05:04","pcpn":"0.0","pop":"0","pres":"1035","sr":"07:03","ss":"17:53","tmp_max":"6","tmp_min":"-5","uv_index":"4","vis":"20","wind_deg":"2","wind_dir":"北风","wind_sc":"1-2","wind_spd":"4"},{"cond_code_d":"104","cond_code_n":"101","cond_txt_d":"阴","cond_txt_n":"多云","date":"2019-02-18","hum":"33","mr":"16:10","ms":"06:00","pcpn":"0.0","pop":"0","pres":"1026","sr":"07:01","ss":"17:54","tmp_max":"4","tmp_min":"-4","uv_index":"2","vis":"20","wind_deg":"189","wind_dir":"南风","wind_sc":"1-2","wind_spd":"3"},{"cond_code_d":"101","cond_code_n":"100","cond_txt_d":"多云","cond_txt_n":"晴","date":"2019-02-19","hum":"49","mr":"17:25","ms":"06:49","pcpn":"0.0","pop":"0","pres":"1024","sr":"07:00","ss":"17:55","tmp_max":"6","tmp_min":"-3","uv_index":"4","vis":"20","wind_deg":"205","wind_dir":"西南风","wind_sc":"1-2","wind_spd":"8"},{"cond_code_d":"100","cond_code_n":"100","cond_txt_d":"晴","cond_txt_n":"晴","date":"2019-02-20","hum":"36","mr":"18:41","ms":"07:32","pcpn":"0.0","pop":"0","pres":"1032","sr":"06:59","ss":"17:56","tmp_max":"8","tmp_min":"-3","uv_index":"4","vis":"20","wind_deg":"259","wind_dir":"西南风","wind_sc":"1-2","wind_spd":"9"},{"cond_code_d":"100","cond_code_n":"101","cond_txt_d":"晴","cond_txt_n":"多云","date":"2019-02-21","hum":"41","mr":"19:56","ms":"08:09","pcpn":"0.0","pop":"0","pres":"1027","sr":"06:57","ss":"17:57","tmp_max":"10","tmp_min":"-2","uv_index":"4","vis":"20","wind_deg":"216","wind_dir":"西南风","wind_sc":"1-2","wind_spd":"5"}]
         */

        public BasicBean basic;
        public UpdateBean update;
        public String status;
        public List<DailyForecastBean> daily_forecast;

        public static HeWeather6Bean objectFromData(String str) {

            return new Gson().fromJson(str, HeWeather6Bean.class);
        }

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

        public List<DailyForecastBean> getDaily_forecast() {
            return daily_forecast;
        }

        public void setDaily_forecast(List<DailyForecastBean> daily_forecast) {
            this.daily_forecast = daily_forecast;
        }

        public static class BasicBean {
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

            public static BasicBean objectFromData(String str) {

                return new Gson().fromJson(str, BasicBean.class);
            }

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
        }

        public static class UpdateBean {
            /**
             * loc : 2019-02-15 15:56
             * utc : 2019-02-15 07:56
             */

            public String loc;
            public String utc;

            public static UpdateBean objectFromData(String str) {

                return new Gson().fromJson(str, UpdateBean.class);
            }

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
        }

        public static class DailyForecastBean {
            /**
             * cond_code_d : 100
             * cond_code_n : 100
             * cond_txt_d : 晴
             * cond_txt_n : 晴
             * date : 2019-02-15
             * hum : 38
             * mr : 12:59
             * ms : 02:57
             * pcpn : 0.0
             * pop : 0
             * pres : 1031
             * sr : 07:05
             * ss : 17:50
             * tmp_max : 2
             * tmp_min : -6
             * uv_index : 3
             * vis : 19
             * wind_deg : 288
             * wind_dir : 西北风
             * wind_sc : 4-5
             * wind_spd : 32
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

            public static DailyForecastBean objectFromData(String str) {

                return new Gson().fromJson(str, DailyForecastBean.class);
            }

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
        }
    }
}
