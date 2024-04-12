package com.mmartin.hackernewscompose.models

import java.time.LocalDateTime

data class User(
  val id: String = "",
  val delay: Long = 0,
  val created: LocalDateTime? = null,
  val karma: Int = 0,
  val about: String? = null,
  val submitted: List<Long>? = null
)