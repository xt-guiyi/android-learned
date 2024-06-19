package com.example.androidlearned.ui.center.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.androidlearned.databinding.FragmentAnimationPracticeMotionLayoutAnimBinding
import com.example.androidlearned.databinding.FragmentAnimationPracticeMotionLayoutChild2AnimBinding


class AnimationPracticeMotionLayoutAnimChild2Fragment : Fragment() {
    lateinit var binding: FragmentAnimationPracticeMotionLayoutChild2AnimBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAnimationPracticeMotionLayoutChild2AnimBinding.inflate(inflater, container, false)
        init()
        return binding.root
    }

    override fun onStart() {
        super.onStart()
    }

    fun init() {
        binding.motion1.setOnClickListener {

        }
    }
}