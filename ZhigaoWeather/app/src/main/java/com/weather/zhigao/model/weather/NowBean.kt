package com.weather.zhigao.model.weather

import android.os.Parcel
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class NowBean (
        var cloud: String,
        var cond_code: String,
        var cond_txt: String,
        var fl: String,
        var hum: String,
        var pcpn: String,
        var pres: String,
        var tmp: String,
        var vis: String,
        var wind_deg: String,
        var wind_dir: String,
        var wind_sc: String,
        var wind_spd: String): Parcelable {
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

//    var cloud: String
//    var cond_code: String
//    var cond_txt: String
//    var fl: String
//    var hum: String
//    var pcpn: String
//    var pres: String
//    var tmp: String
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
//        dest.writeString(this.cloud)
//        dest.writeString(this.cond_code)
//        dest.writeString(this.cond_txt)
//        dest.writeString(this.fl)
//        dest.writeString(this.hum)
//        dest.writeString(this.pcpn)
//        dest.writeString(this.pres)
//        dest.writeString(this.tmp)
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
//        this.cloud = `in`.readString()
//        this.cond_code = `in`.readString()
//        this.cond_txt = `in`.readString()
//        this.fl = `in`.readString()
//        this.hum = `in`.readString()
//        this.pcpn = `in`.readString()
//        this.pres = `in`.readString()
//        this.tmp = `in`.readString()
//        this.vis = `in`.readString()
//        this.wind_deg = `in`.readString()
//        this.wind_dir = `in`.readString()
//        this.wind_sc = `in`.readString()
//        this.wind_spd = `in`.readString()
//    }
//
//    companion object {
//
//        val CREATOR: Parcelable.Creator<NowBean> = object : Parcelable.Creator<NowBean> {
//            override fun createFromParcel(source: Parcel): NowBean {
//                return NowBean(source)
//            }
//
//            override fun newArray(size: Int): Array<NowBean> {
//                return arrayOfNulls(size)
//            }
//        }
//    }
}
