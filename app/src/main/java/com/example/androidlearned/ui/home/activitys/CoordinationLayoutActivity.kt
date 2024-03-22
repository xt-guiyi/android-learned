package com.example.androidlearned.ui.home.activitys

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.ReportFragment.Companion.reportFragment
import androidx.navigation.ActivityNavigator
import androidx.navigation.fragment.NavHostFragment
import com.example.androidlearned.R
import com.example.androidlearned.customNavgination.FragmentNavigator
import com.example.androidlearned.databinding.ActivityCoordinationLayoutBinding
import com.example.androidlearned.utils.CommonCode

class CoordinationLayoutActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCoordinationLayoutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCoordinationLayoutBinding.inflate(layoutInflater,null,false)
        CommonCode.addNaviGator(this,supportFragmentManager,R.id.fragment_container_view_cla,R.navigation.nav_graph_home_coordination_layout)
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