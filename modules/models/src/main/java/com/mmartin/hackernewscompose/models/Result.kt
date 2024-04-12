package com.mmartin.hackernewscompose.models

sealed class Result<Value, Error> {
  data class Success<Value, Error>(val value: Value) : Result<Value, Error>()
  data class Error<Value, Error>(val error: Error) : Result<Value, Error>()
}
