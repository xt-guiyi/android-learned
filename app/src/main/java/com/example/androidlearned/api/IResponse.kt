package com.example.androidlearned.api

data class IResponse<T>(val code: Int, val msg: String, val data: T)