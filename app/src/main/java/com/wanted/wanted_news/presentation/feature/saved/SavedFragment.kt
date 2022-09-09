package com.wanted.wanted_news.presentation.feature.saved

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.findNavController
import com.wanted.wanted_news.R
import com.wanted.wanted_news.base.BaseFragment
import com.wanted.wanted_news.databinding.FragmentSavedBinding
import com.wanted.wanted_news.domain.News
import com.wanted.wanted_news.presentation.MainViewModel
import com.wanted.wanted_news.presentation.feature.adapter.SavedAdapter
import kotlinx.coroutines.launch

class SavedFragment : BaseFragment<FragmentSavedBinding>(R.layout.fragment_saved) {
    private val savedViewModel: MainViewModel by activityViewModels()
    private val savedNewsAdapter: SavedAdapter by lazy {
        SavedAdapter(
            itemClickListener = { doOnClick(it) }
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        savedViewModel.getSavedNews()
        initView()
        collectFlow()
    }

    private fun initView() {
        binding.commonSavedLayout.rvNews.apply {
            adapter = savedNewsAdapter
        }
    }

    private fun collectFlow() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                savedViewModel.savedNews.collect { savedList ->
                    savedNewsAdapter.submitList(savedList.toList())
                }
            }
        }
    }

    private fun doOnClick(item: News) {
        val action =
            SavedFragmentDirections.actionSavedFragmentToDetailFragment(
                item
            )
        requireView().findNavController().navigate(action)
    }
}