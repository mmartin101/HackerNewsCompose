package com.mmartin.hackernewscompose.repository.db.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "stories_list")
data class StoriesListDB(
  @PrimaryKey val type: String,
  val storyIds: String,
  val timeStamp: Long
)
