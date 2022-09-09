package com.wanted.wanted_news.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.wanted.wanted_news.domain.News
import com.wanted.wanted_news.domain.usecase.DeleteNewsUseCase
import com.wanted.wanted_news.domain.usecase.GetNewsListUseCase
import com.wanted.wanted_news.domain.usecase.GetSavedNewsUseCase
import com.wanted.wanted_news.domain.usecase.SaveNewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getNewsListUseCase: GetNewsListUseCase,
    private val getSavedNewsUseCase: GetSavedNewsUseCase,
    private val saveNewsUseCase: SaveNewsUseCase,
    private val deleteNewsUseCase: DeleteNewsUseCase
) : ViewModel() {

    private val _newsList = MutableStateFlow<PagingData<News>>(PagingData.empty())
    val newsList = _newsList.asStateFlow()

    private val _savedNews = MutableStateFlow<List<News>>(emptyList())
    val savedNews = _savedNews.asStateFlow()

    val selectedNews = MutableStateFlow(News())

    fun getNewsList(category: String?) {
        viewModelScope.launch {
            getNewsListUseCase(category)
                ?.cachedIn(viewModelScope)
                ?.collectLatest { newsList ->
                    _newsList.emit(newsList)
                }
        }
    }

    fun setSelectedNews(news: News) {
        selectedNews.update {
            news
        }
    }

    fun getSavedNews() {
        viewModelScope.launch {
            _savedNews.update {
                getSavedNewsUseCase()
            }
        }
    }

    fun changeSaveState(check: Boolean) {
        if (check) saveNews()
        else deleteSavedNews()
    }

    private fun deleteSavedNews() {
        viewModelScope.launch {
            selectedNews.update {
                selectedNews.value.copy(isSaved = false)
            }
            deleteNewsUseCase(selectedNews.value)
        }
    }

    private fun saveNews() {
        viewModelScope.launch {
            selectedNews.update {
                selectedNews.value.copy(isSaved = true)
            }
            saveNewsUseCase(selectedNews.value)
        }
    }

}