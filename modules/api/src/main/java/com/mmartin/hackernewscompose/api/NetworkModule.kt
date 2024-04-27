package com.mmartin.hackernewscompose.api

import com.mmartin.hackernewscompose.models.network.TimeAdapter
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create

@Module
object NetworkModule {

  @Provides
  fun provideOkHttpClient(): OkHttpClient {
    val builder = OkHttpClient.Builder()
      .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
    return builder.build()
  }

  @Provides
  fun providesMoshi(): Moshi {
    return Moshi.Builder()
      .add(TimeAdapter())
      .build()
  }

  @Provides
  fun provideRetrofit(okHttpClient: OkHttpClient, moshi: Moshi): Retrofit {
    return Retrofit.Builder()
      .baseUrl("https://hacker-news.firebaseio.com/")
      .client(okHttpClient)
      .addConverterFactory(MoshiConverterFactory.create(moshi))
      .build()
  }

  @Provides
  fun provideHackerNewsApi(retrofit: Retrofit): HackerNewsApi {
    return retrofit.create()
  }
}