package com.kotlindev.newsforyou.ui.db

import android.content.Context
import androidx.room.*
import com.kotlindev.newsforyou.ui.models.Article

@Database(
entities = [Article::class], //its array since there can be multiple entities
version=1
)

@TypeConverters(Converters::class)

abstract class ArticleDatabase :RoomDatabase() {
    abstract fun getArticleDao(): ArticleDao
//singleton
    companion object {
        @Volatile
        private var instance: ArticleDatabase? = null //creating instance of db, which will be singleton
        private val LOCK = Any()
        //in invoke fun when we create instance of ArticleDb class, if instance is null, then we instance in synchronized block
        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: createDatabase(context).also { instance = it }
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                ArticleDatabase::class.java,
                "article_db.db"
            ).build()
    }
}