package com.weather.zhigao;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.CycleInterpolator;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jaeger.library.StatusBarUtil;
import com.weather.zhigao.application.App;
import com.weather.zhigao.model.HotCityEntity;
import com.weather.zhigao.model.WeatherForecastEntity;
import com.weather.zhigao.net.OkhttpUtil;
import com.weather.zhigao.net.ResponseCallBack;
import com.weather.zhigao.net.Urls;
import com.weather.zhigao.utils.LogUtil;
import com.weather.zhigao.utils.LunarUtil;
import com.weather.zhigao.utils.SPUtils;
import com.weather.zhigao.utils.TimeUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SplashActivity extends AppCompatActivity {

    ImageView ivSplashIcon;
    WeatherForecastEntity weatherBroadcast;
    String TAG = "SplashActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.setTransparent(this);
        setContentView(R.layout.activity_splash);

        ivSplashIcon = findViewById(R.id.iv_splash_icon);

        ivSplashIcon.post(new Runnable() {
            @Override
            public void run() {
                startAnimation();
            }
        });

        setAndroidNativeLightStatusBar(this, true);
        App.addAppActivity(this);


        getHotCityAdvance();

    }

    //提前获取热搜城市信息，这样进入添加城市界面直接获取传递过来的数据，而不用从网络上获取，显示速度快，用户体验好
    private void getHotCityAdvance() {
        Map<String, String> map = new HashMap<>();
        map.put("group", "cn");
        map.put("number", "50");
        OkhttpUtil.getInstance(this).getDataAsync(Urls.url_hot_city, map, new ResponseCallBack() {
            @Override
            public void onFailure(String error) {

            }

            @Override
            public void onResponse(String response) {
//                HotCityEntity hotCityEntity = new Gson().fromJson(response, HotCityEntity.class);
                SPUtils.setParam(SplashActivity.this, "hotcity", response);
                String currentCity = (String) SPUtils.getParam(SplashActivity.this, "currentCity", "");
                LogUtil.d(TAG, "currentCity：" + currentCity);
                if ("".equals(currentCity)) {
                    startActivity(new Intent(SplashActivity.this, FindCityActivity.class));
                    finish();
                } else {
                    getWeatherInfoAdvance(currentCity);
                }

            }
        });
    }

    //提前获取城市天气信息，这样进入主界面直接获取传递过来的数据，而不用从网络上获取，显示速度快，用户体验好
    private void getWeatherInfoAdvance(String location) {
        Map<String, String> params = new HashMap<>();
        params.put("location", location);

        OkhttpUtil.getInstance(this).getDataAsync(Urls.url_weather, params, new ResponseCallBack() {
            @Override
            public void onFailure(String error) {

            }

            @Override
            public void onResponse(String response) {
                weatherBroadcast = new Gson().fromJson(response, WeatherForecastEntity.class);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                        intent.putExtra("weather", weatherBroadcast);
                        startActivity(intent);
                        finish();

                    }
                }, 1500);


            }
        });
    }

    private static void setAndroidNativeLightStatusBar(Activity activity, boolean dark) {

        View decor = activity.getWindow().getDecorView();
        if (dark) {
            decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        } else {
            decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        }
    }


    private void startAnimation() {
        ObjectAnimator animator = ObjectAnimator.ofFloat(ivSplashIcon, "translationY", 0, -(ivSplashIcon.getHeight() >> 1));
        animator.setDuration(800);
        animator.setRepeatCount(ObjectAnimator.INFINITE);
//        animator.setRepeatMode(ObjectAnimator.RESTART);
        animator.setInterpolator(new CycleInterpolator(0.5f));
        animator.start();
    }

    private void stopAnimation() {
        ivSplashIcon.clearAnimation();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onStop() {
        super.onStop();
        stopAnimation();
    }
}
