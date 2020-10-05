package com.axproject.newsapplication.ui.fragment.detail

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.axproject.newsapplication.data.database.NewsDatabase
import com.axproject.newsapplication.data.model.Article
import com.axproject.newsapplication.data.repository.NewsRepository
import com.axproject.newsapplication.di.module.NetworkModule
import com.axproject.newsapplication.source.NewsSharedPreferences
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailViewModel : ViewModel() {
    private lateinit var context : Context


    public fun setContext(context: Context) {
        this.context = context
    }

    public fun getArticleByTitle(title : String?) : LiveData<Article>{
        var article:  MutableLiveData<Article> = MutableLiveData()
        CoroutineScope(Dispatchers.IO).launch {
            article.postValue(NewsDatabase.getDatabase(context).newsDao().getArticleByTitle(title))
        }
        return article
    }

}