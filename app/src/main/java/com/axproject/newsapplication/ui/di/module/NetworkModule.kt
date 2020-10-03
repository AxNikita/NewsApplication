package com.axproject.newsapplication.ui.di.module

import android.content.Context
import com.axproject.newsapplication.ui.source.NetworkSourceService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.io.File

class NetworkModule {

    fun networkService(): NetworkSourceService {
        return retrofit(okHttpClient(httpInterceptor()), gsonConverterFactory()).create(
            NetworkSourceService::class.java
        )
    }

    fun retrofit(okHttpClient: OkHttpClient, gsonConverterFactory: GsonConverterFactory): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("https://newsapi.org")
            .addConverterFactory(gsonConverterFactory)
            .build()
    }

    fun gsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }


    fun okHttpClient(httpInterceptor: Interceptor): OkHttpClient {
        return OkHttpClient()
            .newBuilder()
            .addInterceptor(httpInterceptor)
            .build()
    }

    fun httpInterceptor(): Interceptor {
        return object : Interceptor {
            override fun intercept(chain: Interceptor.Chain): Response {
                val origin = chain.request()
                val requestBuilder = origin.newBuilder()
                    .addHeader("Content-Type", "application/json")
                    .addHeader("apiKey", "e65ee0938a2a43ebb15923b48faed18d") // TODO: 03.10.2020 Проверить работоспособность, реализовать согласно документации
                val request = requestBuilder.build()
                return chain.proceed(request)
            }
        }
    }

}