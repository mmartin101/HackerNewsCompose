package com.mmartin.hackernewscompose.mvvm

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mmartin.hackernewscompose.mvvm.viewmodel.NewsFeedViewModel
import com.mmartin.hackernewscompose.newsfeed.ui.NewsFeedList

@Composable
fun NewsFeedView(viewModel: NewsFeedViewModel) {
  val state by viewModel.state.collectAsStateWithLifecycle()
  NewsFeedList(
    loading = state.loading,
    newsItems = state.newsItems,
    onTapAction = { newsItem ->
      val event = if (newsItem.url.isEmpty()) {
        NewsFeedEvent.ShowNewsItemDetail
      } else {
        NewsFeedEvent.OpenNewsItemUrl(newsItem.id, newsItem.url)
      }
      viewModel.onEvent(event)
    },
    onTapCommentsAction = { newsItem -> viewModel.onEvent(NewsFeedEvent.ShowNewsItemDetail) }
  )
}