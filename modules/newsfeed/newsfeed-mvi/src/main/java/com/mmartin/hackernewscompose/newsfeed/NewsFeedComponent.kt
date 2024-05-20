package com.mmartin.hackernewscompose.newsfeed

import android.content.Context
import com.mmartin.hackernewscompose.api.NetworkModule
import com.mmartin.hackernewscompose.newsfeed.presenter.NewsFeedPresenter
import com.mmartin.hackernewscompose.repository.RepositoryModule
import dagger.BindsInstance
import dagger.Component

@Component(
  modules = [NetworkModule::class, RepositoryModule::class]
)
interface NewsFeedComponent {
  fun createPresenter(): NewsFeedPresenter

  @Component.Factory
  interface Factory {
    fun create(
      @BindsInstance context: Context
    ): NewsFeedComponent
  }

  companion object {
    operator fun invoke(
      context: Context
    ): NewsFeedComponent {
      return DaggerNewsFeedComponent
        .factory()
        .create(context)
    }
  }
}