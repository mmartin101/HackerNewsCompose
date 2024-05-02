package com.mmartin.hackernewscompose.repository.db.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "news_items")
data class NewsItemDBModel(
  @PrimaryKey val id: Long = 0L,
  val deleted: Boolean = false,
  val type: String = "",
  val by: String = "",
  val time: Long = 0L,
  val text: String = "",
  val dead: Boolean = false,
  val parent: Long = 0L,
  val kids: String = "",
  val url: String = "",
  val score: Int = 0,
  val title: String = "",
  val parts: String = "",
  val descendants: Int = 0
)