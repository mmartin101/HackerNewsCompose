package com.mmartin.hackernewscompose.repository.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface NewsItemDao {
  @Query("SELECT * FROM news_items")
  fun getAll(): List<NewsItemDBModel>

  @Insert
  fun insertAll(vararg items: NewsItemDBModel)

  @Query("DELETE FROM news_items")
  fun deleteAll()
}