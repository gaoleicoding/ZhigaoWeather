package com.weather.zhigao;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jaeger.library.StatusBarUtil;
import com.weather.zhigao.adapter.WeatherForecastAdapter;
import com.weather.zhigao.adapter.divider.RecycleViewDivider;
import com.weather.zhigao.model.WeatherBroadcast;
import com.weather.zhigao.model.WeatherBroadcast.HeWeather6Bean.DailyForecastBean;
import com.weather.zhigao.net.ResponseCallBack;
import com.weather.zhigao.net.OkhttpUtil;
import com.weather.zhigao.net.Urls;
import com.weather.zhigao.utils.LunarUtil;
import com.weather.zhigao.utils.TimeUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    TextView tv_position,tv_date;
    ImageView iv_menu;
    String TAG = "MainActivity";
    List<DailyForecastBean> broadcastList;
    RecyclerView broadcast_recyclerview;
    WeatherForecastAdapter forecastAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StatusBarUtil.setTransparent(this);
        broadcast_recyclerview=findViewById(R.id.broadcast_recyclerview);
        iv_menu = findViewById(R.id.iv_menu);
        tv_position = findViewById(R.id.tv_position);
        tv_date = findViewById(R.id.tv_date);

        initRecyclerView();
        Map<String, String> params = new HashMap<>();
        params.put("location", "CN101010300");
        params.put("key", "227849effc2b4e83b4cf1b0caf743cf9");
        OkhttpUtil.getInstance(this).getDataAsync(Urls.url_weather_forecast, params, new ResponseCallBack() {
            @Override
            public void onFailure(String error) {
            }

            @Override
            public void onResponse(String response) {
                try {
                    JSONObject object = new JSONObject(response);
                    WeatherBroadcast weatherBroadcast=new Gson().fromJson(response,WeatherBroadcast.class);
//                    broadcastList = new Gson().fromJson(object.getString("HeWeather6"),
//                            new TypeToken<List<WeatherBroadcast>>() {
//                            }.getType());
                    forecastAdapter.setList(weatherBroadcast.getHeWeather6().get(0).getDaily_forecast());
                    tv_position.setText(weatherBroadcast.getHeWeather6().get(0).getBasic().getLocation());
                    String date=weatherBroadcast.getHeWeather6().get(0).getUpdate().getLoc().split(" ")[0];
                    tv_date.setText(TimeUtil.getStringToDate(date)+" "+TimeUtil.dateToWeek(date)+" "+ LunarUtil.getLunarDate());
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
//                    textView.setText(response);
            }
        });
    }

    private void initRecyclerView() {
        broadcastList = new ArrayList<>();
        forecastAdapter = new WeatherForecastAdapter(this, broadcastList);
        broadcast_recyclerview.addItemDecoration(new RecycleViewDivider(this, LinearLayoutManager.VERTICAL));

        broadcast_recyclerview.setLayoutManager(new LinearLayoutManager(this));
//        //解决数据加载不完的问题
//        broadcast_recyclerview.setNestedScrollingEnabled(false);
//        broadcast_recyclerview.setHasFixedSize(true);
//        //解决数据加载完成后, 没有停留在顶部的问题
//        broadcast_recyclerview.setFocusable(false);
        broadcast_recyclerview.setAdapter(forecastAdapter);
    }
}
