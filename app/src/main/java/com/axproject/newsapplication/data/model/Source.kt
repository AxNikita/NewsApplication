package com.axproject.newsapplication.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "SOURCE")
data class Source(

    @PrimaryKey(autoGenerate = true)
    @Expose
    var articleId: Int,

    @ColumnInfo(name = "newsId")
    @SerializedName("id")
    @Expose
    var newsId: String,

    @ColumnInfo(name = "name")
    @SerializedName("name")
    @Expose
    var name: String

)