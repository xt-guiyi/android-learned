package com.example.androidlearned.utils

import okhttp3.Callback
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import java.util.concurrent.TimeUnit

object OkHttpUtil {
    private val client = OkHttpClient.Builder().connectTimeout(20, TimeUnit.SECONDS).build()

    // 同步GET请求
    @Throws(Exception::class)
    fun get(url: String): String {
        val request = Request.Builder().url(url).build()
        client.newCall(request).execute().use { response ->
            return response.body()!!.string()
        }
    }

    // 异步GET请求
    fun getAsync(url: String, callback: Callback) {
        val request = Request.Builder().url(url).build()
        client.newCall(request).enqueue(callback)
    }

    // 同步POST请求
    @Throws(Exception::class)
    fun post(url: String, json: String): String {
        val body = RequestBody.create(MediaType.get("application/json"), json)
        val request = Request.Builder().url(url).post(body).build()
        client.newCall(request).execute().use { response ->
            return response.body()!!.string()
        }
    }

    // 异步POST请求
    fun postAsync(url: String, json: String, callback: Callback) {
        val body = RequestBody.create(MediaType.get("application/json"), json)
        val request = Request.Builder().url(url).post(body).build()
        client.newCall(request).enqueue(callback)
    }
}
