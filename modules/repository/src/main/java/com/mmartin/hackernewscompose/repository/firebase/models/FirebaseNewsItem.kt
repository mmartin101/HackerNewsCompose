package com.mmartin.hackernewscompose.repository.firebase.models

data class FirebaseNewsItem(
  val id: Long = 0L,
  val deleted: Boolean = false,
  val type: String = "",
  val by: String = "",
  val time: Long = 0L,
  val text: String = "",
  val dead: Boolean = false,
  val parent: Long = 0L,
  // val poll // not sure how this is used
  val kids: List<Long> = emptyList(),
  val url: String = "",
  val score: Int = 0,
  val title: String = "",
  val parts: List<Long> = emptyList(),
  val descendants: Int = 0
)
