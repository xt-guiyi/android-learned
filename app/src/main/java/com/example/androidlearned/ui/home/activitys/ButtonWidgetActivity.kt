package com.example.androidlearned.ui.home.activitys

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.androidlearned.R
import com.example.androidlearned.databinding.ActivityButtonWidgetBinding
import com.example.androidlearned.utils.CommonCode

class ButtonWidgetActivity : AppCompatActivity() {
    lateinit var binding: ActivityButtonWidgetBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityButtonWidgetBinding.inflate(layoutInflater,null,false)
        CommonCode.addNaviGator(this,supportFragmentManager,R.id.fragment_container_view_abw,R.navigation.nav_graph_button_widget)
        setContentView(binding.root)
    }
}