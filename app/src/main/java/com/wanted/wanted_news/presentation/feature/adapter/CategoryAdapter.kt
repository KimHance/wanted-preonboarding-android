package com.wanted.wanted_news.presentation.feature.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.wanted.wanted_news.R
import com.wanted.wanted_news.databinding.ItemCategoryBinding
import com.wanted.wanted_news.domain.model.CATEGORY

class CategoryAdapter(private val itemClickListener: (CATEGORY) -> Unit) :
    ListAdapter<CATEGORY, CategoryViewHolder>(categoryDiffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_category,
                parent,
                false
            ),
            itemClickListener
        )
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        private val categoryDiffUtil = object : DiffUtil.ItemCallback<CATEGORY>() {
            override fun areItemsTheSame(oldItem: CATEGORY, newItem: CATEGORY): Boolean =
                oldItem.category == newItem.category

            override fun areContentsTheSame(oldItem: CATEGORY, newItem: CATEGORY): Boolean =
                oldItem == newItem
        }
    }

}

class CategoryViewHolder(
    private val binding: ItemCategoryBinding,
    private val itemClickListener: (CATEGORY) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.apply {
            itemView.setOnClickListener {
                category!!.run {
                    itemClickListener(this)
                }
            }
        }
    }

    fun bind(item: CATEGORY) {
        binding.apply {
            category = item
            executePendingBindings()
        }
    }
}