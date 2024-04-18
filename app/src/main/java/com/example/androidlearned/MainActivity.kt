package com.example.androidlearned

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.core.view.get
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.androidlearned.databinding.ActivityMainBinding
import com.example.androidlearned.customNavgination.FragmentNavigator
import com.example.androidlearned.utils.CommonCode
import com.example.androidlearned.utils.Display
import com.hjq.toast.Toaster

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navController = CommonCode.addNaviGator(this,supportFragmentManager,R.id.fragmentContainerView,R.navigation.nav_graph)
        // 设置底部导航
        binding.bottomNavView.setupWithNavController(navController)

        // 添加badge
        val badge = binding.bottomNavView.getOrCreateBadge(binding.bottomNavView.menu.getItem(0).itemId)
        badge.isVisible = true
        badge.number = 100
        badge.maxNumber = 99
        badge.verticalOffset = Display.dip2px(this,2f)  // px
        // 添加吐司框架
        Toaster.init(application)

        val sh = Handler(mainLooper)
    }

}