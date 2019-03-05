package com.weather.zhigao.model.weather

import android.os.Parcel
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class UpdateBean( var loc: String,
                  var utc: String) : Parcelable {
        /**
         * loc : 2019-02-16 20:56
         * utc : 2019-02-16 12:56
         */

//        var loc: String
//        var utc: String
//
//        override fun describeContents(): Int {
//            return 0
//        }
//
//        override fun writeToParcel(dest: Parcel, flags: Int) {
//            dest.writeString(this.loc)
//            dest.writeString(this.utc)
//        }
//
//        constructor() {}
//
//        protected constructor(`in`: Parcel) {
//            this.loc = `in`.readString()
//            this.utc = `in`.readString()
//        }
//
//        companion object {
//
//            val CREATOR: Parcelable.Creator<UpdateBean> = object : Parcelable.Creator<UpdateBean> {
//                override fun createFromParcel(source: Parcel): UpdateBean {
//                    return UpdateBean(source)
//                }
//
//                override fun newArray(size: Int): Array<UpdateBean> {
//                    return arrayOfNulls(size)
//                }
//            }
//        }
    }
