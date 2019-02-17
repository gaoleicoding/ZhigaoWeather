package com.weather.zhigao;

import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jaeger.library.StatusBarUtil;
import com.weather.zhigao.adapter.HourlyForecastAdapter;
import com.weather.zhigao.adapter.LifeStyleAdapter;
import com.weather.zhigao.adapter.WeatherForecastAdapter;
import com.weather.zhigao.adapter.divider.RecycleViewDivider;
import com.weather.zhigao.application.App;
import com.weather.zhigao.model.WeatherForecastEntity;
import com.weather.zhigao.model.WeatherForecastEntity.HeWeather6Bean.DailyForecastBean;
import com.weather.zhigao.model.WeatherForecastEntity.HeWeather6Bean.HourlyBean;
import com.weather.zhigao.model.WeatherForecastEntity.HeWeather6Bean.LifestyleBean;
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

public class MainActivity extends AppCompatActivity {
    TextView tv_position, tv_date, tv_temperature, tv_weather, tv_lifestyle_weather, tv_lifestyle_forecast, tv_lifestyle_wind;
    ImageView iv_menu, iv_expand_arrow;
    LinearLayout ll_bottom;
    String TAG = "MainActivity";
    List<DailyForecastBean> forecastList;
    List<HourlyBean> hourlyForecastList;
    List<LifestyleBean> lifeStyleList;
    RecyclerView forecast_recyclerview, hourly_recyclerview,lifestyle_recyclerview;
    WeatherForecastAdapter forecastAdapter;
    HourlyForecastAdapter hourlyForecastAdapter;
    LifeStyleAdapter lifeStyleAdapter;
    RelativeLayout rl_home, rl_title;
    NestedScrollView scrollView;
    int homeHeight, bottomHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StatusBarUtil.setTransparent(this);
        forecast_recyclerview = findViewById(R.id.forecast_recyclerview);
        hourly_recyclerview = findViewById(R.id.hourly_recyclerview);
        lifestyle_recyclerview = findViewById(R.id.lifestyle_recyclerview);
        iv_menu = findViewById(R.id.iv_menu);
        iv_expand_arrow = findViewById(R.id.iv_expand_arrow);
        tv_position = findViewById(R.id.tv_position);
        tv_date = findViewById(R.id.tv_date);
        tv_temperature = findViewById(R.id.tv_temperature);
        tv_weather = findViewById(R.id.tv_weather);
        rl_home = findViewById(R.id.rl_home);
        rl_title = findViewById(R.id.rl_title);
        tv_lifestyle_weather = findViewById(R.id.tv_lifestyle_weather);
        tv_lifestyle_forecast = findViewById(R.id.tv_lifestyle_forecast);
        tv_lifestyle_wind = findViewById(R.id.tv_lifestyle_wind);
        scrollView = findViewById(R.id.scrollView);
        ll_bottom = findViewById(R.id.ll_bottom);

        iv_expand_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scrollView.smoothScrollTo(0, homeHeight - bottomHeight);
            }
        });

        rl_title.post(new Runnable() {
            @Override
            public void run() {
                int height = rl_title.getHeight();
                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) rl_home.getLayoutParams();
                layoutParams.width = ScreenUtils.getScreenWidth(MainActivity.this);
                homeHeight = ScreenUtils.getScreenHeight(MainActivity.this) - ScreenUtils.getStatusHeight(MainActivity.this) - height;
                layoutParams.height = homeHeight;
                rl_home.setLayoutParams(layoutParams);
            }
        });
        ll_bottom.post(new Runnable() {
            @Override
            public void run() {
                bottomHeight = ll_bottom.getHeight();
            }
        });


        initRecyclerView();
        initHourlyRecyclerView();
        initLifeStyleRecyclerView();

        Map<String, String> params = new HashMap<>();
        params.put("location", "CN101010300");
        params.put("key", "227849effc2b4e83b4cf1b0caf743cf9");
        OkhttpUtil.getInstance(this).getDataAsync(Urls.url_weather, params, new ResponseCallBack() {
            @Override
            public void onFailure(String error) {
            }

            @Override
            public void onResponse(String response) {
                try {
                    JSONObject object = new JSONObject(response);
                    WeatherForecastEntity weatherBroadcast = new Gson().fromJson(response, WeatherForecastEntity.class);
//                    forecastList = new Gson().fromJson(object.getString("HeWeather6"),
//                            new TypeToken<List<WeatherBroadcast>>() {
//                            }.getType());
                    forecastAdapter.setList(weatherBroadcast.getHeWeather6().get(0).getDaily_forecast());
                    hourlyForecastAdapter.setList(weatherBroadcast.getHeWeather6().get(0).getHourly());
                    lifeStyleAdapter.setList(weatherBroadcast.getHeWeather6().get(0).getLifestyle());


                    tv_position.setText(weatherBroadcast.getHeWeather6().get(0).getBasic().getLocation());
                    String date = weatherBroadcast.getHeWeather6().get(0).getUpdate().getLoc().split(" ")[0];
                    tv_date.setText(TimeUtil.getStringToDate(date) + " " + TimeUtil.dateToWeek(date) + " " + LunarUtil.getLunarDate());
                    tv_temperature.setText(weatherBroadcast.getHeWeather6().get(0).getNow().tmp);
                    tv_weather.setText(weatherBroadcast.getHeWeather6().get(0).getNow().cond_txt);
                    tv_lifestyle_weather.setText(weatherBroadcast.getHeWeather6().get(0).getLifestyle().get(1).brf);
                    DailyForecastBean bean = weatherBroadcast.getHeWeather6().get(0).getDaily_forecast().get(0);
                    tv_lifestyle_forecast.setText(bean.tmp_min + "~" + bean.tmp_max + " ℃");
                    tv_lifestyle_wind.setText(bean.wind_dir + bean.wind_sc + App.mContext.getString(R.string.degree));
                    App.romoveSplashActivity(MainActivity.this);
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
//                    textView.setText(response);
            }
        });
    }

    private void initData() {

    }

    private void initRecyclerView() {
        forecastList = new ArrayList<>();
        forecastAdapter = new WeatherForecastAdapter(this, forecastList);
        forecast_recyclerview.addItemDecoration(new RecycleViewDivider(this, LinearLayoutManager.VERTICAL));

        forecast_recyclerview.setLayoutManager(new LinearLayoutManager(this));

//        //解决数据加载不完的问题
//        forecast_recyclerview.setNestedScrollingEnabled(false);
//        forecast_recyclerview.setHasFixedSize(true);
//        //解决数据加载完成后, 没有停留在顶部的问题
//        forecast_recyclerview.setFocusable(false);

        forecast_recyclerview.setAdapter(forecastAdapter);
    }

    private void initHourlyRecyclerView() {
        hourlyForecastList = new ArrayList<>();
        hourlyForecastAdapter = new HourlyForecastAdapter(this, hourlyForecastList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        hourly_recyclerview.setLayoutManager(linearLayoutManager);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

//        //解决数据加载不完的问题
//        hourly_recyclerview.setNestedScrollingEnabled(false);
//        hourly_recyclerview.setHasFixedSize(true);
//        //解决数据加载完成后, 没有停留在顶部的问题
//        hourly_recyclerview.setFocusable(false);

        hourly_recyclerview.setAdapter(hourlyForecastAdapter);
    }

    private void initLifeStyleRecyclerView() {
        lifeStyleList = new ArrayList<>();
        lifeStyleAdapter = new LifeStyleAdapter(this, lifeStyleList);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
        lifestyle_recyclerview.setLayoutManager(gridLayoutManager);
        lifestyle_recyclerview.setAdapter(lifeStyleAdapter);
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


}
