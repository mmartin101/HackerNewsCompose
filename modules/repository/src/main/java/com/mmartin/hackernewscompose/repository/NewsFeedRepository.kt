package com.mmartin.hackernewscompose.repository

import androidx.paging.PagingData
import com.mmartin.hackernewscompose.models.NewsItem
import com.mmartin.hackernewscompose.repository.db.models.StoriesList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flow

interface NewsFeedRepository {

    suspend fun topStories(): StoriesList

    suspend fun saveStoriesList(type: String, idList: List<Long>) {}

    suspend fun items(page: List<Long>): List<NewsItem>

    fun itemsFlow(): Flow<PagingData<NewsItem>> {
        return emptyFlow()
    }

    suspend fun saveItems(page: List<NewsItem>) {}

    suspend fun invalidate()
}