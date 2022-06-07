package com.kotlindev.newsforyou.ui.repository

import com.kotlindev.newsforyou.ui.api.RetrofitInstance
import com.kotlindev.newsforyou.ui.db.ArticleDatabase
import com.kotlindev.newsforyou.ui.models.Article

class NewsRepository(val db: ArticleDatabase) {

    suspend fun getBrakingNews(countryCode:String,pageNumber:Int) =
        RetrofitInstance.api.getBreakingNews(countryCode,pageNumber)

    suspend fun searchNews(SearchQuery:String,pageNumber: Int) =
        RetrofitInstance.api.searchForNews(SearchQuery,pageNumber)
    suspend fun upsert(article: Article) = db.getArticleDao().upsert(article)

    fun getSavedNews() = db.getArticleDao().getAllArticles()

    suspend fun deleteArticle(article: Article) = db.getArticleDao().deleteArticle(article)
}
