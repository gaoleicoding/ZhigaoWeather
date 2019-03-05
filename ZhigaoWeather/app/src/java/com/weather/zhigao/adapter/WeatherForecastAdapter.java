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
import com.weather.zhigao.model.WeatherForecastEntity.HeWeather6Bean.DailyForecastBean;
import com.weather.zhigao.utils.TimeUtil;
import com.weather.zhigao.utils.Weather2IconUtil;

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
        holder.iv_weather.setImageResource(Weather2IconUtil.getWeatherIconId(bean.cond_txt_d));
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


}