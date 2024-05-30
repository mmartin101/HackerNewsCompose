package com.mmartin.hackernewscompose.repository

import com.mmartin.hackernewscompose.models.NewsItem
import com.mmartin.hackernewscompose.repository.db.models.StoriesList
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Named

//TODO create separate source set for Remote (google free variant...) vs Firebase
class NewsFeedDataRepository @Inject constructor(
    @Named("Database") private val db: NewsFeedRepository,
    @Named("Remote") private val remoteRepository: NewsFeedRepository,
    @Named("Firebase") private val firebase: NewsFeedRepository,
    private val coroutineDispatcher: CoroutineDispatcher
) : NewsFeedRepository {
    override suspend fun topStories(): StoriesList {
        return withContext(coroutineDispatcher) {
            val storiesFromDB = db.topStories()
            return@withContext if (storiesFromDB.timeStamp.isOlderThan5Minutes()) {
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
    }

    private fun Long.isOlderThan5Minutes(): Boolean {
        return System.currentTimeMillis().minus(this) >= 300_000L
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