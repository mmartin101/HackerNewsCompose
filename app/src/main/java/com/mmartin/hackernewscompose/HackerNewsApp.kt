package com.mmartin.hackernewscompose

import android.app.Application
import com.mmartin.hackernewscompose.api.NetworkModule
import com.mmartin.hackernewscompose.presentation.NewsFeedPresentationModule
import com.mmartin.hackernewscompose.repository.RepositoryModule
import org.koin.android.ext.koin.androidContext
import timber.log.Timber
import timber.log.Timber.DebugTree

class HackerNewsApp : Application() {
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
            androidContext(this@HackerNewsApp)
            modules(

                NetworkModule(BuildConfig.DEBUG).koinModule,
                RepositoryModule.koin,
                NewsFeedPresentationModule.koin
            )
        }
    }
}