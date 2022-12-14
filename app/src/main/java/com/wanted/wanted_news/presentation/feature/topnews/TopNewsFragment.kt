package com.wanted.wanted_news.presentation.feature.topnews

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.findNavController
import androidx.paging.LoadState
import com.wanted.wanted_news.R
import com.wanted.wanted_news.base.BaseFragment
import com.wanted.wanted_news.databinding.FragmentTopNewsBinding
import com.wanted.wanted_news.domain.News
import com.wanted.wanted_news.presentation.MainViewModel
import com.wanted.wanted_news.presentation.feature.adapter.NewsAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TopNewsFragment : BaseFragment<FragmentTopNewsBinding>(R.layout.fragment_top_news) {
    private val mainViewModel: MainViewModel by activityViewModels()
    private val newsAdapter: NewsAdapter by lazy {
        NewsAdapter(
            itemClickListener = { doOnClick(it) }
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainViewModel.getNewsList(null)
        initView()
        collectFlow()
    }

    private fun initView() {
        binding.commonTopNewsLayout.apply {
            rvNews.adapter = newsAdapter
            tbTitleBar.title = getString(R.string.top_news)
        }
    }

    private fun collectFlow() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                mainViewModel.newsList.collectLatest { newsList ->
                    newsAdapter.submitData(newsList)
                }
            }
        }
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                newsAdapter.loadStateFlow
                    .collectLatest {
                        showEmptyText(it.refresh is LoadState.Error)
                    }
            }
        }
    }

    private fun doOnClick(item: News) {
        val action =
            TopNewsFragmentDirections.actionTopNewsFragmentToDetailFragment(
                item
            )
        requireView().findNavController().navigate(action)
    }

    private fun showEmptyText(state: Boolean) {
        binding.commonTopNewsLayout.tvEmptyMessage.apply {
            text = context.getString(R.string.fetching_error)
            isVisible = state
        }
    }
}