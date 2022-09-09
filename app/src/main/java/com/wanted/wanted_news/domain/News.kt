package com.wanted.wanted_news.domain

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class News(
    var title: String? = "",
    var author: String? = "",
    var content: String? = "",
    var imageUrl: String? = "",
    var time: String? = "",
    val isSaved: Boolean = false
) : Parcelable {
    @NonNull
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}