package com.weather.zhigao;

import android.app.Activity;
import android.util.Log;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkhttpUtil {
    String TAG = "OkhttpUtil";

    private OkhttpUtil() {
    }

    private volatile static OkhttpUtil instance;
    private static OkHttpClient client;
    private static Activity activity;

    public static synchronized OkhttpUtil getInstance(Activity context) {

        if (instance == null) {
            synchronized (OkhttpUtil.class) {
                instance = new OkhttpUtil();
                client = new OkHttpClient.Builder().addInterceptor(new HttpLoggingInterceptor())
                        .build();
                activity = context;
            }
        }
        return instance;
    }

    public void getDataAsync(String url, Map<String, String> mParamsMap, final ResponseCallBack callBack) {

        Request request = new Request.Builder()
                .url(setUrl(url, mParamsMap))
                .build();
        execute(request, callBack);
    }

    void execute(Request request, final ResponseCallBack callBack) {
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d(TAG, "获取数据失败");
                Log.d(TAG, " e.getMessage().toString():" + e.getMessage().toString());

            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                if (response.isSuccessful()) {//回调的方法执行在子线程。
                    Log.d(TAG, "获取数据成功");

                    try {
                        final String responseString = response.body().string();
                        Log.d(TAG, "responseString:" + responseString);
                        activity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                callBack.onResponse(responseString);
                            }
                        });

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    /**
     * get请求，只有键值对参数
     */
    private String setUrl(String mUrl, Map<String, String> mParamsMap) {
        if (mParamsMap != null) {
            mUrl = mUrl + "?";
            for (String key : mParamsMap.keySet()) {
                mUrl = mUrl + key + "=" + mParamsMap.get(key) + "&";
            }
            mUrl = mUrl.substring(0, mUrl.length() - 1);
        }

        return mUrl;
    }
}
