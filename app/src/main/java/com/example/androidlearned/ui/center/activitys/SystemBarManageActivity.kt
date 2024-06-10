package com.example.androidlearned.ui.center.activitys

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.example.androidlearned.R
import com.example.androidlearned.databinding.ActivitySystemBarManageBinding
import kotlin.math.abs

/**
 * 系统栏管理
 * */
class SystemBarManageActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySystemBarManageBinding
    private lateinit var windowInsetsController: WindowInsetsControllerCompat

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 防止布局抖动，让窗口可以延生到裁剪区域，同时设置窗口背景色为黑色，这样状态栏消失时会显示黑色
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P){
            window.attributes.layoutInDisplayCutoutMode = LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES
            window.setBackgroundDrawableResource(R.color.black)
        }
        binding = ActivitySystemBarManageBinding.inflate(layoutInflater)
        windowInsetsController = WindowCompat.getInsetsController(window, window.decorView)
        windowInsetsController.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        // 监听window.decorView后，状态栏，导航栏颜色修改就不生效了
        ViewCompat.setOnApplyWindowInsetsListener(window.decorView) { _, inset ->
            val statusBarInset = inset.getInsets(WindowInsetsCompat.Type.statusBars())
            val navigatorBarInset = inset.getInsets(WindowInsetsCompat.Type.navigationBars())
            Log.i("状态","${statusBarInset.top}-${statusBarInset.bottom}")
            binding.statusBarHeight.text = "状态栏高度：${abs(statusBarInset.bottom - statusBarInset.top)}"
            binding.navigationBarHegiht.text = "状态栏高度：${abs(navigatorBarInset.bottom - navigatorBarInset.top)}"
            inset
        }
        init()
        setContentView(binding.root)

    }

    fun init() {
        binding.button1.setOnClickListener {
            if (binding.button1.text == "隐藏状态栏") {
                binding.button1.text = "显示状态栏"
                windowInsetsController.hide(WindowInsetsCompat.Type.statusBars())
            } else {
                binding.button1.text = "隐藏状态栏"
                windowInsetsController.show(WindowInsetsCompat.Type.statusBars())
            }
        }
        binding.button2.setOnClickListener {
            if (binding.button2.text == "隐藏导航栏") {
                binding.button2.text = "显示导航栏"
                windowInsetsController.hide(WindowInsetsCompat.Type.navigationBars())
            } else {
                binding.button2.text = "隐藏导航栏"
                windowInsetsController.show(WindowInsetsCompat.Type.navigationBars())
            }
        }
        binding.button3.setOnClickListener {
            if (binding.button3.text == "隐藏状态栏与导航栏") {
                binding.button3.text = "显示状态栏与导航栏"
                windowInsetsController.hide(WindowInsetsCompat.Type.systemBars())
            } else {
                binding.button3.text = "隐藏状态栏与导航栏"
                windowInsetsController.show(WindowInsetsCompat.Type.systemBars())
            }
        }

        binding.button4.setOnClickListener {
            if (binding.button4.text == "修改状态栏和字体颜色为蓝色") {
                binding.button4.text = "修改状态栏和字体颜色为红色"
                windowInsetsController.isAppearanceLightStatusBars = true
                window.statusBarColor = resources.getColor(R.color.blue,null)
            } else {
                binding.button4.text = "修改状态栏和字体颜色为蓝色"
                windowInsetsController.show(WindowInsetsCompat.Type.systemBars())
                windowInsetsController.isAppearanceLightStatusBars = false
                window.statusBarColor = resources.getColor(R.color.red,null)

            }
        }

        binding.button5.setOnClickListener {
            if (binding.button5.text == "修改导航栏颜色为黑色") {
                binding.button5.text = "修改导航栏颜色为白色"
                window.navigationBarColor = resources.getColor(R.color.black_6,null)
                windowInsetsController.isAppearanceLightNavigationBars = true
            } else {
                binding.button5.text = "修改导航栏颜色为黑色"
                windowInsetsController.show(WindowInsetsCompat.Type.systemBars())
                window.navigationBarColor = resources.getColor(R.color.white,null)
                windowInsetsController.isAppearanceLightNavigationBars = false

            }
        }

        binding.button6.setOnClickListener {
            if (binding.button6.text == "开启全面屏") {
                binding.button6.text = "关闭全面屏"
                enableEdgeToEdge()
            }
        }
    }
}