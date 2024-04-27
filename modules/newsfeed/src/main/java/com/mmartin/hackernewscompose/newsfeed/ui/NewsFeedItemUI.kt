package com.mmartin.hackernewscompose.newsfeed.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mmartin.hackernewscompose.framework.theme.NewsItemPrimaryTextStyle
import com.mmartin.hackernewscompose.framework.theme.NewsItemSecondaryTextStyle
import com.mmartin.hackernewscompose.models.NewsItem
import com.mmartin.hackernewscompose.models.NewsItemClassification.STORY
import java.time.LocalDateTime

@Composable
fun NewsFeedListItem(newsItem: NewsItem) {
  Row {
    Text(
      text = newsItem.title,
      style = NewsItemPrimaryTextStyle
    )
    newsItem.url?.let { url ->
      val site = url.replace(oldValue = "https://", newValue = "")
        .split('/')[0]
      Text(
        style = NewsItemSecondaryTextStyle,
        text = "($site)",
        modifier = Modifier.padding(start = 5.dp)
      )
    }
  }
}

@Preview(showBackground = true)
@Composable
fun newsFeedListItemPreview() {
  val item = NewsItem(
    id = 999999L,
    type = STORY,
    by = "squid_pudding",
    time = LocalDateTime.now(),
    title = "Scientist Discover New Flavor of Pudding",
    url = "https://news.ycombinator.com/item?id=40176806",
    score = 42
  )
  NewsFeedListItem(item)
}