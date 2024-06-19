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
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.BounceInterpolator
import com.example.androidlearned.R
import com.example.androidlearned.databinding.FragmentAnimationPracticeAttrAnimBinding
import com.example.androidlearned.utils.Display
import com.hjq.toast.Toaster


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

    @SuppressLint("Recycle", "ObjectAnimatorBinding")
    fun init() {
        // 1.基本使用
        binding.rect1Start.setOnClickListener {
            ObjectAnimator.ofFloat(binding.rect1, "translationY", 0f, 500f).apply {
                duration = 1000
//                repeatMode = ObjectAnimator.REVERSE // 重复播放-》反转回去
//                repeatCount = 1 // 重复次数
                interpolator = BounceInterpolator() // 动画插值器
                // 动画监听器
                addListener (object : AnimatorListenerAdapter() {
                    override fun onAnimationStart(animation: Animator) {
//                        Toaster.debugShow("开始")
                    }
                })
                start()
            }
        }
        // 2.AnimatorSets使用
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

        // 3.为 ViewGroup 对象的布局更改添加动画效果
        // 安卓自带了默认效果，所以不用设置下面的 LayoutTransition其实也会有动画效果，只需要设置xml的animateLayoutChanges为true
        //  以下是一些常用的 LayoutTransition 常量：
        //  APPEARING：子视图出现时的动画。
        //  DISAPPEARING：子视图消失时的动画。
        //  CHANGE_APPEARING：由于有新的子视图出现而导致其他子视图位置变化时的动画。
        // CHANGE_DISAPPEARING：由于有子视图消失而导致其他子视图位置变化时的动画
        val transition = LayoutTransition()
        // 设置子视图消失动画
        val appearingAnimator = ObjectAnimator.ofFloat(null, "alpha", 0f, 1f).apply {
            duration = 1000
        }
        transition.setAnimator(LayoutTransition.APPEARING, appearingAnimator)

        val disappearingAnimator = ObjectAnimator.ofFloat(null, "alpha", 1f, 0f).apply {
            duration = 1000
        }
        transition.setAnimator(LayoutTransition.DISAPPEARING, disappearingAnimator)
        // 设置布局更改动画
        binding.vgLayout.layoutTransition = transition

        binding.vgStart.setOnClickListener{
            // 隐藏
            binding.vg1React.visibility = View.INVISIBLE
            // 添加子节点
            binding.vg1React.postDelayed({
                val v = View(context)
                val wh = Display.dip2px(requireContext(),100f)
                val margin = Display.dip2px(requireContext(),10f)
                v.layoutParams = ViewGroup.MarginLayoutParams(wh, wh).apply {
                    setMargins(margin, margin, margin, margin)
                }
                v.background = ColorDrawable(Color.BLACK)
                binding.vgLayout.addView(v, 0)
            },1000)
        }

        // 4.关键帧动画
        val kf0 = Keyframe.ofFloat(0f, 0f) // 开始处为0f
        val kf1 = Keyframe.ofFloat(.5f, 360f) // 到中间处为360f
        val kf2 = Keyframe.ofFloat(1f, 0f) // 到最后为0f
        val pvhRotation = PropertyValuesHolder.ofKeyframe("rotation", kf0, kf1, kf2)

        val k0 = Keyframe.ofFloat(0f, 0f)
        val k1 = Keyframe.ofFloat(.25f, 500f)
        val k2 = Keyframe.ofFloat(.5f, 0f)
        val k3 = Keyframe.ofFloat(.75f, -500f)
        val k4 = Keyframe.ofFloat(1f, 0f)

        val pvhTransLate = PropertyValuesHolder.ofKeyframe("translationX", k0, k1, k2,k3, k4)
        val o1 = ObjectAnimator.ofPropertyValuesHolder(binding.keyFrame1, pvhRotation).apply {
            duration = 5000
        }
        val o2 = ObjectAnimator.ofPropertyValuesHolder(binding.keyFrame2, pvhTransLate).apply {
            duration = 3000
        }

        binding.keyFrameStart.setOnClickListener {
            AnimatorSet().apply {
                play(o1).before(o2)
                start()
            }
        }

        // 5.使用 ViewPropertyAnimator 添加动画效果，写法简单
        binding.multipleAnimateStart.setOnClickListener {
//            x： 修改 x 会影响视图在布局中的位置，可能会导致其他视图重新排列。
//            translationY： 修改 translationY 不会影响视图在布局中的位置，其他视图不会受到影响。
            binding.multipleAnimate1.animate().apply {
//                translationX(50f)
                x(50f)
                rotation(360f)
                duration = 2000
            }
        }

        // 6.在 XML 中声明动画，设置动画
        binding.xmlAnimateStart.setOnClickListener {
            AnimatorInflater.loadAnimator(context, R.animator.animate_scale_rotation).apply {
                setTarget(binding.xmlAnimate1)
                start()
            }
        }
    }
}