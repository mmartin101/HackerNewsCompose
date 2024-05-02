package com.mmartin.hackernewscompose.newsfeed.interactor

import android.text.format.DateUtils
import com.mmartin.hackernewscompose.models.NewsItem
import com.mmartin.hackernewscompose.repository.NewsFeedDataRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class NewsFeedInteractor @Inject constructor(private val newsFeedRepository: NewsFeedDataRepository) {

  suspend fun topStories(page: Int): List<NewsItem> {
    return withContext(Dispatchers.IO) {
      val ids = newsFeedRepository.topStories()
      newsFeedRepository.items(ids.subList(0, 50))
    }
  }
}