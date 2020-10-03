package com.axproject.newsapplication.ui.core

import android.app.Application
import com.axproject.newsapplication.ui.di.module.NetworkModule
import com.axproject.newsapplication.ui.source.NetworkSourceService

class App : Application() {

    private lateinit var networkSourceService : NetworkSourceService

    override fun onCreate() {
        super.onCreate()
        networkSourceService = NetworkModule().networkService() // TODO: 03.10.2020 Заменить, используя dagger
    }

}