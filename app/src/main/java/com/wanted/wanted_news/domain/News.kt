package com.wanted.wanted_news.domain

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class News(
    @PrimaryKey
    val title: String = "No Title",
    val author: String? = "",
    val content: String? = "",
    val imageUrl: String? = "",
    val time: String? = "",
    val isSaved: Boolean = false
) : Parcelable