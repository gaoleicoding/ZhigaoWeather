package com.weather.zhigao.net

import android.app.Activity
import android.text.TextUtils
import android.util.Log

import java.io.IOException

import okhttp3.Call
import okhttp3.Callback
import okhttp3.FormBody
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.Response

class OkhttpUtil private constructor() {
    internal var TAG = "OkhttpUtil"

    //get方式
    fun getDataAsync(url: String, mParamsMap: MutableMap<String, String?>, callBack: ResponseCallBack) {
        val request = Request.Builder()
                .url(getRequestParams(url, mParamsMap))
                .build()
        execute(request, callBack)
    }

    fun postDataAsync(url: String, mParamsMap: Map<String, String>, callBack: ResponseCallBack) {
        val requestBody = postRequestBody(mParamsMap)
        val request = Request.Builder()
                .url(url)
                .post(requestBody)//传递请求体
                .build()
        execute(request, callBack)
    }

    //post方式
    internal fun execute(request: Request, callBack: ResponseCallBack) {
        client!!.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.d(TAG, "获取数据失败")
                Log.d(TAG, " e.getMessage().toString():" + e.message.toString())

            }

            @Throws(IOException::class)
            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {//回调的方法执行在子线程。
                    Log.d(TAG, "获取数据成功")

                    try {
                        val responseString = response.body()!!.string()
                        Log.d(TAG, "responseString:$responseString")
                        activity!!.runOnUiThread { callBack.onResponse(responseString) }

                    } catch (e: Exception) {
                        e.printStackTrace()
                    }

                }
            }
        })
    }

    /**
     * get请求，键值对参数拼接到url
     */
    private fun getRequestParams(mUrl: String, mParamsMap: MutableMap<String, String?>): String {
        var mUrl = mUrl
        mParamsMap["key"] = "227849effc2b4e83b4cf1b0caf743cf9"
        if (mParamsMap != null) {
            mUrl = "$mUrl?"
            for (key in mParamsMap.keys) {
                mUrl = mUrl + key + "=" + mParamsMap[key] + "&"
            }
            mUrl = mUrl.substring(0, mUrl.length - 1)
        }

        return mUrl
    }

    /**
     * post请求得到body对象
     */
    private fun postRequestBody(mParamsMap: Map<String, String>?): RequestBody {

        /**
         * post,put,delete都需要body，但也都有body等于空的情况，此时也应该有body对象，但body中的内容为空
         */
        val formBody = FormBody.Builder()
        if (mParamsMap != null) {
            for (key in mParamsMap.keys) {
                formBody.add(key, mParamsMap[key])
            }
        }
        return formBody.build()
    }

    companion object {

        @Volatile
        private var instance: OkhttpUtil? = null
        private var client: OkHttpClient? = null
        private var activity: Activity? = null

        @Synchronized
        fun getInstance(context: Activity): OkhttpUtil? {

            if (instance == null) {
                synchronized(OkhttpUtil::class.java) {
                    instance = OkhttpUtil()
                    client = OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor())
                            .build()
                    activity = context
                }
            }
            return instance
        }
    }
}
