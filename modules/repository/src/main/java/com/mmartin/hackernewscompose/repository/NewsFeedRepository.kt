package com.mmartin.hackernewscompose.repository

import com.mmartin.hackernewscompose.models.NewsItem

interface NewsFeedRepository {
  suspend fun topStories(page: Int): List<NewsItem>
  suspend fun invalidate()
}