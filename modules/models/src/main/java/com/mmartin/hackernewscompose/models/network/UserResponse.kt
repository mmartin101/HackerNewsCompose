package com.mmartin.hackernewscompose.models.network

import java.time.LocalDateTime

data class UserResponse(
  val id: Long = 0,
  val delay: Long = 0,
  val created: LocalDateTime?,
  val karma: Int = 0,
  val about: String?,
  val submitted: List<Long> = emptyList()
)
