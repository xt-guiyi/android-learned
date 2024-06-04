package com.example.androidlearned.ui.center.activitys

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.ActionMode
import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.androidlearned.R
import com.example.androidlearned.databinding.ActivityAcPracticeBinding
import com.hjq.toast.Toaster.init

class AcPracticeActivity : AppCompatActivity() {
    lateinit var binding: ActivityAcPracticeBinding

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
        Log.i("生命周期：","onCreate")
        enableEdgeToEdge()
        binding = ActivityAcPracticeBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_ac_practice)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        init()
    }

    override fun onStart() {
        super.onStart()
        Log.i("生命周期：","onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.i("生命周期：","onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.i("生命周期：","onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.i("生命周期：","onStop")
    }

    override fun onRestart() {
        super.onRestart()
        Log.i("生命周期：","onRestart")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("生命周期：","onDestroy")
    }

    fun init() {
        binding.ac1.setOnClickListener{
            val intent = Intent(this,SystemBarManageActivity::class.java).apply {
                setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
            }
            startActivity(intent)
        }
    }
}