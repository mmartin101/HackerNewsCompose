package com.mmartin.hackernewscompose.presentation

import com.mmartin.hackernewscompose.models.NewsItem

data class NewsFeedState(
  val loading: Boolean = true,
  val newsItems: List<NewsItem> = emptyList(),
  val error: Throwable? = null
)