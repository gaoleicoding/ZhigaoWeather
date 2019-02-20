package com.weather.zhigao;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.renderscript.RenderScript;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.RemoteViews;

import com.weather.zhigao.model.WeatherForecastEntity;
import com.weather.zhigao.model.WeatherForecastEntity.HeWeather6Bean.DailyForecastBean;
import com.weather.zhigao.utils.Weather2IconUtil;

import static android.app.PendingIntent.FLAG_CANCEL_CURRENT;

public class MyService extends Service {
    private static final String TAG = "MyService";
    RemoteViews remoteViews;
    Notification notification;
    NotificationManager notificationManager;
    WeatherForecastEntity weatherBroadcast;
    Intent notificationIntent;

    @Override
    public IBinder onBind(Intent intent) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();
        Log.d(TAG, "MyService: onCreate()");

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // TODO Auto-generated method stub
        Log.d(TAG, "MyService: onStartCommand()");
        weatherBroadcast = intent.getParcelableExtra("weather");

        //第一次创建Notification
        if (remoteViews == null)
            createNotification(weatherBroadcast);
        else {
            //只更新Notification内容，不重新创建
            setRemoteView(weatherBroadcast);
        }

        return super.onStartCommand(intent, flags, startId);
    }

    private void setRemoteView(WeatherForecastEntity weatherBroadcast) {


        String location = weatherBroadcast.getHeWeather6().get(0).getBasic().getLocation();
        String cond_txt = weatherBroadcast.getHeWeather6().get(0).getNow().cond_txt;
        String nowTmp = weatherBroadcast.getHeWeather6().get(0).getNow().tmp;
        DailyForecastBean bean = weatherBroadcast.getHeWeather6().get(0).getDaily_forecast().get(0);
        String tmp = bean.tmp_min + "~" + bean.tmp_max + " ℃";
        int iconId = Weather2IconUtil.getWeatherIconId(cond_txt);

        remoteViews.setTextViewText(R.id.tv_location, location);
        remoteViews.setTextViewText(R.id.tv_nowTmp, nowTmp);
        remoteViews.setTextViewText(R.id.tv_temperature, tmp);
        remoteViews.setTextViewText(R.id.tv_weather, cond_txt);
        remoteViews.setImageViewResource(R.id.iv_weather, iconId);

        if (notification != null && notificationManager != null) {
            notificationIntent = new Intent(this, MainActivity.class);
            notificationIntent.putExtra("weather", weatherBroadcast);
            notification.contentIntent = PendingIntent.getActivity(this, 0, notificationIntent, FLAG_CANCEL_CURRENT);
//            notificationManager.notify(1, notification);
            //使用这个而不是用上面的目的是：1.为了Notification不容易被杀死，和前台service的进程优先级保持一致 2.在下拉通知栏中，不能点清除按钮清除,使其能够常驻
            startForeground(1, notification);
        }
    }

    //把创建Notification放在service目的是为了app退出依然能够在通知栏中看到天气信息
    private void createNotification(WeatherForecastEntity weatherBroadcast) {
        remoteViews = new RemoteViews(getPackageName(), R.layout.layout_notification);
        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        String channel_id = "channel_01";

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createNotificationChannel();
            notification = new NotificationCompat.Builder(this, channel_id)
                    .setSmallIcon(R.mipmap.icon_app_round)
                    //设置自定义的style
//                    .setStyle(new NotificationCompat.DecoratedCustomViewStyle())
                    .setPriority(1000)
                    .setCustomContentView(remoteViews).build();
        } else {
            NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                    .setContent(remoteViews)
                    .setSmallIcon(R.mipmap.icon_app_round)
                    .setPriority(1000)
                    //setOngoing表示它为一个正在进行的通知
                    .setOngoing(true);
            notification = notificationBuilder.build();
        }

        setRemoteView(weatherBroadcast);
    }


    //8.0及已上系统必须创建Channel，不然notification不会显示
    @TargetApi(Build.VERSION_CODES.O)
    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        String channel_id = "channel_01";
        String channel_name = "我是渠道名字";
        int importance = NotificationManager.IMPORTANCE_DEFAULT;
        NotificationChannel channel = new NotificationChannel(channel_id, channel_name, importance);
        channel.setDescription(channel_name);
        // Register the channel with the system; you can't change the importance
        // or other notification behaviors after this
        notificationManager.createNotificationChannel(channel);
    }

}