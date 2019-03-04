package com.weather.zhigao.utils

class ApiClient private constructor(str: String) {
    private var string: String = str

    companion object {
        private var instance: ApiClient? = null
        fun getInstance(s: String): ApiClient? {
            if (instance == null) {
                synchronized(ApiClient::class) {
                    instance = ApiClient(s)
                }
            }
            return instance
        }
    }
}