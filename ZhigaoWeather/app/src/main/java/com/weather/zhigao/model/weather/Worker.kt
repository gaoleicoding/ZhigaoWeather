package com.weather.zhigao.model.weather

import android.os.Parcel
import android.os.Parcelable

data class Worker(
  var id: Int,
  var name: String,
  var tasks: MutableList<Int>
) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readString(),
            TODO("tasks")) {
    }
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
    }
    override fun describeContents(): Int {
        return 0
    }
    companion object CREATOR : Parcelable.Creator<Worker> {
        override fun createFromParcel(parcel: Parcel): Worker {
            return Worker(parcel)
        }
        override fun newArray(size: Int): Array<Worker?> {
            return arrayOfNulls(size)
        }
    }
}