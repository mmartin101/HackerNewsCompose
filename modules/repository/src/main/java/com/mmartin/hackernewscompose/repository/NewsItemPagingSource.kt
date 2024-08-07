package com.mmartin.hackernewscompose.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.mmartin.hackernewscompose.models.NewsItem
import timber.log.Timber

class NewsItemPagingSource(
    private val db: NewsFeedRepository,
    private val firebase: NewsFeedRepository
) : PagingSource<Int, NewsItem>() {
    override fun getRefreshKey(state: PagingState<Int, NewsItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, NewsItem> {
        try {
            // Start refresh at page 1 if undefined.
            val fromIndex = params.key ?: 0
            val toIndex = fromIndex + params.loadSize
            val storiesList = db.topStories()
            val firebaseItems = firebase.items(storiesList.storyIds.subList(fromIndex, toIndex))
            db.saveItems(firebaseItems)

            return LoadResult.Page(
                data = firebaseItems,
                prevKey = null, // Only paging forward.
                nextKey = toIndex - 1
            )
        } catch (e: Exception) {
            // Handle errors in this block and return LoadResult.Error for
            // expected errors (such as a network failure).
            Timber.e(e)
            return LoadResult.Error(e)
        }
    }
}