package com.mmartin.hackernewscompose.newsfeed.interactor

import com.mmartin.hackernewscompose.models.NewsItem
import com.mmartin.hackernewscompose.repository.NewsFeedDataRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class NewsFeedInteractor @Inject constructor(private val newsFeedRepository: NewsFeedDataRepository) {

  // TODO: Add paging
  suspend fun topStories(page: Int): List<NewsItem> {
    return withContext(Dispatchers.IO) {
      val topStories = newsFeedRepository.topStories()
      newsFeedRepository.items(topStories.storyIds.subList(0, 50))
    }
  }
}