package com.example.androidlearned.ui.center.activitys

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.pm.ShortcutInfoCompat
import androidx.core.content.pm.ShortcutManagerCompat
import androidx.core.graphics.drawable.IconCompat
import com.example.androidlearned.MainActivity
import com.example.androidlearned.R
import com.example.androidlearned.databinding.ActivityShortcutsPracticeBinding

class ShortcutsPracticeActivity : AppCompatActivity() {
    lateinit var binding: ActivityShortcutsPracticeBinding
    private val short: ShortcutInfoCompat
        get() {
        return  ShortcutInfoCompat.Builder(this,"START_DYNAMIC_SHORT")
            .setShortLabel("动态快捷方式")
            .setDisabledMessage("快捷方式停用啦")
            .setIcon(IconCompat.createWithResource(this, R.drawable.baseline_add_24))
            .setIntent(Intent(Intent.ACTION_VIEW, Uri.EMPTY,this,NetworkPracticeActivity::class.java))
            .build()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShortcutsPracticeBinding.inflate(layoutInflater)
        init()
        setContentView(binding.root)
    }

    fun init() {
        binding.shortcut1.setOnClickListener {
            ShortcutManagerCompat.pushDynamicShortcut(this,short)
        }

        binding.shortcut2.setOnClickListener {
            ShortcutManagerCompat.removeDynamicShortcuts(this, listOf(short.id))
        }

        binding.shortcut3.setOnClickListener {
            if(ShortcutManagerCompat.isRequestPinShortcutSupported(this)) {
                val pinShortcutInfo = ShortcutInfoCompat.Builder(this,"START_FIX_SHORT")
                    .setIcon(IconCompat.createWithResource(this,
                        R.drawable.baseline_circle_notifications_24
                    ))
                    .setDisabledMessage("快捷方式停用啦")
                    .setShortLabel("固定快捷方式启动")
                    .setIntent(Intent(Intent.ACTION_VIEW, Uri.EMPTY,this,MainActivity::class.java))
                    .build()
                ShortcutManagerCompat.requestPinShortcut(this,pinShortcutInfo,null)
            }
        }

    }
}