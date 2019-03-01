package com.weather.zhigao.adapter

import android.content.Context
import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView

import com.weather.zhigao.R
import com.weather.zhigao.SplashActivity
import com.weather.zhigao.application.App
import com.weather.zhigao.model.CityAddBean
import com.weather.zhigao.utils.SPUtils


class CityAddAdapter(var context: Context, var list: List<CityAddBean>) : RecyclerView.Adapter<CityAddAdapter.MyViewHolder>() {

    private var isEdit = false

    internal var listener: OnItemClickListener?=null

    fun changeList(list: List<CityAddBean>) {
        this.list = list
        notifyDataSetChanged()
    }

    fun setEdit(isEdit: Boolean) {
        this.isEdit = isEdit
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val view = LayoutInflater.from(context).inflate(R.layout.item_city_add, null)

        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val bean = list[position]
        val currentCity = SPUtils.getParam(context, "currentCity", "") as String

        if (currentCity == bean.getLocation()) {
            holder.root_view.setBackgroundColor(Color.parseColor("#ecf5fa"))
        } else
            holder.root_view.setBackgroundColor(Color.parseColor("#f3f3f3"))
        holder.tv_location.text = bean.location
        holder.tv_temperature.text = bean.tmp_min + "~" + bean.tmp_max + " ℃"
        holder.iv_weather.setImageResource(getWeatherIconId(bean.cond_txt))
        if (isEdit && currentCity != bean.getLocation())
        //如果是正在编辑状态，且不是正在显示的城市
            holder.iv_del.visibility = View.VISIBLE
        else
            holder.iv_del.visibility = View.GONE

        holder.iv_del.setOnClickListener { listener?.onItemRemove(holder.root_view, position) }
        holder.root_view.setOnClickListener { listener?.onItemClick(holder.root_view, position) }

    }

    override fun getItemCount(): Int {
        return list.size
    }


     public inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var tv_location: TextView
        var tv_temperature: TextView
        var iv_weather: ImageView
        var iv_del: ImageView
        var root_view: RelativeLayout

        init {
            tv_location = view.findViewById(R.id.tv_location)
            tv_temperature = view.findViewById(R.id.tv_temperature)
            iv_weather = view.findViewById(R.id.iv_weather)
            iv_del = view.findViewById(R.id.iv_del)
            root_view = view.findViewById(R.id.root_view)
        }
    }

    private fun getWeatherIconId(desc: String): Int {
        if (!TextUtils.isEmpty(desc)) {
            if (desc.equals(App.mContext.getString(R.string.sunny), ignoreCase = true)) {
                return R.drawable.iclockweather_w1
            } else if (desc.equals(App.mContext.getString(R.string.partly_cloudy), ignoreCase = true)
                    || desc.equals(App.mContext.getString(R.string.cloudy), ignoreCase = true)
                    || desc.equals(App.mContext.getString(R.string.few_cloud), ignoreCase = true)) {
                return R.drawable.iclockweather_w2
            } else if (desc.equals(App.mContext.getString(R.string.overcast), ignoreCase = true)) {
                return R.drawable.iclockweather_w3
            } else if (desc.equals(App.mContext.getString(R.string.shower_rain), ignoreCase = true)) {
                return R.drawable.iclockweather_w8
            } else if (desc.equals(App.mContext.getString(R.string.heavy_shower_rain), ignoreCase = true)) {
                return R.drawable.iclockweather_w8
            } else if (desc.equals(App.mContext.getString(R.string.thunder_shower), ignoreCase = true) || desc.equals(App.mContext.getString(R.string.heavy_thunderstorm), ignoreCase = true)) {
                return R.drawable.iclockweather_w9
            } else if (desc.equals(App.mContext.getString(R.string.hail), ignoreCase = true)) {
                return R.drawable.iclockweather_w18
            } else if (desc.equals(App.mContext.getString(R.string.light_rain), ignoreCase = true)
                    || desc.equals(App.mContext.getString(R.string.drizzle_rain), ignoreCase = true)
                    || desc.equals(App.mContext.getString(R.string.drizzle_rain_1), ignoreCase = true)) {
                return R.drawable.iclockweather_w4
            } else if (desc.equals(App.mContext.getString(R.string.moderate_rain), ignoreCase = true)) {
                return R.drawable.iclockweather_w5
            } else if (desc.equals(App.mContext.getString(R.string.heavy_rain), ignoreCase = true) || desc.equals(App.mContext.getString(R.string.storm), ignoreCase = true)) {
                return R.drawable.iclockweather_w6
            } else if (desc.equals(App.mContext.getString(R.string.extreme_rain), ignoreCase = true)
                    || desc.equals(App.mContext.getString(R.string.heavy_storm), ignoreCase = true)
                    || desc.equals(App.mContext.getString(R.string.severe_storm), ignoreCase = true)) {
                return R.drawable.iclockweather_w7
            } else if (desc.equals(App.mContext.getString(R.string.freezing_rain), ignoreCase = true)) {
                return R.drawable.iclockweather_w15
            } else if (desc.equals(App.mContext.getString(R.string.light_snow), ignoreCase = true) || desc.equals(App.mContext.getString(R.string.snow_flurry), ignoreCase = true)) {
                return R.drawable.iclockweather_w11
            } else if (desc.equals(App.mContext.getString(R.string.moderate_snow), ignoreCase = true)) {
                return R.drawable.iclockweather_w12
            } else if (desc.equals(App.mContext.getString(R.string.heavy_snow), ignoreCase = true)) {
                return R.drawable.iclockweather_w13
            } else if (desc.equals(App.mContext.getString(R.string.snow_storm), ignoreCase = true)) {
                return R.drawable.iclockweather_w14
            } else if (desc.equals(App.mContext.getString(R.string.sleet), ignoreCase = true)
                    || desc.equals(App.mContext.getString(R.string.rain_snow), ignoreCase = true)
                    || desc.equals(App.mContext.getString(R.string.shower_snow), ignoreCase = true)) {
                return R.drawable.iclockweather_w10
            } else if (desc.equals(App.mContext.getString(R.string.mist), ignoreCase = true) || desc.equals(App.mContext.getString(R.string.foggy), ignoreCase = true)) {
                return R.drawable.iclockweather_w16
            } else if (desc.equals(App.mContext.getString(R.string.haze), ignoreCase = true)
                    || desc.equals(App.mContext.getString(R.string.sand), ignoreCase = true)
                    || desc.equals(App.mContext.getString(R.string.dust), ignoreCase = true)
                    || desc.equals(App.mContext.getString(R.string.volcanic_ash), ignoreCase = true)
                    || desc.equals(App.mContext.getString(R.string.dust_storm), ignoreCase = true)
                    || desc.equals(App.mContext.getString(R.string.sand_storm), ignoreCase = true)) {
                return R.drawable.iclockweather_w17
            }
        }

        return R.drawable.iclockweather_w2
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    interface OnItemClickListener {
        fun onItemClick(view: View, postion: Int)

        fun onItemRemove(view: View, postion: Int)
    }
}