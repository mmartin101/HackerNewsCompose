package com.mmartin.hackernewscompose.presentation

import com.mmartin.hackernewscompose.presentation.viewmodel.NewsFeedViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object NewsFeedPresentationModule {
    val koin = module {
        viewModel { NewsFeedViewModel(get()) }
    }
}