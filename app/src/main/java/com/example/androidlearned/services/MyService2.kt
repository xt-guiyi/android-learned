package com.example.androidlearned.services

import android.app.ForegroundServiceStartNotAllowedException
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.content.pm.ServiceInfo
import android.graphics.drawable.Icon
import android.os.Build
import android.os.Handler
import android.os.HandlerThread
import android.os.IBinder
import android.os.Looper
import android.os.Message
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.ServiceCompat
import com.example.androidlearned.R
import com.hjq.toast.Toaster

class MyService2 : Service() {
    private var serviceLooper: Looper? = null
    private var serviceHandler: ServiceHandler? = null

    // Handler that receives messages from the thread
    private inner class ServiceHandler(looper: Looper) : Handler(looper) {

        override fun handleMessage(msg: Message) {
            // Normally we would do some work here, like download a file.
            // For our sample, we just sleep for 5 seconds.
            var count = 1
            try {
                Toaster.debugShow("MyService2：子线程开始执行任务，下载${msg.data.getString("name")}")
                while (count <= 100) {
                    Toaster.debugShow("MyService2：下载进度----${count}%")
                    Thread.sleep(500)
                    count += 2
                }
            } catch (e: InterruptedException) {
                // Restore interrupt status.
                Thread.currentThread().interrupt()
            }
            // Stop the service using the startId, so that we don't stop
            // the service in the middle of handling another job
            Toaster.debugShow("MyService2：${msg.data.getString("name")}下载完成")
            stopSelf(msg.arg1)
        }
    }

    private fun setForeground(gameName: String) {
        Log.i("Toaster", "111")
        try {
            val name = "下载文件"
            val descriptionText = "下载文件"
            val importance = NotificationManager.IMPORTANCE_HIGH
            val mChannel = NotificationChannel("download_file_channel", name, importance)
            mChannel.description = descriptionText
            val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(mChannel)
            // 创建通知
            val notification = NotificationCompat.Builder(this, "download_file_channel")
                .setSmallIcon(R.drawable.baseline_circle_notifications_24)
                .setContentTitle("下载通知")
                .setContentText("正在下载-->${gameName}")
                .setColor(resources.getColor(R.color.red,null))
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setLargeIcon(Icon.createWithResource(this, R.mipmap.launcher_round))
                .build()
//            startForeground(188, notification)
            ServiceCompat.startForeground(
                /* service = */ this,
                /* id = */ 999, // Cannot be 0
                /* notification = */ notification,
                /* foregroundServiceType = */
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                    ServiceInfo.FOREGROUND_SERVICE_TYPE_DATA_SYNC
                } else {
                    0
                },
            )
        } catch (e: Exception) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S
                && e is ForegroundServiceStartNotAllowedException
            ) {
                // App not in a valid state to start foreground service
                // (e.g. started from bg)
                Log.i("Toaster", e.message.toString())
            }
            // ...
        }
    }

    override fun onCreate() {
        super.onCreate()
        Log.i("Toaster", "MyService2：服务创建，初始化")
        HandlerThread("ServiceStartArguments", HandlerThread.MAX_PRIORITY).apply {
            start()
            serviceLooper = looper
            serviceHandler = ServiceHandler(looper)
        }
    }


    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        super.onStartCommand(intent, flags, startId)
        Toaster.debugShow("MyService2：服务开始")
        serviceHandler?.obtainMessage()?.also { msg ->
            msg.arg1 = startId
            msg.data = intent?.extras
            Toaster.debugShow("MyService2：发送任务给子线程")
            setForeground(intent?.getStringExtra("name") ?: "未命名")
            serviceHandler?.sendMessage(msg)
        }

        // If we get killed, after returning from here, restart
        return START_REDELIVER_INTENT
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("Toaster", "MyService2：服务结束")
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
}