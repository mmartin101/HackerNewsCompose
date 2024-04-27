package com.mmartin.hackernewscompose.repository

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.platform.app.InstrumentationRegistry
import com.mmartin.hackernewscompose.repository.db.AppDatabase
import com.mmartin.hackernewscompose.repository.db.NewsItemDBModel
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
  @Test
  fun addition_isCorrect() {

    val context = InstrumentationRegistry.getInstrumentation().context
    val db = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
    val dao = db.newsItemDao()
    val item = NewsItemDBModel(
      id = 1L,
      type = 1,
      by = "",
      time = "",
      title = "",
      url = ""
      )
    dao.insertAll(item)

    val results = dao.getAll()
    assertEquals(1, results.size)
  }
}