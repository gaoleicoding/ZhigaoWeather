package com.weather.zhigao.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.weather.zhigao.R;
import com.weather.zhigao.SplashActivity;
import com.weather.zhigao.application.App;
import com.weather.zhigao.model.CityAddBean;
import com.weather.zhigao.utils.SPUtils;

import java.util.List;


public class CityAddAdapter extends RecyclerView.Adapter<CityAddAdapter.MyViewHolder> {

    public Context context;

    public List<CityAddBean> list;

    private boolean isEdit = false;


    public CityAddAdapter(Context context, List<CityAddBean> list) {
        this.context = context;
        this.list = list;

    }

    public void setList(List<CityAddBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public void setEdit(boolean isEdit) {
        this.isEdit = isEdit;
        notifyDataSetChanged();
    }

    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_city_add, null);
        MyViewHolder holder = new MyViewHolder(view);

        return holder;
    }

    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final CityAddBean bean = list.get(position);
        final String currentCity = (String) SPUtils.getParam(context, "currentCity", "");

        if (currentCity.equals(bean.getLocation())) {
            holder.root_view.setBackgroundColor(Color.parseColor("#ecf5fa"));
        } else holder.root_view.setBackgroundColor(Color.parseColor("#f3f3f3"));
        holder.tv_location.setText(bean.location);
        holder.tv_temperature.setText(bean.tmp_min + "~" + bean.tmp_max + " ℃");
        holder.iv_weather.setImageResource(getWeatherIconId(bean.cond_txt));
        if (isEdit && !currentCity.equals(bean.getLocation()))//如果是正在编辑状态，且不是正在显示的城市
            holder.iv_del.setVisibility(View.VISIBLE);
        else holder.iv_del.setVisibility(View.GONE);

        holder.iv_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemRemove(holder.root_view, position);
            }
        });
        holder.root_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(holder.root_view, position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_location, tv_temperature;
        ImageView iv_weather, iv_del;
        RelativeLayout root_view;

        public MyViewHolder(View view) {
            super(view);
            tv_location = view.findViewById(R.id.tv_location);
            tv_temperature = view.findViewById(R.id.tv_temperature);
            iv_weather = view.findViewById(R.id.iv_weather);
            iv_del = view.findViewById(R.id.iv_del);
            root_view = view.findViewById(R.id.root_view);
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

    OnItemClickListener listener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener {
        public void onItemClick(View view, int postion);

        public void onItemRemove(View view, int postion);
    }
}