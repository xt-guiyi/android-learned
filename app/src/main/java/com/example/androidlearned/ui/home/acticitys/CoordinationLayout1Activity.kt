package com.example.androidlearned.ui.home.acticitys

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.androidlearned.R
import com.example.androidlearned.databinding.ActivityCoordinationLayout1Binding

class CoordinationLayout1Activity : AppCompatActivity() {
    lateinit var binding: ActivityCoordinationLayout1Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coordination_layout1)
    }
}