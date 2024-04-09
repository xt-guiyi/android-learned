package com.example.androidlearned.ui.home.activitys

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.androidlearned.R
import com.example.androidlearned.databinding.ActivitySwitchWidgetBinding
import com.example.androidlearned.utils.CommonCode

class SwitchWidgetActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySwitchWidgetBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySwitchWidgetBinding.inflate(layoutInflater)
        setContentView(binding.root)
        CommonCode.addNaviGator(this,supportFragmentManager,R.id.fragment_container_view_asw,R.navigation.nav_graph_switch_widget)
    }
}