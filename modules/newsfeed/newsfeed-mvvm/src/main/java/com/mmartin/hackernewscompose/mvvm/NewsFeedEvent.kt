package com.mmartin.hackernewscompose.mvvm

sealed class NewsFeedEvent {

  object LoadNewsFeed : NewsFeedEvent()

  data class OpenNewsItemUrl(
    val id: Long,
    val url: String
  ) : NewsFeedEvent()

  object ShowNewsItemDetail : NewsFeedEvent()
}