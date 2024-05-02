package com.mmartin.hackernewscompose.repository.db

import com.mmartin.hackernewscompose.models.NewsItem
import com.mmartin.hackernewscompose.models.NewsItemClassification
import com.mmartin.hackernewscompose.repository.NewsFeedRepository
import com.mmartin.hackernewscompose.repository.db.models.NewsItemDBModel
import com.mmartin.hackernewscompose.repository.db.models.StoriesList
import java.time.Instant

class NewsFeedDatabaseRepository(private val newsItemDao: NewsItemDao) :
  NewsFeedRepository {
  override suspend fun topStories(): List<Long> {
    return newsItemDao
      .getStoriesList("top")
      ?.storyIds
      ?.split(",")
      ?.map { it.toLong() }
      ?: emptyList()
  }

  override suspend fun saveStoriesList(type: String, idList: List<Long>) {
    newsItemDao.upsertStories(
      StoriesList(
        type = type,
        storyIds = idList.joinToString { it.toString() },
        timeStamp = Instant.now().epochSecond
      )
    )
  }

  override suspend fun items(page: List<Long>): List<NewsItem> {
    TODO("Not yet implemented")
  }

  override suspend fun invalidate() {
    newsItemDao.deleteAll()
  }

  private fun NewsItemDBModel.toNewsItem(): NewsItem {
    return NewsItem(
      id = id,
      type = NewsItemClassification.parse(type),
      by = by,
      time = time,
      parent = parent,
      kids = emptyList(),
      url = url,
      score = score,
      title = title,
      parts = emptyList(),
      descendants = descendants
    )
  }
}