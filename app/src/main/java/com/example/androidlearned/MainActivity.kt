package com.example.androidlearned

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.androidlearned.databinding.ActivityMainBinding
import com.example.androidlearned.customNavgination.FragmentNavigator
import com.example.androidlearned.utils.CommonCode

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navController = CommonCode.addNaviGator(this,supportFragmentManager,R.id.fragmentContainerView,R.navigation.nav_graph)
        binding.bottomNavView.setupWithNavController(navController)
    }

}