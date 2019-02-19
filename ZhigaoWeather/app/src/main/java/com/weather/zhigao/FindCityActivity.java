package com.weather.zhigao;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jaeger.library.StatusBarUtil;
import com.weather.zhigao.adapter.HotCityAdapter;
import com.weather.zhigao.db.DataBaseDao;
import com.weather.zhigao.model.WeatherForecastEntity.HeWeather6Bean.DailyForecastBean;
import com.weather.zhigao.adapter.SearchCityAdapter;
import com.weather.zhigao.adapter.divider.RecycleViewDivider;
import com.weather.zhigao.model.CityAddBean;
import com.weather.zhigao.model.HotCityEntity;
import com.weather.zhigao.model.SearchCityEntity;
import com.weather.zhigao.model.WeatherForecastEntity;
import com.weather.zhigao.net.OkhttpUtil;
import com.weather.zhigao.net.ResponseCallBack;
import com.weather.zhigao.net.Urls;
import com.weather.zhigao.utils.KeyboardUtils;
import com.weather.zhigao.utils.LogUtil;
import com.weather.zhigao.utils.SPUtils;
import com.weather.zhigao.utils.ScreenUtils;

import java.security.Key;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindCityActivity extends AppCompatActivity {

    List<HotCityEntity.HeWeather6Bean.BasicBean> hotCityList;
    List<SearchCityEntity.HeWeather6Bean.BasicBean> searchCityList;
    HotCityAdapter hotCityAdapter;
    SearchCityAdapter searchCityAdapter;
    RecyclerView city_recyclerview, search_city_recyclerview;
    EditText et_search;
    RelativeLayout rl_title;
    ImageView iv_back;
    TextView tv_cancel;
    boolean hasChoosenCity = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.setTransparent(this);
        setContentView(R.layout.activity_find_city);
        city_recyclerview = findViewById(R.id.city_recyclerview);
        search_city_recyclerview = findViewById(R.id.search_city_recyclerview);
        rl_title = findViewById(R.id.rl_title);
        iv_back = findViewById(R.id.iv_back);
        et_search = findViewById(R.id.et_search);
        tv_cancel = findViewById(R.id.tv_cancel);

        //加入透明状态栏后，需要留出和状态栏高度一样的的空间
        int statusBarHeight = ScreenUtils.getStatusHeight2(this);
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) rl_title.getLayoutParams();
        rl_title.setPadding(0, statusBarHeight, 0, 0);
        rl_title.setLayoutParams(layoutParams);

        String response = (String) SPUtils.getParam(this, "hotcity", "");

        initHotCityRecyclerView();
        initSearchCityRecyclerView();

        HotCityEntity hotCityEntity = new Gson().fromJson(response, HotCityEntity.class);
        hotCityList = hotCityEntity.getHeWeather6().get(0).getBasic();
        hotCityAdapter.setList(hotCityList);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        et_search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {//搜索按键action

                    String searchLocation = et_search.getText().toString();
                    if (searchLocation.trim().length() == 0) {
                        Toast.makeText(FindCityActivity.this, "请输入搜索内容", Toast.LENGTH_SHORT).show();
                        return false;
                    }
                    beginSearch(searchLocation);
                }
                return false;
            }
        });

        et_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String content = charSequence.toString();
                if(content.trim().length()>0)
                    tv_cancel.setVisibility(View.VISIBLE);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

tv_cancel.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        et_search.setText("");
        search_city_recyclerview.setVisibility(View.GONE);
        searchCityList.clear();
        KeyboardUtils.hideKeyboard(et_search);
    }
});
    }

    private void initHotCityRecyclerView() {
        hotCityList = new ArrayList<>();
        hotCityAdapter = new HotCityAdapter(this, hotCityList);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        city_recyclerview.setLayoutManager(gridLayoutManager);
        city_recyclerview.setAdapter(hotCityAdapter);
        hotCityAdapter.setOnItemClickListener(new HotCityAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int postion) {
                String location = hotCityList.get(postion).getLocation();
                SPUtils.setParam(FindCityActivity.this, "currentCity", location);
                getWeatherInfoAdvance(location);
            }
        });
    }

    private void initSearchCityRecyclerView() {
        searchCityList = new ArrayList<>();
        searchCityAdapter = new SearchCityAdapter(this, searchCityList);
        search_city_recyclerview.addItemDecoration(new RecycleViewDivider(this, LinearLayoutManager.VERTICAL));
        search_city_recyclerview.setLayoutManager(new LinearLayoutManager(this));
        search_city_recyclerview.setAdapter(searchCityAdapter);
        searchCityAdapter.setOnItemClickListener(new SearchCityAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int postion) {
                String location = searchCityList.get(postion).getLocation();
                SPUtils.setParam(FindCityActivity.this, "currentCity", location);
                getWeatherInfoAdvance(location);
            }
        });
    }

    //提前获取城市天气信息，这样进入主界面直接获取传递过来的数据，而不用从网络上获取，显示速度快，用户体验好
    private void getWeatherInfoAdvance(String location) {
        if (hasChoosenCity) return;
        Map<String, String> params = new HashMap<>();
        params.put("location", location);
        OkhttpUtil.getInstance(this).getDataAsync(Urls.url_weather, params, new ResponseCallBack() {
            @Override
            public void onFailure(String error) {

            }

            @Override
            public void onResponse(String response) {
                final WeatherForecastEntity weatherBroadcast = new Gson().fromJson(response, WeatherForecastEntity.class);

                Intent intent = new Intent(FindCityActivity.this, MainActivity.class);
                intent.putExtra("weather", weatherBroadcast);
                startActivity(intent);
                inserData(weatherBroadcast);
                finish();

            }
        });
        hasChoosenCity = true;
    }

    private void inserData(WeatherForecastEntity weatherBroadcast) {
        String location = weatherBroadcast.getHeWeather6().get(0).getBasic().getLocation();

        String cond_txt = weatherBroadcast.getHeWeather6().get(0).getNow().cond_txt;
        DailyForecastBean bean = weatherBroadcast.getHeWeather6().get(0).getDaily_forecast().get(0);
        String tmp_min = bean.tmp_min;
        String tmp_max = bean.tmp_max;
        CityAddBean cityAddBean = new CityAddBean(location, cond_txt, tmp_min, tmp_max);
        DataBaseDao dataBaseDao = new DataBaseDao(this);
        if (dataBaseDao != null)
            dataBaseDao.insert(cityAddBean);


    }
    //提前获取城市天气信息，这样进入主界面直接获取传递过来的数据，而不用从网络上获取，显示速度快，用户体验好
    private void beginSearch(String location) {
        KeyboardUtils.hideKeyboard(et_search);
        Map<String, String> params = new HashMap<>();
        params.put("location", location);
        OkhttpUtil.getInstance(this).getDataAsync(Urls.url_find_city, params, new ResponseCallBack() {
            @Override
            public void onFailure(String error) {

            }

            @Override
            public void onResponse(String response) {
                final SearchCityEntity searchCityEntity = new Gson().fromJson(response, SearchCityEntity.class);
                searchCityList = searchCityEntity.getHeWeather6().get(0).getBasic();
                if (searchCityList==null||searchCityList.size() == 0) {
                    Toast.makeText(FindCityActivity.this, "城市不存在，请重新输入", Toast.LENGTH_SHORT).show();
                    return;
                }
                search_city_recyclerview.setVisibility(View.VISIBLE);
                searchCityAdapter.setList(searchCityList);

            }
        });
    }
}
