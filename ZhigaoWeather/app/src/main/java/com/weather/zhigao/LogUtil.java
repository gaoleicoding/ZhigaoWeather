/*
 * File Name: LogUtils.java
 * History:
 * Created by lipan on 2014-4-4
 */
package com.weather.zhigao;

import android.util.Log;


/**
 * 日志输出控制类 (Description)
 *
 * @author liyusheng
 */
public class LogUtil {
    public static boolean isDebug = BuildConfig.DEBUG;
    public static boolean isNetDebug = true;
    private static final String TAG = "gaolei";


    public static void d(String msg) {
        if (isDebug)
            Log.d(TAG, msg);
    }

    public static void d(String tag, String msg) {
        if (isDebug)
            Log.d(tag, msg);
    }

    public static void nd(String tag, String msg) {
        if (isDebug && isNetDebug)
            Log.d(tag, msg);
    }

    public static void v(String msg) {
        if (isDebug)
            Log.v(TAG, msg);

    }

    public static void e(String msg) {
        if (isDebug)
            Log.e(TAG, msg);
    }

    public static void e(Exception e) {
        if (isDebug)
            Log.e(TAG, e.getMessage().toString());
    }
}
