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
import com.weather.zhigao.model.HotCityEntity
import com.weather.zhigao.model.HotCityEntity.HeWeather6Bean.BasicBean


class HotCityAdapter(var context: Context, var list: List<BasicBean>) : RecyclerView.Adapter<HotCityAdapter.MyViewHolder>() {
    internal var listener: OnItemClickListener?=null

    fun changeList(list: List<BasicBean>) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val view = LayoutInflater.from(context).inflate(R.layout.item_hot_city, null)

        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val bean = list[position]

        holder.tv_city.text = bean.location
        holder.root_view.setOnClickListener { listener?.onItemClick(holder.root_view, position) }

    }

    override fun getItemCount(): Int {
        return list.size
    }


    public inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var tv_city: TextView
        var root_view: RelativeLayout


        init {
            tv_city = view.findViewById(R.id.tv_city)
            root_view = view.findViewById(R.id.root_view)

        }
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    interface OnItemClickListener {
        fun onItemClick(view: View, postion: Int)
    }

}