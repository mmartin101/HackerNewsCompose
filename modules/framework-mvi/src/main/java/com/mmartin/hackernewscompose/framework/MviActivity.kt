package com.mmartin.hackernewscompose.framework

import androidx.activity.ComponentActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

abstract class MviActivity<State, Event, View> :
  ComponentActivity() where View : MviView<Event, State> {
  abstract val presenter: Presenter<State, Event>
  abstract val view: View
  private val dispatcher: CoroutineDispatcher = Dispatchers.Main
  private val eventsFlow = MutableSharedFlow<Event>()

  private val statesFlow by lazy {
    MutableStateFlow(presenter.initialValue())
  }
  private var presenterJob: Job? = null

  private var viewRenderJob: Job? = null

  override fun onStart() {
    if (presenterJob == null) {
      presenterJob = lifecycleScope.launch(dispatcher) {
        presenter.bind(statesFlow.value, eventsFlow.asSharedFlow())
          .onEach { statesFlow.value = it }
          .launchIn(this)
      }
    }

    viewRenderJob = lifecycleScope.launch(dispatcher) {
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