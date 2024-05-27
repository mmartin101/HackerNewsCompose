package com.mmartin.hackernewscompose.mvvm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.mmartin.hackernewscompose.common.theme.AppTheme
import com.mmartin.hackernewscompose.mvvm.viewmodel.NewsFeedViewModel
import com.mmartin.hackernewscompose.mvvm.viewmodel.NewsFeedViewModelFactory

class NewsFeedMvvmActivity : ComponentActivity() {
  private val viewModel by viewModels<NewsFeedViewModel> { NewsFeedViewModelFactory }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    viewModel.loadNewsFeed()
    setContent {
      AppTheme {
        NewsFeedView(viewModel = viewModel)
      }
    }
  }
}