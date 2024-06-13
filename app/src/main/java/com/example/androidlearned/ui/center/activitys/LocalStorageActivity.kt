package com.example.androidlearned.ui.center.activitys

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.androidlearned.R
import com.example.androidlearned.databinding.ActivityLocalStorageBinding

class LocalStorageActivity : AppCompatActivity() {
    lateinit var binding: ActivityLocalStorageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLocalStorageBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}