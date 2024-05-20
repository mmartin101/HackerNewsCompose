package com.mmartin.hackernewscompose.mvvm

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mmartin.hackernewscompose.newsfeed.ui.NewsFeedList

@Composable
fun NewsFeedView(viewModel: NewsFeedViewModel) {
  val state by viewModel.state.collectAsStateWithLifecycle()
  NewsFeedList(
    loading = state.loading,
    newsItems = state.newsItems
  )
}