package com.wanted.wanted_news.domain

data class News(
    val title: String,
    val author: String,
    val content: String,
    val imageUrl: String,
    val time: String,
    val isSaved: Boolean = false
)