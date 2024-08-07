package com.example.androidlearned.ui.center.activitys

import android.Manifest
import android.app.AlarmManager
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.provider.Settings.ACTION_REQUEST_SCHEDULE_EXACT_ALARM
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.androidlearned.databinding.ActivityAuthorizationManageActivityBinding
import com.hjq.toast.Toaster

/**
 * 权限管理
 * @see <a href="https://developer.android.com/guide/topics/permissions/overview?hl=zh-cn">官方文档</a>
 * */
class AuthorizationManageActivity : AppCompatActivity() {
    lateinit var binding: ActivityAuthorizationManageActivityBinding
    private val requestCameraPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                // Permission is granted. Continue the action or workflow in your
                // app.
                // 有权限,去打开摄像头
                val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                startActivity(takePictureIntent)
            } else {
                // Explain to the user that the feature is unavailable because the
                // feature requires a permission that the user has denied. At the
                // same time, respect the user's decision. Don't link to system
                // settings in an effort to convince the user to change their
                // decision.
                Toaster.show("未授权权限")
            }
        }

    private val requestAlarmPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
            val alarmManager = getSystemService<AlarmManager>(AlarmManager::class.java)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                    if(alarmManager.canScheduleExactAlarms()) {
                        Toaster.show("拿到闹钟权限")
                    }else {
                        Toaster.show("没拿到闹钟权限")
                    }
            } else {
                TODO("VERSION.SDK_INT < S")
            }
        }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityAuthorizationManageActivityBinding.inflate(layoutInflater)
        init()
        setContentView(binding.root)
        // 获取上个界面给的数据
        intent.getIntExtra("id", 0).let {
           if (it != 0) Toaster.show("id:$it")
        }
        // 设置返回结果给上一个页面
        setResult(RESULT_OK, Intent().apply {
            putExtra("msg", "获取到的数据为:${Math.random() * 10}")
        })
    }

    fun init(){
        binding.author1.setOnClickListener {
            when {
                ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.CAMERA
                ) == PackageManager.PERMISSION_GRANTED -> {
                    // You can use the API that requires the permission.
                    // 有权限,去打开摄像头
                    val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                    startActivity(takePictureIntent)
                }
                ActivityCompat.shouldShowRequestPermissionRationale(
                    this, Manifest.permission.CAMERA) -> {
                    // In an educational UI, explain to the user why your app requires this
                    // permission for a specific feature to behave as expected, and what
                    // features are disabled if it's declined. In this UI, include a
                    // "cancel" or "no thanks" button that lets the user continue
                    // using your app without granting the permission.
                    Toaster.show("需要显示ui界面告诉用户为何需要权限")
                }
                else -> {
                    // You can directly ask for the permission.
                    // The registered ActivityResultCallback gets the result of this request.
                    // 请求权限
                    requestCameraPermissionLauncher.launch(
                        Manifest.permission.CAMERA)
                }
            }
        }
        binding.author2.setOnClickListener {
            val alarmManager = getSystemService<AlarmManager>(AlarmManager::class.java)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                when {
                    // if permission is granted, proceed with scheduling exact alarms…
                    alarmManager.canScheduleExactAlarms() -> {
                        // 可以用闹钟
                        Toaster.show("有闹钟权限")

                    }
                    else -> {
                        // ask users to grant the permission in the corresponding settings page
                        requestAlarmPermissionLauncher.launch(Intent(ACTION_REQUEST_SCHEDULE_EXACT_ALARM))
                    }
                }
            }
        }
    }
}