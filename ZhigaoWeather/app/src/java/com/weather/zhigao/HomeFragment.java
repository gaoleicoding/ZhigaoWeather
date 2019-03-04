package com.weather.zhigao;

import android.app.NotificationManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.weather.zhigao.adapter.HourlyForecastAdapter;
import com.weather.zhigao.adapter.LifeStyleAdapter;
import com.weather.zhigao.adapter.WeatherForecastAdapter;
import com.weather.zhigao.adapter.divider.RecycleViewDivider;
import com.weather.zhigao.application.App;
import com.weather.zhigao.db.DatabaseManager;
import com.weather.zhigao.model.WeatherForecastEntity;
import com.weather.zhigao.model.WeatherForecastEntity.HeWeather6Bean.DailyForecastBean;
import com.weather.zhigao.model.WeatherForecastEntity.HeWeather6Bean.HourlyBean;
import com.weather.zhigao.model.WeatherForecastEntity.HeWeather6Bean.LifestyleBean;
import com.weather.zhigao.utils.LogUtil;
import com.weather.zhigao.utils.LunarUtil;
import com.weather.zhigao.utils.ScreenUtils;
import com.weather.zhigao.utils.TimeUtil;
import com.weather.zhigao.utils.Weather2IconUtil;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.NOTIFICATION_SERVICE;

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
    private NotificationManager notificationManager;

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
        notificationManager = (NotificationManager) getActivity().getSystemService(NOTIFICATION_SERVICE);
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


        return mParentView;
    }


    public void setData(WeatherForecastEntity weatherBroadcast) {
        this.weatherBroadcast = weatherBroadcast;
    }

    public void initData(WeatherForecastEntity weatherBroadcast) {

        Intent intent = new Intent(getActivity(), MyService.class);
        intent.putExtra("weather", weatherBroadcast);
        getActivity().startService(intent);

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
        LogUtil.d(TAG, "initData，location：" + location);
        tv_position.setText(location);
        String date = weatherBroadcast.getHeWeather6().get(0).getUpdate().getLoc().split(" ")[0];
        tv_date.setText(TimeUtil.getStringToDate(date) + " " + TimeUtil.dateToWeek(date) + " " + LunarUtil.getLunarDate());
        String nowTemp = weatherBroadcast.getHeWeather6().get(0).getNow().tmp;
        tv_temperature.setText(nowTemp);
        String cond_txt = weatherBroadcast.getHeWeather6().get(0).getNow().cond_txt;
        tv_weather.setText(cond_txt);

        DailyForecastBean bean = weatherBroadcast.getHeWeather6().get(0).getDaily_forecast().get(0);
        tv_lifestyle_forecast.setText(bean.tmp_min + "~" + bean.tmp_max + " ℃");
        tv_lifestyle_wind.setText(bean.wind_dir + bean.wind_sc + App.mContext.getString(R.string.degree));
        tv_update_time.setText(getString(R.string.update_time) + weatherBroadcast.getHeWeather6().get(0).getUpdate().loc);

        long sunriseTime = TimeUtil.dateToLong(TimeUtil.ConverToDate(TimeUtil.getCurrentDate() + " 06:00"));
        long sunsetTime = TimeUtil.dateToLong(TimeUtil.ConverToDate(TimeUtil.getCurrentDate() + " 19:00"));
        long currentTime = System.currentTimeMillis();
        //计算现在是白天还是黑天，现在设置固定白天时间为 06:00 - 19:00
        if (sunriseTime < currentTime && sunsetTime > currentTime) {

            ll_root.setBackgroundResource(Weather2IconUtil.getDayBackgroundId(cond_txt));

        } else {
            ll_root.setBackgroundResource(Weather2IconUtil.getNightBackgroundId(cond_txt));
        }
        //及时更新数据库中城市的天气信息
        DatabaseManager.getInstance(getActivity()).update(location, DatabaseManager.getInstance(getActivity()).getCityBean(weatherBroadcast));

        MainActivity mainActivity=(MainActivity)getActivity();
        //在更新数据库中城市的天气信息后，显示添加的城市天气数据，保持最新
        mainActivity.setRecyclerView();
    }


    private void initRecyclerView() {
        forecastList = new ArrayList<>();
        forecastAdapter = new WeatherForecastAdapter(getActivity(), forecastList);
        forecast_recyclerview.addItemDecoration(new RecycleViewDivider(getActivity(), LinearLayoutManager.VERTICAL));
        forecast_recyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));

//        //以下三条解决RecyclerView和NestedScrollView的冲突
        //解决数据加载不完的问题
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


}
