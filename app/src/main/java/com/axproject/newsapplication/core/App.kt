package com.axproject.newsapplication.core

import android.app.Application
import android.content.Context
import androidx.core.content.ContextCompat
import androidx.room.Room
import androidx.room.RoomDatabase
import com.axproject.newsapplication.data.database.NewsDatabase
import com.axproject.newsapplication.di.module.NetworkModule
import com.axproject.newsapplication.source.NetworkSourceService

class App : Application() {

//    private lateinit var networkSourceService : NetworkSourceService

    override fun onCreate() {
        super.onCreate()
//        networkSourceService = NetworkModule().networkService() // TODO: 03.10.2020 Заменить, используя dagger
    }

}