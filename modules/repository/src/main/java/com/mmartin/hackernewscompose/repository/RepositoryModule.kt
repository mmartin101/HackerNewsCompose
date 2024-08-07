package com.mmartin.hackernewscompose.repository

import androidx.room.Room
import com.mmartin.hackernewscompose.repository.db.AppDatabase
import com.mmartin.hackernewscompose.repository.db.NewsFeedDatabaseRepository
import com.mmartin.hackernewscompose.repository.firebase.NewsFeedFirebaseRepository
import dagger.Module
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

@Module
object RepositoryModule {
    val koinModule = module {
        single {
            NewsFeedDataRepository(
                db = NewsFeedDatabaseRepository(get()),
                firebase = NewsFeedFirebaseRepository(),
                coroutineDispatcher = Dispatchers.IO
            )
        }

        single {
            Room.databaseBuilder(
                context = get(),
                klass = AppDatabase::class.java,
                name = "db.sqlite"
            )
                .fallbackToDestructiveMigration()
                .build()
                .newsItemDao()
        }
    }
}