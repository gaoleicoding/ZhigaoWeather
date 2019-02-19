package com.weather.zhigao;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
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

public class HomeFragment extends Fragment {
    TextView tv_position, tv_date, tv_temperature, tv_weather, tv_lifestyle_weather,
            tv_lifestyle_forecast, tv_lifestyle_wind, tv_update_time, tv_air_content;
    ImageView iv_menu, iv_expand_arrow;
    LinearLayout ll_root, ll_bottom;
    String TAG = "HomeFragment";
    List<DailyForecastBean> forecastList;
    List<HourlyBean> hourlyForecastList;
    List<LifestyleBean> lifeStyleList;
    RecyclerView forecast_recyclerview, hourly_recyclerview, lifestyle_recyclerview;
    WeatherForecastAdapter forecastAdapter;
    HourlyForecastAdapter hourlyForecastAdapter;
    LifeStyleAdapter lifeStyleAdapter;
    RelativeLayout rl_home, rl_title;
    NestedScrollView scrollView;
    int homeHeight, bottomHeight;
    WeatherForecastEntity weatherBroadcast;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View mParentView = inflater.inflate(R.layout.fragment_home, container, false);


        ll_root = mParentView.findViewById(R.id.ll_root);
        forecast_recyclerview = mParentView.findViewById(R.id.forecast_recyclerview);
        hourly_recyclerview = mParentView.findViewById(R.id.hourly_recyclerview);
        lifestyle_recyclerview = mParentView.findViewById(R.id.lifestyle_recyclerview);
        iv_menu = mParentView.findViewById(R.id.iv_menu);
        iv_expand_arrow = mParentView.findViewById(R.id.iv_expand_arrow);
        tv_position = mParentView.findViewById(R.id.tv_position);
        tv_date = mParentView.findViewById(R.id.tv_date);
        tv_temperature = mParentView.findViewById(R.id.tv_temperature);
        tv_weather = mParentView.findViewById(R.id.tv_weather);
        tv_update_time = mParentView.findViewById(R.id.tv_update_time);
        rl_home = mParentView.findViewById(R.id.rl_home);
        rl_title = mParentView.findViewById(R.id.rl_title);
        tv_lifestyle_weather = mParentView.findViewById(R.id.tv_lifestyle_weather);
        tv_lifestyle_forecast = mParentView.findViewById(R.id.tv_lifestyle_forecast);
        tv_lifestyle_wind = mParentView.findViewById(R.id.tv_lifestyle_wind);
        scrollView = mParentView.findViewById(R.id.scrollView);
        ll_bottom = mParentView.findViewById(R.id.ll_bottom);
        tv_air_content = mParentView.findViewById(R.id.tv_air_content);

        //加入透明状态栏后，需要留出和状态栏高度一样的的空间
        int statusBarHeight = ScreenUtils.getStatusHeight(getActivity());
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) rl_title.getLayoutParams();
        rl_title.setPadding(0, statusBarHeight, 0, ScreenUtils.dp2px(getActivity(), 30));
        rl_title.setLayoutParams(layoutParams);

        iv_expand_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scrollView.smoothScrollTo(0, homeHeight - bottomHeight);
            }
        });
        iv_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity mainActivity = (MainActivity) getActivity();
                mainActivity.openDrawer();
            }
        });
        rl_title.post(new Runnable() {
            @Override
            public void run() {
                int height = rl_title.getHeight();
                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) rl_home.getLayoutParams();
                layoutParams.width = ScreenUtils.getScreenWidth(getActivity());
                homeHeight = ScreenUtils.getScreenHeight(getActivity()) - height;
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

        initData(weatherBroadcast);


//        Map<String, String> params = new HashMap<>();
//        params.put("location", "CN101010300");
//        params.put("key", "227849effc2b4e83b4cf1b0caf743cf9");
//        OkhttpUtil.getInstance(getActivity()).getDataAsync(Urls.url_weather, params, new ResponseCallBack() {
//            @Override
//            public void onFailure(String error) {
//
//            }
//
//            @Override
//            public void onResponse(String response) {
//                try {
//                    JSONObject object = new JSONObject(response);
//                    WeatherForecastEntity weatherBroadcast = new Gson().fromJson(response, WeatherForecastEntity.class);
////                    forecastList = new Gson().fromJson(object.getString("HeWeather6"),
////                            new TypeToken<List<WeatherBroadcast>>() {
////                            }.getType());
//                    forecastAdapter.setList(weatherBroadcast.getHeWeather6().get(0).getDaily_forecast());
//                    hourlyForecastAdapter.setList(weatherBroadcast.getHeWeather6().get(0).getHourly());
//                    lifeStyleAdapter.setList(weatherBroadcast.getHeWeather6().get(0).getLifestyle());
//                    List<LifestyleBean> lifestyleList = weatherBroadcast.getHeWeather6().get(0).getLifestyle();
//                    tv_air_content.setText(lifestyleList.get(lifestyleList.size() - 1).getBrf());
//
//                    tv_position.setText(weatherBroadcast.getHeWeather6().get(0).getBasic().getLocation());
//                    String date = weatherBroadcast.getHeWeather6().get(0).getUpdate().getLoc().split(" ")[0];
//                    tv_date.setText(TimeUtil.getStringToDate(date) + " " + TimeUtil.dateToWeek(date) + " " + LunarUtil.getLunarDate());
//                    tv_temperature.setText(weatherBroadcast.getHeWeather6().get(0).getNow().tmp);
//                    tv_weather.setText(weatherBroadcast.getHeWeather6().get(0).getNow().cond_txt);
//                    tv_lifestyle_weather.setText(weatherBroadcast.getHeWeather6().get(0).getLifestyle().get(1).brf);
//                    DailyForecastBean bean = weatherBroadcast.getHeWeather6().get(0).getDaily_forecast().get(0);
//                    tv_lifestyle_forecast.setText(bean.tmp_min + "~" + bean.tmp_max + " ℃");
//                    tv_lifestyle_wind.setText(bean.wind_dir + bean.wind_sc + App.mContext.getString(R.string.degree));
//                    tv_update_time.setText(getString(R.string.update_time) + weatherBroadcast.getHeWeather6().get(0).getUpdate().loc);
//
//
//                } catch (JSONException e) {
//                    // TODO Auto-generated catch block
//                    e.printStackTrace();
//                }
////                    textView.setText(response);
//            }
//        });

        return mParentView;
    }

    public void setData(WeatherForecastEntity weatherBroadcast) {
        this.weatherBroadcast = weatherBroadcast;
    }

    public void initData(WeatherForecastEntity weatherBroadcast) {
        initRecyclerView();
        initHourlyRecyclerView();
        initLifeStyleRecyclerView();

        forecastAdapter.setList(weatherBroadcast.getHeWeather6().get(0).getDaily_forecast());
        hourlyForecastAdapter.setList(weatherBroadcast.getHeWeather6().get(0).getHourly());

        List<LifestyleBean> lifestyleList = weatherBroadcast.getHeWeather6().get(0).getLifestyle();
        if (lifestyleList != null && lifestyleList.size() > 0) {
            lifeStyleAdapter.setList(lifestyleList);
            tv_air_content.setText(lifestyleList.get(lifestyleList.size() - 1).getBrf());
            tv_lifestyle_weather.setText(lifestyleList.get(1).brf);
        }
        String location = weatherBroadcast.getHeWeather6().get(0).getBasic().getLocation();
        tv_position.setText(location);
        String date = weatherBroadcast.getHeWeather6().get(0).getUpdate().getLoc().split(" ")[0];
        tv_date.setText(TimeUtil.getStringToDate(date) + " " + TimeUtil.dateToWeek(date) + " " + LunarUtil.getLunarDate());
        tv_temperature.setText(weatherBroadcast.getHeWeather6().get(0).getNow().tmp);
        String cond_txt = weatherBroadcast.getHeWeather6().get(0).getNow().cond_txt;
        tv_weather.setText(cond_txt);

        DailyForecastBean bean = weatherBroadcast.getHeWeather6().get(0).getDaily_forecast().get(0);
        tv_lifestyle_forecast.setText(bean.tmp_min + "~" + bean.tmp_max + " ℃");
        tv_lifestyle_wind.setText(bean.wind_dir + bean.wind_sc + App.mContext.getString(R.string.degree));
        tv_update_time.setText(getString(R.string.update_time) + weatherBroadcast.getHeWeather6().get(0).getUpdate().loc);

        long sunriseTime = TimeUtil.dateToLong(TimeUtil.ConverToDate(TimeUtil.getCurrentDate() + " 06:00"));
        long sunsetTime = TimeUtil.dateToLong(TimeUtil.ConverToDate(TimeUtil.getCurrentDate() + " 19:00"));
        long currentTime = System.currentTimeMillis();
        if (sunriseTime < currentTime && sunsetTime > currentTime) {

            ll_root.setBackgroundResource(getDayBackgroundId(cond_txt));

        } else {
            ll_root.setBackgroundResource(getNightBackgroundId(cond_txt));
        }
    }


    private void initRecyclerView() {
        forecastList = new ArrayList<>();
        forecastAdapter = new WeatherForecastAdapter(getActivity(), forecastList);
        forecast_recyclerview.addItemDecoration(new RecycleViewDivider(getActivity(), LinearLayoutManager.VERTICAL));
        forecast_recyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));

//        //解决数据加载不完的问题
//        forecast_recyclerview.setNestedScrollingEnabled(false);
//        forecast_recyclerview.setHasFixedSize(true);
//        //解决数据加载完成后, 没有停留在顶部的问题
//        forecast_recyclerview.setFocusable(false);

        forecast_recyclerview.setAdapter(forecastAdapter);
    }

    private void initHourlyRecyclerView() {
        hourlyForecastList = new ArrayList<>();
        hourlyForecastAdapter = new HourlyForecastAdapter(getActivity(), hourlyForecastList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
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
        lifeStyleAdapter = new LifeStyleAdapter(getActivity(), lifeStyleList);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        lifestyle_recyclerview.setLayoutManager(gridLayoutManager);
        lifestyle_recyclerview.setAdapter(lifeStyleAdapter);
    }

    private int getDayBackgroundId(final String desc) {
        if (desc.contains(getString(R.string.sunny)))
            return R.mipmap.day_qing_yun;
        if (desc.contains(getString(R.string.cloud)))
            return R.mipmap.day_yun;
        if (desc.contains(getString(R.string.overcast)))
            return R.mipmap.day_yintian;
        if (desc.contains(getString(R.string.snow)))
            return R.mipmap.day_yu_xue;
        if (desc.contains(getString(R.string.rain)))
            return R.mipmap.day_yu;
        if (desc.contains(getString(R.string.haze)))
            return R.mipmap.wumai;
        return R.mipmap.day_qing_yun;
    }

    private int getNightBackgroundId(final String desc) {
        if (desc.contains(getString(R.string.sunny)))
            return R.mipmap.background_sunny_night;
        if (desc.contains(getString(R.string.cloud)))
            return R.mipmap.night_yun;
        if (desc.contains(getString(R.string.overcast)))
            return R.mipmap.night_yintian;
        if (desc.contains(getString(R.string.snow)))
            return R.mipmap.night_yu_xue;
        if (desc.contains(getString(R.string.rain)))
            return R.mipmap.night_yu;

        return R.mipmap.background_sunny_night;
    }
}
