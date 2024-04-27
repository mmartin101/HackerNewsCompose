package com.mmartin.hackernewscompose.repository.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [NewsItemDBModel::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
  abstract fun newsItemDao(): NewsItemDao
}