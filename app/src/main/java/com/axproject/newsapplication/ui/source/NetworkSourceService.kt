package com.axproject.newsapplication.ui.source



import com.axproject.newsapplication.ui.data.model.ResponseModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST

interface NetworkSourceService {

    @GET("/v2/top-headlines?q=air")
    fun topHeadlines() : Call<ResponseModel>

    @GET("/v2/everything?q=air")
    fun everything () : Call<ResponseModel>

}