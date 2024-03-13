package com.example.androidlearned.ui.home.activitys

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.ActivityNavigator
import com.example.androidlearned.databinding.ActivityCoordinationLayout1Binding

class CoordinationLayout1Activity : AppCompatActivity() {
    lateinit var binding: ActivityCoordinationLayout1Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCoordinationLayout1Binding.inflate(layoutInflater,null,false)
        setContentView(binding.root)
    }

    /**
     * 导航到一个 Activity 后，从中导航出来时，系统不会自动应用弹出动画，需要设置下面这段方法.
     * 将弹出动画应用于 Activity 过渡(https://developer.android.com/guide/navigation/use-graph/animate-transitions?hl=zh-cn#activity)
     * */
    override fun finish() {
        super.finish()
        ActivityNavigator.applyPopAnimationsToPendingTransition(this)
    }
}