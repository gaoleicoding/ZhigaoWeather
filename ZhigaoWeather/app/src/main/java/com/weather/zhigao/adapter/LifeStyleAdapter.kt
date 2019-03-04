package com.weather.zhigao.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.PopupWindow
import android.widget.RelativeLayout
import android.widget.TextView

import com.weather.zhigao.R
import com.weather.zhigao.model.WeatherForecastEntity
import com.weather.zhigao.model.WeatherForecastEntity.HeWeather6Bean.LifestyleBean


class LifeStyleAdapter(var context: Context, var list: List<LifestyleBean>) : RecyclerView.Adapter<LifeStyleAdapter.MyViewHolder>() {

    fun changeList(list: List<LifestyleBean>) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val view = LayoutInflater.from(context).inflate(R.layout.item_lifestyle, null)

        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val bean = list[position]

        holder.tv_title.text = getStyleIndicator(bean.type)
        holder.tv_content.text = bean.brf
        holder.iv_type.setImageResource(getTypeIconId(bean.type))
        holder.rl_root.setOnClickListener { openPop(bean) }
    }

    override fun getItemCount(): Int {
        return list.size
    }


    public inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var tv_title: TextView
        var tv_content: TextView
        var iv_type: ImageView
        var rl_root: RelativeLayout

        init {
            tv_title = view.findViewById(R.id.tv_title)
            tv_content = view.findViewById(R.id.tv_content)
            iv_type = view.findViewById(R.id.iv_type)
            rl_root = view.findViewById(R.id.rl_root)
        }
    }

    private fun getStyleIndicator(style: String): String {
        if ("comf" == style) {
            return "舒适度指数"
        }
        if ("cw" == style) {
            return "洗车指数"
        }
        if ("drsg" == style) {
            return "穿衣指数"
        }
        if ("flu" == style) {
            return "感冒指数"
        }
        if ("sport" == style) {
            return "运动指数"
        }
        if ("trav" == style) {
            return "旅游指数"
        }
        if ("uv" == style) {
            return "紫外线指数"
        }
        return if ("air" == style) {
            "空气质量指数"
        } else ""

    }

    private fun getTypeIconId(style: String): Int {
        if ("comf" == style) {
            return R.mipmap.icon_lifestyle_comfort
        }
        if ("cw" == style) {
            return R.mipmap.icon_lifestyle_wash
        }
        if ("drsg" == style) {
            return R.mipmap.icon_lifestyle_dress
        }
        if ("flu" == style) {
            return R.mipmap.icon_lifestyle_drug
        }
        if ("sport" == style) {
            return R.mipmap.icon_lifestyle_sport
        }
        if ("trav" == style) {
            return R.mipmap.icon_lifestyle_travel
        }
        if ("uv" == style) {
            return R.mipmap.icon_lifestyle_rays
        }
        return if ("air" == style) {
            R.mipmap.icon_lifestyle_air
        } else R.mipmap.icon_lifestyle_comfort

    }

    private fun getTypeBigIconId(style: String): Int {
        if ("comf" == style) {
            return R.mipmap.icon_lifestyle_comfort_big
        }
        if ("cw" == style) {
            return R.mipmap.icon_lifestyle_wash_big
        }
        if ("drsg" == style) {
            return R.mipmap.icon_lifestyle_dress_big
        }
        if ("flu" == style) {
            return R.mipmap.icon_lifestyle_drug_big
        }
        if ("sport" == style) {
            return R.mipmap.icon_lifestyle_sport_big
        }
        if ("trav" == style) {
            return R.mipmap.icon_lifestyle_travel_big
        }
        if ("uv" == style) {
            return R.mipmap.icon_lifestyle_rays_big
        }
        return if ("air" == style) {
            R.mipmap.icon_lifestyle_air_big
        } else R.mipmap.icon_lifestyle_comfort_big

    }


    /**
     * 弹出底部对话框
     */
    fun openPop(bean: WeatherForecastEntity.HeWeather6Bean.LifestyleBean) {
        val popView = LayoutInflater.from(context).inflate(
                R.layout.lifestyle_item_layout, null)
        val rootView = popView.findViewById<View>(R.id.root_main)
        val iv_type: ImageView
        val iv_close: ImageView
        val tv_title: TextView
        val tv_content: TextView
        val tv_lifestyle_detail: TextView
        iv_type = popView.findViewById(R.id.iv_type)
        iv_close = popView.findViewById(R.id.iv_close)
        tv_title = popView.findViewById(R.id.tv_title)
        tv_content = popView.findViewById(R.id.tv_content)
        tv_lifestyle_detail = popView.findViewById(R.id.tv_lifestyle_detail)
        iv_type.setImageResource(getTypeBigIconId(bean.type))
        tv_title.text = getStyleIndicator(bean.type)
        tv_content.text = bean.brf
        tv_lifestyle_detail.text = bean.txt


        val popupWindow = PopupWindow(popView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        popupWindow.isOutsideTouchable = false
        popupWindow.isFocusable = true// 点击空白处时，隐藏掉pop窗口
        popupWindow.showAtLocation(rootView, Gravity.BOTTOM, 0,
                0)
        iv_close.setOnClickListener { popupWindow.dismiss() }

    }

}