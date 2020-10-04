package com.axproject.newsapplication.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.axproject.newsapplication.data.model.Article
import com.axproject.newsapplication.data.model.Source

@Dao
interface NewsDao {

    @Insert
    fun insertArticle(article: Article)

    @Insert
    fun insertSource(source: Source)

    @Query("DELETE FROM ARTICLE")
    fun deleteArticleTable()

    @Query("DELETE FROM SOURCE")
    fun deleteSourceTable()

}