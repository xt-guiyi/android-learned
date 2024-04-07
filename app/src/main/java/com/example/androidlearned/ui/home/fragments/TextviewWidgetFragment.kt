package com.example.androidlearned.ui.home.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.androidlearned.R
import com.example.androidlearned.databinding.FragmentTextviewWidgetBinding


class TextviewWidgetFragment : Fragment() {
    lateinit var binding: FragmentTextviewWidgetBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentTextviewWidgetBinding.inflate(inflater,container,false)
        init()
        return binding.root
    }

    private fun init(){
        binding.tvMarquee.isSelected = true // 设置聚焦
    }

}