package com.mmartin.hackernewscompose.newsfeed.presenter

import com.mmartin.hackernewscompose.framework.Presenter
import com.mmartin.hackernewscompose.newsfeed.interactor.NewsFeedInteractor
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.transform
import javax.inject.Inject

class NewsFeedPresenter @Inject constructor(
  private val interactor: NewsFeedInteractor
) :
  Presenter<NewsFeedState, NewsFeedEvent> {
  override fun initialValue(): NewsFeedState {
    return NewsFeedState()
  }

  override fun bind(
    previousState: NewsFeedState?,
    eventsFlow: Flow<NewsFeedEvent>
  ): Flow<StateTransformer> {
    return merge(eventsFlow.load())
  }

  private fun Flow<NewsFeedEvent>.load(): Flow<StateTransformer> {
    return filterIsInstance<NewsFeedEvent.LoadNewsFeed>()
      .transform { state ->
        try {
          val newsItems = interactor?.topStories()
          emit { previousState ->
            previousState.copy(
              loading = false,
              newsItems = newsItems ?: emptyList()
            )
          }
        } catch (t: Throwable) {
          emit { previousState ->
            previousState.copy(
              loading = false,
              error = t
            )
          }
        }
      }
  }
}

private typealias StateTransformer = (NewsFeedState) -> NewsFeedState
