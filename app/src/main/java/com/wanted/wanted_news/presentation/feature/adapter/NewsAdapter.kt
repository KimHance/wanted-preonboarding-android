package com.wanted.wanted_news.presentation.feature.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.wanted.wanted_news.R
import com.wanted.wanted_news.databinding.ItemNewsBinding
import com.wanted.wanted_news.domain.News

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

class NewsViewHolder(
    private val binding: ItemNewsBinding,
    private val itemClickListener: (News) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.apply {
            itemView.setOnClickListener {
                news?.run{
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