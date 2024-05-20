package com.mmartin.hackernewscompose.mvvm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mmartin.hackernewscompose.repository.NewsFeedDataRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class NewsFeedViewModel @Inject constructor(val newsFeedRepository: NewsFeedDataRepository): ViewModel() {

  private val _state = MutableStateFlow(NewsFeedState())
  val state = _state.asStateFlow()

  init {
    viewModelScope.launch {
      val stories = newsFeedRepository.topStories()
      val newsItems = newsFeedRepository.items(stories.storyIds.subList(0, 100))
      _state.value = NewsFeedState(false, newsItems)
    }
  }

  fun loadNewsFeed() {

  }

}