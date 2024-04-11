package com.example.androidlearned

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.get
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.androidlearned.databinding.ActivityMainBinding
import com.example.androidlearned.customNavgination.FragmentNavigator
import com.example.androidlearned.utils.CommonCode
import com.example.androidlearned.utils.Display

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navController = CommonCode.addNaviGator(this,supportFragmentManager,R.id.fragmentContainerView,R.navigation.nav_graph)
        binding.bottomNavView.setupWithNavController(navController)

        // 添加badge
        val badge = binding.bottomNavView.getOrCreateBadge(binding.bottomNavView.menu.getItem(0).itemId)
        badge.isVisible = true
        badge.number = 100
        badge.maxNumber = 99
        badge.verticalOffset = Display.dip2px(this,2f)  // px
    }

}