package com.example.androidlearned.services

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.Handler
import android.os.HandlerThread
import android.os.IBinder
import android.os.Looper
import android.os.Message
import android.util.Log
import com.hjq.toast.Toaster

class MyService3 : Service() {
    private val mMyService3Binding = MyService3Binding()

    override fun onBind(intent: Intent?): IBinder? {
        Toaster.show("绑定服务连接成功")
        return mMyService3Binding
    }

    override fun onUnbind(intent: Intent?): Boolean {
        Toaster.show("绑定服务关闭成功")
        return super.onUnbind(intent)
    }

    fun getRandomNumber() :Double {
        return Math.random() * 10
    }

    inner class MyService3Binding: Binder() {
            fun getService(): MyService3 = this@MyService3 // service实例,可调用service方法
            fun getMessage(): String {
                return "来自服务的消息"
            }
    }
}