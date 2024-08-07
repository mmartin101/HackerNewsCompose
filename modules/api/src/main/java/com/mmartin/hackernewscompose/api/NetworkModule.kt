package com.mmartin.hackernewscompose.api

import com.mmartin.hackernewscompose.models.network.TimeAdapter
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create

/**
 * Since we are using firebase there's no need to use this api module. However it could still
 * be of use if we want to have a version without google/firebase dependencies
 */
class NetworkModule(private val debug: Boolean) {
    val koinModule = module {
        factory {
            val builder = OkHttpClient.Builder()
            if (debug) {
                builder.addInterceptor(
                    HttpLoggingInterceptor()
                        .setLevel(HttpLoggingInterceptor.Level.BODY)
                )
            }
            builder.build()
        }

        factory {
            Moshi.Builder()
                .add(TimeAdapter())
                .build()
        }

        single {
            Retrofit.Builder()
                .baseUrl("https://hacker-news.firebaseio.com/")
                .client(get())
                .addConverterFactory(MoshiConverterFactory.create(get()))
                .build()
        }

        single { get<Retrofit>().create<HackerNewsApi>() }
    }
}

