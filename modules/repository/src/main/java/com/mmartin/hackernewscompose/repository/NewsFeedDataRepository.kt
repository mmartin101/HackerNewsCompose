package com.mmartin.hackernewscompose.repository

import com.mmartin.hackernewscompose.models.NewsItem
import com.mmartin.hackernewscompose.repository.db.models.StoriesList
import javax.inject.Inject
import javax.inject.Named

//TODO create separate source set for Remote (google free variant...) vs Firebase
class NewsFeedDataRepository @Inject constructor(
  @Named("Database") private val db: NewsFeedRepository,
  @Named("Remote") private val remoteRepository: NewsFeedRepository,
  @Named("Firebase") private val firebase: NewsFeedRepository
) : NewsFeedRepository {
  override suspend fun topStories(): StoriesList {
    val storiesFromDB = db.topStories()
    val currentTimeStamp = System.currentTimeMillis()
    // fetch new list if list from db is older than 5 minutes
    return if (currentTimeStamp.minus(storiesFromDB.timeStamp) >= 300_000L) {
      val freshStories = firebase.topStories()
      db.saveStoriesList(
        type = "top",
        idList = freshStories.storyIds
      )
      freshStories
    } else {
      storiesFromDB
    }
  }

  override suspend fun items(page: List<Long>): List<NewsItem> {
    val items = firebase.items(page)
    db.saveItems(items)
    return items
  }

  override suspend fun invalidate() {
    db.invalidate()
  }
}