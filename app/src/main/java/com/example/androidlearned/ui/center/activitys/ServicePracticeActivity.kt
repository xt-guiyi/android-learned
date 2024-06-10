package com.example.androidlearned.ui.center.activitys

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.androidlearned.databinding.ActivityServicePracticeBinding
import com.example.androidlearned.services.MyService1
import com.example.androidlearned.services.MyService2

class ServicePracticeActivity : AppCompatActivity() {
    lateinit var binding: ActivityServicePracticeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityServicePracticeBinding.inflate(layoutInflater)
        init()
        setContentView(binding.root)
    }

    fun init() {
        binding.bcService.setOnClickListener {
            startService(Intent(this, MyService1::class.java).apply {
                putExtra("name","王者荣耀")
            })
        }

        binding.fgService.setOnClickListener {
            startForegroundService(Intent(this, MyService2::class.java).apply {
                putExtra("name","和平精英")
            })
        }

        binding.bindingService.setOnClickListener {

        }
    }
}