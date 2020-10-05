package com.axproject.newsapplication.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "FAVORITE")
class Favorite(

//    @PrimaryKey(autoGenerate = true)
//    @Expose
//    var id: Int,

    @PrimaryKey
    @ColumnInfo(name = "articleId")
    @SerializedName("articleId")
    @Expose
    var articleTitle: String // TODO: 05.10.2020 Должен быть ID
)

