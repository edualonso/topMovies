package com.barbasdev.base.network.interceptor

import okhttp3.Interceptor
import okhttp3.Response

class ApiKeyInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val originalHttpUrl = original.url()

        val url = originalHttpUrl.newBuilder()
                .addQueryParameter(API_KEY, API_KEY_VALUE)
                .build()

        // Request customization: add request headers
        val requestBuilder = original.newBuilder()
                .url(url)

        val request = requestBuilder.build()
        return chain.proceed(request)
    }


    private companion object {
        const val API_KEY = "api_key"
        const val API_KEY_VALUE = "0de63959274df38be1139ab269854c44"
    }
}