package com.example.androidlearned.ui.home.fragments

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.androidlearned.R
import com.example.androidlearned.components.CustomSnackBar
import com.example.androidlearned.databinding.FragmentSnackbarWidgetBinding
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar


class SnackbarWidgetFragment : Fragment() {

    lateinit var binding: FragmentSnackbarWidgetBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSnackbarWidgetBinding.inflate(inflater,container,false)
        init()
        return binding.root
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun init() {
        binding.buttonOpenSnackbar1.setOnClickListener {
            Snackbar.make(binding.snackbarContext,"显示一个提示一！",Snackbar.LENGTH_SHORT)
                .setAnimationMode(BaseTransientBottomBar.ANIMATION_MODE_SLIDE)
                .show()
        }

        binding.buttonOpenSnackbar2.setOnClickListener {
            Snackbar.make(binding.snackbarContext,"显示一个提示二,显示一个提示二,显示一个提示二,显示一个提示二,显示一个提示二,显示一个提示二,显示一个提示二,显示一个提示二,显示一个提示二,显示一个提示二,显示一个提示二,显示一个提示二,显示一个提示二,显示一个提示二,",Snackbar.LENGTH_SHORT)
                .setAnchorView(binding.textCenter)
                .setTextColor(resources.getColor(R.color.bilibili_pink,null))
                .setBackgroundTint(Color.BLUE)
                .setTextMaxLines(1)
                .show()
        }

        binding.buttonOpenSnackbar3.setOnClickListener {
            CustomSnackBar.make(requireContext(),it,CustomSnackBar.LENGTH_INDEFINITE)
                .setImageDrawable(resources.getDrawable(R.mipmap.launcher_round,null))
                .setText("哈哈哈哈哈哈👍")
                .show()
        }
    }

}