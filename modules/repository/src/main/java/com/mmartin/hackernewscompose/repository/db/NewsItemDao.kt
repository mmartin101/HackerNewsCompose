package com.mmartin.hackernewscompose.repository.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Upsert
import com.mmartin.hackernewscompose.repository.db.models.NewsItemDBModel
import com.mmartin.hackernewscompose.repository.db.models.StoriesListDB

@Dao
interface NewsItemDao {
  @Query("SELECT * FROM news_items")
  suspend fun getItems(): List<NewsItemDBModel>

  @Upsert
  suspend fun upsertAll(items: List<NewsItemDBModel>)

  @Upsert
  suspend fun upsertStories(storiesList: StoriesListDB)

  @Query("SELECT * FROM stories_list WHERE type IS :type")
  suspend fun getStoriesList(type: String): StoriesListDB?

  @Query("DELETE FROM news_items")
  suspend fun deleteAll()
}