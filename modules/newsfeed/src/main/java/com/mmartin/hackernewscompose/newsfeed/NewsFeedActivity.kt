package com.mmartin.hackernewscompose.newsfeed

import android.os.Bundle
import com.mmartin.hackernewscompose.framework.MviActivity
import com.mmartin.hackernewscompose.framework.Presenter
import com.mmartin.hackernewscompose.newsfeed.presenter.NewsFeedEvent
import com.mmartin.hackernewscompose.newsfeed.presenter.NewsFeedState
import com.mmartin.hackernewscompose.newsfeed.ui.NewsFeedView

class NewsFeedActivity : MviActivity<NewsFeedState, NewsFeedEvent, NewsFeedView>() {

  override val presenter: Presenter<NewsFeedState, NewsFeedEvent> by lazy {
    NewsFeedComponent(applicationContext).createPresenter()
  }

  override val view: NewsFeedView by lazy { NewsFeedView(this) }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(view)
  }
}