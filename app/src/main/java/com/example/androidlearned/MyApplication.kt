package com.example.androidlearned

import android.app.Application
import android.util.Log
import com.example.androidlearned.api.MokeAPi
import com.example.androidlearned.repository.ExampleRepository
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors


class MyApplication : Application() {
    private var executorService: ExecutorService = Executors.newFixedThreadPool(4) // 全局线程池
    private var exampleRepository: ExampleRepository= ExampleRepository(MokeAPi.create())
    fun getExecutorService() = executorService
    fun getExampleRepository() = exampleRepository
    init {
        Log.i("Toaster","应用创建")
    }
}