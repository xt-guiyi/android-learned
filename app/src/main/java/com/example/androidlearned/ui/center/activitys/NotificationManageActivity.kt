package com.example.androidlearned.ui.center.activitys

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.drawable.Icon
import android.os.Build
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.androidlearned.MainActivity
import com.example.androidlearned.R
import com.example.androidlearned.databinding.ActivityNotificationManageBinding
import com.hjq.toast.Toaster
/**
 * 通知管理
 * @see <a href="https://developer.android.com/develop/ui/views/notifications?hl=zh-cn">官方文档</a>
 * */
class NotificationManageActivity : AppCompatActivity() {
    lateinit var binding: ActivityNotificationManageBinding
    private val CHANNEL_ID = "ad_channel"
    private val requestPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
        if (isGranted) {
            Toaster.show("已授权")
        } else {
            Toaster.show("未授权")
        }
    }
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityNotificationManageBinding.inflate(layoutInflater)
        createNotificationChannel()
        init()
        setContentView(binding.root)
    }


    /**
     * 创建通知渠道
     * */
    private fun createNotificationChannel() {
        // Create the NotificationChannel.
        val name = "营销通知"
        val descriptionText = "发送广告，为用户提供更好的广告服务🤣"
        val importance = NotificationManager.IMPORTANCE_HIGH
        val mChannel = NotificationChannel(CHANNEL_ID, name, importance)
        mChannel.description = descriptionText
        // Register the channel with the system. You can't change the importance
        // or other notification behaviors after this.
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(mChannel)

    }

    private  fun createNotification1() {
        val intent = Intent(this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent: PendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)

        val builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.baseline_circle_notifications_24)
            .setColor(resources.getColor(R.color.red,null))
            .setContentTitle("购物通知🐶")
            .setContentText("快来抢购更多好物吧，这里有十分便宜的商品🌸🌸🌸")
            .setStyle(NotificationCompat.BigTextStyle().bigText("快来抢购更多好物吧，这里有十分便宜的商品🌸🌸🌸")) // 展示大文本，可以显示全部内容
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setLargeIcon(Icon.createWithResource(this, R.mipmap.launcher_round))
            .setContentIntent(pendingIntent)
            .setAutoCancel(true) // 点击后关闭
            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC) // 设置在锁定屏幕上显示通知的完整内容。
            .setTimeoutAfter(15000) // 设置通知超时时间，超过后关闭通知
            .build()

        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(1, builder)
    }


    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    fun init () {
        binding.button1.setOnClickListener {
            with(NotificationManagerCompat.from(this)) {
                if (ActivityCompat.checkSelfPermission(
                        this@NotificationManageActivity,
                        Manifest.permission.POST_NOTIFICATIONS
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    // 没权限，申请权限
                    requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
                    return@with
                }
                createNotification1()
            }
        }
    }
}