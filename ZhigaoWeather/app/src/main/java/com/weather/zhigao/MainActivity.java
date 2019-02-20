package com.weather.zhigao;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jaeger.library.StatusBarUtil;
import com.weather.zhigao.adapter.CityAddAdapter;
import com.weather.zhigao.db.DataBaseDao;
import com.weather.zhigao.model.CityAddBean;
import com.weather.zhigao.model.WeatherForecastEntity;
import com.weather.zhigao.net.OkhttpUtil;
import com.weather.zhigao.net.ResponseCallBack;
import com.weather.zhigao.net.Urls;
import com.weather.zhigao.utils.LogUtil;
import com.weather.zhigao.utils.SPUtils;
import com.weather.zhigao.utils.ScreenUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    String TAG = "MainActivity";
    DrawerLayout mDrawerLayout;
    FrameLayout frament_content;
    RelativeLayout rl_drawer;
    HomeFragment homeFragment;
    ImageView iv_add;
    DataBaseDao dataBaseDao;
    RecyclerView city_recyclerview;
    List<CityAddBean> cityList;
    CityAddAdapter cityAddAdapter;
    TextView tv_edit;
    boolean isEdit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.setTransparent(this);
        setContentView(R.layout.activity_main);
        dataBaseDao = new DataBaseDao(this);
        homeFragment = new HomeFragment();

        mDrawerLayout = findViewById(R.id.drawer_layout);
        frament_content = findViewById(R.id.frament_content);
        rl_drawer = findViewById(R.id.rl_drawer);
        iv_add = findViewById(R.id.iv_add);
        tv_edit = findViewById(R.id.tv_edit);
        city_recyclerview = findViewById(R.id.drawer_recyclerview);

        //加入透明状态栏后，需要留出和状态栏高度一样的的空间
        int statusBarHeight = ScreenUtils.getStatusHeight2(this);
        ViewGroup.LayoutParams layoutParams = (ViewGroup.LayoutParams) rl_drawer.getLayoutParams();
        rl_drawer.setPadding(0, statusBarHeight, 0, 0);
        rl_drawer.setLayoutParams(layoutParams);

        StatusBarUtil.setColorForDrawerLayout(this, mDrawerLayout, 0);
        getSupportFragmentManager().beginTransaction().replace(R.id.frament_content, homeFragment).commit();
        iv_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, FindCityActivity.class));
            }
        });
        tv_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isEdit) {
                    cityAddAdapter.setEdit(true);
                    tv_edit.setText(getString(R.string.complete));
                    iv_add.setVisibility(View.GONE);
                    isEdit = true;
                } else {
                    cityAddAdapter.setEdit(false);
                    tv_edit.setText(getString(R.string.edit));
                    iv_add.setVisibility(View.VISIBLE);
                    isEdit = false;
                }
            }
        });

        initRecyclerView();
        WeatherForecastEntity weatherBroadcast = getIntent().getParcelableExtra("weather");
        homeFragment.setData(weatherBroadcast);

    }

    protected void onNewIntent(Intent intent) {

        super.onNewIntent(intent);
        WeatherForecastEntity weatherBroadcast = intent.getParcelableExtra("weather");
        if (weatherBroadcast != null) {
            homeFragment.initData(weatherBroadcast);
            //添加城市的时候调用，更新数据
            setRecyclerView();
        }

    }

    private void setRecyclerView() {
        cityList = dataBaseDao.queryAll();
        if (cityList != null)
            cityAddAdapter.setList(cityList);
        LogUtil.d(TAG, "cityList.size()：" + cityList.size());
    }

    private void initRecyclerView() {
        cityList = new ArrayList<>();
        cityAddAdapter = new CityAddAdapter(this, cityList);
//        city_recyclerview.addItemDecoration(new RecycleViewDivider(this, LinearLayoutManager.VERTICAL));
        city_recyclerview.setLayoutManager(new LinearLayoutManager(this));

        city_recyclerview.setAdapter(cityAddAdapter);

        setRecyclerView();

        cityAddAdapter.setOnItemClickListener(new CityAddAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int postion) {
                mDrawerLayout.closeDrawer(Gravity.LEFT);
                String location = cityList.get(postion).getLocation();
                SPUtils.setParam(MainActivity.this, "currentCity", location);
                getWeatherInfo(location);
                cityAddAdapter.notifyDataSetChanged();

            }

            @Override
            public void onItemRemove(View view, int postion) {
                String location = cityList.get(postion).getLocation();
                cityList.remove(postion);
                cityAddAdapter.notifyDataSetChanged();
                dataBaseDao.delete(location);
            }
        });
    }

    // 用来计算返回键的点击间隔时间
    private long exitTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN) {
            if (mDrawerLayout.isDrawerOpen(Gravity.LEFT)) {
                mDrawerLayout.closeDrawer(Gravity.LEFT);
                return false;
            }

            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(getApplicationContext(), getString(R.string.press_exit), Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
            }
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    public void openDrawer() {
        mDrawerLayout.openDrawer(Gravity.LEFT);  //便可以实现弹出抽屉界面
    }

    //提前获取城市天气信息
    private void getWeatherInfo(String location) {
        Map<String, String> params = new HashMap<>();
        params.put("location", location);
        OkhttpUtil.getInstance(this).getDataAsync(Urls.url_weather, params, new ResponseCallBack() {
            @Override
            public void onFailure(String error) {

            }

            @Override
            public void onResponse(String response) {
                final WeatherForecastEntity weatherBroadcast = new Gson().fromJson(response, WeatherForecastEntity.class);
                homeFragment.initData(weatherBroadcast);

            }
        });
    }
}
