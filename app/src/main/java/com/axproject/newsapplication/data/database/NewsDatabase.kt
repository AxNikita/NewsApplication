package com.axproject.newsapplication.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.axproject.newsapplication.data.database.dao.NewsDao
import com.axproject.newsapplication.data.model.Article
import com.axproject.newsapplication.data.model.Favorite
import com.axproject.newsapplication.data.model.Source

@Database(entities = [Article::class, Source::class, Favorite::class], version = 1)
abstract class NewsDatabase : RoomDatabase() {

    abstract fun newsDao(): NewsDao

    companion object {
        @Volatile private var instance: NewsDatabase? = null
        private val LOCK = Any()

        fun getDatabase(context: Context)= instance ?: synchronized(LOCK){
            instance ?: buildDatabase(context).also { instance = it}
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(context,
            NewsDatabase::class.java, "NEWS")
            .build()
    }

}