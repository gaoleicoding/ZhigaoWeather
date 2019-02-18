package com.weather.zhigao;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.CycleInterpolator;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jaeger.library.StatusBarUtil;
import com.weather.zhigao.application.App;
import com.weather.zhigao.model.WeatherForecastEntity;
import com.weather.zhigao.net.OkhttpUtil;
import com.weather.zhigao.net.ResponseCallBack;
import com.weather.zhigao.net.Urls;
import com.weather.zhigao.utils.LunarUtil;
import com.weather.zhigao.utils.TimeUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SplashActivity extends AppCompatActivity {

    ImageView ivSplashIcon;
    WeatherForecastEntity weatherBroadcast;
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
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)

        Map<String, String> params = new HashMap<>();
        params.put("location", "CN101010300");
        params.put("key", "227849effc2b4e83b4cf1b0caf743cf9");
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

                        Intent intent=new Intent(SplashActivity.this, MainActivity.class);
                        intent.putExtra("weather",weatherBroadcast);
                        startActivity(intent);

                    }
                }, 2000);


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

    // 用来计算返回键的点击间隔时间
    private long exitTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN) {

            if ((System.currentTimeMillis() - exitTime) > 2000) {
                //弹出提示，可以有多种方式
                Toast.makeText(getApplicationContext(), getString(R.string.press_exit), Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
            }
            return true;
        }

        return super.onKeyDown(keyCode, event);
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
