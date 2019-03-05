package com.weather.zhigao.model.weather

import android.os.Parcel
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class DailyForecastBean(var cond_code_d: String,
                        var cond_code_n: String,
                        var cond_txt_d: String,
                        var cond_txt_n: String,
                        var date: String,
                        var hum: String,
                        var mr: String,
                        var ms: String,
                        var pcpn: String,
                        var pop: String,
                        var pres: String,
                        var sr: String,
                        var ss: String,
                        var tmp_max: String,
                        var tmp_min: String,
                        var uv_index: String,
                        var vis: String,
                        var wind_deg: String,
                        var wind_dir: String,
                        var wind_sc: String,
                        var wind_spd: String) : Parcelable {
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

//    var cond_code_d: String
//    var cond_code_n: String
//    var cond_txt_d: String
//    var cond_txt_n: String
//    var date: String
//    var hum: String
//    var mr: String
//    var ms: String
//    var pcpn: String
//    var pop: String
//    var pres: String
//    var sr: String
//    var ss: String
//    var tmp_max: String
//    var tmp_min: String
//    var uv_index: String
//    var vis: String
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
//        dest.writeString(this.cond_code_d)
//        dest.writeString(this.cond_code_n)
//        dest.writeString(this.cond_txt_d)
//        dest.writeString(this.cond_txt_n)
//        dest.writeString(this.date)
//        dest.writeString(this.hum)
//        dest.writeString(this.mr)
//        dest.writeString(this.ms)
//        dest.writeString(this.pcpn)
//        dest.writeString(this.pop)
//        dest.writeString(this.pres)
//        dest.writeString(this.sr)
//        dest.writeString(this.ss)
//        dest.writeString(this.tmp_max)
//        dest.writeString(this.tmp_min)
//        dest.writeString(this.uv_index)
//        dest.writeString(this.vis)
//        dest.writeString(this.wind_deg)
//        dest.writeString(this.wind_dir)
//        dest.writeString(this.wind_sc)
//        dest.writeString(this.wind_spd)
//    }
//
//    constructor() {}
//
//    protected constructor(`in`: Parcel) {
//        this.cond_code_d = `in`.readString()
//        this.cond_code_n = `in`.readString()
//        this.cond_txt_d = `in`.readString()
//        this.cond_txt_n = `in`.readString()
//        this.date = `in`.readString()
//        this.hum = `in`.readString()
//        this.mr = `in`.readString()
//        this.ms = `in`.readString()
//        this.pcpn = `in`.readString()
//        this.pop = `in`.readString()
//        this.pres = `in`.readString()
//        this.sr = `in`.readString()
//        this.ss = `in`.readString()
//        this.tmp_max = `in`.readString()
//        this.tmp_min = `in`.readString()
//        this.uv_index = `in`.readString()
//        this.vis = `in`.readString()
//        this.wind_deg = `in`.readString()
//        this.wind_dir = `in`.readString()
//        this.wind_sc = `in`.readString()
//        this.wind_spd = `in`.readString()
//    }
//
//    companion object {
//
//        val CREATOR: Parcelable.Creator<DailyForecastBean> = object : Parcelable.Creator<DailyForecastBean> {
//            override fun createFromParcel(source: Parcel): DailyForecastBean {
//                return DailyForecastBean(source)
//            }
//
//            override fun newArray(size: Int): Array<DailyForecastBean> {
//                return arrayOfNulls(size)
//            }
//        }
//    }
}