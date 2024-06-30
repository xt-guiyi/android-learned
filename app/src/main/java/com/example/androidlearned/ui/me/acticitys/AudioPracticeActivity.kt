package com.example.androidlearned.ui.me.acticitys

import android.media.AudioFocusRequest
import android.media.AudioManager
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.androidlearned.databinding.ActivityAudioPracticeBinding
import com.hjq.toast.Toaster

class AudioPracticeActivity : AppCompatActivity() {
    lateinit var binding: ActivityAudioPracticeBinding
    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var audioManager: AudioManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        audioManager = getSystemService(AUDIO_SERVICE) as AudioManager
        binding = ActivityAudioPracticeBinding.inflate(layoutInflater)
        mediaPlayer = MediaPlayer()
        mediaPlayer.setDataSource(
            this,
            Uri.parse("https://audios.cubox.pro/iw3rni/file/2024063018174248065/Roys+-+Can't+you+say%E6%81%8B%E7%88%B1%E7%A6%81%E6%AD%A2%E4%B8%96%E7%95%8Ced.mp3")
        )
        mediaPlayer.isLooping = true
        mediaPlayer.prepareAsync() // 异步准备

        init()
        setContentView(binding.root)
    }

    fun init() {
        binding.start1.setOnClickListener {
            // 播放音乐
            mediaPlayer.start() // 开始播放
        }

        binding.pause1.setOnClickListener {
            mediaPlayer.pause()
        }

        binding.add1.setOnClickListener {
            // 增加音量
            val maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC)
            val currentVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC)
            val newVolume = (currentVolume + 1).coerceAtMost(maxVolume) // 增加音量
            audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, newVolume, 0)
            Toaster.show("当前音量：$currentVolume")

        }

        binding.decrease1.setOnClickListener {
            // 减少音量
            val currentVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC)
            val newVolume = (currentVolume - 1).coerceAtLeast(0) // 减少音量
            audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, newVolume, 0)
            Toaster.show("当前音量：$currentVolume")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.release()
    }
}