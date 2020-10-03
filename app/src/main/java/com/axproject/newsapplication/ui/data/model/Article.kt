package com.axproject.newsapplication.ui.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Article(

    @SerializedName("source")
    @Expose
    private var source: Source,

    @SerializedName("author")
    @Expose
    private var author: String,

    @SerializedName("title")
    @Expose
    private var title: String,

    @SerializedName("description")
    @Expose
    private var description: String,

    @SerializedName("url")
    @Expose
    private var url: String,

    @SerializedName("urlToImage")
    @Expose
    private var urlToImage: String,

    @SerializedName("publishedAt")
    @Expose
    private var publishedAt: String,

    @SerializedName("content")
    @Expose
    private var content: String
)