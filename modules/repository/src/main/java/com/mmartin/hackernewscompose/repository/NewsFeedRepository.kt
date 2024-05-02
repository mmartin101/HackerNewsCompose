package com.mmartin.hackernewscompose.repository

import com.mmartin.hackernewscompose.models.NewsItem
import com.mmartin.hackernewscompose.repository.db.models.StoriesList

interface NewsFeedRepository {

  suspend fun topStories(): List<Long>

  suspend fun saveStoriesList(type: String, idList: List<Long>) {}

  suspend fun items(page: List<Long>): List<NewsItem>

  suspend fun invalidate()
}