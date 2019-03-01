package com.weather.zhigao.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import com.weather.zhigao.R
import com.weather.zhigao.application.App
import com.weather.zhigao.model.WeatherForecastEntity.HeWeather6Bean.DailyForecastBean
import com.weather.zhigao.utils.TimeUtil
import com.weather.zhigao.utils.Weather2IconUtil


class WeatherForecastAdapter(var context: Context, var list: List<DailyForecastBean>) : RecyclerView.Adapter<WeatherForecastAdapter.MyViewHolder>() {

    fun changeList(list: List<DailyForecastBean>) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val view = LayoutInflater.from(context).inflate(R.layout.item_weather_forecast, null)

        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val bean = list[position]
        if (position == 0) {
            holder.tv_week.text = App.mContext.getString(R.string.today)
        } else if (position == 1) {
            holder.tv_week.text = App.mContext.getString(R.string.tomorrow)
        } else
            holder.tv_week.text = TimeUtil.dateToWeek(bean.date)
        val splits = bean.date.split("-".toRegex()).dropLastWhile({ it.isEmpty() }).toTypedArray()
        holder.tv_date.setText(splits[1] + "/" + splits[2])
        holder.tv_weather.text = bean.cond_txt_d
        holder.tv_temperature.text = bean.tmp_min + "~" + bean.tmp_max + " ℃"
        holder.tv_wind.text = bean.wind_dir + bean.wind_sc + App.mContext.getString(R.string.degree)
        holder.iv_weather.setImageResource(Weather2IconUtil.getWeatherIconId(bean.cond_txt_d))
    }

    override fun getItemCount(): Int {
        return list.size
    }


    public inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var tv_week: TextView
        var tv_date: TextView
        var tv_weather: TextView
        var tv_temperature: TextView
        var tv_wind: TextView
        var iv_weather: ImageView

        init {
            tv_week = view.findViewById(R.id.tv_week)
            tv_date = view.findViewById(R.id.tv_date)
            tv_weather = view.findViewById(R.id.tv_weather)
            tv_temperature = view.findViewById(R.id.tv_temperature)
            tv_wind = view.findViewById(R.id.tv_wind)
            iv_weather = view.findViewById(R.id.iv_weather)
        }
    }


}