package com.weather.zhigao.utils;

import android.text.TextUtils;

import com.weather.zhigao.R;
import com.weather.zhigao.application.App;

public class Weather2IconUtil {
    public static int getDayBackgroundId(final String desc) {
        if (desc.contains(App.mContext.getString(R.string.sunny)))
            return R.mipmap.day_qing_yun;
        if (desc.contains(App.mContext.getString(R.string.cloud)))
            return R.mipmap.day_yun;
        if (desc.contains(App.mContext.getString(R.string.overcast)))
            return R.mipmap.day_yintian;
        if (desc.contains(App.mContext.getString(R.string.snow)))
            return R.mipmap.day_yu_xue;
        if (desc.contains(App.mContext.getString(R.string.rain)))
            return R.mipmap.day_rain;
        if (desc.contains(App.mContext.getString(R.string.haze)))
            return R.mipmap.wumai;
        if (desc.contains(App.mContext.getString(R.string.foggy)))
            return R.mipmap.day_fog;
        return R.mipmap.day_qing_yun;
    }

    public static int getNightBackgroundId(final String desc) {
        if (desc.contains(App.mContext.getString(R.string.sunny)))
            return R.mipmap.background_sunny_night;
        if (desc.contains(App.mContext.getString(R.string.cloud)))
            return R.mipmap.night_yun;
        if (desc.contains(App.mContext.getString(R.string.overcast)))
            return R.mipmap.night_yintian;
        if (desc.contains(App.mContext.getString(R.string.snow)))
            return R.mipmap.night_yu_xue;
        if (desc.contains(App.mContext.getString(R.string.rain)))
            return R.mipmap.night_yu;

        return R.mipmap.background_sunny_night;
    }

    public static int getWeatherIconId(final String desc) {
        if (!TextUtils.isEmpty(desc)) {
            if (desc.equalsIgnoreCase(App.mContext.getString(R.string.sunny))) {
                return R.drawable.iclockweather_w1;
            } else if (desc.equalsIgnoreCase(App.mContext.getString(R.string.partly_cloudy))
                    || desc.equalsIgnoreCase(App.mContext.getString(R.string.cloudy))
                    || desc.equalsIgnoreCase(App.mContext.getString(R.string.few_cloud))) {
                return R.drawable.iclockweather_w2;
            } else if (desc.equalsIgnoreCase(App.mContext.getString(R.string.overcast))) {
                return R.drawable.iclockweather_w3;
            } else if (desc.equalsIgnoreCase(App.mContext.getString(R.string.shower_rain))) {
                return R.drawable.iclockweather_w8;
            } else if (desc.equalsIgnoreCase(App.mContext.getString(R.string.heavy_shower_rain))) {
                return R.drawable.iclockweather_w8;
            } else if (desc.equalsIgnoreCase(App.mContext.getString(R.string.thunder_shower))
                    || desc.equalsIgnoreCase(App.mContext.getString(R.string.heavy_thunderstorm))) {
                return R.drawable.iclockweather_w9;
            } else if (desc.equalsIgnoreCase(App.mContext.getString(R.string.hail))) {
                return R.drawable.iclockweather_w18;
            } else if (desc.equalsIgnoreCase(App.mContext.getString(R.string.light_rain))
                    || desc.equalsIgnoreCase(App.mContext.getString(R.string.drizzle_rain))
                    || desc.equalsIgnoreCase(App.mContext.getString(R.string.drizzle_rain_1))) {
                return R.drawable.iclockweather_w4;
            } else if (desc.equalsIgnoreCase(App.mContext.getString(R.string.moderate_rain))) {
                return R.drawable.iclockweather_w5;
            } else if (desc.equalsIgnoreCase(App.mContext.getString(R.string.heavy_rain))
                    || desc.equalsIgnoreCase(App.mContext.getString(R.string.storm))) {
                return R.drawable.iclockweather_w6;
            } else if (desc.equalsIgnoreCase(App.mContext.getString(R.string.extreme_rain))
                    || desc.equalsIgnoreCase(App.mContext.getString(R.string.heavy_storm))
                    || desc.equalsIgnoreCase(App.mContext.getString(R.string.severe_storm))) {
                return R.drawable.iclockweather_w7;
            } else if (desc.equalsIgnoreCase(App.mContext.getString(R.string.freezing_rain))) {
                return R.drawable.iclockweather_w15;
            } else if (desc.equalsIgnoreCase(App.mContext.getString(R.string.light_snow))
                    || desc.equalsIgnoreCase(App.mContext.getString(R.string.snow_flurry))) {
                return R.drawable.iclockweather_w11;
            } else if (desc.equalsIgnoreCase(App.mContext.getString(R.string.moderate_snow))) {
                return R.drawable.iclockweather_w12;
            } else if (desc.equalsIgnoreCase(App.mContext.getString(R.string.heavy_snow))) {
                return R.drawable.iclockweather_w13;
            } else if (desc.equalsIgnoreCase(App.mContext.getString(R.string.snow_storm))) {
                return R.drawable.iclockweather_w14;
            } else if (desc.equalsIgnoreCase(App.mContext.getString(R.string.sleet))
                    || desc.equalsIgnoreCase(App.mContext.getString(R.string.rain_snow))
                    || desc.equalsIgnoreCase(App.mContext.getString(R.string.shower_snow))) {
                return R.drawable.iclockweather_w10;
            } else if (desc.equalsIgnoreCase(App.mContext.getString(R.string.mist))
                    || desc.equalsIgnoreCase(App.mContext.getString(R.string.foggy))) {
                return R.drawable.iclockweather_w16;
            } else if (desc.equalsIgnoreCase(App.mContext.getString(R.string.haze))
                    || desc.equalsIgnoreCase(App.mContext.getString(R.string.sand))
                    || desc.equalsIgnoreCase(App.mContext.getString(R.string.dust))
                    || desc.equalsIgnoreCase(App.mContext.getString(R.string.volcanic_ash))
                    || desc.equalsIgnoreCase(App.mContext.getString(R.string.dust_storm))
                    || desc.equalsIgnoreCase(App.mContext.getString(R.string.sand_storm))) {
                return R.drawable.iclockweather_w17;
            }
        }

        return R.drawable.iclockweather_w2;
    }
}
