package com.axproject.newsapplication.ui.fragment.topheadlines

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.axproject.newsapplication.data.model.Article
import com.axproject.newsapplication.data.repository.NewsRepository
import com.axproject.newsapplication.di.module.NetworkModule

class TopHeadlinesViewModel : ViewModel() {

    private var repository: NewsRepository

    init {
        repository = NewsRepository(NetworkModule().networkService())
    }





    public fun getTopArticle() : LiveData<List<Article>> {
        return repository.getTopArticle()
    }

}