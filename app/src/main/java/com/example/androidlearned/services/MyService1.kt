package com.example.androidlearned.services

import android.app.Service
import android.content.Intent
import android.os.Handler
import android.os.HandlerThread
import android.os.IBinder
import android.os.Looper
import android.os.Message
import android.util.Log
import com.hjq.toast.Toaster

class MyService1 : Service() {
    private var serviceLooper: Looper? = null
    private var serviceHandler: ServiceHandler? = null

    // Handler that receives messages from the thread
    private inner class ServiceHandler(looper: Looper) : Handler(looper) {

        override fun handleMessage(msg: Message) {
            // Normally we would do some work here, like download a file.
            // For our sample, we just sleep for 5 seconds.
            var count = 1
            try {
                Toaster.debugShow("MyService1：子线程开始执行任务，下载${msg.data.getString("name")}")
                while (count <= 100) {
                    Toaster.debugShow("MyService1：下载进度----${count}%")
                    Thread.sleep(500)
                    count += 2
                }
            } catch (e: InterruptedException) {
                // Restore interrupt status.
                Thread.currentThread().interrupt()
            }
            // Stop the service using the startId, so that we don't stop
            // the service in the middle of handling another job
            Toaster.debugShow("MyService1：${msg.data.getString("name")}下载完成")
            stopSelf(msg.arg1)
        }
    }

    override fun onCreate() {
        super.onCreate()
        Log.i("Toaster", "MyService1：服务创建，初始化")
        HandlerThread("ServiceStartArguments", HandlerThread.MAX_PRIORITY).apply {
            start()
            serviceLooper = looper
            serviceHandler = ServiceHandler(looper)
        }
    }


    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        super.onStartCommand(intent, flags, startId)
        Toaster.debugShow("MyService1：服务开始")
        serviceHandler?.obtainMessage()?.also { msg ->
            msg.arg1 = startId
            msg.data = intent?.extras
            Toaster.debugShow("MyService1：发送任务给子线程")
            serviceHandler?.sendMessage(msg)
        }

        // If we get killed, after returning from here, restart
        return START_REDELIVER_INTENT
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("Toaster", "MyService1：服务结束")
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
}