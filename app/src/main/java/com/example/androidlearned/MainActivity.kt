package com.example.androidlearned

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.androidlearned.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
//        val navHostFragment = binding.fragmentContainerView as NavHostFragment
//        val navController = navHostFragment.navController
//        binding.bottomNavView.setupWithNavController(navController)
        setContentView(binding.root)
    }

}