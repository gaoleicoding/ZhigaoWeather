package com.weather.zhigao.net;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;

public interface ResponseCallBack {
    public void onFailure(String error);

    public void onResponse(String response);
}
