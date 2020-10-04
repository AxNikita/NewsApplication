package com.axproject.newsapplication.ui.fragment.topheadlines

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.axproject.newsapplication.data.model.Article
import com.axproject.newsapplication.data.repository.NewsRepository
import com.axproject.newsapplication.di.module.NetworkModule

class TopHeadlinesViewModel : ViewModel() {

    private var repository: NewsRepository
    private lateinit var handler: Handler
    private lateinit var runnable: Runnable

    init {
        repository = NewsRepository(NetworkModule().networkService())

        handler = Handler(Looper.getMainLooper())
        runnable = Runnable {
            getTopArticle()
            handler.postDelayed(runnable, 5000)
        }
        handler.postDelayed(runnable, 5000)
    }

    public fun getTopArticle() : LiveData<List<Article>> {
        println("REQUEST")
        return repository.getTopArticle()
    }

}