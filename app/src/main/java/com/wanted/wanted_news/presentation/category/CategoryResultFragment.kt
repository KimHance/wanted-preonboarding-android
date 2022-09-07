package com.wanted.wanted_news.presentation.category

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.paging.LoadState
import com.wanted.wanted_news.R
import com.wanted.wanted_news.base.BaseFragment
import com.wanted.wanted_news.databinding.FragmentCategoryResultBinding
import com.wanted.wanted_news.domain.News
import com.wanted.wanted_news.presentation.LoadingDialog
import com.wanted.wanted_news.presentation.MainViewModel
import com.wanted.wanted_news.presentation.adapter.NewsAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CategoryResultFragment :
    BaseFragment<FragmentCategoryResultBinding>(R.layout.fragment_category_result) {

    private lateinit var loadingDialog: LoadingDialog
    private val resultViewModel: MainViewModel by activityViewModels()
    private val newsAdapter: NewsAdapter by lazy {
        NewsAdapter(
            itemClickListener = { doOnClick(it) }
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadingDialog = LoadingDialog(requireContext())
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
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                newsAdapter.loadStateFlow
                    .collectLatest {
                        if (it.source.append is LoadState.Loading) loadingDialog.show()
                        else loadingDialog.dismiss()
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