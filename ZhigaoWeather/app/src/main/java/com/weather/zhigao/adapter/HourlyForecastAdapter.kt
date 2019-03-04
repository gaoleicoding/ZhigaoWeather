package com.weather.zhigao.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import com.weather.zhigao.R
import com.weather.zhigao.model.WeatherForecastEntity
import com.weather.zhigao.model.weather.HourlyBean
import com.weather.zhigao.utils.Weather2IconUtil


class HourlyForecastAdapter(var context: Context, var list: List<HourlyBean?>) : RecyclerView.Adapter<HourlyForecastAdapter.MyViewHolder>() {

    fun changeList(list: List<HourlyBean>) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val view = LayoutInflater.from(context).inflate(R.layout.item_hourly_forecast, null)

        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val bean = list[position]

        holder.tv_time.setText(bean?.time!!.split(" ".toRegex()).dropLastWhile({ it.isEmpty() }).toTypedArray()[1])
        holder.tv_temperature.text = bean?.tmp + " ℃"
        holder.iv_weather.setImageResource(Weather2IconUtil.getWeatherIconId(bean.cond_txt))
    }

    override fun getItemCount(): Int {
        return list.size
    }


    public inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var tv_time: TextView
        var tv_temperature: TextView
        var iv_weather: ImageView

        init {
            tv_time = view.findViewById(R.id.tv_time)
            tv_temperature = view.findViewById(R.id.tv_temperature)
            iv_weather = view.findViewById(R.id.iv_weather)
        }
    }


}