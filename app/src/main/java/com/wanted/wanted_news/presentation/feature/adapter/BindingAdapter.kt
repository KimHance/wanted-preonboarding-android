package com.wanted.wanted_news.presentation.feature.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.wanted.wanted_news.R

@BindingAdapter("bindImage")
fun ImageView.bindImage(imageUrl: String?) {
    imageUrl?.let {
        Glide.with(this).load(imageUrl).error(R.drawable.ic_no_image)
            .placeholder(R.drawable.ic_loading)
            .centerCrop().into(this)
    }
}

@BindingAdapter("bindDetailImage")
fun ImageView.bindDetailImage(imageUrl: String?) {
    imageUrl?.let {
        Glide.with(this).load(imageUrl).error(R.drawable.ic_no_image)
            .placeholder(R.drawable.ic_loading)
            .transform(CenterCrop(), RoundedCorners(20)).into(this)
    }
}

@BindingAdapter("bindCategory")
fun ImageView.bindCategory(imageUrl: Int) {
    Glide.with(this).load(imageUrl).into(this)
}

@BindingAdapter("bindBookMark")
fun ImageView.bindBookMark(state: Boolean) {
    if (state) {
        this.setImageResource(R.drawable.ic_star_marked)
    } else {
        this.setImageResource(R.drawable.ic_star)
    }
}