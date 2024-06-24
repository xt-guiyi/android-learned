package com.example.androidlearned.ui.center.activitys

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.example.androidlearned.R
import com.example.androidlearned.databinding.ActivityGlidePracticeBinding

class GlidePracticeActivity : AppCompatActivity() {
    lateinit var binding: ActivityGlidePracticeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGlidePracticeBinding.inflate(layoutInflater)
        init()
        setContentView(binding.root)
    }

    fun init () {
        Glide.with(this)
            .load("https://privateimage-1306081565.cos.ap-shanghai.myqcloud.com/%E9%BE%99%E4%B8%8E%E8%99%8E%20%28115%29.jpg")
            .transition(withCrossFade()) // 淡入淡出过渡动画
            .into(binding.imageView1)

        Glide.with(this)
            .load("https://privateimage-1306081565.cos.ap-shanghai.myqcloud.com/%E9%BE%99%E4%B8%8E%E8%99%8E%20%28116%29.jpg")
//            .placeholder(R.drawable.ic_launcher_foreground) // 占位图
//            .centerCrop() // 裁剪，确保图片填满整个 ImageView，同时保持图片的纵横比。
            .into(binding.imageView2)

        Glide.with(this)
            .load("https://privateimage-1306081565.cos.ap-shanghai.myqcloud.com/%E9%BE%99%E4%B8%8E%E8%99%8E%20%28104%29.jpg")
            .circleCrop() // 圆形裁剪
            .into(binding.imageView3)

        Glide.with(this)
            .load("https://privateimage-1306081565.cos.ap-shanghai.myqcloud.com/%E9%BE%99%E4%B8%8E%E8%99%8E%20%28121%29.jpg")
            .fitCenter() // 计算缩放比例： 根据 ImageView 的大小和图片的原始大小，计算出合适的缩放比例， 以确保图片能够完整地显示在 ImageView 中。
            .into(binding.imageView4)

        Glide.with(this)
            .load("https://privateimage-1306081565.cos.ap-shanghai.myqcloud.com/%E9%BE%99%E4%B8%8E%E8%99%8E%20%28188%29.jpg")
            .transition(withCrossFade()) // 淡入淡出过渡动画
            .into(binding.imageView5)

        Glide.with(this)
            .load(R.drawable.img_5)
            .circleCrop()
            .into(binding.imageView6)
    }
}