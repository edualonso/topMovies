package com.barbasdev.base.network

import com.barbasdev.base.BuildConfig
import com.barbasdev.base.network.NetworkConst.URL
import com.barbasdev.base.network.interceptor.ApiKeyInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 *
 */
val baseNetworkModule = module {

    single {
        val loggingInterceptor = HttpLoggingInterceptor()
        when {
            BuildConfig.DEBUG -> loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            else -> loggingInterceptor.level = HttpLoggingInterceptor.Level.NONE
        }

        Retrofit.Builder()
                .baseUrl(URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(OkHttpClient.Builder()
                        .addInterceptor(loggingInterceptor)
                        .addInterceptor(ApiKeyInterceptor())
                        .build())
                .build()
    }
}


/**
 *
 */
object NetworkConst {
    private const val VERSION = 3

    const val URL = "https://api.themoviedb.org/$VERSION/"
}