package com.weather.zhigao.adapter;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
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
import com.weather.zhigao.model.WeatherForecastEntity.HeWeather6Bean.LifestyleBean;

import java.util.List;


public class LifeStyleAdapter extends RecyclerView.Adapter<LifeStyleAdapter.MyViewHolder> {

    public Context context;

    public List<LifestyleBean> list;


    public LifeStyleAdapter(Context context, List<LifestyleBean> list) {
        this.context = context;
        this.list = list;

    }

    public void setList(List<LifestyleBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_lifestyle, null);
        MyViewHolder holder = new MyViewHolder(view);

        return holder;
    }

    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final LifestyleBean bean = list.get(position);

        holder.tv_title.setText(getStyleIndicator(bean.getType()));
        holder.tv_content.setText(bean.getBrf());
        holder.iv_type.setImageResource(getTypeIconId(bean.getType()));
        holder.rl_root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPop(bean);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_title, tv_content;
        ImageView iv_type;
        RelativeLayout rl_root;

        public MyViewHolder(View view) {
            super(view);
            tv_title = view.findViewById(R.id.tv_title);
            tv_content = view.findViewById(R.id.tv_content);
            iv_type = view.findViewById(R.id.iv_type);
            rl_root = view.findViewById(R.id.rl_root);
        }
    }

    private String getStyleIndicator(final String style) {
        if ("comf".equals(style)) {
            return "舒适度指数";
        }
        if ("cw".equals(style)) {
            return "洗车指数";
        }
        if ("drsg".equals(style)) {
            return "穿衣指数";
        }
        if ("flu".equals(style)) {
            return "感冒指数";
        }
        if ("sport".equals(style)) {
            return "运动指数";
        }
        if ("trav".equals(style)) {
            return "旅游指数";
        }
        if ("uv".equals(style)) {
            return "紫外线指数";
        }
        if ("air".equals(style)) {
            return "空气质量指数";
        }

        return "";
    }

    private int getTypeIconId(final String style) {
        if ("comf".equals(style)) {
            return R.mipmap.icon_lifestyle_comfort;
        }
        if ("cw".equals(style)) {
            return R.mipmap.icon_lifestyle_wash;
        }
        if ("drsg".equals(style)) {
            return R.mipmap.icon_lifestyle_dress;
        }
        if ("flu".equals(style)) {
            return R.mipmap.icon_lifestyle_drug;
        }
        if ("sport".equals(style)) {
            return R.mipmap.icon_lifestyle_sport;
        }
        if ("trav".equals(style)) {
            return R.mipmap.icon_lifestyle_travel;
        }
        if ("uv".equals(style)) {
            return R.mipmap.icon_lifestyle_rays;
        }
        if ("air".equals(style)) {
            return R.mipmap.icon_lifestyle_air;
        }

        return R.mipmap.icon_lifestyle_comfort;
    }

    private int getTypeBigIconId(final String style) {
        if ("comf".equals(style)) {
            return R.mipmap.icon_lifestyle_comfort_big;
        }
        if ("cw".equals(style)) {
            return R.mipmap.icon_lifestyle_wash_big;
        }
        if ("drsg".equals(style)) {
            return R.mipmap.icon_lifestyle_dress_big;
        }
        if ("flu".equals(style)) {
            return R.mipmap.icon_lifestyle_drug_big;
        }
        if ("sport".equals(style)) {
            return R.mipmap.icon_lifestyle_sport_big;
        }
        if ("trav".equals(style)) {
            return R.mipmap.icon_lifestyle_travel_big;
        }
        if ("uv".equals(style)) {
            return R.mipmap.icon_lifestyle_rays_big;
        }
        if ("air".equals(style)) {
            return R.mipmap.icon_lifestyle_air_big;
        }

        return R.mipmap.icon_lifestyle_comfort_big;
    }


    /**
     * 弹出底部对话框
     */
    public void openPop(LifestyleBean bean) {
        View popView = LayoutInflater.from(context).inflate(
                R.layout.lifestyle_item_layout, null);
        View rootView = popView.findViewById(R.id.root_main);
        ImageView iv_type, iv_close;
        TextView tv_title, tv_content, tv_lifestyle_detail;
        iv_type = popView.findViewById(R.id.iv_type);
        iv_close = popView.findViewById(R.id.iv_close);
        tv_title = popView.findViewById(R.id.tv_title);
        tv_content = popView.findViewById(R.id.tv_content);
        tv_lifestyle_detail = popView.findViewById(R.id.tv_lifestyle_detail);
        iv_type.setImageResource(getTypeBigIconId(bean.getType()));
        tv_title.setText(getStyleIndicator(bean.getType()));
        tv_content.setText(bean.getBrf());
        tv_lifestyle_detail.setText(bean.getTxt());


        final PopupWindow popupWindow = new PopupWindow(popView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setOutsideTouchable(false);
        popupWindow.setFocusable(true);// 点击空白处时，隐藏掉pop窗口
        popupWindow.showAtLocation(rootView, Gravity.BOTTOM, 0,
                0);
        iv_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });

    }

}