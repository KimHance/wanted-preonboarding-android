package com.wanted.wanted_news.di

import com.wanted.wanted_news.data.repository.remote.RemoteNewsRepository
import com.wanted.wanted_news.data.repository.remote.RemoteNewsRepositoryImpl
import com.wanted.wanted_news.data.service.NewsService
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class NewsModule {

    @Binds
    @Singleton
    abstract fun bindRemoteNewsRepository(
        repositoryImpl: RemoteNewsRepositoryImpl
    ): RemoteNewsRepository

    companion object {
        @Provides
        @Singleton
        fun provideNewsService(retrofit: Retrofit): NewsService =
            retrofit.create(NewsService::class.java)
    }
}