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
import com.example.androidlearned.databinding.FragmentAnimationPracticeLottieAnimBinding
import com.example.androidlearned.utils.Display


class AnimationPracticeLottieAnimFragment : Fragment() {
    lateinit var binding: FragmentAnimationPracticeLottieAnimBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAnimationPracticeLottieAnimBinding.inflate(inflater, container, false)
        init()
        return binding.root
    }

    override fun onStart() {
        super.onStart()
    }

    fun init() {
        binding.lottieAnimateAction.setOnClickListener {
            if(binding.lottieAnimateAction.text == "暂停动画") {
                binding.lottieAnimateAction.text = "播放动画"
                binding.lottieAnimate1.pauseAnimation()
                binding.lottieAnimate2.pauseAnimation()
            } else {
                binding.lottieAnimateAction.text = "暂停动画"
                binding.lottieAnimate1.playAnimation()
                binding.lottieAnimate2.playAnimation()
            }
        }
    }

}