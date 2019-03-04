package com.weather.zhigao.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.weather.zhigao.R;
import com.weather.zhigao.model.WeatherForecastEntity.HeWeather6Bean.HourlyBean;
import com.weather.zhigao.utils.Weather2IconUtil;

import java.util.List;


public class HourlyForecastAdapter extends RecyclerView.Adapter<HourlyForecastAdapter.MyViewHolder> {

    public Context context;

    public List<HourlyBean> list;


    public HourlyForecastAdapter(Context context, List<HourlyBean> list) {
        this.context = context;
        this.list = list;

    }

    public void setList(List<HourlyBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_hourly_forecast, null);
        MyViewHolder holder = new MyViewHolder(view);

        return holder;
    }

    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final HourlyBean bean = list.get(position);

        holder.tv_time.setText(bean.time.split(" ")[1]);
        holder.tv_temperature.setText(bean.tmp+" ℃");
        holder.iv_weather.setImageResource(Weather2IconUtil.getWeatherIconId(bean.cond_txt));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_time, tv_temperature;
        ImageView iv_weather;

        public MyViewHolder(View view) {
            super(view);
            tv_time = view.findViewById(R.id.tv_time);
            tv_temperature = view.findViewById(R.id.tv_temperature);
            iv_weather = view.findViewById(R.id.iv_weather);
        }
    }


}