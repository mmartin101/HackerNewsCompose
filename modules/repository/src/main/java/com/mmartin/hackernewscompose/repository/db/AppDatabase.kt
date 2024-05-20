package com.mmartin.hackernewscompose.repository.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mmartin.hackernewscompose.repository.db.models.NewsItemDBModel
import com.mmartin.hackernewscompose.repository.db.models.StoriesListDB

@Database(
  entities = [NewsItemDBModel::class, StoriesListDB::class],
  version = 1,
  exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
  abstract fun newsItemDao(): NewsItemDao
}