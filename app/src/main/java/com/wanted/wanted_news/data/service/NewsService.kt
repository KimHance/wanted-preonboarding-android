package com.wanted.wanted_news.data.service

import com.wanted.wanted_news.base.di.RetrofitModule.Companion.API_KEY
import com.wanted.wanted_news.data.dto.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {

    @GET("v2/top-headlines")
    suspend fun getNews(
        @Query("country") country: String = "us",
        @Query("category") category: String? = null,
        @Query("apiKey") key: String = API_KEY
    ): NewsResponse

}