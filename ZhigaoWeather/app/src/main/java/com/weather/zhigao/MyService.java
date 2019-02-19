package com.weather.zhigao;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.weather.zhigao.model.WeatherForecastEntity.HeWeather6Bean.DailyForecastBean;

import com.weather.zhigao.model.WeatherForecastEntity;
import com.weather.zhigao.utils.LogUtil;
import com.weather.zhigao.utils.Weather2IconUtil;

public class MyService extends Service {
    private static final String TAG = "wxx";
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
        String location = weatherBroadcast.getHeWeather6().get(0).getBasic().getLocation();
        String cond_txt = weatherBroadcast.getHeWeather6().get(0).getNow().cond_txt;
        String nowTmp = weatherBroadcast.getHeWeather6().get(0).getNow().tmp;
        DailyForecastBean bean = weatherBroadcast.getHeWeather6().get(0).getDaily_forecast().get(0);
        String tmp = bean.tmp_min + "~" + bean.tmp_max + " ℃";
        int iconId = Weather2IconUtil.getWeatherIconId(cond_txt);
        if (remoteViews == null)
            createNotification(location, iconId, nowTmp, tmp);
        else setRemoteView(location, iconId, nowTmp, tmp);
        return super.onStartCommand(intent, flags, startId);
    }

    private void setRemoteView(String location, int iconId, String nowTmp, String tmp) {

        remoteViews.setTextViewText(R.id.tv_location, location);
        remoteViews.setTextViewText(R.id.tv_temperature, tmp);
        remoteViews.setImageViewResource(R.id.iv_weather, iconId);
        if (notification != null && notificationManager != null && notificationIntent != null) {
            notificationIntent.putExtra("weather", weatherBroadcast);
            String city = weatherBroadcast.getHeWeather6().get(0).getBasic().getLocation();
            LogUtil.d(TAG, "setRemoteView,notificationIntent,city：" + city);
            notification.contentIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);
//            startForeground(2, notification);
            notificationManager.notify(2, notification);
        }
    }

    private void createNotification(String location, int iconId, String nowTmp, String tmp) {
        remoteViews = new RemoteViews(getPackageName(), R.layout.layout_notification);
        setRemoteView(location, iconId, nowTmp, tmp);
        String channel_01 = "my_channel_01";
        String name = "我是渠道名字";
        LogUtil.d(TAG, "createNotification,notificationIntent,city：" + location);
        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel mChannel = new NotificationChannel(channel_01, name, NotificationManager.IMPORTANCE_LOW);
            Log.i(TAG, mChannel.toString());
//            notificationManager.createNotificationChannel(mChannel);
            notification = new NotificationCompat.Builder(this, channel_01)
                    .setSmallIcon(R.mipmap.icon_app_round)
                    //设置自定义的style
                    .setStyle(new NotificationCompat.DecoratedCustomViewStyle())
                    .setCustomContentView(remoteViews).build();
        } else {
            NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                    .setContent(remoteViews)
                    .setOngoing(true);
//                    .setChannel(id);//无效
            notification = notificationBuilder.build();
        }
        notificationIntent = new Intent(this, MainActivity.class);
        notificationIntent.putExtra("weather", weatherBroadcast);
        notification.contentIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);

//        notificationManager.notify(1, notification);
        startForeground(2, notification);
    }

    private void sendNotification() {

    }
}