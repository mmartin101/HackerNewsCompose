package com.mmartin.hackernewscompose.newsfeed.ui

import android.text.format.DateUtils
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mmartin.hackernewscompose.framework.theme.NewsItemPrimaryTextStyle
import com.mmartin.hackernewscompose.framework.theme.NewsItemSecondaryTextStyle
import com.mmartin.hackernewscompose.models.NewsItem
import com.mmartin.hackernewscompose.models.NewsItemClassification.STORY
import com.mmartin.hackernewscompose.newsfeed.R
import java.time.LocalDateTime
import java.time.ZoneId

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

@Preview(showBackground = true)
@Composable
fun NewsFeedListItemPreview() {
  val item = NewsItem(
    id = 999999L,
    type = STORY,
    by = "squid_pudding",
    time = LocalDateTime.now()
      .atZone(ZoneId.systemDefault())
      .minusMinutes(42).toEpochSecond(),
    title = "Scientist Discover New Flavor of Pudding",
    url = "https://news.ycombinator.com/item?id=40176806",
    score = 42
  )
  NewsFeedListItem(item)
}