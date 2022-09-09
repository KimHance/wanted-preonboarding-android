package com.wanted.wanted_news.domain.usecase

import com.wanted.wanted_news.data.repository.local.LocalNewsRepository
import com.wanted.wanted_news.domain.News
import javax.inject.Inject

class DeleteNewsUseCase @Inject constructor(
    private val localNewsRepository: LocalNewsRepository
) {
    suspend operator fun invoke(news: News) =
        localNewsRepository.deleteNews(news)
}