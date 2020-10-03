package com.axproject.newsapplication.di.module

import com.axproject.newsapplication.source.NetworkSourceService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

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
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .callTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    fun httpInterceptor(): Interceptor {
        return object : Interceptor {
            override fun intercept(chain: Interceptor.Chain): Response {
                val origin = chain.request()
                val requestBuilder = origin.newBuilder()
                    .addHeader("Content-Type", "application/json")
//                    .addHeader("apiKey", "e65ee0938a2a43ebb15923b48faed18d") // TODO: 03.10.2020 Проверить работоспособность, реализовать согласно документации
                val request = requestBuilder.build()
                return chain.proceed(request)
            }
        }
    }

}