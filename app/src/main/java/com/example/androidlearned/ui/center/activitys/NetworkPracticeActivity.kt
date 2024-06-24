package com.example.androidlearned.ui.center.activitys

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.androidlearned.databinding.ActivityNetworkPracticeBinding

class NetworkPracticeActivity : AppCompatActivity() {
    lateinit var binding: ActivityNetworkPracticeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNetworkPracticeBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}