package com.wanted.wanted_news.domain.usecase

import com.wanted.wanted_news.data.repository.local.LocalNewsRepository
import javax.inject.Inject

class GetSavedNewsUseCase @Inject constructor(
    private val localNewsRepository: LocalNewsRepository
) {
    suspend operator fun invoke() =
        localNewsRepository.getSaveNews()
}