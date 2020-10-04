package com.axproject.newsapplication.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "ARTICLE")
data class Article(
    @PrimaryKey(autoGenerate = true)
    @Expose
    var id: Int,

//    @Ignore
//    @SerializedName("source")
//    @Expose
//    var source: Source,

    @ColumnInfo(name = "author")
    @SerializedName("author")
    @Expose
    var author: String,

    @ColumnInfo(name = "title")
    @SerializedName("title")
    @Expose
    var title: String,

    @ColumnInfo(name = "description")
    @SerializedName("description")
    @Expose
    var description: String,

    @ColumnInfo(name = "url")
    @SerializedName("url")
    @Expose
    var url: String,

    @ColumnInfo(name = "urlToImage")
    @SerializedName("urlToImage")
    @Expose
    var urlToImage: String,

    @ColumnInfo(name = "publishedAt")
    @SerializedName("publishedAt")
    @Expose
    var publishedAt: String,

    @ColumnInfo(name = "content")
    @SerializedName("content")
    @Expose
    var content: String
)