package com.weather.zhigao.net;

import android.database.Observable;

import java.util.Map;

public class Urls {
    //    public interface WeatherInfoService {
//        /*https://cdn.heweather.com/china-city-list.txt*/
//        @Headers("Accept-Encoding: application/json")
//        @GET()
//        Observable<String> getCityInfos(@Url String url);
//
//        /*https://api.heweather.com/x3/condition?search=allcond&key=035591c2b70c45fa9b4dd2bcabce13fe*/
//        @Headers("Accept-Encoding: application/json")
//        @GET("x3/condition")
//        Observable<ConditionsResponse> getConditionInfos(@QueryMap Map<String, String> params);
//
//        /*https://free-api.heweather.com/v5/weather?city=CN101010300&key=035591c2b70c45fa9b4dd2bcabce13fe*/
//        @Headers("Accept-Encoding: application/json")
//        @GET("v5/weather")
//        Observable<CityWeatherResponse> getCityWeatherInfo(@QueryMap Map<String, String> params);
//    }
    //常规天气数据集合：本接口包含了3-7天天气预报、实况天气、逐小时天气预报以及生活指数，有对应权限的用户可通过访问此接口一次性获取某一地区的上述所有天气数据
    public static final String url_weather = "https://free-api.heweather.net/s6/weather";
    // 3-10天天气预报：最长10天天气预报数据，天气预报包含的数据：日出日落、月升月落、最高最低温度、天气白天和夜间状况、风力、风速、风向、相对湿度、大气压强、降水量、降水概率、露点温度、紫外线强度、能见度等数据
//    public static final String url_weather_forecast = "https://free-api.heweather.net/s6/weather/forecast";

    // 生活指数：生活指数和生活指数预报包括：穿衣、洗车、感冒、紫外线、运动、舒适度、旅游、空气污染扩散条件
    public static final String url_weather_lifestyle = "https://free-api.heweather.net/s6/weather/lifestyle";

    // 空气质量实况：环保部1500+个监测站点实时空气质量（AQI）数据，以及目前唯一可提供的全国3240个城市的实时空气质量（AQI）数据
    public static final String url_air_now = "https://free-api.heweather.net/s6/air/now";
    //日出日落：可获得全球3-7天日出日落时间
    public static final String url_sunrise_sunset = "https://free-api.heweather.net/s6/solar/sunrise-sunset";

    //逐小时天气预报：具体包含的数据：温度、天气状况、风力、风速、风向、相对湿度、大气压强、降水概率等。
    public static final String url_weather_hourly = "https://free-api.heweather.net/s6/weather/hourly";
}
