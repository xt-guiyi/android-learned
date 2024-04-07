package com.example.androidlearned.ui.home.activitys

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.ActivityNavigator
import com.example.androidlearned.R
import com.example.androidlearned.databinding.ActivityButtonWidgetBinding
import com.example.androidlearned.databinding.ActivityTextviewWidgetBinding
import com.example.androidlearned.utils.CommonCode

class TextviewWidgetActivity : AppCompatActivity() {
    lateinit var binding: ActivityTextviewWidgetBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityTextviewWidgetBinding.inflate(layoutInflater,null,false)
        setContentView(binding.root)
        CommonCode.addNaviGator(this,supportFragmentManager,R.id.fragment_container_view_atw,R.navigation.nav_graph_textview_widget)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }
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