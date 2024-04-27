package com.mmartin.hackernewscompose.framework

import kotlinx.coroutines.flow.Flow

interface Presenter<State, Event> {
  fun bind(previousState: State?, eventsFlow: Flow<Event>): Flow<(State) -> State>
  fun initialValue(): State
}
