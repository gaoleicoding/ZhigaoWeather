package com.weather.zhigao;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.google.gson.Gson;
import com.jaeger.library.StatusBarUtil;
import com.weather.zhigao.adapter.HotCityAdapter;
import com.weather.zhigao.adapter.LifeStyleAdapter;
import com.weather.zhigao.model.HotCityEntity;
import com.weather.zhigao.model.WeatherForecastEntity;
import com.weather.zhigao.net.OkhttpUtil;
import com.weather.zhigao.net.ResponseCallBack;
import com.weather.zhigao.net.Urls;
import com.weather.zhigao.utils.SPUtils;
import com.weather.zhigao.utils.ScreenUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindCityActivity extends AppCompatActivity {

    List<HotCityEntity.HeWeather6Bean.BasicBean> hotCityList;
    HotCityAdapter hotCityAdapter;
    RecyclerView city_recyclerview;
    RelativeLayout rl_title;
    ImageView iv_back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.setTransparent(this);
        setContentView(R.layout.activity_find_city);
        city_recyclerview = findViewById(R.id.city_recyclerview);
        rl_title = findViewById(R.id.rl_title);
        iv_back = findViewById(R.id.iv_back);

        //加入透明状态栏后，需要留出和状态栏高度一样的的空间
        int statusBarHeight = ScreenUtils.getStatusHeight2(this);
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) rl_title.getLayoutParams();
        rl_title.setPadding(0, statusBarHeight, 0, 0);
        rl_title.setLayoutParams(layoutParams);
        String response = (String) SPUtils.getParam(this, "hotcity", "");

        initHotCityRecyclerView();
        HotCityEntity hotCityEntity = new Gson().fromJson(response, HotCityEntity.class);
        hotCityList = hotCityEntity.getHeWeather6().get(0).getBasic();
        hotCityAdapter.setList(hotCityList);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void initHotCityRecyclerView() {
        hotCityList = new ArrayList<>();
        hotCityAdapter = new HotCityAdapter(this, hotCityList);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        city_recyclerview.setLayoutManager(gridLayoutManager);
        city_recyclerview.setAdapter(hotCityAdapter);
        hotCityAdapter.setOnItemClickListener(new HotCityAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int postion) {
                String location = hotCityList.get(postion).getLocation();
                SPUtils.setParam(FindCityActivity.this, "currentCity", location);
                getWeatherInfoAdvance(location);
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
                final WeatherForecastEntity weatherBroadcast = new Gson().fromJson(response, WeatherForecastEntity.class);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        Intent intent = new Intent(FindCityActivity.this, MainActivity.class);
                        intent.putExtra("weather", weatherBroadcast);
                        startActivity(intent);

                    }
                }, 1500);


            }
        });
    }

}
