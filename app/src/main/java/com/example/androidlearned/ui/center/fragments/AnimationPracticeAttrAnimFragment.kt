package com.example.androidlearned.ui.center.fragments

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.commit
import com.example.androidlearned.R
import com.example.androidlearned.databinding.FragmentAnimationPracticeAttrAnimBinding
import com.example.androidlearned.databinding.FragmentAnimationPracticeRootBinding


class AnimationPracticeAttrAnimFragment : Fragment() {
    lateinit var binding: FragmentAnimationPracticeAttrAnimBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAnimationPracticeAttrAnimBinding.inflate(inflater, container, false)
        init()
        return binding.root
    }

    @SuppressLint("Recycle")
    fun init() {
        binding.rect1Start.setOnClickListener {
            ObjectAnimator.ofFloat(binding.rect1, "translationX", 0f, 500f).apply {
                duration = 1000
                repeatMode = ObjectAnimator.REVERSE // 重复播放
                repeatCount = 1 // 重复次数
                start()
            }
        }

        binding.rect2Start.setOnClickListener {
            val a1 = ObjectAnimator.ofFloat(binding.rect21, "translationX", 0f, 500f).apply {
                duration = 1000
                repeatMode = ObjectAnimator.REVERSE // 重复播放
                repeatCount = 1 // 重复次数
            }

            val a2 = ObjectAnimator.ofFloat(binding.rect22, "translationX", 0f, 500f).apply {
                duration = 1000
                repeatMode = ObjectAnimator.REVERSE // 重复播放
                repeatCount = 1 // 重复次数
            }

            val a3 = ObjectAnimator.ofFloat(binding.rect21, "alpha", 1f, 0f).apply {
                duration = 1000
                repeatMode = ObjectAnimator.REVERSE // 重复播放
                repeatCount = 1 // 重复次数
            }
            val a4 = ObjectAnimator.ofFloat(binding.rect22, "alpha", 1f, 0f).apply {
                duration = 1000
                repeatMode = ObjectAnimator.REVERSE // 重复播放
                repeatCount = 1 // 重复次数
            }


            AnimatorSet().apply {
                // with为同时播放， before为，先播放Play的，再播放before里的， after为先播放after里的，在播放Play里的
                play(a1).before(a3) // a1, a3
                play(a3).before(a2) // a3, a2
                play(a2).before(a4) // a2,a4
                // 最后顺序为： a1, a3, a2, a4,
                start()

            }
        }
    }


}