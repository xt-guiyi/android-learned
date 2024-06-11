package com.example.androidlearned.broadcasts

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.hjq.toast.Toaster

class MyBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        // This method is called when the BroadcastReceiver is receiving an Intent broadcast.
        Toaster.show("广播被调用,收到消息${intent.getStringExtra("msg")}")
    }
}