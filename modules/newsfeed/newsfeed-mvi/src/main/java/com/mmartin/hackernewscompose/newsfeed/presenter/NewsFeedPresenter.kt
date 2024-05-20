package com.mmartin.hackernewscompose.newsfeed.presenter

import com.mmartin.hackernewscompose.framework.Presenter
import com.mmartin.hackernewscompose.newsfeed.interactor.NewsFeedInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.scan
import kotlinx.coroutines.flow.transform
import timber.log.Timber
import javax.inject.Inject

class NewsFeedPresenter @Inject constructor(
  private val interactor: NewsFeedInteractor
) :
  Presenter<NewsFeedState, NewsFeedEvent> {
  override fun initialValue(): NewsFeedState {
    return NewsFeedState()
  }

  @OptIn(ExperimentalCoroutinesApi::class)
  override fun bind(
    previousState: NewsFeedState?,
    eventsFlow: Flow<NewsFeedEvent>
  ): Flow<NewsFeedState> {
    val initialStateFlow = flow {
      try {
        val list = interactor.topStories(0)
        emit(NewsFeedState(loading = false, newsItems = list))
      } catch (e: Throwable) {
        Timber.e(e, "oops")
      }
    }
      .flowOn(Dispatchers.IO)
    val states = initialStateFlow.flatMapMerge { state ->
      eventsFlow.load().scan(state) { prev, transformer -> transformer(prev) }
    }
    return merge(states)
  }

  private fun Flow<NewsFeedEvent>.load(): Flow<StateTransformer> {
    return filterIsInstance<NewsFeedEvent.LoadNewsFeed>()
      .transform { state ->
        try {
          val newsItems = interactor.topStories(0)

          emit { previousState ->
            previousState.copy(
              loading = false,
              newsItems = newsItems
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
