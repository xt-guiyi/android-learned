package com.example.androidlearned

import android.app.Application
import android.util.Log
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors


class MyApplication : Application() {
    private var executorService: ExecutorService = Executors.newFixedThreadPool(4) // 全局线程池
    fun getExecutorService() = executorService
    init {
        Log.i("Toaster","应用创建")
    }
}