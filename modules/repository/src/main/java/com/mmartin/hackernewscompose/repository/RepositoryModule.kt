package com.mmartin.hackernewscompose.repository

import android.content.Context
import androidx.room.Room
import com.mmartin.hackernewscompose.api.HackerNewsApi
import com.mmartin.hackernewscompose.repository.db.AppDatabase
import com.mmartin.hackernewscompose.repository.db.NewsItemDao
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Qualifier
import kotlin.annotation.AnnotationRetention.RUNTIME

@Module
object RepositoryModule {
  @Provides
  @Named("Remote")
  fun provideNewsFeedRemoteRepository(api: HackerNewsApi): NewsFeedRemoteRepository {
    return NewsFeedRemoteRepository(api)
  }

  @Provides
  @Named("DB")
  fun provideNewsFeedDatabaseRepository(newsItemDao: NewsItemDao): NewsFeedDatabaseRepository {
    return NewsFeedDatabaseRepository(newsItemDao)
  }

  @Provides
  fun provideNewsItemDao(context: Context): NewsItemDao {
    return Room.databaseBuilder(
      context = context,
      klass = AppDatabase::class.java,
      name = "db.sqlite"
    )
      .build()
      .newsItemDao()
  }
}

@Qualifier @Retention(RUNTIME) annotation class RemoteRepository
@Qualifier @Retention(RUNTIME) annotation class DBRepository
@Qualifier @Retention(RUNTIME) annotation class DataRepository