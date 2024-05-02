package com.mmartin.hackernewscompose.models.network

import com.squareup.moshi.JsonClass
import java.time.LocalDateTime

@JsonClass(generateAdapter = true)
data class NewsItemResponse(
  val id: Long = -1L,
  val isDeleted: Boolean = false,
  val type: String?,
  val by: String?,
  val time: Long?,
  val parent: Long = 0,
  val poll: Long = 0,
  val kids: List<Long> = emptyList(),
  val url: String?,
  val score: Int = 0,
  val title: String?,
  val parts: List<Long> = emptyList(),
  val descendants: Int = 0
)
