package com.weather.zhigao

import android.annotation.TargetApi
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.support.v4.app.NotificationCompat
import android.util.Log
import android.widget.RemoteViews

import com.weather.zhigao.model.WeatherForecastEntity
import com.weather.zhigao.model.WeatherForecastEntity.HeWeather6Bean.DailyForecastBean
import com.weather.zhigao.utils.Weather2IconUtil

import android.app.PendingIntent.FLAG_CANCEL_CURRENT
import android.content.Context

class MyService : Service() {
    internal var remoteViews: RemoteViews? = null
    internal var notification: Notification? = null
    internal var notificationManager: NotificationManager? = null
    lateinit var weatherBroadcast: WeatherForecastEntity
    lateinit var notificationIntent: Intent

    override fun onBind(intent: Intent): IBinder? {
        // TODO Auto-generated method stub
        return null
    }

    override fun onCreate() {
        // TODO Auto-generated method stub
        super.onCreate()
        Log.d(TAG, "MyService: onCreate()")

    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        // TODO Auto-generated method stub
        Log.d(TAG, "MyService: onStartCommand()")
        weatherBroadcast = intent.getParcelableExtra("weather")

        //第一次创建Notification
        if (remoteViews == null)
            createNotification(weatherBroadcast)
        else {
            //只更新Notification内容，不重新创建
            setRemoteView(weatherBroadcast)
        }

        return super.onStartCommand(intent, flags, startId)
    }

    private fun setRemoteView(weatherBroadcast: WeatherForecastEntity) {


        val location = weatherBroadcast.heWeather6[0].getBasic().getLocation()
        val cond_txt = weatherBroadcast.heWeather6[0].getNow().getCond_txt()
        val nowTmp = weatherBroadcast.heWeather6[0].getNow().getTmp()
        val bean = weatherBroadcast.heWeather6[0].getDaily_forecast()[0]
        val tmp = bean.getTmp_min() + "~" + bean.getTmp_max() + " ℃"
        val iconId = Weather2IconUtil.getWeatherIconId(cond_txt)

        remoteViews!!.setTextViewText(R.id.tv_location, location)
        remoteViews!!.setTextViewText(R.id.tv_nowTmp, nowTmp)
        remoteViews!!.setTextViewText(R.id.tv_temperature, tmp)
        remoteViews!!.setTextViewText(R.id.tv_weather, cond_txt)
        remoteViews!!.setImageViewResource(R.id.iv_weather, iconId)

        if (notification != null && notificationManager != null) {
            notificationIntent = Intent(this, MainActivity::class.java)
            notificationIntent.putExtra("weather", weatherBroadcast)
            notification!!.contentIntent = PendingIntent.getActivity(this, 0, notificationIntent, FLAG_CANCEL_CURRENT)
            //            notificationManager.notify(1, notification);
            //使用这个而不是用上面的目的是：1.为了Notification不容易被杀死，和前台service的进程优先级保持一致 2.在下拉通知栏中，不能点清除按钮清除,使其能够常驻
            startForeground(1, notification)
        }
    }

    //把创建Notification放在service目的是为了app退出依然能够在通知栏中看到天气信息
    private fun createNotification(weatherBroadcast: WeatherForecastEntity) {
        remoteViews = RemoteViews(packageName, R.layout.layout_notification)
        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val channel_id = "channel_01"

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createNotificationChannel()
            notification = NotificationCompat.Builder(this, channel_id)
                    .setSmallIcon(R.mipmap.icon_app_round)
                    //设置自定义的style
                    //                    .setStyle(new NotificationCompat.DecoratedCustomViewStyle())
                    .setPriority(1000)
                    .setCustomContentView(remoteViews).build()
        } else {
            val notificationBuilder = NotificationCompat.Builder(this)
                    .setContent(remoteViews)
                    .setSmallIcon(R.mipmap.icon_app_round)
                    .setPriority(1000)
                    //setOngoing表示它为一个正在进行的通知
                    .setOngoing(true)
            notification = notificationBuilder.build()
        }

        setRemoteView(weatherBroadcast)
    }


    //8.0及已上系统必须创建Channel，不然notification不会显示
    @TargetApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        val channel_id = "channel_01"
        val channel_name = "我是渠道名字"
        // IMPORTANCE_LOW 开启通知，不会弹出，不发出提示音，状态栏中显示
        val importance = NotificationManager.IMPORTANCE_LOW
        val channel = NotificationChannel(channel_id, channel_name, importance)
        channel.description = channel_name
        // Register the channel with the system; you can't change the importance
        // or other notification behaviors after this
        notificationManager!!.createNotificationChannel(channel)
    }

    companion object {
        private val TAG = "MyService"
    }

}