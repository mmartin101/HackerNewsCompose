package com.mmartin.hackernewscompose.mvvm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.mmartin.hackernewscompose.framework.theme.HackerNewsComposeTheme
import com.mmartin.hackernewscompose.mvvm.viewmodel.NewsFeedViewModel
import com.mmartin.hackernewscompose.mvvm.viewmodel.NewsFeedViewModelFactory

class NewsFeedMvvmActivity : ComponentActivity() {
  private val viewModel by viewModels<NewsFeedViewModel> { NewsFeedViewModelFactory }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      HackerNewsComposeTheme {
        NewsFeedView(viewModel = viewModel)
      }
    }
  }
}