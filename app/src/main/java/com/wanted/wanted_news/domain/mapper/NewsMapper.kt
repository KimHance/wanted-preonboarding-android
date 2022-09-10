package com.wanted.wanted_news.domain.mapper

import com.wanted.wanted_news.data.dto.NewsResponse
import com.wanted.wanted_news.domain.News
import java.text.SimpleDateFormat
import java.util.*

fun NewsResponse.toNews(): List<News> {
    val list = mutableListOf<News>()
    this.articles?.map {
        list.add(
            News(
                title = it.title,
                author = it.author,
                content = it.content,
                imageUrl = it.urlToImage,
                time = toIntervalBetweenDateText(it.publishedAt)
            )
        )
    }
    return list
}

fun toIntervalBetweenDateText(date: String): String {
    val timeFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.US)
    val now = timeFormat.format(Date(System.currentTimeMillis()))

    val nowFormat = timeFormat.parse(now)
    val beforeFormat = timeFormat.parse(date)

    val diffSec = (nowFormat.time - beforeFormat.time) / 1000
    val diffMin = (nowFormat.time - beforeFormat.time) / (60 * 1000)
    val diffHor = (nowFormat.time - beforeFormat.time) / (60 * 60 * 1000)
    val diffDays = diffSec / (24 * 60 * 60)

    return when {
        diffDays > 0 -> { "$diffDays days ago" }
        diffHor > 0 -> { "$diffHor hours ago" }
        diffMin > 0 -> { "$diffMin minutes ago" }
        else -> { "$diffSec seconds ago" }
    }
}