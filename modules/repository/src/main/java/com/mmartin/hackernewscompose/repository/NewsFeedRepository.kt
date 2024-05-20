package com.mmartin.hackernewscompose.repository

import com.mmartin.hackernewscompose.models.NewsItem
import com.mmartin.hackernewscompose.repository.db.models.StoriesList
import com.mmartin.hackernewscompose.repository.db.models.StoriesListDB

interface NewsFeedRepository {

  suspend fun topStories(): StoriesList

  suspend fun saveStoriesList(type: String, idList: List<Long>) {}

  suspend fun items(page: List<Long>): List<NewsItem>
  suspend fun saveItems(page: List<NewsItem>) {}

  suspend fun invalidate()
}