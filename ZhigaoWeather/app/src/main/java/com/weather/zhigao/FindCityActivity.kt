package com.weather.zhigao

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast

import com.google.gson.Gson
import com.jaeger.library.StatusBarUtil
import com.weather.zhigao.adapter.HotCityAdapter
import com.weather.zhigao.db.DatabaseManager
import com.weather.zhigao.model.WeatherForecastEntity.HeWeather6Bean.DailyForecastBean
import com.weather.zhigao.adapter.SearchCityAdapter
import com.weather.zhigao.adapter.divider.RecycleViewDivider
import com.weather.zhigao.model.HotCityEntity
import com.weather.zhigao.model.SearchCityEntity
import com.weather.zhigao.model.WeatherForecastEntity
import com.weather.zhigao.net.OkhttpUtil
import com.weather.zhigao.net.ResponseCallBack
import com.weather.zhigao.net.Urls
import com.weather.zhigao.utils.KeyboardUtils
import com.weather.zhigao.utils.SPUtils
import com.weather.zhigao.utils.ScreenUtils

import java.util.ArrayList
import java.util.HashMap

class FindCityActivity : AppCompatActivity() {

    lateinit var hotCityList: List<HotCityEntity.HeWeather6Bean.BasicBean>
    lateinit var searchCityList: MutableList<SearchCityEntity.HeWeather6Bean.BasicBean>
    lateinit var hotCityAdapter: HotCityAdapter
    lateinit var searchCityAdapter: SearchCityAdapter
    lateinit var city_recyclerview: RecyclerView
    lateinit var search_city_recyclerview: RecyclerView
    lateinit var et_search: EditText
    lateinit var rl_title: RelativeLayout
    lateinit var iv_back: ImageView
    lateinit var tv_cancel: TextView
    var hasChoosenCity = false
    var  TAG="FindCityActivity"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        StatusBarUtil.setTransparent(this)
        setContentView(R.layout.activity_find_city)
        city_recyclerview = findViewById(R.id.city_recyclerview)
        search_city_recyclerview = findViewById(R.id.search_city_recyclerview)
        rl_title = findViewById(R.id.rl_title)
        iv_back = findViewById(R.id.iv_back)
        et_search = findViewById(R.id.et_search)
        tv_cancel = findViewById(R.id.tv_cancel)

        //加入透明状态栏后，需要留出和状态栏高度一样的的空间
        val statusBarHeight = ScreenUtils.getStatusHeight2(this)
        val layoutParams = rl_title.layoutParams as RelativeLayout.LayoutParams
        rl_title.setPadding(0, statusBarHeight, 0, 0)
        rl_title.layoutParams = layoutParams

        val response = SPUtils.getParam(this, "hotcity", "") as String

        initHotCityRecyclerView()
        initSearchCityRecyclerView()
//        val searchCityEntity = Gson().fromJson(response, SearchCityEntity::class.java)
//        searchCityList = searchCityEntity.heWeather6!![0].basic
//        if (searchCityList == null || searchCityList!!.size == 0) {
//            Toast.makeText(this@FindCityActivity, "城市不存在，请重新输入", Toast.LENGTH_SHORT).show()
//            return
//        }
//        search_city_recyclerview.visibility = View.VISIBLE
//        searchCityAdapter.list = searchCityList
        Log.d(TAG,"response------------"+response);
        val hotCityEntity = Gson().fromJson(response, HotCityEntity::class.java)
//        if (hotCityEntity.heWeather6 != null)
            hotCityList = hotCityEntity.HeWeather6!![0].basic
        hotCityAdapter.list = hotCityList
        iv_back.setOnClickListener { finish() }

        et_search.setOnEditorActionListener(TextView.OnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {//搜索按键action

                val searchLocation = et_search.text.toString()
                if (searchLocation.trim { it <= ' ' }.length == 0) {
                    Toast.makeText(this@FindCityActivity, "请输入搜索内容", Toast.LENGTH_SHORT).show()
                    return@OnEditorActionListener false
                }
                beginSearch(searchLocation)
            }
            false
        })

        et_search.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {

            }

            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                val content = charSequence.toString()
                if (content.trim { it <= ' ' }.length > 0)
                    tv_cancel.visibility = View.VISIBLE
            }

            override fun afterTextChanged(editable: Editable) {

            }
        })

        tv_cancel.setOnClickListener {
            et_search.setText("")
            search_city_recyclerview.visibility = View.GONE
            searchCityList!!.clear()
            KeyboardUtils.hideKeyboard(et_search)
        }
    }

    private fun initHotCityRecyclerView() {
        hotCityList = ArrayList()
        hotCityAdapter = HotCityAdapter(this, hotCityList)
        val gridLayoutManager = GridLayoutManager(this, 3)
        city_recyclerview.layoutManager = gridLayoutManager
        city_recyclerview.adapter = hotCityAdapter
        hotCityAdapter.setOnItemClickListener(object : HotCityAdapter.OnItemClickListener {
            override fun onItemClick(view: View, postion: Int) {
                val location= hotCityList[postion].location
                SPUtils.setParam(this@FindCityActivity, "currentCity", location as Any)
                getWeatherInfoAdvance(location)
            }
        })
    }

    private fun initSearchCityRecyclerView() {
        searchCityList = ArrayList<SearchCityEntity.HeWeather6Bean.BasicBean>()
        searchCityAdapter = SearchCityAdapter(this, searchCityList!!)
        search_city_recyclerview.addItemDecoration(RecycleViewDivider(this, LinearLayoutManager.VERTICAL))
        search_city_recyclerview.layoutManager = LinearLayoutManager(this)
        search_city_recyclerview.adapter = searchCityAdapter
        searchCityAdapter.setOnItemClickListener(object : SearchCityAdapter.OnItemClickListener {
            override fun onItemClick(view: View, postion: Int) {
                val location = searchCityList!![postion].location
                SPUtils.setParam(this@FindCityActivity, "currentCity", location!!)
                getWeatherInfoAdvance(location)
            }
        })
    }

    //提前获取城市天气信息，这样进入主界面直接获取传递过来的数据，而不用从网络上获取，显示速度快，用户体验好
    private fun getWeatherInfoAdvance(location: String?) {
        if (hasChoosenCity) return
        val params = HashMap<String, String?>()
        params["location"] = location
        OkhttpUtil.getInstance(this)!!.getDataAsync(Urls.url_weather, params, object : ResponseCallBack {
            override fun onFailure(error: String) {

            }

            override fun onResponse(response: String) {
                val weatherBroadcast = Gson().fromJson(response, WeatherForecastEntity::class.java)

                val intent = Intent(this@FindCityActivity, MainActivity::class.java)
                intent.putExtra("weather", weatherBroadcast)
                var HeWeather6_size=weatherBroadcast.HeWeather6!!.size
                Log.d(TAG,"weatherBroadcast.HeWeather6!!.size--------------------"+HeWeather6_size)
                var daily_forecast_size=weatherBroadcast.HeWeather6!!.get(0).daily_forecast.size
                Log.d(TAG,"weatherBroadcast.HeWeather6!!.get(0).daily_forecast.size--------------------"+daily_forecast_size)
                startActivity(intent)
                inserData(weatherBroadcast)
                finish()

            }
        })
        hasChoosenCity = true
    }

    private fun inserData(weatherBroadcast: WeatherForecastEntity) {

        val dataBaseDao = DatabaseManager.getInstance(this)
        val cityAddBean = dataBaseDao!!.getCityBean(weatherBroadcast)
        dataBaseDao?.insert(cityAddBean)

    }

    //提前获取城市天气信息，这样进入主界面直接获取传递过来的数据，而不用从网络上获取，显示速度快，用户体验好
    private fun beginSearch(location: String) {
        KeyboardUtils.hideKeyboard(et_search)
        val params = HashMap<String, String?>()
        params["location"] = location
        OkhttpUtil.getInstance(this)!!.getDataAsync(Urls.url_find_city, params, object : ResponseCallBack {
            override fun onFailure(error: String) {

            }

            override fun onResponse(response: String) {
                val searchCityEntity = Gson().fromJson(response, SearchCityEntity::class.java)
                searchCityList = searchCityEntity.HeWeather6!![0].basic
                if (searchCityList == null || searchCityList!!.size == 0) {
                    Toast.makeText(this@FindCityActivity, "城市不存在，请重新输入", Toast.LENGTH_SHORT).show()
                    return
                }
                search_city_recyclerview.visibility = View.VISIBLE
                searchCityAdapter.list = searchCityList

            }
        })
    }

}
