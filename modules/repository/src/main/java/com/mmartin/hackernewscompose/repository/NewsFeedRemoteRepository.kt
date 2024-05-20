package com.mmartin.hackernewscompose.repository

import com.mmartin.hackernewscompose.api.HackerNewsApi
import com.mmartin.hackernewscompose.models.NewsItem
import com.mmartin.hackernewscompose.models.NewsItemClassification
import com.mmartin.hackernewscompose.models.NewsItemClassification.UNKNOWN
import com.mmartin.hackernewscompose.models.network.NewsItemResponse
import com.mmartin.hackernewscompose.repository.db.models.StoriesList

class NewsFeedRemoteRepository(private val api: HackerNewsApi) :
  NewsFeedRepository {

  private fun NewsItemResponse.toNewsItem(): NewsItem {
    return NewsItem(
      id = id,
      type = type?.toNewsItemClassification() ?: UNKNOWN,
      by = by ?: "",
      time = time ?: 0L,
      parent = parent,
      kids = kids,
      url = url ?: "",
      score = score,
      title = title ?: "",
      parts = parts,
      descendants = descendants
    )
  }

  private fun String.toNewsItemClassification(): NewsItemClassification {
    return NewsItemClassification.parse(this)
  }

  override suspend fun topStories(): StoriesList {
    return StoriesList(
      type = "top",
      storyIds = api.topStories(),
      timeStamp = System.currentTimeMillis()
    )
  }

  override suspend fun items(page: List<Long>): List<NewsItem> {
    return page.take(30)
      .map { id -> api.getItem(id.toString()) }
      .map { response -> response.toNewsItem() }
  }

  override suspend fun invalidate() {
    TODO("Not yet implemented")
  }
}