package com.mmartin.hackernewscompose.repository.firebase

import com.google.firebase.database.GenericTypeIndicator

inline fun <reified T>typeIndicator() : GenericTypeIndicator<T> {
    return object : GenericTypeIndicator<T>() {

    }
}