package com.mmartin.hackernewscompose.mvvm.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.CreationExtras
import com.mmartin.hackernewscompose.mvvm.NewsFeedComponent

object NewsFeedViewModelFactory : ViewModelProvider.Factory {
  @Suppress("UNCHECKED_CAST")
  override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
    val application = checkNotNull(extras[APPLICATION_KEY])
    return NewsFeedComponent(application.applicationContext).createViewModel() as T
  }
}