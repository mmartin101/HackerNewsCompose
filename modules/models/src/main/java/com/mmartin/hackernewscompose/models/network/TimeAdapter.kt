package com.mmartin.hackernewscompose.models.network

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId

class TimeAdapter {
  @FromJson
  fun fromJson(timestamp: Long): LocalDateTime {
    return Instant.ofEpochSecond(timestamp).atZone(ZoneId.systemDefault()).toLocalDateTime()
  }

  // TODO not used but will it cause an error if deleted?
  @ToJson
  fun toJson(timestamp: LocalDateTime): Long {
    return -1L
  }
}