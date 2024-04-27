package com.mmartin.hackernewscompose.framework

import android.app.Activity
import androidx.core.os.BuildCompat
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@OptIn(DelicateCoroutinesApi::class)
abstract class MviActivity<State, Event, View> : Activity() where View : MviView<Event, State> {
  private val dispatcher: CoroutineDispatcher = Dispatchers.Main
  abstract val presenter: Presenter<State, Event>

  private val eventsFlow = MutableSharedFlow<Event>()
  private val statesFlow by lazy {
    MutableStateFlow(presenter.initialValue())
  }

  private var presenterJob: Job? = null
  private var viewRenderJob: Job? = null

  @Suppress("UNCHECKED_CAST")
  private val view: View
    get() = window.decorView.rootView as View

  override fun onStart() {
    if (presenterJob == null) {
      presenterJob = GlobalScope.launch(dispatcher) {
        presenter.bind(statesFlow.value, eventsFlow.asSharedFlow())
          .onEach { statesFlow.value = it.invoke(statesFlow.value) }
          .launchIn(this)
      }
    }

    viewRenderJob = GlobalScope.launch(dispatcher) {
      view.eventsFlow().onEach { eventsFlow.emit(it) }.launchIn(this)
      statesFlow.onEach { renderView(it) }.launchIn(this)
    }
    super.onStart()
  }

  private fun renderView(state: State) {
    view.render(state)
  }

  override fun onPause() {
    viewRenderJob?.cancel()
    super.onPause()
  }

  override fun onStop() {
    presenterJob?.cancel()
    super.onStop()
  }
}