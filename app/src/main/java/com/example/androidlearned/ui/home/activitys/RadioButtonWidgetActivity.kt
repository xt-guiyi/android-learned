package com.example.androidlearned.ui.home.activitys

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.ActivityNavigator
import com.example.androidlearned.R
import com.example.androidlearned.utils.CommonCode

class RadioButtonWidgetActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_radio_button_widget)
        CommonCode.addNaviGator(this,supportFragmentManager,R.id.fragment_container_view_arbw,R.navigation.nav_graph_radio_button_widget)

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