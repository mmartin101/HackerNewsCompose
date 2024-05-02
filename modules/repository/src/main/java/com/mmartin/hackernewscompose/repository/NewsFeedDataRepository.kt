package com.mmartin.hackernewscompose.repository

import com.mmartin.hackernewscompose.models.NewsItem
import com.mmartin.hackernewscompose.repository.db.NewsFeedDatabaseRepository
import com.mmartin.hackernewscompose.repository.firebase.NewsFeedFirebaseRepository
import javax.inject.Inject
import javax.inject.Named

class NewsFeedDataRepository @Inject constructor(
  @Named("Database") private val db: NewsFeedRepository,
  @Named("Remote") private val remoteRepository: NewsFeedRepository,
  @Named("Firebase") private val firebase: NewsFeedRepository
) : NewsFeedRepository {
  override suspend fun topStories(): List<Long> {
    // TODO: Expand on this, incorporate the timestamp maybe? does it even make sense?
    val topStories = firebase.topStories()
    db.saveStoriesList(
      type = "top",
      idList = topStories
    )
    return topStories
  }

  override suspend fun items(page: List<Long>): List<NewsItem> {
    return firebase.items(page)
  }

  override suspend fun invalidate() {
    db.invalidate()
  }
}