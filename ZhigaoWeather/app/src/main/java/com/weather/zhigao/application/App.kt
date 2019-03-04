package com.weather.zhigao.application

import android.app.Activity
import android.app.Application
import android.content.Context

import com.weather.zhigao.FindCityActivity
import com.weather.zhigao.SplashActivity

import java.util.ArrayList


class App : Application() {

    override fun onCreate() {
        super.onCreate()

        mContext = this
        activityList = ArrayList()
    }

    companion object {
        lateinit var mContext: Context
        var activityList: MutableList<Activity>?=null

        /**
         * 添加Activity到列表中
         */
        fun addAppActivity(activity: Activity) {
            if (activityList!!.contains(activity)) {
                activityList?.add(activity)
            }
        }

        /**
         * 移除SplashActivity
         */
        fun romoveUselessActivity(context: Context) {

            for (ac in activityList!!) {
                if (ac is SplashActivity) {
                    ac.finish()
                }
                if (ac is FindCityActivity) {
                    ac.finish()
                }
            }
        }
    }


}
