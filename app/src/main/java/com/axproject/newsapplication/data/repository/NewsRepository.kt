package com.axproject.newsapplication.data.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.axproject.newsapplication.data.database.NewsDatabase
import com.axproject.newsapplication.data.model.Article
import com.axproject.newsapplication.data.model.ResponseModel
import com.axproject.newsapplication.source.NetworkSourceService
import com.axproject.newsapplication.source.NewsSharedPreferences
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsRepository constructor(
    private val networkSourceService: NetworkSourceService,
    private val newsSharedPreferences: NewsSharedPreferences,
    private val context: Context
) {

    private val database = NewsDatabase.getDatabase(context)

    fun getTopArticle(): LiveData<List<Article>> {
        val listArticle: MutableLiveData<List<Article>> = MutableLiveData()
        networkSourceService.topHeadlines().enqueue(object : Callback<ResponseModel> {
            override fun onResponse(call: Call<ResponseModel>, response: Response<ResponseModel>) {
                if (response.isSuccessful) {
                    response.body()?.let { it ->
                        if (it.status == "ok") {
                            response.body()?.let {
                                if (it.totalResults != newsSharedPreferences.getContNews()) {
                                    newsSharedPreferences.saveContNews(it.totalResults)
                                    saveDataToDataBase(it)
                                }
                                listArticle.value = it.articles
                            }

                        } else {
                            // TODO: 04.10.2020 Добавить реакцию на ошибки
                        }
                    }
                }
            }

            override fun onFailure(call: Call<ResponseModel>, t: Throwable) {
                // TODO: 04.10.2020 Добавить реакцию на ошибки
            }

        })
        return listArticle
    }

    fun getEverythingArticle(): LiveData<List<Article>> {
        val listArticle: MutableLiveData<List<Article>> = MutableLiveData()
        networkSourceService.everything().enqueue(object : Callback<ResponseModel> {
            override fun onResponse(call: Call<ResponseModel>, response: Response<ResponseModel>) {
                if (response.isSuccessful) {
                    response.body()?.let { it ->
                        if (it.status == "ok") {
                            response.body()?.let {
                                if (it.totalResults != newsSharedPreferences.getContNews()) {
                                    newsSharedPreferences.saveContNews(it.totalResults)
                                    saveDataToDataBase(it)
                                }
                                listArticle.value = it.articles
                            }

                        } else {
                            // TODO: 04.10.2020 Добавить реакцию на ошибки
                        }
                    }
                }
            }

            override fun onFailure(call: Call<ResponseModel>, t: Throwable) {
                // TODO: 04.10.2020 Добавить реакцию на ошибки
            }

        })
        return listArticle
    }

    public fun deleteTables() {
        CoroutineScope(Dispatchers.IO).launch {
            database.newsDao().deleteArticleTable()
            database.newsDao().deleteSourceTable()
        }
    }

    private fun saveDataToDataBase(responseModel: ResponseModel) {
        for (article in responseModel.articles) {
            CoroutineScope(Dispatchers.IO).launch {
                database.newsDao().deleteArticleTable()
                database.newsDao().deleteSourceTable()
                database.newsDao().insertArticle(article)
                //            database.newsDao().insertSource(article.source!!)
            }
        }
    }

}

