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
import com.example.androidlearned.databinding.ActivityBottomDialogPracticeBinding
import com.example.androidlearned.databinding.ActivityDownMenuPracticeBinding
import com.example.androidlearned.databinding.LayoutDownMenuItemBinding
import com.example.androidlearned.ui.me.fragments.ActionsDialogFragment
import com.hjq.toast.Toaster


// 下拉菜单
class BottomDialogPracticeActivity : AppCompatActivity() {
    lateinit var binding: ActivityBottomDialogPracticeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBottomDialogPracticeBinding.inflate(layoutInflater, null, false)
        init()
        setContentView(binding.root)
    }

    fun init() {
        binding.openMenu.setOnClickListener {
            ActionsDialogFragment().show(supportFragmentManager, ActionsDialogFragment.TAG)
        }


    }

    override fun onDestroy() {
        super.onDestroy()
    }
}