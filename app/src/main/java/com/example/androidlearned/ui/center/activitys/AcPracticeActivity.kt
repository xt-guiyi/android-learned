package com.example.androidlearned.ui.center.activitys

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.OnBackPressedCallback
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.androidlearned.databinding.ActivityAcPracticeBinding
import com.example.androidlearned.ui.home.activitys.CoordinationLayoutActivity
import com.hjq.toast.Toaster

/**
 * Activity使用
 * @see <a href="https://developer.android.com/guide/components/activities/intro-activities?hl=zh-cn">官方文档</a>
 * */
class AcPracticeActivity : AppCompatActivity() {
    lateinit var binding: ActivityAcPracticeBinding
    private val requestParamsLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        if (it.resultCode == RESULT_OK) {
            val intent = it.data
            binding.result.text = intent?.getStringExtra("msg")
        } else {
            Toaster.show("获取失败：${it.resultCode}")
        }
    }


    // 横，竖屏切换，保存简单,少量数据
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.apply {
            putString("id","1")
        }

    }

    // 恢复数据执行的操作，也可以写在onCreate
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        Log.i("恢复的数据",savedInstanceState.getString("id","null"))
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("Activity生命周期","onCreate")
        enableEdgeToEdge()
        binding = ActivityAcPracticeBinding.inflate(layoutInflater)
        init()
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
        Log.i("Activity生命周期","onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.i("Activity生命周期","onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.i("Activity生命周期","onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.i("Activity生命周期","onStop")
    }

    override fun onRestart() {
        super.onRestart()
        Log.i("Activity生命周期","onRestart")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("Activity生命周期","onDestroy")
    }

    fun init() {
        // 跳转页面
        binding.ac1.setOnClickListener{
            val intent = Intent(this,CoordinationLayoutActivity::class.java).apply {
                setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
            }
            startActivity(intent)
        }
        // 接受、传递数据
        binding.ac2.setOnClickListener{
            requestParamsLauncher.launch(
                Intent(
                    this,
                    AuthorizationManageActivity::class.java
                ).apply {
                    putExtra("id", 1)
                })
        }

        // 二次退出
        var lastBackPressTime = -1L
        onBackPressedDispatcher.apply {
            addCallback(object: OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    val currentTIme = System.currentTimeMillis()
                    if (lastBackPressTime == -1L || currentTIme - lastBackPressTime >= 2000) {
                        // 显示提示信息
                        Toaster.show("请再返回一次")
                        // 记录时间
                        lastBackPressTime = currentTIme
                    } else {
                        //退出应用
                        finish()
                    }
                }
            })
        }
    }

}