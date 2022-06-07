package com.kotlindev.newsforyou.ui.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.kotlindev.newsforyou.ui.models.Article


@Dao
interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(article: Article):Long //using suspend: the function executes in background thread

    @Query("SELECT * FROM articles")
    fun getAllArticles() :LiveData<List<Article>>

    @Delete
    suspend fun deleteArticle(article: Article)
}