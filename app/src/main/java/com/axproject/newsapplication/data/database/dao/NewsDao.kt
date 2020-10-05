package com.axproject.newsapplication.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.axproject.newsapplication.data.model.Article
import com.axproject.newsapplication.data.model.Favorite
import com.axproject.newsapplication.data.model.Source

@Dao
interface NewsDao {

    @Insert
    fun insertArticle(article: Article)

    @Insert
    fun insertSource(source: Source)

    @Insert
    fun insertFavorite(favoriteModel: Favorite)

    @Query("SELECT * FROM FAVORITE")
    fun getAllFavorites() : List<Favorite>

    @Query("DELETE FROM FAVORITE")
    fun deleteFavoriteTable()

    @Query("DELETE FROM ARTICLE")
    fun deleteArticleTable()

    @Query("DELETE FROM SOURCE")
    fun deleteSourceTable()

}