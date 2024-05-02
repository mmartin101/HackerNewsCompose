package com.mmartin.hackernewscompose.repository.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Upsert
import com.mmartin.hackernewscompose.repository.db.models.NewsItemDBModel
import com.mmartin.hackernewscompose.repository.db.models.StoriesList

@Dao
interface NewsItemDao {
  @Query("SELECT * FROM news_items")
  suspend fun getItems(): List<NewsItemDBModel>

  @Insert
  suspend fun insertAll(vararg items: NewsItemDBModel)

  @Upsert
  suspend fun upsertStories(storiesList: StoriesList)

  @Query("SELECT * FROM stories_list WHERE type IS :type")
  suspend fun getStoriesList(type: String): StoriesList?

  @Query("DELETE FROM news_items")
  suspend fun deleteAll()
}