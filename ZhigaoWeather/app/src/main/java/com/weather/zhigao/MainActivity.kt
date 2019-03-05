package com.weather.zhigao

import android.content.Intent
import android.os.Bundle
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.Gravity
import android.view.KeyEvent
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast

import com.google.gson.Gson
import com.jaeger.library.StatusBarUtil
import com.weather.zhigao.adapter.CityAddAdapter
import com.weather.zhigao.db.DatabaseManager
import com.weather.zhigao.model.CityAddBean
import com.weather.zhigao.model.WeatherForecastEntity
import com.weather.zhigao.net.OkhttpUtil
import com.weather.zhigao.net.ResponseCallBack
import com.weather.zhigao.net.Urls
import com.weather.zhigao.utils.LogUtil
import com.weather.zhigao.utils.SPUtils
import com.weather.zhigao.utils.ScreenUtils

import java.util.ArrayList
import java.util.HashMap

class MainActivity : AppCompatActivity() {
    internal var TAG = "MainActivity"
    lateinit var mDrawerLayout: DrawerLayout
    lateinit var frament_content: FrameLayout
    lateinit var rl_drawer: RelativeLayout
    lateinit var homeFragment: HomeFragment
    lateinit var iv_add: ImageView
     var dataBaseDao: DatabaseManager?=null
    lateinit var city_recyclerview: RecyclerView
    lateinit var cityList: MutableList<CityAddBean>
    lateinit var cityAddAdapter: CityAddAdapter
    lateinit var tv_edit: TextView
    internal var isEdit = false

    // 用来计算返回键的点击间隔时间
    private var exitTime: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        StatusBarUtil.setTransparent(this)
        setContentView(R.layout.activity_main)
        dataBaseDao = DatabaseManager.getInstance(this)
        homeFragment = HomeFragment()

        mDrawerLayout = findViewById(R.id.drawer_layout)
        frament_content = findViewById(R.id.frament_content)
        rl_drawer = findViewById(R.id.rl_drawer)
        iv_add = findViewById(R.id.iv_add)
        tv_edit = findViewById(R.id.tv_edit)
        city_recyclerview = findViewById(R.id.drawer_recyclerview)

        //加入透明状态栏后，需要留出和状态栏高度一样的的空间
        val statusBarHeight = ScreenUtils.getStatusHeight2(this)
        val layoutParams = rl_drawer.layoutParams as ViewGroup.LayoutParams
        rl_drawer.setPadding(0, statusBarHeight, 0, 0)
        rl_drawer.layoutParams = layoutParams

        StatusBarUtil.setColorForDrawerLayout(this, mDrawerLayout, 0)
        supportFragmentManager.beginTransaction().replace(R.id.frament_content, homeFragment).commit()
        iv_add.setOnClickListener { startActivity(Intent(this@MainActivity, FindCityActivity::class.java)) }
        tv_edit.setOnClickListener {
            if (!isEdit) {
                cityAddAdapter.setEdit(true)
                tv_edit.text = getString(R.string.complete)
                iv_add.visibility = View.GONE
                isEdit = true
            } else {
                cityAddAdapter.setEdit(false)
                tv_edit.text = getString(R.string.edit)
                iv_add.visibility = View.VISIBLE
                isEdit = false
            }
        }

        initRecyclerView()
        val weatherBroadcast = intent.getParcelableExtra<WeatherForecastEntity>("weather")
        var HeWeather6_size=weatherBroadcast.HeWeather6!!.size
        Log.d(TAG,"weatherBroadcast.HeWeather6!!.size--------------------"+HeWeather6_size)
        var daily_forecast_size=weatherBroadcast.HeWeather6!!.get(0).daily_forecast.size
        Log.d(TAG,"weatherBroadcast.HeWeather6!!.get(0).daily_forecast.size--------------------"+daily_forecast_size)
        homeFragment.setData(weatherBroadcast)

    }

    override fun onNewIntent(intent: Intent) {

        super.onNewIntent(intent)
        val weatherBroadcast = intent.getParcelableExtra<WeatherForecastEntity>("weather")
        if (weatherBroadcast != null) {
            homeFragment.initData(weatherBroadcast)
            //添加城市的时候调用，更新数据
            //            setRecyclerView();
        }

    }

    fun setRecyclerView() {
        cityList = dataBaseDao!!.queryAll()
        if (cityList != null) {
            cityAddAdapter.changeList(cityList)
        }

        LogUtil.d(TAG, "cityList.size()：" + cityList!!.size)
    }

    private fun initRecyclerView() {
        cityList = ArrayList()
        cityAddAdapter = CityAddAdapter(this, cityList!!)
        //        city_recyclerview.addItemDecoration(new RecycleViewDivider(this, LinearLayoutManager.VERTICAL));
        city_recyclerview.layoutManager = LinearLayoutManager(this)

        city_recyclerview.adapter = cityAddAdapter

        //        setRecyclerView();

        cityAddAdapter.setOnItemClickListener(object : CityAddAdapter.OnItemClickListener {
            override fun onItemClick(view: View, postion: Int) {
                mDrawerLayout.closeDrawer(Gravity.LEFT)
                val location = cityList!![postion].location
                SPUtils.setParam(this@MainActivity, "currentCity", location)
                getWeatherInfo(location)
                cityAddAdapter.notifyDataSetChanged()

            }

            override fun onItemRemove(view: View, postion: Int) {
                val location = cityList!![postion].location
                cityList!!.removeAt(postion)
                cityAddAdapter.notifyDataSetChanged()
                dataBaseDao!!.delete(location)
            }
        })
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.action == KeyEvent.ACTION_DOWN) {
            if (mDrawerLayout.isDrawerOpen(Gravity.LEFT)) {
                mDrawerLayout.closeDrawer(Gravity.LEFT)
                return false
            }

            if (System.currentTimeMillis() - exitTime > 2000) {
                Toast.makeText(applicationContext, getString(R.string.press_exit), Toast.LENGTH_SHORT).show()
                exitTime = System.currentTimeMillis()
            } else {
                finish()
            }
            return true
        }

        return super.onKeyDown(keyCode, event)
    }

    fun openDrawer() {
        mDrawerLayout.openDrawer(Gravity.LEFT)  //便可以实现弹出抽屉界面
    }

    //提前获取城市天气信息
    private fun getWeatherInfo(location: String) {
        val params = HashMap<String, String?>()
        params["location"] = location
        OkhttpUtil.getInstance(this)!!.getDataAsync(Urls.url_weather, params, object : ResponseCallBack {
            override fun onFailure(error: String) {

            }

            override fun onResponse(response: String) {
                val weatherBroadcast = Gson().fromJson(response, WeatherForecastEntity::class.java)
                homeFragment.initData(weatherBroadcast)

            }
        })
    }
}
