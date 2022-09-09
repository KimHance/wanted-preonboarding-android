package com.wanted.wanted_news.presentation.feature.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.wanted.wanted_news.R
import com.wanted.wanted_news.domain.model.News
import com.wanted.wanted_news.presentation.feature.viewholder.NewsViewHolder

class NewsAdapter(private val itemClickListener: (News) -> Unit) :
    PagingDataAdapter<News, NewsViewHolder>(newsDiffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_news,
                parent,
                false
            ),
            itemClickListener
        )
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

    companion object {
        private val newsDiffUtil = object : DiffUtil.ItemCallback<News>() {
            override fun areItemsTheSame(oldItem: News, newItem: News): Boolean =
                oldItem.title == newItem.title

            override fun areContentsTheSame(oldItem: News, newItem: News): Boolean =
                oldItem == newItem

        }
    }
}
