package com.example.androidlearned

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.forEach
import androidx.navigation.ui.setupWithNavController
import com.example.androidlearned.databinding.ActivityMainBinding
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
        // ps: 注意需要覆盖掉默认的导航动画，不然会会有默认的淡入淡出效果，覆盖方法https://blog.csdn.net/qq_43680303/article/details/139222828
        binding.bottomNavView.setupWithNavController(navController)
        // 添加badge
        val badge = binding.bottomNavView.getOrCreateBadge(binding.bottomNavView.menu.getItem(0).itemId)
        // 去除长按提示，拦截长按事件
        binding.bottomNavView.menu.forEach {
            val menuItemView: View = findViewById(it.itemId)
            menuItemView.setOnLongClickListener {
                true
            }
        }
        badge.isVisible = true
        badge.number = 100
        badge.maxNumber = 99
        badge.verticalOffset = Display.dip2px(this,2f)  // px
        // 添加吐司框架
        Toaster.init(application)
    }

}