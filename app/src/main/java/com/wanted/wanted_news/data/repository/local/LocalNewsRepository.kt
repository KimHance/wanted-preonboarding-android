package com.wanted.wanted_news.data.repository.local

import com.wanted.wanted_news.domain.model.News
import kotlinx.coroutines.flow.Flow

interface LocalNewsRepository {
    suspend fun getSaveNews(): Flow<List<News>>

    suspend fun saveNews(news: News)

    suspend fun deleteNews(news: News)
}