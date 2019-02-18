package com.weather.zhigao.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.weather.zhigao.R;
import com.weather.zhigao.model.HotCityEntity;
import com.weather.zhigao.model.HotCityEntity.HeWeather6Bean.BasicBean;

import java.util.List;


public class HotCityAdapter extends RecyclerView.Adapter<HotCityAdapter.MyViewHolder> {

    public Context context;

    public List<BasicBean> list;
    OnItemClickListener listener;

    public HotCityAdapter(Context context, List<BasicBean> list) {
        this.context = context;
        this.list = list;

    }

    public void setList(List<BasicBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_hot_city, null);
        MyViewHolder holder = new MyViewHolder(view);

        return holder;
    }

    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final BasicBean bean = list.get(position);

        holder.tv_city.setText(bean.getLocation());
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
        TextView tv_city;
        RelativeLayout root_view;


        public MyViewHolder(View view) {
            super(view);
            tv_city = view.findViewById(R.id.tv_city);
            root_view = view.findViewById(R.id.root_view);

        }
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener {
        public void onItemClick(View view, int postion);
    }

}