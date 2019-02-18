package com.weather.zhigao.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.weather.zhigao.R;
import com.weather.zhigao.model.SearchCityEntity;
import com.weather.zhigao.model.SearchCityEntity.HeWeather6Bean.BasicBean;

import java.util.List;


public class SearchCityAdapter extends RecyclerView.Adapter<SearchCityAdapter.MyViewHolder> {

    public Context context;

    public List<BasicBean> list;
    OnItemClickListener listener;

    public SearchCityAdapter(Context context, List<BasicBean> list) {
        this.context = context;
        this.list = list;

    }

    public void setList(List<BasicBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_search_city, null);
        MyViewHolder holder = new MyViewHolder(view);

        return holder;
    }

    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final BasicBean bean = list.get(position);

        holder.tv_city.setText(bean.getLocation()+"-"+bean.getAdmin_area());
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