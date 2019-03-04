package com.weather.zhigao.net

import java.io.IOException

import okhttp3.Call
import okhttp3.Response

interface ResponseCallBack {
    fun onFailure(error: String)

    fun onResponse(response: String)
}
