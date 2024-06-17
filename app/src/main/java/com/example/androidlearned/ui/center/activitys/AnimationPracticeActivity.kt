package com.example.androidlearned.ui.center.activitys

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.commit
import com.example.androidlearned.R
import com.example.androidlearned.databinding.ActivityAnimationPracticeBinding
import com.example.androidlearned.ui.center.fragments.AnimationPracticeRootFragment

class AnimationPracticeActivity : AppCompatActivity() {
    lateinit var binding: ActivityAnimationPracticeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnimationPracticeBinding.inflate(layoutInflater)
        if(savedInstanceState == null ) {
            val ft = AnimationPracticeRootFragment()
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                add(binding.fragmentContainerViewAnimate.id, ft,"root")
                setPrimaryNavigationFragment(ft)
            }
        }
        setContentView(binding.root)
    }


}