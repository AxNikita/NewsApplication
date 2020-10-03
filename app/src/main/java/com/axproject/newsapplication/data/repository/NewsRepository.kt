package com.axproject.newsapplication.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.axproject.newsapplication.data.model.Article
import com.axproject.newsapplication.data.model.ResponseModel
import com.axproject.newsapplication.source.NetworkSourceService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsRepository constructor(private val networkSourceService: NetworkSourceService) {

    fun getTopArticle(): LiveData<List<Article>> {
        val listArticle :  MutableLiveData<List<Article>> = MutableLiveData()
        networkSourceService.topHeadlines().enqueue(object : Callback<ResponseModel> {
            override fun onResponse(call: Call<ResponseModel>, response: Response<ResponseModel>) {
                listArticle.value = response.body()?.articles
            }

            override fun onFailure(call: Call<ResponseModel>, t: Throwable) {
                print(t)
            }

        })
        return listArticle
    }

}

