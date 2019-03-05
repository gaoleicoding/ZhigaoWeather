package com.weather.zhigao.model.weather

import android.os.Parcel
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class BasicBean(var cid: String,var location: String,var parent_city: String,var admin_area: String, var cnty: String,var lat: String, var lon: String,var tz: String) : Parcelable {
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

//    var cid: String
//    var location: String
//    var parent_city: String
//    var admin_area: String
//    var cnty: String
//    var lat: String
//    var lon: String
//    var tz: String
//
//    override fun describeContents(): Int {
//        return 0
//    }
//
//    override fun writeToParcel(dest: Parcel, flags: Int) {
//        dest.writeString(this.cid)
//        dest.writeString(this.location)
//        dest.writeString(this.parent_city)
//        dest.writeString(this.admin_area)
//        dest.writeString(this.cnty)
//        dest.writeString(this.lat)
//        dest.writeString(this.lon)
//        dest.writeString(this.tz)
//    }
//
//    constructor() {}
//
//    protected constructor(`in`: Parcel) {
//        this.cid = `in`.readString()
//        this.location = `in`.readString()
//        this.parent_city = `in`.readString()
//        this.admin_area = `in`.readString()
//        this.cnty = `in`.readString()
//        this.lat = `in`.readString()
//        this.lon = `in`.readString()
//        this.tz = `in`.readString()
//    }
//
//    companion object {
//
//        val CREATOR: Parcelable.Creator<BasicBean> = object : Parcelable.Creator<BasicBean> {
//            override fun createFromParcel(source: Parcel): BasicBean {
//                return BasicBean(source)
//            }
//
//            override fun newArray(size: Int): Array<BasicBean> {
//                return arrayOfNulls(size)
//            }
//        }
//    }
}