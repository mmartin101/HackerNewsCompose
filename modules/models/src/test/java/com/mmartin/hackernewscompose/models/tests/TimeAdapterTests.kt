package com.mmartin.hackernewscompose.models.tests

import com.mmartin.hackernewscompose.models.network.TimeAdapter
import org.junit.Assert
import org.junit.Test
import java.time.LocalDateTime

class TimeAdapterTests {
  @Test
  fun `Can convert unix time to LocalDateTime`() {
    val timestamp = 1_321_801_200L
    val timeAdapter = TimeAdapter()

    val expectedTimeStamp = LocalDateTime.of(2011, 11, 20, 9, 0, 0)

    Assert.assertEquals(expectedTimeStamp, timeAdapter.fromJson(timestamp))
  }
}