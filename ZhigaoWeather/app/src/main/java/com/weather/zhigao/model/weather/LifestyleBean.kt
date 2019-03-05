package com.weather.zhigao.model.weather

import android.os.Parcel
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class LifestyleBean(  var type: String,
                      var brf: String,
                      var txt: String) : Parcelable {
    /**
     * type : comf
     * brf : 较不舒适
     * txt : 白天天气晴好，但仍会使您感觉偏冷，不很舒适，请注意适时添加衣物，以防感冒。
     */

//    var type: String
//    var brf: String
//    var txt: String
//
//    override fun describeContents(): Int {
//        return 0
//    }
//
//    override fun writeToParcel(dest: Parcel, flags: Int) {
//        dest.writeString(this.type)
//        dest.writeString(this.brf)
//        dest.writeString(this.txt)
//    }
//
//    constructor() {}
//
//    protected constructor(`in`: Parcel) {
//        this.type = `in`.readString()
//        this.brf = `in`.readString()
//        this.txt = `in`.readString()
//    }
//
//    companion object {
//
//        val CREATOR: Parcelable.Creator<LifestyleBean> = object : Parcelable.Creator<LifestyleBean> {
//            override fun createFromParcel(source: Parcel): LifestyleBean {
//                return LifestyleBean(source)
//            }
//
//            override fun newArray(size: Int): Array<LifestyleBean> {
//                return arrayOfNulls(size)
//            }
//        }
//    }
}
