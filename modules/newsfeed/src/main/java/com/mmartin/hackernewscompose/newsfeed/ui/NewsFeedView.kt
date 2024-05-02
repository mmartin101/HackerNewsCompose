package com.mmartin.hackernewscompose.newsfeed.ui

import android.content.Context
import android.text.format.DateUtils
import android.util.AttributeSet
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mmartin.hackernewscompose.framework.MviView
import com.mmartin.hackernewscompose.framework.theme.HackerNewsComposeTheme
import com.mmartin.hackernewscompose.models.NewsItem
import com.mmartin.hackernewscompose.models.NewsItemClassification.STORY
import com.mmartin.hackernewscompose.newsfeed.presenter.NewsFeedEvent
import com.mmartin.hackernewscompose.newsfeed.presenter.NewsFeedState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import java.time.LocalDateTime
import java.time.ZoneId
import kotlin.random.Random

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

    HackerNewsComposeTheme {
      NewsFeedViewComposable(state = state, events)
    }
  }
}

@Composable
fun NewsFeedViewComposable(state: NewsFeedState, events: MutableSharedFlow<NewsFeedEvent>? = null) {
  Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
    if (state.loading) {
      Column(
        modifier = Modifier.width(64.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
      ) {
        CircularProgressIndicator()
      }
    } else {
      LazyColumn(
        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 4.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
      ) {
        items(state.newsItems) { item ->
          NewsFeedListItem(newsItem = item)
        }
      }
    }
  }
}

@Preview
@Composable
fun NewsFeedViewPreview() {
  val random = Random(845654)
  val now = LocalDateTime.now().atZone(ZoneId.systemDefault())
  val state = NewsFeedState(
    loading = false,
    newsItems = listOf(
      NewsItem(
        id = 999999L,
        type = STORY,
        by = "squid_pudding",
        time = now.minusMinutes(random.nextLong(240)).toEpochSecond(),
        title = "Double Agent: Inside Al Qaeda (2015) [video]",
        url = "https://www.youtube.com/watch?v=Wbt_G2Kia7g",
        score = random.nextInt(1000)
      ),
      NewsItem(
        id = 999999L,
        type = STORY,
        by = "squid_pudding",
        time = now.minusMinutes(random.nextLong(40)).toEpochSecond(),
        title = "How Tally bootstrapped a \$1M/year, no-code form builder",
        url = "https://news.ycombinator.com/item?id=40176806",
        score = random.nextInt(1000)
      ),
      NewsItem(
        id = 999999L,
        type = STORY,
        by = "squid_pudding",
        time = now.minusMinutes(random.nextLong(40)).toEpochSecond(),
        title = "Extension is a PnP, zero-config, cross-browser extension development tool",
        url = "https://news.ycombinator.com/item?id=40176806",
        score = random.nextInt(1000)
      ),
      NewsItem(
        id = 999999L,
        type = STORY,
        by = "squid_pudding",
        time = now.minusMinutes(random.nextLong(40)).toEpochSecond(),
        title = "Show HN: Easy Itemized Bill Splitting ",
        url = "https://splitparty.co/",
        score = random.nextInt(1000)
      ),
      NewsItem(
        id = 999999L,
        type = STORY,
        by = "squid_pudding",
        time = now.minusMinutes(random.nextLong(240)).toEpochSecond(),
        title = "Scientist Discover New Flavor of Pudding",
        url = "https://news.ycombinator.com/item?id=40176806",
        score = random.nextInt(1000)
      ),
      NewsItem(
        id = 999999L,
        type = STORY,
        by = "squid_pudding",
        time = now.minusMinutes(random.nextLong(240)).toEpochSecond(),
        title = "Scientist Discover New Flavor of Pudding",
        url = "https://news.ycombinator.com/item?id=40176806",
        score = random.nextInt(1000)
      ),
      NewsItem(
        id = 999999L,
        type = STORY,
        by = "squid_pudding",
        time = now.minusMinutes(random.nextLong(240)).toEpochSecond(),
        title = "Scientist Discover New Flavor of Pudding",
        url = "https://news.ycombinator.com/item?id=40176806",
        score = random.nextInt(1000)
      ),
      NewsItem(
        id = 999999L,
        type = STORY,
        by = "squid_pudding",
        time = now.minusMinutes(random.nextLong(240)).toEpochSecond(),
        title = "Scientist Discover New Flavor of Pudding",
        url = "https://news.ycombinator.com/item?id=40176806",
        score = random.nextInt(1000)
      )
    )
  )
  HackerNewsComposeTheme {
    NewsFeedViewComposable(state = state)
  }
}