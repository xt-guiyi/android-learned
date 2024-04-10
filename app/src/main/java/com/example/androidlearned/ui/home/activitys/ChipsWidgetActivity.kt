package com.example.androidlearned.ui.home.activitys

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.androidlearned.R
import com.example.androidlearned.databinding.ActivityChipsWidgetBinding
import com.example.androidlearned.utils.CommonCode

class ChipsWidgetActivity : AppCompatActivity() {
    lateinit var binding:ActivityChipsWidgetBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChipsWidgetBinding.inflate(layoutInflater,null,false)
        setContentView(binding.root)
        CommonCode.addNaviGator(this,supportFragmentManager,R.id.fragment_container_view_acw,R.navigation.nav_graph_chips_widget)

    }
}