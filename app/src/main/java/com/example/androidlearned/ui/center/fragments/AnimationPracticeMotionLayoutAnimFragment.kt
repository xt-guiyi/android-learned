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
import androidx.fragment.app.commit
import androidx.transition.AutoTransition
import com.example.androidlearned.R
import com.example.androidlearned.databinding.FragmentAnimationPracticeDrawableAnimBinding
import com.example.androidlearned.databinding.FragmentAnimationPracticeMotionLayoutAnimBinding
import com.example.androidlearned.utils.Display


class AnimationPracticeMotionLayoutAnimFragment : Fragment() {
    lateinit var binding: FragmentAnimationPracticeMotionLayoutAnimBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        exitTransition = AutoTransition()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAnimationPracticeMotionLayoutAnimBinding.inflate(inflater, container, false)
        init()
        return binding.root
    }

    override fun onStart() {
        super.onStart()
    }

    fun init() {
        binding.motion1.setOnClickListener {
            parentFragmentManager.commit {
                setReorderingAllowed(true)
                replace(R.id.fragment_container_view_animate, AnimationPracticeMotionLayoutAnimChild1Fragment(),"motionLayoutAnimateChild1")
                addToBackStack("motionLayoutAnimateChild1")
            }
        }
    }
}