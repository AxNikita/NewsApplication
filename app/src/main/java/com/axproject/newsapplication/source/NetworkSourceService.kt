package com.axproject.newsapplication.source



import com.axproject.newsapplication.data.model.ResponseModel
import retrofit2.Call
import retrofit2.http.GET

interface NetworkSourceService {

    @GET("/v2/top-headlines?q=air&pageSize=25&apiKey=e65ee0938a2a43ebb15923b48faed18d") // TODO: 04.10.2020 Убрать ключ
    fun topHeadlines() : Call<ResponseModel>

    @GET("/v2/everything?q=air&pageSize=25&apiKey=e65ee0938a2a43ebb15923b48faed18d") // TODO: 04.10.2020 Убрать ключ
    fun everything () : Call<ResponseModel>

}