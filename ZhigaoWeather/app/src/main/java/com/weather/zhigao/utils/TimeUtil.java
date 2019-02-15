package com.weather.zhigao.utils;

import android.annotation.SuppressLint;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeUtil {
    public static String[] WEEK = {"星期天", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
    public static final int WEEKDAYS = 7;

    //把日期转为字符串
    public static String ConverToString(Date date) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        return df.format(date);
    }

    //把字符串转为日期
    public static Date ConverToDate(String strDate) throws Exception {
        @SuppressLint("SimpleDateFormat") DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.parse(strDate);
    }

    public static String dateToWeek(String strDate) {
        int dayIndex = 0;
        try {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            Date date = df.parse(strDate);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            dayIndex = calendar.get(Calendar.DAY_OF_WEEK);
            if (dayIndex < 1 || dayIndex > WEEKDAYS) {
                return null;
            }
        } catch (Exception e) {
        }


        return WEEK[dayIndex - 1];
    }

}