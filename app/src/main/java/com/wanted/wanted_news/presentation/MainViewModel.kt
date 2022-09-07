package com.wanted.wanted_news.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.wanted.wanted_news.domain.News
import com.wanted.wanted_news.domain.usecase.GetNewsListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getNewsListUseCase: GetNewsListUseCase
) : ViewModel() {

    private val _newsList = MutableStateFlow<PagingData<News>>(PagingData.empty())
    val newsList = _newsList.asStateFlow()

    fun getNewsList(category: String?) {
        viewModelScope.launch {
            getNewsListUseCase(category)
                ?.cachedIn(viewModelScope)
                ?.collectLatest { newsList ->
                    _newsList.emit(newsList)
                }
        }
    }

}