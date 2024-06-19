package com.example.androidlearned.ui.center.fragments

import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.transition.AutoTransition
import androidx.transition.Explode
import androidx.transition.Fade
import androidx.transition.Scene
import androidx.transition.Slide
import androidx.transition.TransitionManager
import com.example.androidlearned.R
import com.example.androidlearned.databinding.FragmentAnimationPracticeTransitionAnimBinding


class AnimationPracticeTransitionAnimFragment : Fragment() {
    lateinit var binding: FragmentAnimationPracticeTransitionAnimBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAnimationPracticeTransitionAnimBinding.inflate(inflater, container, false)
        init()
        return binding.root
    }

    override fun onStart() {
        super.onStart()
    }

    fun init() {
//        1.为起始布局和结束布局创建 Scene 对象。不过，起始布局的场景通常是根据当前布局自动确定的。
//        2.创建 Transition 对象以定义所需的动画类型。
//        3.调用 TransitionManager.go()，然后系统会运行动画来切换布局。
        val sceneRoot: ViewGroup = binding.transitionManageContainer
        val aScene: Scene = Scene.getSceneForLayout(sceneRoot, R.layout.custom_snackbar_view, requireContext())
        val anotherScene: Scene = Scene.getSceneForLayout(sceneRoot, R.layout.custom_dialog_1, requireContext())

        var status = false
        binding.transitionManageAnimate.setOnClickListener {
            // Explode() Fade() Slide() AutoTransition
            if(status) {
                TransitionManager.go(aScene, Explode())
                status = false
            }else {
                TransitionManager.go(anotherScene, Slide(Gravity.END))
                status = true
            }
        }


        binding.noSceneAnimate.setOnClickListener {
            val rootView: ViewGroup = binding.noSceneContainer
            rootView.removeView(binding.loadingProgressBar)
            TransitionManager.beginDelayedTransition(rootView, Explode())
            binding.contentTextView.visibility = View.VISIBLE
        }
    }
}