package com.weather.zhigao.model

import android.os.Parcel
import android.os.Parcelable
import com.weather.zhigao.model.weather.*

data class WeatherForecastEntity(

        var heWeather6: List<HeWeather6Bean>
)  : Parcelable {

    override fun writeToParcel(parcel: Parcel, flags: Int) {
    }
    override fun describeContents(): Int {
        return 0
    }
    companion object CREATOR : Parcelable.Creator<WeatherForecastEntity> {
        override fun createFromParcel(parcel: Parcel): WeatherForecastEntity {
            return WeatherForecastEntity(parcel)
        }
        override fun newArray(size: Int): Array<WeatherForecastEntity?> {
            return arrayOfNulls(size)
        }
    }





}
