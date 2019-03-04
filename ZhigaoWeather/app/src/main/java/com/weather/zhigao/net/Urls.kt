package com.weather.zhigao.net

import android.database.Observable

object Urls {

    //    https://free-api.heweather.net/s6/weather?location=%E6%B5%B7%E6%B7%80&key=227849effc2b4e83b4cf1b0caf743cf9


    //常规天气数据集合：本接口包含了3-7天天气预报、实况天气、逐小时天气预报以及生活指数，有对应权限的用户可通过访问此接口一次性获取某一地区的上述所有天气数据
    val url_weather = "https://free-api.heweather.net/s6/weather"
    // 3-10天天气预报：最长10天天气预报数据，天气预报包含的数据：日出日落、月升月落、最高最低温度、天气白天和夜间状况、风力、风速、风向、相对湿度、大气压强、降水量、降水概率、露点温度、紫外线强度、能见度等数据
    //    public static final String url_weather_forecast = "https://free-api.heweather.net/s6/weather/forecast";

    // 生活指数：生活指数和生活指数预报包括：穿衣、洗车、感冒、紫外线、运动、舒适度、旅游、空气污染扩散条件
    val url_weather_lifestyle = "https://free-api.heweather.net/s6/weather/lifestyle"

    // 空气质量实况：环保部1500+个监测站点实时空气质量（AQI）数据，以及目前唯一可提供的全国3240个城市的实时空气质量（AQI）数据
    val url_air_now = "https://free-api.heweather.net/s6/air/now"
    //日出日落：可获得全球3-7天日出日落时间
    val url_sunrise_sunset = "https://free-api.heweather.net/s6/solar/sunrise-sunset"

    //逐小时天气预报：具体包含的数据：温度、天气状况、风力、风速、风向、相对湿度、大气压强、降水概率等。
    val url_weather_hourly = "https://free-api.heweather.net/s6/weather/hourly"

    //热门城市列表：根据用户访问热度，我们可提供中国和海外热门城市列表，用于开发者可以为用户提供热门城市查询天气的功能
    val url_hot_city = "https://search.heweather.net/top"
    //城市搜索：支持模糊搜索的全球城市搜索服务
    val url_find_city = "https://search.heweather.net/find"
}
