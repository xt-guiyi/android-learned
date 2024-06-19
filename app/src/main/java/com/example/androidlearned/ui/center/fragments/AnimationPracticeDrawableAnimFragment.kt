package com.example.androidlearned.ui.center.fragments

import android.animation.Animator
import android.animation.AnimatorInflater
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.Keyframe
import android.animation.LayoutTransition
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.AnimatedVectorDrawable
import android.graphics.drawable.AnimationDrawable
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.BounceInterpolator
import androidx.core.content.ContextCompat
import com.example.androidlearned.R
import com.example.androidlearned.databinding.FragmentAnimationPracticeDrawableAnimBinding
import com.example.androidlearned.utils.Display


class AnimationPracticeDrawableAnimFragment : Fragment() {
    lateinit var binding: FragmentAnimationPracticeDrawableAnimBinding
    private lateinit var rocketAnimation:AnimationDrawable
    private lateinit var rocketAnimation2:AnimationDrawable
    private lateinit var vectorAnimation: AnimatedVectorDrawable
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAnimationPracticeDrawableAnimBinding.inflate(inflater, container, false)
        init()
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        rocketAnimation2.isOneShot = false // 一直播放
        rocketAnimation2.start()

        vectorAnimation.start()
    }

    @SuppressLint("Recycle", "ObjectAnimatorBinding")
    fun init() {
        // 1.基本使用
        val rocketImage = binding.drawableAnimate1.apply {
            setBackgroundResource(R.drawable.da_root)
            rocketAnimation = background as AnimationDrawable
        }
        rocketAnimation?.let { animationDrawable ->
            rocketImage.setOnClickListener {
                animationDrawable.stop() // 停止当前动画
                animationDrawable.selectDrawable(0) // 选择第一帧
                animationDrawable.start() // 开始播放动画
            }
        }


        // 另一种设置背景图片方式
        rocketAnimation2 = ContextCompat.getDrawable(requireContext(), R.drawable.da_root) as AnimationDrawable
        binding.drawableAnimate2.setImageDrawable(rocketAnimation2)


        vectorAnimation = ContextCompat.getDrawable(requireContext(), R.drawable.vectordrawable_animator) as AnimatedVectorDrawable
        binding.vectorAnimate1.setImageDrawable(vectorAnimation)

    }
}