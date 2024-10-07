package com.mmartin.hackernewscompose.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.mmartin.hackernewscompose.common.theme.AppTheme
import com.mmartin.hackernewscompose.presentation.viewmodel.NewsFeedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class NewsFeedMvvmActivity : ComponentActivity() {
    private val viewModel: NewsFeedViewModel by viewModel<NewsFeedViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.loadNewsFeed()
        setContent {
            AppTheme {
                NewsFeedView(viewModel = viewModel)
            }
        }
    }
}