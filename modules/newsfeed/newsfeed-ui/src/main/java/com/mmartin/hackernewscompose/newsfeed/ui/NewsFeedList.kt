package com.mmartin.hackernewscompose.newsfeed.ui

import android.text.format.DateUtils
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mmartin.hackernewscompose.framework.theme.HackerNewsComposeTheme
import com.mmartin.hackernewscompose.framework.theme.NewsItemPrimaryTextStyle
import com.mmartin.hackernewscompose.framework.theme.NewsItemSecondaryTextStyle
import com.mmartin.hackernewscompose.models.NewsItem
import com.mmartin.hackernewscompose.models.NewsItemClassification.STORY
import kotlin.random.Random

// TODO add parameters for list item actions (e.g. open, detail view, etc)
// TODO implement pull to refresh that ignores the local db
@Composable
fun NewsFeedList(
  loading: Boolean,
  newsItems: List<NewsItem>,
  onTapAction: (NewsItem) -> Unit = {},
  onTapCommentsAction: (NewsItem) -> Unit = {},
  onRefreshAction: () -> Unit = {}
) {
  Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
    if (loading) {
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
        items(newsItems) { item ->
          NewsFeedListItem(newsItem = item)
        }
      }
    }
  }
}

@Composable
fun NewsFeedListItem(newsItem: NewsItem) {
  Column {
    Text(
      text = newsItem.title,
      style = NewsItemPrimaryTextStyle
    )
    if (newsItem.url.isNotEmpty()) {
      val site = newsItem.url.replace(oldValue = "https://", newValue = "")
        .split('/')[0]
      Text(
        modifier = Modifier.padding(top = 2.dp),
        style = NewsItemSecondaryTextStyle,
        text = "($site)"
      )
    }
    Row(Modifier.padding(top = 2.dp)) {
      Text(
        style = NewsItemSecondaryTextStyle,
        text = pluralStringResource(
          id = R.plurals.points,
          count = newsItem.score,
          newsItem.score
        ),
        modifier = Modifier.padding(end = 3.dp)
      )
      Text(
        style = NewsItemSecondaryTextStyle,
        text = stringResource(id = R.string.by_author, newsItem.by),
        modifier = Modifier.padding(end = 3.dp)
      )
      val now = System.currentTimeMillis()
      Text(
        style = NewsItemSecondaryTextStyle,
        text = DateUtils.getRelativeTimeSpanString(
          newsItem.time * 1000,
          now,
          DateUtils.MINUTE_IN_MILLIS
        ).toString(),
        modifier = Modifier.padding(end = 3.dp)
      )
      val commentText = if (newsItem.descendants > 0) {
        pluralStringResource(id = R.plurals.comments, newsItem.descendants, newsItem.descendants)
      } else {
        stringResource(id = R.string.discuss)
      }
      Text(
        style = NewsItemSecondaryTextStyle,
        text = "| $commentText"
      )
    }
  }
}

@Preview
@Composable
fun NewsFeedViewPreview() {
  val random = Random(845654)
  val now = System.currentTimeMillis().div(1000)
  HackerNewsComposeTheme {
    NewsFeedList(
      loading = false,
      listOf(
        NewsItem(
          id = 999999L,
          type = STORY,
          by = "squid_pudding",
          time = now.minus(random.nextLong(240).times(60)),
          title = "Double Agent: Inside Al Qaeda (2015) [video]",
          url = "https://www.youtube.com/watch?v=Wbt_G2Kia7g",
          score = random.nextInt(1000)
        ),
        NewsItem(
          id = 999999L,
          type = STORY,
          by = "squid_pudding",
          time = now.minus(random.nextLong(40).times(60)),
          title = "How Tally bootstrapped a \$1M/year, no-code form builder",
          url = "https://news.ycombinator.com/item?id=40176806",
          score = random.nextInt(1000)
        ),
        NewsItem(
          id = 999999L,
          type = STORY,
          by = "squid_pudding",
          time = now.minus(random.nextLong(40).times(60)),
          title = "Extension is a PnP, zero-config, cross-browser extension development tool",
          url = "https://news.ycombinator.com/item?id=40176806",
          score = random.nextInt(1000)
        ),
        NewsItem(
          id = 999999L,
          type = STORY,
          by = "squid_pudding",
          time = now.minus(random.nextLong(40).times(60)),
          title = "Show HN: Easy Itemized Bill Splitting ",
          url = "https://splitparty.co/",
          score = random.nextInt(1000)
        ),
        NewsItem(
          id = 999999L,
          type = STORY,
          by = "squid_pudding",
          time = now.minus(random.nextLong(240).times(60)),
          title = "Scientist Discover New Flavor of Pudding",
          url = "https://news.ycombinator.com/item?id=40176806",
          score = random.nextInt(1000)
        ),
        NewsItem(
          id = 999999L,
          type = STORY,
          by = "squid_pudding",
          time = now.minus(random.nextLong(240).times(60)),
          title = "Scientist Discover New Flavor of Pudding",
          url = "https://news.ycombinator.com/item?id=40176806",
          score = random.nextInt(1000)
        ),
        NewsItem(
          id = 999999L,
          type = STORY,
          by = "squid_pudding",
          time = now.minus(random.nextLong(240).times(60)),
          title = "Scientist Discover New Flavor of Pudding",
          url = "https://news.ycombinator.com/item?id=40176806",
          score = random.nextInt(1000)
        ),
        NewsItem(
          id = 999999L,
          type = STORY,
          by = "squid_pudding",
          time = now.minus(random.nextLong(240).times(60)),
          title = "Scientist Discover New Flavor of Pudding",
          url = "https://news.ycombinator.com/item?id=40176806",
          score = random.nextInt(1000)
        )
      )
    )
  }
}

@Preview(showBackground = true)
@Composable
fun NewsFeedListItemPreview() {
  val item = NewsItem(
    id = 999999L,
    type = STORY,
    by = "squid_pudding",
    time = System.currentTimeMillis().div(1000).minus(2520),
    title = "Scientist Discover New Flavor of Pudding",
    url = "https://news.ycombinator.com/item?id=40176806",
    score = 42
  )
  NewsFeedListItem(item)
}