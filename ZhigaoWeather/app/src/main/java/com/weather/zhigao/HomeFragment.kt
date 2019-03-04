package com.weather.zhigao

import android.app.NotificationManager
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.NestedScrollView
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView

import com.weather.zhigao.adapter.HourlyForecastAdapter
import com.weather.zhigao.adapter.LifeStyleAdapter
import com.weather.zhigao.adapter.WeatherForecastAdapter
import com.weather.zhigao.adapter.divider.RecycleViewDivider
import com.weather.zhigao.application.App
import com.weather.zhigao.db.DatabaseManager
import com.weather.zhigao.model.WeatherForecastEntity
import com.weather.zhigao.utils.LogUtil
import com.weather.zhigao.utils.LunarUtil
import com.weather.zhigao.utils.ScreenUtils
import com.weather.zhigao.utils.TimeUtil
import com.weather.zhigao.utils.Weather2IconUtil

import java.util.ArrayList

import android.content.Context.NOTIFICATION_SERVICE
import com.weather.zhigao.model.weather.DailyForecastBean
import com.weather.zhigao.model.weather.HourlyBean
import com.weather.zhigao.model.weather.LifestyleBean

class HomeFragment : Fragment() {
    lateinit var tv_position: TextView
    lateinit var tv_date: TextView
    lateinit var tv_temperature: TextView
    lateinit var tv_weather: TextView
    lateinit var tv_lifestyle_weather: TextView
    lateinit var tv_lifestyle_forecast: TextView
    lateinit var tv_lifestyle_wind: TextView
    lateinit var tv_update_time: TextView
    lateinit var tv_air_content: TextView
    lateinit var iv_menu: ImageView
    lateinit var iv_expand_arrow: ImageView
    lateinit var ll_root: LinearLayout
    lateinit var ll_bottom: LinearLayout
    var TAG = "HomeFragment"
    lateinit var forecastList: List<DailyForecastBean>
    lateinit var hourlyForecastList: List<HourlyBean>
    lateinit var lifeStyleList: List<LifestyleBean>
    lateinit var forecast_recyclerview: RecyclerView
    lateinit var hourly_recyclerview: RecyclerView
    lateinit var lifestyle_recyclerview: RecyclerView
    lateinit var forecastAdapter: WeatherForecastAdapter
    lateinit var hourlyForecastAdapter: HourlyForecastAdapter
    lateinit var lifeStyleAdapter: LifeStyleAdapter
    lateinit var rl_home: RelativeLayout
    lateinit var rl_title: RelativeLayout
    lateinit var scrollView: NestedScrollView
    var homeHeight: Int = 0
    var bottomHeight: Int = 0
    lateinit var weatherBroadcast: WeatherForecastEntity
    private var notificationManager: NotificationManager? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val mParentView = inflater.inflate(R.layout.fragment_home, container, false)


        ll_root = mParentView.findViewById(R.id.ll_root)
        forecast_recyclerview = mParentView.findViewById(R.id.forecast_recyclerview)
        hourly_recyclerview = mParentView.findViewById(R.id.hourly_recyclerview)
        lifestyle_recyclerview = mParentView.findViewById(R.id.lifestyle_recyclerview)
        iv_menu = mParentView.findViewById(R.id.iv_menu)
        iv_expand_arrow = mParentView.findViewById(R.id.iv_expand_arrow)
        tv_position = mParentView.findViewById(R.id.tv_position)
        tv_date = mParentView.findViewById(R.id.tv_date)
        tv_temperature = mParentView.findViewById(R.id.tv_temperature)
        tv_weather = mParentView.findViewById(R.id.tv_weather)
        tv_update_time = mParentView.findViewById(R.id.tv_update_time)
        rl_home = mParentView.findViewById(R.id.rl_home)
        rl_title = mParentView.findViewById(R.id.rl_title)
        tv_lifestyle_weather = mParentView.findViewById(R.id.tv_lifestyle_weather)
        tv_lifestyle_forecast = mParentView.findViewById(R.id.tv_lifestyle_forecast)
        tv_lifestyle_wind = mParentView.findViewById(R.id.tv_lifestyle_wind)
        scrollView = mParentView.findViewById(R.id.scrollView)
        ll_bottom = mParentView.findViewById(R.id.ll_bottom)
        tv_air_content = mParentView.findViewById(R.id.tv_air_content)

        //加入透明状态栏后，需要留出和状态栏高度一样的的空间
        val statusBarHeight = ScreenUtils.getStatusHeight(activity!!)
        val layoutParams = rl_title.layoutParams as LinearLayout.LayoutParams
        rl_title.setPadding(0, statusBarHeight, 0, ScreenUtils.dp2px(activity!!, 30f))
        rl_title.layoutParams = layoutParams
        notificationManager = activity!!.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        iv_expand_arrow.setOnClickListener { scrollView.smoothScrollTo(0, homeHeight - bottomHeight) }
        iv_menu.setOnClickListener {
            val mainActivity = activity as MainActivity?
            mainActivity!!.openDrawer()
        }
        rl_title.post {
            val height = rl_title.height
            val layoutParams = rl_home.layoutParams as LinearLayout.LayoutParams
            layoutParams.width = ScreenUtils.getScreenWidth(activity!!)
            homeHeight = ScreenUtils.getScreenHeight(activity!!) - height
            layoutParams.height = homeHeight
            rl_home.layoutParams = layoutParams
        }
        ll_bottom.post { bottomHeight = ll_bottom.height }

        initData(weatherBroadcast)


        return mParentView
    }


    fun setData(weatherBroadcast: WeatherForecastEntity) {
        this.weatherBroadcast = weatherBroadcast
    }

    fun initData(weatherBroadcast: WeatherForecastEntity) {

        val intent = Intent(activity, MyService::class.java)
        intent.putExtra("weather", weatherBroadcast)
        activity!!.startService(intent)

        initRecyclerView()
        initHourlyRecyclerView()
        initLifeStyleRecyclerView()
        var heWeather=weatherBroadcast.heWeather6!![0]
//        Log.d(TAG, "weatherBroadcast.HeWeather6!!.size-------HomeFragment-------------" + weatherBroadcast.heWeather6.size)
        forecastAdapter.list = heWeather.daily_forecast
        hourlyForecastAdapter.list = heWeather.hourly

        val lifestyleList = heWeather.lifestyle
        if (lifestyleList != null && lifestyleList.size > 0) {
            lifeStyleAdapter.list = lifestyleList
            tv_air_content.text = lifestyleList[lifestyleList.size - 1].brf
            tv_lifestyle_weather.text = lifestyleList[1].brf
        }
        val location = heWeather.basic.location
        LogUtil.d(TAG, "initData，location：$location")
        tv_position.text = location
        val date = heWeather.update.loc.split(" ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[0]
        tv_date.text = TimeUtil.getStringToDate(date) + " " + TimeUtil.dateToWeek(date) + " " + LunarUtil.lunarDate
        val nowTemp = heWeather.now.tmp
        tv_temperature.text = nowTemp
        val cond_txt = heWeather.now.cond_txt
        tv_weather.text = cond_txt

        val bean = heWeather.daily_forecast[0]
        tv_lifestyle_forecast.text = bean.tmp_min + "~" + bean.tmp_max + " ℃"
        tv_lifestyle_wind.text = bean.wind_dir + bean.wind_sc + App.mContext.getString(R.string.degree)
        tv_update_time.text = getString(R.string.update_time) + heWeather.update.loc

        val sunriseTime = TimeUtil.dateToLong(TimeUtil.ConverToDate(TimeUtil.currentDate + " 06:00")!!)
        val sunsetTime = TimeUtil.dateToLong(TimeUtil.ConverToDate(TimeUtil.currentDate + " 19:00")!!)
        val currentTime = System.currentTimeMillis()
        //计算现在是白天还是黑天，现在设置固定白天时间为 06:00 - 19:00
        if (sunriseTime < currentTime && sunsetTime > currentTime) {

            ll_root.setBackgroundResource(Weather2IconUtil.getDayBackgroundId(cond_txt))

        } else {
            ll_root.setBackgroundResource(Weather2IconUtil.getNightBackgroundId(cond_txt))
        }
        //及时更新数据库中城市的天气信息
        DatabaseManager.getInstance(activity!!)!!.update(location, DatabaseManager.getInstance(activity!!)!!.getCityBean(weatherBroadcast))

        val mainActivity = activity as MainActivity?
        //在更新数据库中城市的天气信息后，显示添加的城市天气数据，保持最新
        mainActivity!!.setRecyclerView()
    }


    private fun initRecyclerView() {
        forecastList = ArrayList()
        forecastAdapter = WeatherForecastAdapter(activity!!, forecastList)
        forecast_recyclerview.addItemDecoration(RecycleViewDivider(activity!!, LinearLayoutManager.VERTICAL))
        forecast_recyclerview.layoutManager = LinearLayoutManager(activity)

        //        //以下三条解决RecyclerView和NestedScrollView的冲突
        //解决数据加载不完的问题
        //        forecast_recyclerview.setNestedScrollingEnabled(false);
        //        forecast_recyclerview.setHasFixedSize(true);
        //        //解决数据加载完成后, 没有停留在顶部的问题
        //        forecast_recyclerview.setFocusable(false);

        forecast_recyclerview.adapter = forecastAdapter
    }

    private fun initHourlyRecyclerView() {
        hourlyForecastList = ArrayList()
        hourlyForecastAdapter = HourlyForecastAdapter(activity!!, hourlyForecastList)
        val linearLayoutManager = LinearLayoutManager(activity)
        hourly_recyclerview.layoutManager = linearLayoutManager
        linearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL

        //        //解决数据加载不完的问题
        //        hourly_recyclerview.setNestedScrollingEnabled(false);
        //        hourly_recyclerview.setHasFixedSize(true);
        //        //解决数据加载完成后, 没有停留在顶部的问题
        //        hourly_recyclerview.setFocusable(false);

        hourly_recyclerview.adapter = hourlyForecastAdapter
    }

    private fun initLifeStyleRecyclerView() {
        lifeStyleList = ArrayList()
        lifeStyleAdapter = LifeStyleAdapter(activity!!, lifeStyleList)
        val gridLayoutManager = GridLayoutManager(activity, 2)
        lifestyle_recyclerview.layoutManager = gridLayoutManager
        lifestyle_recyclerview.adapter = lifeStyleAdapter
    }


}
