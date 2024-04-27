package com.mmartin.hackernewscompose.newsfeed.ui

import android.content.Context
import android.util.AttributeSet
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.mmartin.hackernewscompose.framework.MviView
import com.mmartin.hackernewscompose.newsfeed.presenter.NewsFeedEvent
import com.mmartin.hackernewscompose.newsfeed.presenter.NewsFeedState
import com.mmartin.hackernewscompose.ui.theme.HackerNewsComposeTheme
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
    // val scope = rememberCoroutineScope()

    HackerNewsComposeTheme {
      NewsFeedViewComposable(state = state)
    }
  }
}

@Composable
fun NewsFeedViewComposable(state: NewsFeedState) {
  Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
    Text(
      color = Color.Black,
      text = if (state.loading) "feed is loading" else "show feed items"
    )
  }
}

@Preview(showBackground = true)
@Composable
fun NewsFeedViewPreview() {
  HackerNewsComposeTheme {
    NewsFeedViewComposable(state = NewsFeedState(loading = false))
  }
}