package com.wanted.wanted_news.presentation.feature.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.wanted.wanted_news.databinding.ItemNewsBinding
import com.wanted.wanted_news.domain.News

class NewsViewHolder(
    private val binding: ItemNewsBinding,
    private val itemClickListener: (News) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.apply {
            itemView.setOnClickListener {
                news?.run {
                    itemClickListener(this)
                }
            }
        }
    }

    fun bind(item: News) {
        binding.apply {
            news = item
            executePendingBindings()
        }
    }
}