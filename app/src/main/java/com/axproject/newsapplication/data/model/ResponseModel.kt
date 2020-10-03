package com.axproject.newsapplication.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ResponseModel(

    @SerializedName("status")
    @Expose
    var status: String,

    @SerializedName("totalResults")
    @Expose
    var totalResults: Int,

    @SerializedName("articles")
    @Expose
    var articles: List<Article>
)