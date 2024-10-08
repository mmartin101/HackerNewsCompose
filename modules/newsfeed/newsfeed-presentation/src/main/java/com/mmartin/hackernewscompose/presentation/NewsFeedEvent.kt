package com.mmartin.hackernewscompose.presentation

sealed class NewsFeedEvent {
    data object LoadNewsFeed : NewsFeedEvent()
    data class OpenNewsItemUrl(
        val id: Long,
        val url: String
    ) : NewsFeedEvent()
    data class ShowNewsItemDetail(val id: Long) : NewsFeedEvent()
}