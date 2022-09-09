package com.wanted.wanted_news.domain

import com.wanted.wanted_news.data.dto.NewsResponse
import com.wanted.wanted_news.domain.model.News

fun NewsResponse.toNews(): List<News> {
    val list = mutableListOf<News>()
    this.articles?.map {
        list.add(
            News(
                title = it.title,
                author = it.author,
                content = it.content,
                imageUrl = it.urlToImage,
                time = it.publishedAt
            )
        )
    }
    return list
}