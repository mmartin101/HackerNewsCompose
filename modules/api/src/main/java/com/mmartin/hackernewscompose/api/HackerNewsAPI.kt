package com.mmartin.hackernewscompose.api

import com.mmartin.hackernewscompose.models.network.NewsItemResponse
import com.mmartin.hackernewscompose.models.network.UserResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface HackerNewsAPI {
  @GET("v0/topstories.json")
  suspend fun topStories(): List<Long>

  @GET("v0/newstories.json")
  suspend fun newStories(): List<Long>

  @GET("v0/beststories.json")
  suspend fun bestStories(): List<Long>

  @GET("v0/item/{item_id}.json")
  suspend fun getItem(@Path("item_id") itemId: String): NewsItemResponse

  // Not used just yet, maybe will be useful in the future
  @GET("v0/user/{username}.json")
  suspend fun getUser(@Path("username") user: String): UserResponse
}