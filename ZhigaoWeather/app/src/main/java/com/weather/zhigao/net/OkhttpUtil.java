package com.weather.zhigao.net;

import android.app.Activity;
import android.text.TextUtils;
import android.util.Log;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
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

    //get方式
    public void getDataAsync(String url, Map<String, String> mParamsMap, final ResponseCallBack callBack) {
        Request request = new Request.Builder()
                .url(getRequestParams(url, mParamsMap))
                .build();
        execute(request, callBack);
    }

    public void postDataAsync(String url, Map<String, String> mParamsMap, final ResponseCallBack callBack) {
        RequestBody requestBody = postRequestBody(mParamsMap);
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)//传递请求体
                .build();
        execute(request, callBack);
    }

    //post方式
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
     * get请求，键值对参数拼接到url
     */
    private String getRequestParams(String mUrl, Map<String, String> mParamsMap) {
        mParamsMap.put("key", "227849effc2b4e83b4cf1b0caf743cf9");
        if (mParamsMap != null) {
            mUrl = mUrl + "?";
            for (String key : mParamsMap.keySet()) {
                mUrl = mUrl + key + "=" + mParamsMap.get(key) + "&";
            }
            mUrl = mUrl.substring(0, mUrl.length() - 1);
        }

        return mUrl;
    }

    /**
     * post请求得到body对象
     */
    private RequestBody postRequestBody(Map<String, String> mParamsMap) {

        /**
         * post,put,delete都需要body，但也都有body等于空的情况，此时也应该有body对象，但body中的内容为空
         */
        FormBody.Builder formBody = new FormBody.Builder();
        if (mParamsMap != null) {
            for (String key : mParamsMap.keySet()) {
                formBody.add(key, mParamsMap.get(key));
            }
        }
        return formBody.build();
    }
}
