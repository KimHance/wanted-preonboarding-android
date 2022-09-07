package com.wanted.wanted_news.di

import com.wanted.wanted_news.data.repository.remote.RemoteNewsRepository
import com.wanted.wanted_news.data.repository.remote.RemoteNewsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class NewsModule {

    @Binds
    @Singleton
    abstract fun bindRemoteNewsRepository(
        repositoryImpl: RemoteNewsRepositoryImpl
    ): RemoteNewsRepository

}