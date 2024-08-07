package com.mmartin.hackernewscompose

import android.app.Application
import com.mmartin.hackernewscompose.api.NetworkModule
import timber.log.Timber
import timber.log.Timber.DebugTree

class HackerNewsApp: Application() {
  override fun onCreate() {
    super.onCreate()
    if (BuildConfig.DEBUG) {
      Timber.plant(DebugTree())
    }
    // TODO Add production timber tree implementation, log to crashlytics?
    startKoin()
  }

  private fun startKoin() {
    org.koin.core.context.startKoin {
      modules(NetworkModule(BuildConfig.DEBUG).koinModule)
    }
  }
}