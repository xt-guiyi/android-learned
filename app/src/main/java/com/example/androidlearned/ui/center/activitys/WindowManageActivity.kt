package com.example.androidlearned.ui.center.activitys

import android.content.Intent
import android.graphics.PixelFormat
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.WindowManager
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.androidlearned.R
import com.example.androidlearned.databinding.ActivityWindowManageBinding
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.hjq.toast.Toaster

/**
 * 窗口管理
 * @see <a href="https://juejin.cn/post/7076274407416528909?searchId=202406121129113C24FA161A0673A0328A">参考文档</a>
 * */
class WindowManageActivity : AppCompatActivity() {
    lateinit var binding: ActivityWindowManageBinding
    private lateinit var floatButton3: FloatingActionButton
    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { _ ->
        if (!Settings.canDrawOverlays(this)) {
            // 未授予权限，请求权限
            Toaster.show("未授予权限")
        } else {
            // 已授予权限，执行添加悬浮窗的操作
            // 权限请求成功
            Toaster.show("权限请求成功")
            addFloatingWindow()
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWindowManageBinding.inflate(LayoutInflater.from(this))
        floatButton3 = FloatingActionButton(this)
        enableEdgeToEdge()
        setContentView(binding.root)
        init()

    }

    fun init() {
        val floatButton = ExtendedFloatingActionButton(this)
        val floatButton2 = ExtendedFloatingActionButton(this)
        floatButton.text = "应用层级窗口"
        floatButton2.text = "子窗口层级窗口"
        floatButton.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT)
        floatButton2.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT)
        binding.window1.setOnClickListener {
            if (binding.window1.text == "打开一个应用层级窗口") {
                binding.window1.text = "关闭一个应用层级窗口"
                val windowParams = WindowManager.LayoutParams(
                    400,
                    200,
                    WindowManager.LayoutParams.LAST_APPLICATION_WINDOW,
                    WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE, // 设置这个才可以按返回键退出页面
                    PixelFormat.TRANSPARENT
                )
                windowParams.x =  0
                windowParams.y  = 0
                windowManager.addView(floatButton,windowParams)
            }else {
                binding.window1.text = "打开一个应用层级窗口"
                windowManager.removeView(floatButton)
            }

        }

        binding.window2.setOnClickListener {
            if (binding.window2.text == "打开一个子窗口层级窗口") {
                binding.window2.text = "关闭一个子窗口层级窗口"
                val windowParams = WindowManager.LayoutParams(
                    400,
                    200,
                    WindowManager.LayoutParams.LAST_SUB_WINDOW,
                    WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE, // 设置这个才可以按返回键退出页面
                    PixelFormat.TRANSPARENT
                )
                windowParams.gravity = Gravity.START or Gravity.TOP
                windowParams.x =  0
                windowParams.y  = 0
                // 成为floatButton所在窗口的子窗口，这样可以显示在floatButton的上面，不写的话会默认为activity所在的根窗口
                windowParams.token = floatButton.windowToken
                windowManager.addView(floatButton2,windowParams)
            }else {
                binding.window2.text = "打开一个子窗口层级窗口"
                windowManager.removeView(floatButton2)
            }

        }

        binding.window3.setOnClickListener {
            if (!Settings.canDrawOverlays(this)) {
                // 未授予权限，请求权限
                val intent = Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:$packageName"))
                requestPermissionLauncher.launch(intent)
            } else {
                // 已授予权限，执行添加悬浮窗的操作
                addFloatingWindow()
            }
        }
    }


    private fun addFloatingWindow() {
        floatButton3.setImageResource(R.drawable.baseline_add_24)
        floatButton3.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT)
        if (binding.window3.text == "打开一个系统层级窗口") {
            binding.window3.text = "关闭一个系统层级窗口"
            val windowParams = WindowManager.LayoutParams(
                200,
                200,
                WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE, // 设置这个才可以按返回键退出页面
                PixelFormat.TRANSPARENT
            )
            windowParams.gravity = Gravity.BOTTOM or Gravity.END
            windowParams.x =  100
            windowParams.y  = 100
            windowManager.addView(floatButton3,windowParams)
        }else {
            binding.window3.text = "打开一个系统层级窗口"
            windowManager.removeView(floatButton3)
        }
    }
}