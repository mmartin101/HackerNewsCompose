package com.mmartin.hackernewscompose.newsfeed.interactor

import com.mmartin.hackernewscompose.api.NetworkModule
import com.mmartin.hackernewscompose.models.NewsItem
import com.mmartin.hackernewscompose.repository.NewsFeedDataRepository
import com.mmartin.hackernewscompose.repository.NewsFeedRepository
import dagger.Module
import dagger.Provides
import javax.inject.Inject

class NewsFeedInteractor @Inject constructor(private val newsFeedRepository: NewsFeedDataRepository) {
  //TODO: Add paging functionality
  suspend fun topStories(): List<NewsItem> {
    return newsFeedRepository.topStories(0)
  }
}