package com.mmartin.hackernewscompose

import android.app.Application
import timber.log.Timber
import timber.log.Timber.DebugTree

class HackerNewsApp: Application() {
  override fun onCreate() {
    super.onCreate()
    Timber.plant(DebugTree())
  }
}