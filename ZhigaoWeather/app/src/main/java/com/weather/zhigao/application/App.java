package com.weather.zhigao.application;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.weather.zhigao.SplashActivity;

import java.util.ArrayList;
import java.util.List;


public class App extends Application {
    public static Context mContext;
    public static List<Activity> activityList;

    @Override
    public void onCreate() {
        super.onCreate();

        mContext = this;
        activityList = new ArrayList<Activity>();
    }

    /**
     * 添加Activity到列表中
     */
    public static void addAppActivity(Activity activity) {
        if (!activityList.contains(activity)) {
            activityList.add(activity);
        }
    }

    /**
     * 移除SplashActivity
     */
    public static void romoveSplashActivity(Context context) {

        for (Activity ac : activityList) {
            if (ac instanceof SplashActivity) {
                ac.finish();
            }
        }
    }


}
