package com.mmartin.hackernewscompose.repository

import com.mmartin.hackernewscompose.models.NewsItem
import javax.inject.Inject
import javax.inject.Named

class NewsFeedDataRepository @Inject constructor(
  @Named("DB") private val db: NewsFeedDatabaseRepository,
  @Named("Remote") private val remoteRepository: NewsFeedRemoteRepository
) : NewsFeedRepository {
  override suspend fun topStories(page: Int): List<NewsItem> {
    //TODO: save items to DB and load from DB
    return remoteRepository.topStories(0)
  }

  override suspend fun invalidate() {
    db.invalidate()
  }
}