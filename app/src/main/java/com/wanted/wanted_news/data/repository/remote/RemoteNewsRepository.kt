package com.wanted.wanted_news.data.repository.remote

import androidx.paging.PagingData
import com.wanted.wanted_news.domain.News
import kotlinx.coroutines.flow.Flow

interface RemoteNewsRepository {

    suspend fun getNews(category: String?): Flow<PagingData<News>>?

}