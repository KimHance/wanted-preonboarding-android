package com.wanted.wanted_news.data.repository.local

import com.wanted.wanted_news.data.database.NewsDao
import com.wanted.wanted_news.domain.News
import javax.inject.Inject

class LocalNewsRepositoryImpl @Inject constructor(
    private val newsDao: NewsDao
) : LocalNewsRepository {
    override suspend fun getSaveNews(): List<News> =
        newsDao.getNews()

    override suspend fun saveNews(news: News) =
        newsDao.saveNews(news)

    override suspend fun deleteNews(news: News) =
        newsDao.deleteNews(news)
}