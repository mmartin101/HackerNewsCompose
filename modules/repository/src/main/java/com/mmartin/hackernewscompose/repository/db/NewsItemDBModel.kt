package com.mmartin.hackernewscompose.repository.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "news_items")
data class NewsItemDBModel(
  @PrimaryKey val id: Long,
  val isDeleted: Boolean = false,
  val type: Int,
  val by: String,
  val time: String,
  val parent: Long = 0,
  val poll: Long? = 0,
  val kids: String = "",
  val url: String?,
  val score: Int = 0,
  val title: String,
  val parts: String = "",
  val descendants: Int = 0
)