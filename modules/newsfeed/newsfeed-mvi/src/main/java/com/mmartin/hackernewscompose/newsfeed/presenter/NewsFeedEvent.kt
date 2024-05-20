package com.mmartin.hackernewscompose.newsfeed.presenter

sealed class NewsFeedEvent {
  data class LoadNewsFeed(val page: Int): NewsFeedEvent()
  data class ShowNewsFeedItemDetail(val id: String): NewsFeedEvent()
  data class OpenNewsFeedItemUrl(val url: String): NewsFeedEvent()
}