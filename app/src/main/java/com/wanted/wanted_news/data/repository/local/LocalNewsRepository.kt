package com.wanted.wanted_news.data.repository.local

import com.wanted.wanted_news.domain.News

interface LocalNewsRepository {
    suspend fun getSaveNews(): List<News>

    suspend fun saveNews(news: News)

    suspend fun deleteNews(news: News)
}