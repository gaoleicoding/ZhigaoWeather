package com.weather.zhigao;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;

public class OkhttpUtil {
    String TAG = "OkhttpUtil";
    public static Handler mMainHandler = new Handler(Looper.getMainLooper());

    private OkhttpUtil() {
    }

    private volatile static OkhttpUtil instance;
    private static OkHttpClient client;

    public static synchronized OkhttpUtil getInstance() {

        if (instance == null) {
            synchronized (OkhttpUtil.class) {
                instance = new OkhttpUtil();
                client = new OkHttpClient();
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
                Log.d(TAG, " e.getMessage().toString()==" + e.getMessage().toString());

            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                if (response.isSuccessful()) {//回调的方法执行在子线程。
                    Log.d(TAG, "获取数据成功了");
                    Log.d(TAG, "response.code()==" + response.code());
                    Log.d(TAG, "response.body().string()==" + response.body().string());
                    mMainHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            try {
//                                String data=response.body().string();
                                ResponseBody responseBody = response.body();
                                BufferedSource source = responseBody.source();
                                source.request(Long.MAX_VALUE); // Buffer the entire body.
                                Buffer buffer = source.buffer();
                                MediaType contentType = responseBody.contentType();
                                Charset charset =null;
                                if (contentType != null) {
                                    charset = contentType.charset(Charset.forName("UTF-8"));
                                }

                                String bodyString = buffer.clone().readString(charset);
                                callBack.onResponse(bodyString);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });

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
