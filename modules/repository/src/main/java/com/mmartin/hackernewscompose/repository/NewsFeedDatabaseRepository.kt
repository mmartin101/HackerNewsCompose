package com.mmartin.hackernewscompose.repository

import com.mmartin.hackernewscompose.models.NewsItem
import com.mmartin.hackernewscompose.models.NewsItemClassification
import com.mmartin.hackernewscompose.repository.db.NewsItemDBModel
import com.mmartin.hackernewscompose.repository.db.NewsItemDao
import java.time.LocalDateTime

class NewsFeedDatabaseRepository(private val newsItemDao: NewsItemDao) :
  NewsFeedRepository {
  override suspend fun topStories(page: Int): List<NewsItem> {
    return newsItemDao.getAll().map { fromDb -> fromDb.toNewsItem() }
  }

  override suspend fun invalidate() {
    newsItemDao.deleteAll()
  }

  private fun NewsItemDBModel.toNewsItem(): NewsItem {
    return NewsItem(
      id = id,
      isDeleted = isDeleted,
      type = NewsItemClassification.entries[type],
      by = by,
      time = LocalDateTime.parse(time),
      parent = parent,
      poll = poll,
      kids = emptyList(),
      url = url,
      score = score,
      title = title,
      parts = emptyList(),
      descendants = descendants
    )
  }
}