package com.weather.zhigao.model

import android.os.Parcel
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
@Parcelize
data class WeatherForecastEntity : Parcelable {


     var HeWeather6: List<HeWeather6Bean>?=null
    @Parcelize
    class HeWeather6Bean : Parcelable {
        /**
         * basic : {"cid":"CN101010300","location":"朝阳","parent_city":"北京","admin_area":"北京","cnty":"中国","lat":"39.92148972","lon":"116.48641205","tz":"+8.00"}
         * update : {"loc":"2019-02-16 20:56","utc":"2019-02-16 12:56"}
         * status : ok
         * now : {"cloud":"0","cond_code":"100","cond_txt":"晴","fl":"-5","hum":"22","pcpn":"0.0","pres":"1035","tmp":"0","vis":"30","wind_deg":"289","wind_dir":"西北风","wind_sc":"3","wind_spd":"14"}
         * daily_forecast : [{"cond_code_d":"100","cond_code_n":"100","cond_txt_d":"晴","cond_txt_n":"晴","date":"2019-02-16","hum":"43","mr":"13:54","ms":"04:02","pcpn":"0.0","pop":"0","pres":"1037","sr":"07:04","ss":"17:52","tmp_max":"2","tmp_min":"-6","uv_index":"3","vis":"17","wind_deg":"9","wind_dir":"北风","wind_sc":"3-4","wind_spd":"24"},{"cond_code_d":"100","cond_code_n":"101","cond_txt_d":"晴","cond_txt_n":"多云","date":"2019-02-17","hum":"40","mr":"14:58","ms":"05:04","pcpn":"0.0","pop":"0","pres":"1035","sr":"07:03","ss":"17:53","tmp_max":"6","tmp_min":"-5","uv_index":"3","vis":"20","wind_deg":"201","wind_dir":"西南风","wind_sc":"1-2","wind_spd":"6"},{"cond_code_d":"101","cond_code_n":"104","cond_txt_d":"多云","cond_txt_n":"阴","date":"2019-02-18","hum":"35","mr":"16:10","ms":"06:00","pcpn":"0.0","pop":"0","pres":"1027","sr":"07:01","ss":"17:54","tmp_max":"5","tmp_min":"-3","uv_index":"2","vis":"20","wind_deg":"184","wind_dir":"南风","wind_sc":"1-2","wind_spd":"2"},{"cond_code_d":"101","cond_code_n":"100","cond_txt_d":"多云","cond_txt_n":"晴","date":"2019-02-19","hum":"40","mr":"17:25","ms":"06:49","pcpn":"0.0","pop":"0","pres":"1024","sr":"07:00","ss":"17:55","tmp_max":"7","tmp_min":"-3","uv_index":"2","vis":"19","wind_deg":"359","wind_dir":"北风","wind_sc":"1-2","wind_spd":"9"},{"cond_code_d":"100","cond_code_n":"100","cond_txt_d":"晴","cond_txt_n":"晴","date":"2019-02-20","hum":"42","mr":"18:41","ms":"07:32","pcpn":"0.0","pop":"0","pres":"1032","sr":"06:59","ss":"17:56","tmp_max":"9","tmp_min":"-4","uv_index":"4","vis":"20","wind_deg":"356","wind_dir":"北风","wind_sc":"1-2","wind_spd":"10"},{"cond_code_d":"100","cond_code_n":"101","cond_txt_d":"晴","cond_txt_n":"多云","date":"2019-02-21","hum":"41","mr":"19:56","ms":"08:09","pcpn":"0.0","pop":"0","pres":"1027","sr":"06:57","ss":"17:57","tmp_max":"10","tmp_min":"-3","uv_index":"4","vis":"20","wind_deg":"0","wind_dir":"北风","wind_sc":"1-2","wind_spd":"5"},{"cond_code_d":"101","cond_code_n":"101","cond_txt_d":"多云","cond_txt_n":"多云","date":"2019-02-22","hum":"36","mr":"21:08","ms":"08:44","pcpn":"0.0","pop":"2","pres":"1025","sr":"06:56","ss":"17:58","tmp_max":"9","tmp_min":"-2","uv_index":"1","vis":"19","wind_deg":"175","wind_dir":"南风","wind_sc":"1-2","wind_spd":"8"}]
         * hourly : [{"cloud":"2","cond_code":"100","cond_txt":"晴","dew":"-20","hum":"75","pop":"0","pres":"1036","time":"2019-02-16 22:00","tmp":"-2","wind_deg":"317","wind_dir":"西北风","wind_sc":"3-4","wind_spd":"17"},{"cloud":"2","cond_code":"100","cond_txt":"晴","dew":"-21","hum":"81","pop":"0","pres":"1037","time":"2019-02-17 01:00","tmp":"-4","wind_deg":"358","wind_dir":"北风","wind_sc":"3-4","wind_spd":"17"},{"cloud":"3","cond_code":"100","cond_txt":"晴","dew":"-21","hum":"83","pop":"0","pres":"1031","time":"2019-02-17 04:00","tmp":"-5","wind_deg":"1","wind_dir":"北风","wind_sc":"3-4","wind_spd":"14"},{"cloud":"1","cond_code":"100","cond_txt":"晴","dew":"-20","hum":"82","pop":"0","pres":"1027","time":"2019-02-17 07:00","tmp":"-4","wind_deg":"197","wind_dir":"西南风","wind_sc":"3-4","wind_spd":"23"},{"cloud":"19","cond_code":"100","cond_txt":"晴","dew":"-21","hum":"60","pop":"0","pres":"1027","time":"2019-02-17 10:00","tmp":"0","wind_deg":"174","wind_dir":"南风","wind_sc":"1-2","wind_spd":"11"},{"cloud":"15","cond_code":"100","cond_txt":"晴","dew":"-24","hum":"41","pop":"0","pres":"1031","time":"2019-02-17 13:00","tmp":"3","wind_deg":"19","wind_dir":"东北风","wind_sc":"1-2","wind_spd":"7"},{"cloud":"2","cond_code":"100","cond_txt":"晴","dew":"-24","hum":"49","pop":"0","pres":"1032","time":"2019-02-17 16:00","tmp":"4","wind_deg":"94","wind_dir":"东风","wind_sc":"1-2","wind_spd":"10"},{"cloud":"49","cond_code":"100","cond_txt":"晴","dew":"-24","hum":"74","pop":"0","pres":"1031","time":"2019-02-17 19:00","tmp":"0","wind_deg":"81","wind_dir":"东风","wind_sc":"1-2","wind_spd":"5"}]
         * lifestyle : [{"type":"comf","brf":"较不舒适","txt":"白天天气晴好，但仍会使您感觉偏冷，不很舒适，请注意适时添加衣物，以防感冒。"},{"type":"drsg","brf":"冷","txt":"天气冷，建议着棉服、羽绒服、皮夹克加羊毛衫等冬季服装。年老体弱者宜着厚棉衣、冬大衣或厚羽绒服。"},{"type":"flu","brf":"较易发","txt":"天气较凉，较易发生感冒，请适当增加衣服。体质较弱的朋友尤其应该注意防护。"},{"type":"sport","brf":"较不宜","txt":"天气较好，但考虑天气寒冷，风力较强，推荐您进行室内运动，若户外运动请注意保暖并做好准备活动。"},{"type":"trav","brf":"一般","txt":"天气较好，温度稍低，而且风稍大，让您感觉有些冷，会对外出有一定影响，外出注意防风保暖。"},{"type":"uv","brf":"弱","txt":"紫外线强度较弱，建议出门前涂擦SPF在12-15之间、PA+的防晒护肤品。"},{"type":"cw","brf":"较适宜","txt":"较适宜洗车，未来一天无雨，风力较小，擦洗一新的汽车至少能保持一天。"},{"type":"air","brf":"较差","txt":"气象条件较不利于空气污染物稀释、扩散和清除，请适当减少室外活动时间。"}]
         */

        lateinit  var basic: BasicBean
        lateinit  var update: BasicBean.UpdateBean
        lateinit  var status: String
        lateinit  var now: BasicBean.NowBean
        lateinit  var daily_forecast: List<BasicBean.DailyForecastBean>
        lateinit  var hourly: List<BasicBean.HourlyBean>
        lateinit  var lifestyle: List<BasicBean.LifestyleBean>
        @Parcelize
        class BasicBean : Parcelable {
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

            lateinit var cid: String
            lateinit var location: String
            lateinit var parent_city: String
            lateinit var admin_area: String
            lateinit var cnty: String
            lateinit var lat: String
            lateinit var lon: String
            lateinit var tz: String

//            override fun describeContents(): Int {
//                return 0
//            }
//
//            override fun writeToParcel(dest: Parcel, flags: Int) {
//                dest.writeString(this.cid)
//                dest.writeString(this.location)
//                dest.writeString(this.parent_city)
//                dest.writeString(this.admin_area)
//                dest.writeString(this.cnty)
//                dest.writeString(this.lat)
//                dest.writeString(this.lon)
//                dest.writeString(this.tz)
//            }
//
//            constructor() {}
//
//            protected constructor(`in`: Parcel) {
//                this.cid = `in`.readString()
//                this.location = `in`.readString()
//                this.parent_city = `in`.readString()
//                this.admin_area = `in`.readString()
//                this.cnty = `in`.readString()
//                this.lat = `in`.readString()
//                this.lon = `in`.readString()
//                this.tz = `in`.readString()
//            }
//
//            companion object {
//
//                val CREATOR: Parcelable.Creator<BasicBean> = object : Parcelable.Creator<BasicBean> {
//                    override fun createFromParcel(source: Parcel): BasicBean {
//                        return BasicBean(source)
//                    }
//
//                    override fun newArray(size: Int): Array<BasicBean> {
//                        return arrayOfNulls(size)
//                    }
//                }
//            }
//        }
 @Parcelize
        class UpdateBean : Parcelable {
            /**
             * loc : 2019-02-16 20:56
             * utc : 2019-02-16 12:56
             */

            lateinit var loc: String
            lateinit var utc: String
//
//            override fun describeContents(): Int {
//                return 0
//            }
//
//            override fun writeToParcel(dest: Parcel, flags: Int) {
//                dest.writeString(this.loc)
//                dest.writeString(this.utc)
//            }
//
//            constructor() {}
//
//            protected constructor(`in`: Parcel) {
//                this.loc = `in`.readString()
//                this.utc = `in`.readString()
//            }
//
//            companion object {
//
//                val CREATOR: Parcelable.Creator<UpdateBean> = object : Parcelable.Creator<UpdateBean> {
//                    override fun createFromParcel(source: Parcel): UpdateBean {
//                        return UpdateBean(source)
//                    }
//
//                    override fun newArray(size: Int): Array<UpdateBean> {
//                        return arrayOfNulls(size)
//                    }
//                }
//            }
        }
        @Parcelize
        class NowBean : Parcelable {
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

            lateinit var cloud: String
            lateinit var cond_code: String
            lateinit var cond_txt: String
            lateinit var fl: String
            lateinit var hum: String
            lateinit var pcpn: String
            lateinit var pres: String
            lateinit var tmp: String
            lateinit var vis: String
            lateinit var wind_deg: String
            lateinit var wind_dir: String
            lateinit var wind_sc: String
            lateinit var wind_spd: String

//            override fun describeContents(): Int {
//                return 0
//            }
//
//            override fun writeToParcel(dest: Parcel, flags: Int) {
//                dest.writeString(this.cloud)
//                dest.writeString(this.cond_code)
//                dest.writeString(this.cond_txt)
//                dest.writeString(this.fl)
//                dest.writeString(this.hum)
//                dest.writeString(this.pcpn)
//                dest.writeString(this.pres)
//                dest.writeString(this.tmp)
//                dest.writeString(this.vis)
//                dest.writeString(this.wind_deg)
//                dest.writeString(this.wind_dir)
//                dest.writeString(this.wind_sc)
//                dest.writeString(this.wind_spd)
//            }
//
//            constructor() {}
//
//            protected constructor(`in`: Parcel) {
//                this.cloud = `in`.readString()
//                this.cond_code = `in`.readString()
//                this.cond_txt = `in`.readString()
//                this.fl = `in`.readString()
//                this.hum = `in`.readString()
//                this.pcpn = `in`.readString()
//                this.pres = `in`.readString()
//                this.tmp = `in`.readString()
//                this.vis = `in`.readString()
//                this.wind_deg = `in`.readString()
//                this.wind_dir = `in`.readString()
//                this.wind_sc = `in`.readString()
//                this.wind_spd = `in`.readString()
//            }
//
//            companion object {
//
//                val CREATOR: Parcelable.Creator<NowBean> = object : Parcelable.Creator<NowBean> {
//                    override fun createFromParcel(source: Parcel): NowBean {
//                        return NowBean(source)
//                    }
//
//                    override fun newArray(size: Int): Array<NowBean> {
//                        return arrayOfNulls(size)
//                    }
//                }
//            }
        }
        @Parcelize
        class DailyForecastBean : Parcelable {
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

            lateinit var cond_code_d: String
            lateinit var cond_code_n: String
            lateinit var cond_txt_d: String
            lateinit var cond_txt_n: String
            lateinit var date: String
            lateinit var hum: String
            lateinit var mr: String
            lateinit var ms: String
            lateinit var pcpn: String
            lateinit var pop: String
            lateinit var pres: String
            lateinit var sr: String
            lateinit var ss: String
            lateinit var tmp_max: String
            lateinit var tmp_min: String
            lateinit var uv_index: String
            lateinit var vis: String
            lateinit var wind_deg: String
            lateinit var wind_dir: String
            lateinit var wind_sc: String
            lateinit var wind_spd: String

//            override fun describeContents(): Int {
//                return 0
//            }
//
//            override fun writeToParcel(dest: Parcel, flags: Int) {
//                dest.writeString(this.cond_code_d)
//                dest.writeString(this.cond_code_n)
//                dest.writeString(this.cond_txt_d)
//                dest.writeString(this.cond_txt_n)
//                dest.writeString(this.date)
//                dest.writeString(this.hum)
//                dest.writeString(this.mr)
//                dest.writeString(this.ms)
//                dest.writeString(this.pcpn)
//                dest.writeString(this.pop)
//                dest.writeString(this.pres)
//                dest.writeString(this.sr)
//                dest.writeString(this.ss)
//                dest.writeString(this.tmp_max)
//                dest.writeString(this.tmp_min)
//                dest.writeString(this.uv_index)
//                dest.writeString(this.vis)
//                dest.writeString(this.wind_deg)
//                dest.writeString(this.wind_dir)
//                dest.writeString(this.wind_sc)
//                dest.writeString(this.wind_spd)
//            }
//
//            constructor() {}
//
//            protected constructor(`in`: Parcel) {
//                this.cond_code_d = `in`.readString()
//                this.cond_code_n = `in`.readString()
//                this.cond_txt_d = `in`.readString()
//                this.cond_txt_n = `in`.readString()
//                this.date = `in`.readString()
//                this.hum = `in`.readString()
//                this.mr = `in`.readString()
//                this.ms = `in`.readString()
//                this.pcpn = `in`.readString()
//                this.pop = `in`.readString()
//                this.pres = `in`.readString()
//                this.sr = `in`.readString()
//                this.ss = `in`.readString()
//                this.tmp_max = `in`.readString()
//                this.tmp_min = `in`.readString()
//                this.uv_index = `in`.readString()
//                this.vis = `in`.readString()
//                this.wind_deg = `in`.readString()
//                this.wind_dir = `in`.readString()
//                this.wind_sc = `in`.readString()
//                this.wind_spd = `in`.readString()
//            }
//
//            companion object {
//
//                val CREATOR: Parcelable.Creator<DailyForecastBean> = object : Parcelable.Creator<DailyForecastBean> {
//                    override fun createFromParcel(source: Parcel): DailyForecastBean {
//                        return DailyForecastBean(source)
//                    }
//
//                    override fun newArray(size: Int): Array<DailyForecastBean> {
//                        return arrayOfNulls(size)
//                    }
//                }
//            }
        }
        @Parcelize
        class HourlyBean : Parcelable {
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

            lateinit var cloud: String
            lateinit var cond_code: String
            lateinit var cond_txt: String
            lateinit var dew: String
            lateinit var hum: String
            lateinit var pop: String
            lateinit var pres: String
            lateinit var time: String
            lateinit var tmp: String
            lateinit var wind_deg: String
            lateinit var wind_dir: String
            lateinit var wind_sc: String
            lateinit var wind_spd: String

//            override fun describeContents(): Int {
//                return 0
//            }
//
//            override fun writeToParcel(dest: Parcel, flags: Int) {
//                dest.writeString(this.cloud)
//                dest.writeString(this.cond_code)
//                dest.writeString(this.cond_txt)
//                dest.writeString(this.dew)
//                dest.writeString(this.hum)
//                dest.writeString(this.pop)
//                dest.writeString(this.pres)
//                dest.writeString(this.time)
//                dest.writeString(this.tmp)
//                dest.writeString(this.wind_deg)
//                dest.writeString(this.wind_dir)
//                dest.writeString(this.wind_sc)
//                dest.writeString(this.wind_spd)
//            }
//
//            constructor() {}
//
//            protected constructor(`in`: Parcel) {
//                this.cloud = `in`.readString()
//                this.cond_code = `in`.readString()
//                this.cond_txt = `in`.readString()
//                this.dew = `in`.readString()
//                this.hum = `in`.readString()
//                this.pop = `in`.readString()
//                this.pres = `in`.readString()
//                this.time = `in`.readString()
//                this.tmp = `in`.readString()
//                this.wind_deg = `in`.readString()
//                this.wind_dir = `in`.readString()
//                this.wind_sc = `in`.readString()
//                this.wind_spd = `in`.readString()
//            }
//
//            companion object {
//
//                val CREATOR: Parcelable.Creator<HourlyBean> = object : Parcelable.Creator<HourlyBean> {
//                    override fun createFromParcel(source: Parcel): HourlyBean {
//                        return HourlyBean(source)
//                    }
//
//                    override fun newArray(size: Int): Array<HourlyBean> {
//                        return HourlyBean(size)
//                    }
//                }
//            }
        }
            @Parcelize
        class LifestyleBean : Parcelable {
            /**
             * type : comf
             * brf : 较不舒适
             * txt : 白天天气晴好，但仍会使您感觉偏冷，不很舒适，请注意适时添加衣物，以防感冒。
             */

            lateinit var type: String
            lateinit var brf: String
            lateinit var txt: String

//            override fun describeContents(): Int {
//                return 0
//            }
//
//            override fun writeToParcel(dest: Parcel, flags: Int) {
//                dest.writeString(this.type)
//                dest.writeString(this.brf)
//                dest.writeString(this.txt)
//            }
//
//            constructor() {}
//
//            protected constructor(`in`: Parcel) {
//                this.type = `in`.readString()
//                this.brf = `in`.readString()
//                this.txt = `in`.readString()
//            }
//
//            companion object {
//
//                val CREATOR: Parcelable.Creator<LifestyleBean> = object : Parcelable.Creator<LifestyleBean> {
//                    override fun createFromParcel(source: Parcel): LifestyleBean {
//                        return LifestyleBean(source)
//                    }
//
//                    override fun newArray(size: Int): Array<LifestyleBean> {
//                        return arrayOfNulls(size)
//                    }
//                }
//            }
        }
//
//        override fun describeContents(): Int {
//            return 0
//        }
//
//        override fun writeToParcel(dest: Parcel, flags: Int) {
//            dest.writeParcelable(this.basic, flags)
//            dest.writeParcelable(this.update, flags)
//            dest.writeString(this.status)
//            dest.writeParcelable(this.now, flags)
//            dest.writeTypedList(this.daily_forecast)
//            dest.writeTypedList(this.hourly)
//            dest.writeTypedList(this.lifestyle)
//        }
//
//        constructor() {}
//
//        protected constructor(`in`: Parcel) {
//            this.basic = `in`.readParcelable(BasicBean::class.java.classLoader)
//            this.update = `in`.readParcelable(UpdateBean::class.java.classLoader)
//            this.status = `in`.readString()
//            this.now = `in`.readParcelable(NowBean::class.java.classLoader)
//            this.daily_forecast = `in`.createTypedArrayList(DailyForecastBean.CREATOR)
//            this.hourly = `in`.createTypedArrayList(HourlyBean.CREATOR)
//            this.lifestyle = `in`.createTypedArrayList(LifestyleBean.CREATOR)
//        }
//
//        companion object {
//
//            val CREATOR: Parcelable.Creator<HeWeather6Bean> = object : Parcelable.Creator<HeWeather6Bean> {
//                override fun createFromParcel(source: Parcel): HeWeather6Bean {
//                    return HeWeather6Bean(source)
//                }
//
//                override fun newArray(size: Int): Array<HeWeather6Bean> {
//                    return arrayOfNulls(size)
//                }
//            }
//        }
//    }
//
//    override fun describeContents(): Int {
//        return 0
//    }
//
//    override fun writeToParcel(dest: Parcel, flags: Int) {
//        dest.writeTypedList(this.heWeather6)
//    }
//
//    constructor() {}
//
//    protected constructor(`in`: Parcel) {
//        this.heWeather6 = `in`.createTypedArrayList(HeWeather6Bean.CREATOR)
//    }
//
//    companion object {
//
//        val CREATOR: Parcelable.Creator<WeatherForecastEntity> = object : Parcelable.Creator<WeatherForecastEntity> {
//            override fun createFromParcel(source: Parcel): WeatherForecastEntity {
//                return WeatherForecastEntity(source)
//            }
//
//            override fun newArray(size: Int): Array<WeatherForecastEntity> {
//                return arrayOfNulls(size)
//            }
        }
    }
}
