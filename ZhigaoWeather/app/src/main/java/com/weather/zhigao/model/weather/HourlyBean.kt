package com.weather.zhigao.model.weather

import android.os.Parcel
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class HourlyBean (  var cloud: String,
                    var cond_code: String,
                    var cond_txt: String,
                    var dew: String,
                    var hum: String,
                    var pop: String,
                    var pres: String,
                    var time: String,
                    var tmp: String,
                    var wind_deg: String,
                    var wind_dir: String,
                    var wind_sc: String,
                    var wind_spd: String): Parcelable {
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

//    var cloud: String
//    var cond_code: String
//    var cond_txt: String
//    var dew: String
//    var hum: String
//    var pop: String
//    var pres: String
//    var time: String
//    var tmp: String
//    var wind_deg: String
//    var wind_dir: String
//    var wind_sc: String
//    var wind_spd: String
//
//    override fun describeContents(): Int {
//        return 0
//    }
//
//    override fun writeToParcel(dest: Parcel, flags: Int) {
//        dest.writeString(this.cloud)
//        dest.writeString(this.cond_code)
//        dest.writeString(this.cond_txt)
//        dest.writeString(this.dew)
//        dest.writeString(this.hum)
//        dest.writeString(this.pop)
//        dest.writeString(this.pres)
//        dest.writeString(this.time)
//        dest.writeString(this.tmp)
//        dest.writeString(this.wind_deg)
//        dest.writeString(this.wind_dir)
//        dest.writeString(this.wind_sc)
//        dest.writeString(this.wind_spd)
//    }
//
//    constructor() {}
//
//    protected constructor(`in`: Parcel) {
//        this.cloud = `in`.readString()
//        this.cond_code = `in`.readString()
//        this.cond_txt = `in`.readString()
//        this.dew = `in`.readString()
//        this.hum = `in`.readString()
//        this.pop = `in`.readString()
//        this.pres = `in`.readString()
//        this.time = `in`.readString()
//        this.tmp = `in`.readString()
//        this.wind_deg = `in`.readString()
//        this.wind_dir = `in`.readString()
//        this.wind_sc = `in`.readString()
//        this.wind_spd = `in`.readString()
//    }
//
//    companion object {
//
//        val CREATOR: Parcelable.Creator<HourlyBean> = object : Parcelable.Creator<HourlyBean> {
//            override fun createFromParcel(source: Parcel): HourlyBean {
//                return HourlyBean(source)
//            }
//
//            override fun newArray(size: Int): Array<HourlyBean> {
//                return arrayOfNulls(size)
//            }
//        }
//    }
}