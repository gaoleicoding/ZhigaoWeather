package com.weather.zhigao

import android.animation.ObjectAnimator
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.animation.CycleInterpolator
import android.widget.ImageView

import com.google.gson.Gson
import com.jaeger.library.StatusBarUtil
import com.weather.zhigao.application.App
import com.weather.zhigao.model.WeatherForecastEntity
import com.weather.zhigao.net.OkhttpUtil
import com.weather.zhigao.net.ResponseCallBack
import com.weather.zhigao.net.Urls
import com.weather.zhigao.utils.LogUtil
import com.weather.zhigao.utils.SPUtils

import java.util.HashMap

class SplashActivity : AppCompatActivity() {

    lateinit var ivSplashIcon: ImageView
    lateinit var weatherBroadcast: WeatherForecastEntity
    internal var TAG = "SplashActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        StatusBarUtil.setTransparent(this)
        setContentView(R.layout.activity_splash)

        ivSplashIcon = findViewById(R.id.iv_splash_icon)

        ivSplashIcon.post { startAnimation() }

        setAndroidNativeLightStatusBar(this, true)
        App.addAppActivity(this)


        getHotCityAdvance()

    }

    //提前获取热搜城市信息，这样进入添加城市界面直接获取传递过来的数据，而不用从网络上获取，显示速度快，用户体验好
    private fun getHotCityAdvance() {
        val map = HashMap<String, String?>()
        map["group"] = "cn"
        map["number"] = "50"
        OkhttpUtil.getInstance(this)!!.getDataAsync(Urls.url_hot_city, map, object : ResponseCallBack {
            override fun onFailure(error: String) {

            }

            override fun onResponse(response: String) {
                //                HotCityEntity hotCityEntity = new Gson().fromJson(response, HotCityEntity.class);
                SPUtils.setParam(this@SplashActivity, "hotcity", response)
                val currentCity = SPUtils.getParam(this@SplashActivity, "currentCity", "") as String
                LogUtil.d(TAG, "currentCity：$currentCity")
                if ("" == currentCity) {
                    startActivity(Intent(this@SplashActivity, FindCityActivity::class.java))
                    finish()
                } else {
                    getWeatherInfoAdvance(currentCity)
                }

            }
        })
    }

    //提前获取城市天气信息，这样进入主界面直接获取传递过来的数据，而不用从网络上获取，显示速度快，用户体验好
    private fun getWeatherInfoAdvance(location: String) {
        val params = HashMap<String, String?>()
        params["location"] = location

        OkhttpUtil.getInstance(this)!!.getDataAsync(Urls.url_weather, params, object : ResponseCallBack {
            override fun onFailure(error: String) {

            }

            override fun onResponse(response: String) {
                weatherBroadcast = Gson().fromJson(response, WeatherForecastEntity::class.java)

                Handler().postDelayed({
                    val intent = Intent(this@SplashActivity, MainActivity::class.java)
                    intent.putExtra("weather", weatherBroadcast)
                    startActivity(intent)
                    finish()
                }, 1500)


            }
        })
    }

    private fun setAndroidNativeLightStatusBar(activity: Activity, dark: Boolean) {

        val decor = activity.window.decorView
        if (dark) {
            decor.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        } else {
            decor.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        }
    }


    private fun startAnimation() {
        var from: Int = 0;
        var to: Int = -ivSplashIcon.height / 2;
        val animator = ObjectAnimator.ofFloat(ivSplashIcon, "translationY", from.toFloat(), to.toFloat())
        animator.setDuration(800)
        animator.setRepeatCount(ObjectAnimator.INFINITE)
        //        animator.setRepeatMode(ObjectAnimator.RESTART);
        animator.setInterpolator(CycleInterpolator(0.5f))
        animator.start()
    }

    private fun stopAnimation() {
        ivSplashIcon.clearAnimation()
    }

    override fun onResume() {
        super.onResume()

    }

    override fun onStop() {
        super.onStop()
        stopAnimation()
    }
}
