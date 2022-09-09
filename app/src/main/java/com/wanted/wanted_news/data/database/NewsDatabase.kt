package com.wanted.wanted_news.data.database

import android.content.Context
import androidx.room.*
import com.wanted.wanted_news.domain.News

@Database(
    entities = [News::class],
    version = 1,
    exportSchema = false
)
abstract class NewsDatabase : RoomDatabase() {
    abstract fun newsDao(): NewsDao

    companion object {
        fun getInstance(context: Context): NewsDatabase = Room
            .databaseBuilder(context, NewsDatabase::class.java, "news.db")
            .build()
    }

}

@Dao
interface NewsDao {

    @Query("SELECT * FROM News")
    suspend fun getNews(): List<News>

    @Insert
    suspend fun saveNews(news: News)

    @Delete
    suspend fun deleteNews(news: News)
}