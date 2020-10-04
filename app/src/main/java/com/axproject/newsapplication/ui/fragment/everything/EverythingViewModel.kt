package com.axproject.newsapplication.ui.fragment.everything

import android.content.Context
import android.os.Handler
import android.os.Looper
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.axproject.newsapplication.data.model.Article
import com.axproject.newsapplication.data.repository.NewsRepository
import com.axproject.newsapplication.di.module.NetworkModule
import com.axproject.newsapplication.source.NewsSharedPreferences

class EverythingViewModel : ViewModel() {

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


    public fun getEverythingArticle() : LiveData<List<Article>> {
        println("REQUEST")
        return repository.getEverythingArticle()
    }

    public fun deleteTables() {
        repository.deleteTables()
    }

}