package com.wanted.wanted_news.presentation.feature.category

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.wanted.wanted_news.R
import com.wanted.wanted_news.base.BaseFragment
import com.wanted.wanted_news.databinding.FragmentCategoryResultBinding
import com.wanted.wanted_news.domain.News
import com.wanted.wanted_news.presentation.MainViewModel
import com.wanted.wanted_news.presentation.feature.adapter.NewsAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class CategoryResultFragment :
    BaseFragment<FragmentCategoryResultBinding>(R.layout.fragment_category_result) {

    private val resultViewModel: MainViewModel by activityViewModels()
    private val newsAdapter: NewsAdapter by lazy {
        NewsAdapter(
            itemClickListener = { doOnClick(it) }
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        fetchNews()
        collectFlow()
    }

    private fun initView() {
        binding.commonCategoryNewsLayout.rvNews.apply {
            adapter = newsAdapter
        }
    }

    private fun collectFlow() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                resultViewModel.newsList.collectLatest { newsList ->
                    newsAdapter.submitData(newsList)
                }
            }
        }
    }

    private fun fetchNews() {
        val args: CategoryResultFragmentArgs by navArgs()
        resultViewModel.getNewsList(args.category)
    }

    private fun doOnClick(item: News) {
        val action =
            CategoryResultFragmentDirections.actionCategoryResultFragmentToDetailFragment(
                item
            )
        requireView().findNavController().navigate(action)
    }
}