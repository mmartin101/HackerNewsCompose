package com.mmartin.hackernewscompose.newsfeed.presenter

import com.mmartin.hackernewscompose.models.NewsItem

data class NewsFeedState(
  val loading: Boolean = true,
  val newsItems: List<NewsItem> = emptyList(),
  val error: Throwable? = null
)