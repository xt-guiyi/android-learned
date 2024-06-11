package com.example.androidlearned.ui.center.activitys

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import androidx.appcompat.app.AppCompatActivity
import com.example.androidlearned.databinding.ActivityServicePracticeBinding
import com.example.androidlearned.services.MyService1
import com.example.androidlearned.services.MyService2
import com.example.androidlearned.services.MyService3
import com.hjq.toast.Toaster

/**
 * 服务
 * PS: 当应用处于后台时（回到主屏幕，切换到其他app。
 * 发现使用线程、协程、后台服务、前台服务，在国产手机（oppo)上，后台执行20秒后就会停止运行，重新回到应用才会继续执行。哪怕声明为前台服务也没用，而谷歌原生安卓系统没有这个问题。
 * 这是因为国产手机厂商的省电策略导致的，为了避免有的app一直在后台执行后台任务，导致手机发热，耗电快。不同的手机厂商都会定制系统，使用相应的策略去阻止后台任务一直运行。
 * 解决办法：打开应用设置，允许应用在后台运行。
 * */
class ServicePracticeActivity : AppCompatActivity() {
    lateinit var binding: ActivityServicePracticeBinding
    var mBound = false
    var mService: MyService3? = null
    private val serviceConnection = object : ServiceConnection{
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            mBound = true
            mService = (service as MyService3.MyService3Binding).getService()
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            mBound = false
            Toaster.show("绑定服务连接丢失")
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityServicePracticeBinding.inflate(layoutInflater)

        init()
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
        // 绑定服务，可以与服务通信
        bindService(Intent(this,MyService3::class.java),serviceConnection, BIND_AUTO_CREATE)
    }

    override fun onStop() {
        super.onStop()
        unbindService(serviceConnection)
        mBound = false
    }

    fun init() {
        binding.bcService.setOnClickListener {
            startService(Intent(this, MyService1::class.java).apply {
                putExtra("name","王者荣耀")
            })
        }

        binding.fgService.setOnClickListener {
            startForegroundService(Intent(this, MyService2::class.java).apply {
                putExtra("name","和平精英")
            })
        }

        binding.bindingService.setOnClickListener {

            mService?.apply {
                Toaster.show("随机值：${getRandomNumber()}")
            }
        }
    }
}