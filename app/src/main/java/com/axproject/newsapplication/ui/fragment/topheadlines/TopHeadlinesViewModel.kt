package com.axproject.newsapplication.ui.fragment.topheadlines

import android.content.Context
import android.os.Handler
import android.os.Looper
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.room.Room
import com.axproject.newsapplication.core.App
import com.axproject.newsapplication.data.database.NewsDatabase
import com.axproject.newsapplication.data.model.Article
import com.axproject.newsapplication.data.repository.NewsRepository
import com.axproject.newsapplication.di.module.NetworkModule
import com.axproject.newsapplication.source.NewsSharedPreferences
import kotlin.coroutines.coroutineContext

class TopHeadlinesViewModel : ViewModel() {

    private lateinit var repository: NewsRepository
    private lateinit var handler: Handler
    private lateinit var runnable: Runnable
    private lateinit var context : Context
    private lateinit var newsSharedPreferences: NewsSharedPreferences

    public fun setContext(context: Context) {
        this.context = context
        newsSharedPreferences = NewsSharedPreferences(context)
        repository = NewsRepository(
            NetworkModule().networkService(),
            newsSharedPreferences,
            context)
    }

    public fun initTimer() {
        handler = Handler(Looper.getMainLooper())
        var count = newsSharedPreferences.getContNews()
        runnable = Runnable {
            if (count != newsSharedPreferences.getContNews()) {
                count = newsSharedPreferences.getContNews()
                getTopArticle()
            }
            handler.postDelayed(runnable, 5000)
        }
        handler.postDelayed(runnable, 5000)
    }

    public fun getTopArticle() : LiveData<List<Article>> {
        println("REQUEST")
        return repository.getTopArticle()
    }


}