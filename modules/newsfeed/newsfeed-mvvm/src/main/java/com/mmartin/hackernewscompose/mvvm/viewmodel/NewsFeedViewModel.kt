package com.mmartin.hackernewscompose.mvvm.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mmartin.hackernewscompose.mvvm.NewsFeedEvent
import com.mmartin.hackernewscompose.mvvm.NewsFeedEvent.LoadNewsFeed
import com.mmartin.hackernewscompose.mvvm.NewsFeedEvent.OpenNewsItemUrl
import com.mmartin.hackernewscompose.mvvm.NewsFeedEvent.ShowNewsItemDetail
import com.mmartin.hackernewscompose.mvvm.NewsFeedState
import com.mmartin.hackernewscompose.repository.NewsFeedDataRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class NewsFeedViewModel @Inject constructor(val newsFeedRepository: NewsFeedDataRepository): ViewModel() {

  private val _state = MutableStateFlow(NewsFeedState())
  val state = _state.asStateFlow()

  // TODO implement paging
  fun loadNewsFeed() {
    viewModelScope.launch {
      val stories = newsFeedRepository.topStories()
      val newsItems = newsFeedRepository.items(stories.storyIds.subList(0, 100))
      _state.value = NewsFeedState(false, newsItems)
    }
  }

  fun onEvent(event: NewsFeedEvent) {
    when (event) {
      is LoadNewsFeed -> loadNewsFeed()
      is OpenNewsItemUrl -> Timber.w("TODO: implement open url")
      ShowNewsItemDetail -> Timber.w("TODO: implement show detail")
    }
  }

}