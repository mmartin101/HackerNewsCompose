package com.mmartin.hackernewscompose.mvvm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.mmartin.hackernewscompose.framework.theme.HackerNewsComposeTheme

class NewsFeedMvvmActivity : ComponentActivity() {
  private val viewModel by lazy {
    NewsFeedComponent(applicationContext).createViewModel()
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      HackerNewsComposeTheme {
        NewsFeedView(viewModel = viewModel)
      }
    }
  }
}