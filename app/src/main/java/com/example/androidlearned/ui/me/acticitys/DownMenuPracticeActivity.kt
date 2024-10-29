package com.example.androidlearned.ui.me.acticitys

import android.media.AudioFocusRequest
import android.media.AudioManager
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.view.ViewGroup
import android.widget.PopupWindow
import androidx.appcompat.app.AppCompatActivity
import com.example.androidlearned.databinding.ActivityAudioPracticeBinding
import com.example.androidlearned.databinding.ActivityDownMenuPracticeBinding
import com.example.androidlearned.databinding.LayoutDownMenuItemBinding
import com.hjq.toast.Toaster


// 下拉菜单
class DownMenuPracticeActivity : AppCompatActivity() {
    lateinit var binding: ActivityDownMenuPracticeBinding
    private lateinit var popupWindowLayoutBinding: LayoutDownMenuItemBinding
    private val popupWindow: PopupWindow by lazy {
        PopupWindow(
            popupWindowLayoutBinding.root,
            ViewGroup.LayoutParams.MATCH_PARENT,
            // 设置高度： 屏幕高度 - 要出现的view的所处地方高度
            resources.displayMetrics.heightPixels - binding.openMenu.y.toInt() - binding.openMenu.height,
//            resources.displayMetrics.heightPixels -600,
            true
        ).apply {
            animationStyle = 0;
            isOutsideTouchable = true
            setOnDismissListener {
                Toaster.show("关闭")
            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 布局
        popupWindowLayoutBinding =  LayoutDownMenuItemBinding.inflate(layoutInflater, null, false)
        binding = ActivityDownMenuPracticeBinding.inflate(layoutInflater, null, false)
        init()
        setContentView(binding.root)
    }

    fun init() {
        binding.openMenu.setOnClickListener {
            popupWindow.showAsDropDown(
                binding.openMenu,
                0,
               0
            )

            popupWindowLayoutBinding.mark.setOnClickListener {
                popupWindow.dismiss()
            }
        }


    }

    override fun onDestroy() {
        super.onDestroy()
    }
}