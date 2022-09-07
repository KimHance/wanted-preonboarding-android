package com.wanted.wanted_news.presentation.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("bindImage")
fun ImageView.bindImage(imageUrl: String) {
    imageUrl.let {
        Glide.with(this).load(imageUrl).into(this)
    }
}