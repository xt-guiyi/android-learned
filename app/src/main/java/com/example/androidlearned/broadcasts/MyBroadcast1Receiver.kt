package com.example.androidlearned.broadcasts

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.util.Log
import android.widget.Toast
import com.example.androidlearned.ui.center.activitys.BroadcastPracticeActivity

class MyBroadcast1Receiver : BroadcastReceiver() {
    // 接收器的 onReceive(Context, Intent) 方法在主线程上运行，因此它应该快速执行并返回
    @SuppressLint("UnsafeProtectedBroadcastReceiver")
    override fun onReceive(context: Context, intent: Intent) {
        Log.i("Toaster","收到充电广播")
        Toast.makeText(context,"开始充电啦",Toast.LENGTH_SHORT).show()
        // 应用没有启动时，跳转代码在国产手机不会生效，启动后才会生效
        context.startActivity(Intent(context, BroadcastPracticeActivity::class.java).apply {
            flags = FLAG_ACTIVITY_NEW_TASK
        })
    }
}