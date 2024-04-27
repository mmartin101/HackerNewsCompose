package com.mmartin.hackernewscompose.framework

import android.content.Context
import android.util.AttributeSet
import androidx.compose.ui.platform.AbstractComposeView
import kotlinx.coroutines.flow.Flow

abstract class MviView<Event, State> @JvmOverloads constructor(
  context: Context, attrs: AttributeSet? = null
) : AbstractComposeView(context, attrs){

  abstract fun render(state: State)
  abstract fun eventsFlow(): Flow<Event>
}