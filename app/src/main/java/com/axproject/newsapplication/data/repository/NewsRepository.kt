package com.axproject.newsapplication.data.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.axproject.newsapplication.core.App
import com.axproject.newsapplication.data.database.NewsDatabase
import com.axproject.newsapplication.data.model.Article
import com.axproject.newsapplication.data.model.ResponseModel
import com.axproject.newsapplication.source.NetworkSourceService
import com.axproject.newsapplication.source.NewsSharedPreferences
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsRepository constructor(
    private val networkSourceService: NetworkSourceService,
    private val newsSharedPreferences: NewsSharedPreferences,
    private val context: Context) {

    private val database= NewsDatabase.invoke(context)

    fun getTopArticle(): LiveData<List<Article>> {
        val listArticle :  MutableLiveData<List<Article>> = MutableLiveData()
//        listArticle.value = listOf(
//            Article(1, Source(1, null.toString(),
//                "New York Times"),
//                "Donald G. McNeil Jr.",
//                "How to Identify the Different Symptoms of the Flu and Covid-19 - The New York Times",
//                "With fears of a “twindemic” in the United States this fall, here’s a guide to understanding what’s making you feel terrible.",
//                "https://www.nytimes.com/2020/10/03/at-home/coronavirus-flu-symptoms.html",
//                "https://static01.nyt.com/images/2020/10/04/multimedia/04-ah-symptoms/04-ah-symptoms-facebookJumbo.jpg",
//                "2020-10-04T02:46:00Z",
//                "In severe cases, the most common complication is pneumonia. The typical signs of a flu pneumonia are shortness of breath, especially when you exert yourself, and unusually rapid breathing doctors typ… [+1800 chars]"),
//
//        Article(2, Source(2, null.toString(),
//            "New York Times"),
//            "Donald G. McNeil Jr.",
//            "How to Identify the Different Symptoms of the Flu and Covid-19 - The New York Times",
//            "With fears of a “twindemic” in the United States this fall, here’s a guide to understanding what’s making you feel terrible.",
//            "https://www.nytimes.com/2020/10/03/at-home/coronavirus-flu-symptoms.html",
//            "https://a2.espncdn.com/combiner/i?img=%2Fphoto%2F2020%2F1001%2Fr753892_1296x729_16%2D9.jpg",
//            "2020-10-04T02:46:00Z",
//            "In severe cases, the most common complication is pneumonia. The typical signs of a flu pneumonia are shortness of breath, especially when you exert yourself, and unusually rapid breathing doctors typ… [+1800 chars]"),
//
//        Article(3, Source(3, null.toString(),
//            "New York Times"),
//            "Donald G. McNeil Jr.",
//            "How to Identify the Different Symptoms of the Flu and Covid-19 - The New York Times",
//            "With fears of a “twindemic” in the United States this fall, here’s a guide to understanding what’s making you feel terrible.",
//            "https://www.nytimes.com/2020/10/03/at-home/coronavirus-flu-symptoms.html",
//            "https://static01.nyt.com/images/2020/10/04/multimedia/04-ah-symptoms/04-ah-symptoms-facebookJumbo.jpg",
//            "2020-10-04T02:46:00Z",
//            "In severe cases, the most common complication is pneumonia. The typical signs of a flu pneumonia are shortness of breath, especially when you exert yourself, and unusually rapid breathing doctors typ… [+1800 chars]"))

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

    private fun saveDataToDataBase(responseModel: ResponseModel) {
           for (article in responseModel.articles) {
            MainScope().launch {
                database.newsDao().deleteArticleTable()
                database.newsDao().deleteSourceTable()
                database.newsDao().insertArticle(article)
            }

//            database.newsDao().insertSource(article.source!!)
        }
    }

}

