package com.example.androidlearned.ui.home.activitys

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ActivityNavigator
import com.example.androidlearned.R
import com.example.androidlearned.databinding.ActivityButtonWidgetBinding
import com.example.androidlearned.utils.CommonCode

class ButtonWidgetActivity : AppCompatActivity() {
    lateinit var binding: ActivityButtonWidgetBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityButtonWidgetBinding.inflate(layoutInflater,null,false)
        setContentView(binding.root)
        CommonCode.addNaviGator(this,supportFragmentManager,R.id.fragment_container_view_abw,R.navigation.nav_graph_button_widget)
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