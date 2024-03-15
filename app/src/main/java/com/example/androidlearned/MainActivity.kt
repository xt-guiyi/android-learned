package com.example.androidlearned

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.androidlearned.databinding.ActivityMainBinding
import com.example.androidlearned.customNavgination.FragmentNavigator
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController
        // navHostFragment.childFragmentManager,防止导航失败， view does not have a NavController set错误
        navController.navigatorProvider.addNavigator("fragment",FragmentNavigator(this,navHostFragment.childFragmentManager,navHostFragment.id))
        navController.setGraph(R.navigation.nav_graph)

        binding.bottomNavView.setupWithNavController(navController)
    }

}