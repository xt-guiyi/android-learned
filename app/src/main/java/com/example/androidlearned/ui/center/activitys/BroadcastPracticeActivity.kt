package com.example.androidlearned.ui.center.activitys

import android.content.BroadcastReceiver
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.androidlearned.broadcasts.MyBroadcastReceiver
import com.example.androidlearned.databinding.ActivityBroadcastPracticeBinding
import com.hjq.toast.Toaster


class BroadcastPracticeActivity : AppCompatActivity() {
    lateinit var binding: ActivityBroadcastPracticeBinding
    private lateinit var mBroadcastReceiver: BroadcastReceiver
    private lateinit var localBroadcastManager: LocalBroadcastManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBroadcastPracticeBinding.inflate(layoutInflater)
        init()
        setContentView(binding.root)
    }

    fun init() {
        binding.broadCast1.setOnClickListener{
            registerBroadcast()

        }
        binding.broadCast2.setOnClickListener{
            // unregisterReceiver(mBroadcastReceiver)
            localBroadcastManager.unregisterReceiver(mBroadcastReceiver);
        }
        binding.broadCast3.setOnClickListener{
            val intent = Intent()
            intent.setAction("xt-msg")
            intent.putExtra("msg","嘿嘿嘿看🤞")
            // sendBroadcast(intent)
            localBroadcastManager.sendBroadcast(intent)
        }
    }
    private fun registerBroadcast() {
        mBroadcastReceiver = MyBroadcastReceiver()
        val intentFilter = IntentFilter().apply {
            addAction("xt-msg")
        }
        // 本地广播
        // registerReceiver(mBroadcastReceiver,intentFilter, RECEIVER_NOT_EXPORTED)
         localBroadcastManager  = LocalBroadcastManager.getInstance(this);
        localBroadcastManager.registerReceiver(mBroadcastReceiver, intentFilter);
    }
}