package com.weather.zhigao;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.CycleInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.jaeger.library.StatusBarUtil;
import com.weather.zhigao.application.App;
import com.weather.zhigao.model.WeatherForecastEntity;
import com.weather.zhigao.utils.ScreenUtils;

public class MainActivity extends AppCompatActivity {

    DrawerLayout mDrawerLayout;
    FrameLayout frament_content;
    RelativeLayout rl_drawer;
    HomeFragment homeFragment;
    ImageView iv_add;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.setTransparent(this);
        setContentView(R.layout.activity_main);
        homeFragment = new HomeFragment();
        WeatherForecastEntity weatherBroadcast = getIntent().getParcelableExtra("weather");
        homeFragment.setData(weatherBroadcast);

        mDrawerLayout = findViewById(R.id.drawer_layout);
        frament_content = findViewById(R.id.frament_content);
        rl_drawer = findViewById(R.id.rl_drawer);
        iv_add = findViewById(R.id.iv_add);

        StatusBarUtil.setColorForDrawerLayout(this, mDrawerLayout, 0);
        getSupportFragmentManager().beginTransaction().replace(R.id.frament_content, homeFragment).commit();
        iv_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, FindCityActivity.class));
            }
        });

    }

    protected void onNewIntent(Intent intent) {

        super.onNewIntent(intent);
        WeatherForecastEntity weatherBroadcast = intent.getParcelableExtra("weather");
        homeFragment.initData(weatherBroadcast);

    }

    // 用来计算返回键的点击间隔时间
    private long exitTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN) {

            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(getApplicationContext(), getString(R.string.press_exit), Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
            }
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    public void openDrawer() {
        mDrawerLayout.openDrawer(Gravity.LEFT);  //便可以实现弹出抽屉界面
    }
}
