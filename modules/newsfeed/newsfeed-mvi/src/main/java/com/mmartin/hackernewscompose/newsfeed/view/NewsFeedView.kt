package com.mmartin.hackernewscompose.newsfeed.view

import android.content.Context
import android.util.AttributeSet
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import com.mmartin.hackernewscompose.framework.MviView
import com.mmartin.hackernewscompose.framework.theme.HackerNewsComposeTheme
import com.mmartin.hackernewscompose.models.NewsItem
import com.mmartin.hackernewscompose.newsfeed.presenter.NewsFeedEvent
import com.mmartin.hackernewscompose.newsfeed.presenter.NewsFeedState
import com.mmartin.hackernewscompose.newsfeed.ui.NewsFeedList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow

class NewsFeedView @JvmOverloads constructor(
  context: Context, attrs: AttributeSet? = null
) : MviView<NewsFeedEvent, NewsFeedState>(context, attrs) {
  private var state: NewsFeedState? by mutableStateOf(null)
  private val events = MutableSharedFlow<NewsFeedEvent>()

  override fun eventsFlow(): Flow<NewsFeedEvent> {
    return events
  }

  override fun render(state: NewsFeedState) {
    this.state = state
  }

  @Composable
  override fun Content() {
    val state = if (isInEditMode) {
      NewsFeedState()
    } else {
      state ?: return
    }
    val scope = rememberCoroutineScope()
    // TODO use events for ui events

    HackerNewsComposeTheme {
      NewsFeedScreen(
        loading = state.loading,
        newsItems = state.newsItems
      )
    }
  }
}

@Composable
fun NewsFeedScreen(loading: Boolean = false, newsItems: List<NewsItem>) {
  NewsFeedList(loading = loading, newsItems = newsItems)
}