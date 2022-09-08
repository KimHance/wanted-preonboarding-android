package com.wanted.wanted_news.presentation.feature.category

import android.os.Bundle
import android.view.View
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.wanted.wanted_news.R
import com.wanted.wanted_news.base.BaseFragment
import com.wanted.wanted_news.databinding.FragmentCategoryBinding
import com.wanted.wanted_news.domain.CATEGORY
import com.wanted.wanted_news.presentation.feature.adapter.CategoryAdapter

class CategoryFragment : BaseFragment<FragmentCategoryBinding>(R.layout.fragment_category) {

    private val categoryAdapter: CategoryAdapter by lazy {
        CategoryAdapter(
            itemClickListener = { doOnClick(it) }
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        binding.rvCategory.apply {
            layoutManager = GridLayoutManager(requireContext(), 3)
            adapter = categoryAdapter
        }
        categoryAdapter.submitList(categoryList)
    }

    private fun doOnClick(item: CATEGORY) {
        val action =
            CategoryFragmentDirections.actionCategoryFragmentToCategoryResultFragment(
                item.category
            )
        requireView().findNavController().navigate(action)
    }

    companion object {
        private val categoryList = listOf<CATEGORY>(
            CATEGORY.BUSINESS,
            CATEGORY.GENERAL,
            CATEGORY.ENTERTAINMENT,
            CATEGORY.HEALTH,
            CATEGORY.SCIENCE,
            CATEGORY.SPORTS,
            CATEGORY.TECHNOLOGY
        )
    }
}