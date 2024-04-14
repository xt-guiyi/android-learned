package com.example.androidlearned.ui.home.fragments

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.widget.Toast
import com.example.androidlearned.R
import com.example.androidlearned.databinding.FragmentBadgeDrawableBinding
import com.google.android.material.badge.BadgeDrawable
import com.google.android.material.badge.BadgeUtils


class BadgeDrawableFragment : Fragment() {

    lateinit var binding: FragmentBadgeDrawableBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBadgeDrawableBinding.inflate(inflater,container,false)
        init()
        return binding.root
    }

    private fun init() {
        binding.textviewBadge.viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                BadgeDrawable.create(requireContext()).apply {
                    maxNumber = 100
                    number = 24
                    badgeGravity = BadgeDrawable.TOP_END
                    BadgeUtils.attachBadgeDrawable(this,binding.textviewBadge)
                }
            }

        })

        binding.imageViewBadge.viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                BadgeDrawable.create(requireContext()).apply {
                    maxNumber = 99
                    number = 100
                    verticalOffset = 30
                    horizontalOffset = 30
                    badgeGravity = BadgeDrawable.TOP_END
                    BadgeUtils.attachBadgeDrawable(this,binding.imageViewBadge)
                }
            }

        })

        binding.iconButtonBadge.viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                BadgeDrawable.create(requireContext()).apply {
                    maxNumber = 99
                    number = 100
                    verticalOffset = 30
                    horizontalOffset = 30
                    badgeGravity = BadgeDrawable.TOP_END
                    backgroundColor = Color.BLACK
                    badgeTextColor = resources.getColor(R.color.bilibili_pink,null)
                    BadgeUtils.attachBadgeDrawable(this,binding.iconButtonBadge













                    )
                }
            }

        })
    }


}