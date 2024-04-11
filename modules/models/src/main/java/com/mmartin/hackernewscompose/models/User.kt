package com.mmartin.hackernewscompose.models

data class User(
  val id: String = "",
  val delay: Long = 0,
  // val created: DateTime? = null,
  val karma: Int = 0,
  val about: String? = null,
  val submitted: List<Long>? = null
)