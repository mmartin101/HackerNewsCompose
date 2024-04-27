package com.mmartin.hackernewscompose.repository

import com.mmartin.hackernewscompose.api.HackerNewsApi
import com.mmartin.hackernewscompose.models.NewsItem
import com.mmartin.hackernewscompose.models.NewsItemClassification
import com.mmartin.hackernewscompose.models.NewsItemClassification.UNKNOWN
import com.mmartin.hackernewscompose.models.network.NewsItemResponse
import java.time.LocalDateTime
import java.time.ZoneId

class NewsFeedRemoteRepository(private val api: HackerNewsApi) :
  NewsFeedRepository {
  override suspend fun topStories(page: Int): List<NewsItem> {
    return api.topStories()
      .map { id -> id.toString() }
      .take(30)
      .map { id -> api.getItem(id) }
      .map { response -> response.toNewsItem() }
  }

  private fun NewsItemResponse.toNewsItem(): NewsItem {
    return NewsItem(
      id = id,
      isDeleted = isDeleted,
      type = type?.toNewsItemClassification() ?: UNKNOWN,
      by = by ?: "",
      time = time ?: LocalDateTime.now().atZone(ZoneId.systemDefault()).toLocalDateTime(),
      parent = parent,
      poll = poll,
      kids = kids,
      url = url,
      score = score,
      title = title ?: "",
      parts = parts,
      descendants = descendants
    )
  }

  private fun String.toNewsItemClassification(): NewsItemClassification {
    return NewsItemClassification.parse(this)
  }

  override suspend fun invalidate() {
    TODO("Not yet implemented")
  }
}