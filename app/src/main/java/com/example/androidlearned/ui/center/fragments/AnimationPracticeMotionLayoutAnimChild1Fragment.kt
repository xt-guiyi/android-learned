package com.example.androidlearned.ui.center.fragments

import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.transition.Slide
import com.example.androidlearned.databinding.FragmentAnimationPracticeMotionLayoutAnimBinding
import com.example.androidlearned.databinding.FragmentAnimationPracticeMotionLayoutChild1AnimBinding


class AnimationPracticeMotionLayoutAnimChild1Fragment : Fragment() {
    lateinit var binding: FragmentAnimationPracticeMotionLayoutChild1AnimBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enterTransition = Slide(Gravity.END)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAnimationPracticeMotionLayoutChild1AnimBinding.inflate(inflater, container, false)
        init()
        return binding.root
    }

    override fun onStart() {
        super.onStart()
    }

    fun init() {
//        binding.motion1.setOnClickListener {
//
//        }
    }
}