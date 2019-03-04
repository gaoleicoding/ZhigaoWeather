package com.weather.zhigao.utils

import android.annotation.SuppressLint

import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date

object TimeUtil {
    var WEEK = arrayOf("星期天", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六")
    val WEEKDAYS = 7
    var pattern = "yyyy年MM月dd日"

    //设置日期格式
    val currentDate: String
        get() {
            val sdf = SimpleDateFormat("yyyy-MM-dd")
            val now = Date()
            return sdf.format(now)
        }

    //把日期转为字符串
    fun ConverToString(date: Date): String {
        val df = SimpleDateFormat("yyyy-MM-dd")

        return df.format(date)
    }

    //把字符串转为日期
    fun ConverToDate(strDate: String): Date? {
        val df: DateFormat
        try {
            df = SimpleDateFormat("yyyy-MM-dd HH:mm")
            return df.parse(strDate)
        } catch (e: Exception) {
        }

        return null

    }

    //date类型的时间转为long
    fun dateToLong(date: Date): Long {
        return date.time
    }

    fun getStringToDate(dateString: String): String {
        var date = ""
        try {
            val d1 = SimpleDateFormat("yyyy-MM-dd").parse(dateString)
            val sdf1 = SimpleDateFormat("MM")//月份的
            val sdf2 = SimpleDateFormat("dd")//日的
            val str2 = sdf1.format(d1)//取出特定日期d1的月份
            val str3 = sdf2.format(d1)//取出特定日期d1的日
            date = str2 + "月" + str3 + "日"

        } catch (e: ParseException) {
            e.printStackTrace()
        }

        return date
    }

    fun dateToWeek(strDate: String): String? {
        var dayIndex = 0
        try {
            val df = SimpleDateFormat("yyyy-MM-dd")
            val date = df.parse(strDate)
            val calendar = Calendar.getInstance()
            calendar.time = date
            dayIndex = calendar.get(Calendar.DAY_OF_WEEK)
            if (dayIndex < 1 || dayIndex > WEEKDAYS) {
                return null
            }
        } catch (e: Exception) {
        }


        return WEEK[dayIndex - 1]
    }

}