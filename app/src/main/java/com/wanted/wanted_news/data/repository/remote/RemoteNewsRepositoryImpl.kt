package com.wanted.wanted_news.data.repository.remote

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.wanted.wanted_news.data.paging.NewsPagingSource
import com.wanted.wanted_news.data.service.NewsService
import com.wanted.wanted_news.domain.News
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RemoteNewsRepositoryImpl @Inject constructor(
    private val service: NewsService
) : RemoteNewsRepository {

    companion object {
        const val PAGE_SIZE = 30
    }

    override suspend fun getNews(category: String): Flow<PagingData<News>> {
        return Pager(PagingConfig(PAGE_SIZE)) {
            NewsPagingSource(service, category)
        }.flow
    }
}