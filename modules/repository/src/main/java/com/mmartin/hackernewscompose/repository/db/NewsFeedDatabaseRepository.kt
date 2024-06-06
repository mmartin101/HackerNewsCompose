package com.mmartin.hackernewscompose.repository.db

import com.mmartin.hackernewscompose.models.NewsItem
import com.mmartin.hackernewscompose.models.NewsItemClassification
import com.mmartin.hackernewscompose.repository.NewsFeedRepository
import com.mmartin.hackernewscompose.repository.db.models.NewsItemDBModel
import com.mmartin.hackernewscompose.repository.db.models.StoriesList
import com.mmartin.hackernewscompose.repository.db.models.StoriesListDB

class NewsFeedDatabaseRepository(private val newsItemDao: NewsItemDao) :
    NewsFeedRepository {
    override suspend fun topStories(): StoriesList {
        val db = newsItemDao.getStoriesList("top")
        return db?.toStoriesList() ?: StoriesList()
    }

    override suspend fun saveStoriesList(type: String, idList: List<Long>) {
        newsItemDao.upsertStories(
            StoriesListDB(
                type = type,
                storyIds = idList.joinToString(",") { it.toString() },
                timeStamp = System.currentTimeMillis().div(1000)
            )
        )
    }

    override suspend fun items(page: List<Long>): List<NewsItem> {
        return newsItemDao.getItems().map { dbItem -> dbItem.toNewsItem() }
    }

    override suspend fun saveItems(page: List<NewsItem>) {
        newsItemDao.upsertAll(page.map { newsItem -> newsItem.toDbModel() })
    }

    override suspend fun invalidate() {
        newsItemDao.deleteAll()
    }

    private fun StoriesListDB.toStoriesList(): StoriesList {
        return StoriesList(
            type = type,
            storyIds = storyIds.split(",").map { it.toLong() },
            timeStamp = timeStamp
        )
    }

    fun NewsItemDBModel.toNewsItem(): NewsItem {
        return NewsItem(
            id = id,
            type = NewsItemClassification.parse(type),
            by = by,
            time = time,
            parent = parent,
            kids = kids.split(",").map { it.toLong() },
            url = url,
            score = score,
            title = title,
            parts = parts.split(",").map { it.toLong() },
            descendants = descendants
        )
    }

    private fun NewsItem.toDbModel(): NewsItemDBModel {
        return NewsItemDBModel(
            id = id,
            type = type.toString(),
            by = by,
            time = time,
            parent = parent,
            kids = kids.joinToString { id -> id.toString() },
            url = url,
            score = score,
            title = title,
            parts = parts.joinToString { id -> id.toString() },
            descendants = descendants
        )
    }
}