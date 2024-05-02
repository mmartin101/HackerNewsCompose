package com.mmartin.hackernewscompose.models

import java.time.LocalDateTime

/**
 * Item model from HN API
 *
 *
 * https://github.com/HackerNews/API#items
 */
data class NewsItem(
    val id: Long,
    val type: NewsItemClassification,
    val by: String,
    val time: Long,
    val parent: Long = 0,
    val kids: List<Long> = emptyList(),
    val url: String,
    val score: Int = 0,
    val title: String,
    val parts: List<Long> = emptyList(),
    val text: String = "",
    val descendants: Int = 0
)
