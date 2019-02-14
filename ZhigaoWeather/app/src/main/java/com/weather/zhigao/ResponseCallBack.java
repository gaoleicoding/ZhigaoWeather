package com.weather.zhigao;

import java.io.IOException;

import okhttp3.Call;

public interface ResponseCallBack {
    public void onFailure(String error);

    public void onResponse(String response);
}
