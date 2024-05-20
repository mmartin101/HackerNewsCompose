package com.mmartin.hackernewscompose.repository.db.models

data class StoriesList(
  val type: String = "",
  val storyIds: List<Long> = emptyList(),
  val timeStamp: Long = 0L
)