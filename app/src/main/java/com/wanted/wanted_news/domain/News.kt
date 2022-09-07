package com.wanted.wanted_news.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class News(
    val title: String? = "",
    val author: String? = "",
    val content: String? = "",
    val imageUrl: String? = "",
    val time: String? = "",
    val isSaved: Boolean = false
) : Parcelable