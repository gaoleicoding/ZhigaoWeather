package com.weather.zhigao.utils

import android.text.TextUtils

import com.weather.zhigao.R
import com.weather.zhigao.application.App

object Weather2IconUtil {
    fun getDayBackgroundId(desc: String): Int {
        if (desc.contains(App.mContext.getString(R.string.sunny)))
            return R.mipmap.day_qing_yun
        if (desc.contains(App.mContext.getString(R.string.cloud)))
            return R.mipmap.day_yun
        if (desc.contains(App.mContext.getString(R.string.overcast)))
            return R.mipmap.day_yintian
        if (desc.contains(App.mContext.getString(R.string.snow)))
            return R.mipmap.day_yu_xue
        if (desc.contains(App.mContext.getString(R.string.rain)))
            return R.mipmap.day_rain
        if (desc.contains(App.mContext.getString(R.string.haze)))
            return R.mipmap.wumai
        return if (desc.contains(App.mContext.getString(R.string.foggy))) R.mipmap.day_fog else R.mipmap.day_qing_yun
    }

    fun getNightBackgroundId(desc: String): Int {
        if (desc.contains(App.mContext.getString(R.string.sunny)))
            return R.mipmap.background_sunny_night
        if (desc.contains(App.mContext.getString(R.string.cloud)))
            return R.mipmap.night_yun
        if (desc.contains(App.mContext.getString(R.string.overcast)))
            return R.mipmap.night_yintian
        if (desc.contains(App.mContext.getString(R.string.snow)))
            return R.mipmap.night_yu_xue
        return if (desc.contains(App.mContext.getString(R.string.rain))) R.mipmap.night_yu else R.mipmap.background_sunny_night

    }

    fun getWeatherIconId(desc: String): Int {
        if (!TextUtils.isEmpty(desc)) {
            if (desc.equals(App.mContext.getString(R.string.sunny), ignoreCase = true)) {
                return R.drawable.iclockweather_w1
            } else if (desc.equals(App.mContext.getString(R.string.partly_cloudy), ignoreCase = true)
                    || desc.equals(App.mContext.getString(R.string.cloudy), ignoreCase = true)
                    || desc.equals(App.mContext.getString(R.string.few_cloud), ignoreCase = true)) {
                return R.drawable.iclockweather_w2
            } else if (desc.equals(App.mContext.getString(R.string.overcast), ignoreCase = true)) {
                return R.drawable.iclockweather_w3
            } else if (desc.equals(App.mContext.getString(R.string.shower_rain), ignoreCase = true)) {
                return R.drawable.iclockweather_w8
            } else if (desc.equals(App.mContext.getString(R.string.heavy_shower_rain), ignoreCase = true)) {
                return R.drawable.iclockweather_w8
            } else if (desc.equals(App.mContext.getString(R.string.thunder_shower), ignoreCase = true) || desc.equals(App.mContext.getString(R.string.heavy_thunderstorm), ignoreCase = true)) {
                return R.drawable.iclockweather_w9
            } else if (desc.equals(App.mContext.getString(R.string.hail), ignoreCase = true)) {
                return R.drawable.iclockweather_w18
            } else if (desc.equals(App.mContext.getString(R.string.light_rain), ignoreCase = true)
                    || desc.equals(App.mContext.getString(R.string.drizzle_rain), ignoreCase = true)
                    || desc.equals(App.mContext.getString(R.string.drizzle_rain_1), ignoreCase = true)) {
                return R.drawable.iclockweather_w4
            } else if (desc.equals(App.mContext.getString(R.string.moderate_rain), ignoreCase = true)) {
                return R.drawable.iclockweather_w5
            } else if (desc.equals(App.mContext.getString(R.string.heavy_rain), ignoreCase = true) || desc.equals(App.mContext.getString(R.string.storm), ignoreCase = true)) {
                return R.drawable.iclockweather_w6
            } else if (desc.equals(App.mContext.getString(R.string.extreme_rain), ignoreCase = true)
                    || desc.equals(App.mContext.getString(R.string.heavy_storm), ignoreCase = true)
                    || desc.equals(App.mContext.getString(R.string.severe_storm), ignoreCase = true)) {
                return R.drawable.iclockweather_w7
            } else if (desc.equals(App.mContext.getString(R.string.freezing_rain), ignoreCase = true)) {
                return R.drawable.iclockweather_w15
            } else if (desc.equals(App.mContext.getString(R.string.light_snow), ignoreCase = true) || desc.equals(App.mContext.getString(R.string.snow_flurry), ignoreCase = true)) {
                return R.drawable.iclockweather_w11
            } else if (desc.equals(App.mContext.getString(R.string.moderate_snow), ignoreCase = true)) {
                return R.drawable.iclockweather_w12
            } else if (desc.equals(App.mContext.getString(R.string.heavy_snow), ignoreCase = true)) {
                return R.drawable.iclockweather_w13
            } else if (desc.equals(App.mContext.getString(R.string.snow_storm), ignoreCase = true)) {
                return R.drawable.iclockweather_w14
            } else if (desc.equals(App.mContext.getString(R.string.sleet), ignoreCase = true)
                    || desc.equals(App.mContext.getString(R.string.rain_snow), ignoreCase = true)
                    || desc.equals(App.mContext.getString(R.string.shower_snow), ignoreCase = true)) {
                return R.drawable.iclockweather_w10
            } else if (desc.equals(App.mContext.getString(R.string.mist), ignoreCase = true) || desc.equals(App.mContext.getString(R.string.foggy), ignoreCase = true)) {
                return R.drawable.iclockweather_w16
            } else if (desc.equals(App.mContext.getString(R.string.haze), ignoreCase = true)
                    || desc.equals(App.mContext.getString(R.string.sand), ignoreCase = true)
                    || desc.equals(App.mContext.getString(R.string.dust), ignoreCase = true)
                    || desc.equals(App.mContext.getString(R.string.volcanic_ash), ignoreCase = true)
                    || desc.equals(App.mContext.getString(R.string.dust_storm), ignoreCase = true)
                    || desc.equals(App.mContext.getString(R.string.sand_storm), ignoreCase = true)) {
                return R.drawable.iclockweather_w17
            }
        }

        return R.drawable.iclockweather_w2
    }
}
