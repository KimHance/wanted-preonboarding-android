package com.wanted.wanted_news.presentation.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.wanted.wanted_news.R

@BindingAdapter("bindImage")
fun ImageView.bindImage(imageUrl: String?) {
    imageUrl?.let {
        Glide.with(this).load(imageUrl).error(R.drawable.ic_baseline_image_not_supported)
            .centerCrop().into(this)
    }
}