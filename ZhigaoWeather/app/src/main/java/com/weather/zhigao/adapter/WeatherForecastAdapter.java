package com.weather.zhigao.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.weather.zhigao.R;
import com.weather.zhigao.application.App;
import com.weather.zhigao.model.WeatherBroadcast.HeWeather6Bean.DailyForecastBean;
import com.weather.zhigao.utils.TimeUtil;

import java.util.List;


public class WeatherForecastAdapter extends RecyclerView.Adapter<WeatherForecastAdapter.MyViewHolder> {

    public Context context;

    public List<DailyForecastBean> list;


    public WeatherForecastAdapter(Context context, List<DailyForecastBean> list) {
        this.context = context;
        this.list = list;

    }

    public void setList(List<DailyForecastBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_weather_forecast, null);
        MyViewHolder holder = new MyViewHolder(view);

        return holder;
    }

    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final DailyForecastBean bean = list.get(position);
        if (position == 0) {
            holder.tv_week.setText(App.mContext.getString(R.string.today));
        } else if (position == 1) {
            holder.tv_week.setText(App.mContext.getString(R.string.tomorrow));
        } else holder.tv_week.setText(TimeUtil.dateToWeek(bean.date));
        String splits[] = bean.date.split("-");
        holder.tv_date.setText(splits[1] + "/" + splits[2]);
        holder.tv_weather.setText(bean.cond_txt_d);
        holder.tv_temperature.setText(bean.tmp_min + "~" + bean.tmp_max + " ℃");
        holder.tv_wind.setText(bean.wind_dir + bean.wind_sc + App.mContext.getString(R.string.degree));
        holder.iv_weather.setImageResource(getWeatherIconId(bean.cond_txt_d));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_week, tv_date, tv_weather, tv_temperature, tv_wind;
        ImageView iv_weather;

        public MyViewHolder(View view) {
            super(view);
            tv_week = view.findViewById(R.id.tv_week);
            tv_date = view.findViewById(R.id.tv_date);
            tv_weather = view.findViewById(R.id.tv_weather);
            tv_temperature = view.findViewById(R.id.tv_temperature);
            tv_wind = view.findViewById(R.id.tv_wind);
            iv_weather = view.findViewById(R.id.iv_weather);
        }
    }

    private int getWeatherIconId(final String desc) {
        if (!TextUtils.isEmpty(desc)) {
            if (desc.equalsIgnoreCase(App.mContext.getString(R.string.sunny))) {
                return R.drawable.iclockweather_w1;
            } else if (desc.equalsIgnoreCase(App.mContext.getString(R.string.partly_cloudy))
                    || desc.equalsIgnoreCase(App.mContext.getString(R.string.cloudy))
                    || desc.equalsIgnoreCase(App.mContext.getString(R.string.few_cloud))) {
                return R.drawable.iclockweather_w2;
            } else if (desc.equalsIgnoreCase(App.mContext.getString(R.string.overcast))) {
                return R.drawable.iclockweather_w3;
            } else if (desc.equalsIgnoreCase(App.mContext.getString(R.string.shower_rain))) {
                return R.drawable.iclockweather_w8;
            } else if (desc.equalsIgnoreCase(App.mContext.getString(R.string.heavy_shower_rain))) {
                return R.drawable.iclockweather_w8;
            } else if (desc.equalsIgnoreCase(App.mContext.getString(R.string.thunder_shower))
                    || desc.equalsIgnoreCase(App.mContext.getString(R.string.heavy_thunderstorm))) {
                return R.drawable.iclockweather_w9;
            } else if (desc.equalsIgnoreCase(App.mContext.getString(R.string.hail))) {
                return R.drawable.iclockweather_w18;
            } else if (desc.equalsIgnoreCase(App.mContext.getString(R.string.light_rain))
                    || desc.equalsIgnoreCase(App.mContext.getString(R.string.drizzle_rain))
                    || desc.equalsIgnoreCase(App.mContext.getString(R.string.drizzle_rain_1))) {
                return R.drawable.iclockweather_w4;
            } else if (desc.equalsIgnoreCase(App.mContext.getString(R.string.moderate_rain))) {
                return R.drawable.iclockweather_w5;
            } else if (desc.equalsIgnoreCase(App.mContext.getString(R.string.heavy_rain))
                    || desc.equalsIgnoreCase(App.mContext.getString(R.string.storm))) {
                return R.drawable.iclockweather_w6;
            } else if (desc.equalsIgnoreCase(App.mContext.getString(R.string.extreme_rain))
                    || desc.equalsIgnoreCase(App.mContext.getString(R.string.heavy_storm))
                    || desc.equalsIgnoreCase(App.mContext.getString(R.string.severe_storm))) {
                return R.drawable.iclockweather_w7;
            } else if (desc.equalsIgnoreCase(App.mContext.getString(R.string.freezing_rain))) {
                return R.drawable.iclockweather_w15;
            } else if (desc.equalsIgnoreCase(App.mContext.getString(R.string.light_snow))
                    || desc.equalsIgnoreCase(App.mContext.getString(R.string.snow_flurry))) {
                return R.drawable.iclockweather_w11;
            } else if (desc.equalsIgnoreCase(App.mContext.getString(R.string.moderate_snow))) {
                return R.drawable.iclockweather_w12;
            } else if (desc.equalsIgnoreCase(App.mContext.getString(R.string.heavy_snow))) {
                return R.drawable.iclockweather_w13;
            } else if (desc.equalsIgnoreCase(App.mContext.getString(R.string.snow_storm))) {
                return R.drawable.iclockweather_w14;
            } else if (desc.equalsIgnoreCase(App.mContext.getString(R.string.sleet))
                    || desc.equalsIgnoreCase(App.mContext.getString(R.string.rain_snow))
                    || desc.equalsIgnoreCase(App.mContext.getString(R.string.shower_snow))) {
                return R.drawable.iclockweather_w10;
            } else if (desc.equalsIgnoreCase(App.mContext.getString(R.string.mist))
                    || desc.equalsIgnoreCase(App.mContext.getString(R.string.foggy))) {
                return R.drawable.iclockweather_w16;
            } else if (desc.equalsIgnoreCase(App.mContext.getString(R.string.haze))
                    || desc.equalsIgnoreCase(App.mContext.getString(R.string.sand))
                    || desc.equalsIgnoreCase(App.mContext.getString(R.string.dust))
                    || desc.equalsIgnoreCase(App.mContext.getString(R.string.volcanic_ash))
                    || desc.equalsIgnoreCase(App.mContext.getString(R.string.dust_storm))
                    || desc.equalsIgnoreCase(App.mContext.getString(R.string.sand_storm))) {
                return R.drawable.iclockweather_w17;
            }
        }

        return R.drawable.iclockweather_w2;
    }
}