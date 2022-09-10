package com.wanted.wanted_news.presentation.feature.detail

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
import com.wanted.wanted_news.databinding.FragmentDetailBinding
import com.wanted.wanted_news.presentation.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailFragment : BaseFragment<FragmentDetailBinding>(R.layout.fragment_detail) {

    private val detailViewModel: MainViewModel by activityViewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        collectFlow()
        fetchNews()
        initView()
    }

    private fun initView() {
        binding.apply {
            ivBookMark.setOnClickListener {
                detailViewModel.apply {
                    changeSaveState(!selectedNews.value.isSaved)
                }
            }
            tbTitleBar.apply {
                setNavigationIcon(R.drawable.ic_baseline_arrow_back)
                setNavigationOnClickListener {
                    it.findNavController().popBackStack()
                }
                title = detailViewModel.selectedNews.value.title
            }
        }
    }

    private fun collectFlow() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                detailViewModel.selectedNews.collect { news ->
                    binding.news = news
                }
            }
        }
    }

    private fun fetchNews() {
        val args: DetailFragmentArgs by navArgs()
        detailViewModel.setSelectedNews(args.news)
        detailViewModel.checkSaved(args.news)
    }
}