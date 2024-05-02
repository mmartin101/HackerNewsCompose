package com.mmartin.hackernewscompose.repository.firebase

import com.google.firebase.Firebase
import com.google.firebase.database.database
import com.google.firebase.database.getValue
import com.mmartin.hackernewscompose.models.NewsItem
import com.mmartin.hackernewscompose.models.NewsItemClassification
import com.mmartin.hackernewscompose.repository.NewsFeedRepository
import com.mmartin.hackernewscompose.repository.firebase.models.FirebaseNewsItem
import kotlinx.coroutines.tasks.await
import timber.log.Timber

class NewsFeedFirebaseRepository : NewsFeedRepository {
  val db = Firebase.database("https://hacker-news.firebaseio.com/")
  override suspend fun topStories(): List<Long> {
    val ids = db.getReference("/v0/topstories").get().await().getValue<List<Long>>()
    return ids ?: emptyList()
  }

  override suspend fun items(page: List<Long>): List<NewsItem> {
    var newsList = emptyList<NewsItem>()
    try {
      newsList = page.mapNotNull { id ->
        db.getReference("/v0/item")
          .child(id.toString())
          .get()
          .await()
          .getValue<FirebaseNewsItem>()
      }
        .map { fb -> fb.toNewsItem() }
    } catch (e: Throwable) {
      Timber.e(e, "Exception thrown while retrieving items from Firebase")
    }
    return newsList
  }

  override suspend fun invalidate() {
    TODO("Not yet implemented")
  }

  private fun FirebaseNewsItem.toNewsItem(): NewsItem {
    return NewsItem(
      id = id,
      type = NewsItemClassification.parse(type),
      by = by,
      time = time,
      text = text,
      parent = parent,
      kids = kids,
      url = url,
      title = title,
      score = score,
      parts = parts,
      descendants = descendants
    )
  }
}