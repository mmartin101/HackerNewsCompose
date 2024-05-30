package com.mmartin.hackernewscompose.repository

import android.content.Context
import androidx.room.Room
import com.mmartin.hackernewscompose.api.HackerNewsApi
import com.mmartin.hackernewscompose.repository.db.AppDatabase
import com.mmartin.hackernewscompose.repository.db.NewsFeedDatabaseRepository
import com.mmartin.hackernewscompose.repository.db.NewsItemDao
import com.mmartin.hackernewscompose.repository.firebase.NewsFeedFirebaseRepository
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Named

@Module
object RepositoryModule {
  @Provides
  @Named("Remote")
  fun provideNewsFeedRemoteRepository(api: HackerNewsApi): NewsFeedRepository {
    return NewsFeedRemoteRepository(api)
  }

  @Provides
  @Named("Database")
  fun provideNewsFeedDatabaseRepository(newsItemDao: NewsItemDao): NewsFeedRepository {
    return NewsFeedDatabaseRepository(newsItemDao)
  }

  @Provides
  @Named("Firebase")
  fun provideNewsFeedFirebaseRepository(): NewsFeedRepository {
    return NewsFeedFirebaseRepository()
  }

  @Provides
  fun provideCoroutineDispatcher(): CoroutineDispatcher = Dispatchers.IO

  @Provides
  fun provideNewsItemDao(context: Context): NewsItemDao {
    // TODO: Add db migrations for future versions
    return Room.databaseBuilder(
      context = context,
      klass = AppDatabase::class.java,
      name = "db.sqlite"
    )
      .fallbackToDestructiveMigration()
      .build()
      .newsItemDao()
  }
}