package com.wanted.wanted_news.domain.model

import com.wanted.wanted_news.R

enum class CATEGORY(val category: String, val img: Int) {
    BUSINESS("business", R.drawable.ic_business),
    ENTERTAINMENT("entertainment", R.drawable.ic_music),
    GENERAL("general", R.drawable.ic_group),
    HEALTH("health", R.drawable.ic_cross),
    SCIENCE("science", R.drawable.ic_science),
    SPORTS("sports", R.drawable.ic_sports),
    TECHNOLOGY("technology", R.drawable.ic_robot)
}