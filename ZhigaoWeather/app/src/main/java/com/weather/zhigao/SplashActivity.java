package com.weather.zhigao;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jaeger.library.StatusBarUtil;
import com.weather.zhigao.adapter.WeatherForecastAdapter;
import com.weather.zhigao.adapter.divider.RecycleViewDivider;
import com.weather.zhigao.application.App;
import com.weather.zhigao.model.WeatherForecastEntity;
import com.weather.zhigao.model.WeatherForecastEntity.HeWeather6Bean.DailyForecastBean;
import com.weather.zhigao.net.OkhttpUtil;
import com.weather.zhigao.net.ResponseCallBack;
import com.weather.zhigao.net.Urls;
import com.weather.zhigao.utils.LunarUtil;
import com.weather.zhigao.utils.ScreenUtils;
import com.weather.zhigao.utils.TimeUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SplashActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.setTransparent(this);
        setContentView(R.layout.activity_splash);
        setAndroidNativeLightStatusBar(this,true);
        App.addAppActivity(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));

                }
            }, 3000);

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
}
