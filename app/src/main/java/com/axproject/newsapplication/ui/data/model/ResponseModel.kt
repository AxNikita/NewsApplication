package com.axproject.newsapplication.ui.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ResponseModel(

    @SerializedName("status")
    @Expose
    private var status: String,

    @SerializedName("totalResults")
    @Expose
    private var totalResults: Int,

    @SerializedName("articles")
    @Expose
    private var articles: List<Article>
)