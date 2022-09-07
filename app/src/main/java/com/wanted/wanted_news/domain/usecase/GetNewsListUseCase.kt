package com.wanted.wanted_news.domain.usecase

import com.wanted.wanted_news.data.repository.remote.RemoteNewsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetNewsListUseCase @Inject constructor(
    private val remoteNewsRepository: RemoteNewsRepository
) {
    suspend operator fun invoke(category: String) =
        remoteNewsRepository.getNews(category)?.flowOn(Dispatchers.Default)
}