/*
 * File Name: LogUtils.java
 * History:
 * Created by lipan on 2014-4-4
 */
package com.weather.zhigao.utils

import android.util.Log

import com.weather.zhigao.BuildConfig


/**
 * 日志输出控制类 (Description)
 *
 * @author liyusheng
 */
object LogUtil {
    var isDebug = BuildConfig.DEBUG
    var isNetDebug = true
    private val TAG = "gaolei"


    fun d(msg: String) {
        if (isDebug)
            Log.d(TAG, msg)
    }

    fun d(tag: String, msg: String) {
        if (isDebug)
            Log.d(tag, msg)
    }

    fun nd(tag: String, msg: String) {
        if (isDebug && isNetDebug)
            Log.d(tag, msg)
    }

    fun v(msg: String) {
        if (isDebug)
            Log.v(TAG, msg)

    }

    fun e(msg: String) {
        if (isDebug)
            Log.e(TAG, msg)
    }

    fun e(e: Exception) {
        if (isDebug)
            Log.e(TAG, e.message.toString())
    }
}
