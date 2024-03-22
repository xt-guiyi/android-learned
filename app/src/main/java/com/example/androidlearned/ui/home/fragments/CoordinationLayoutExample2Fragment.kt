package com.example.androidlearned.ui.home.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.androidlearned.R
import com.example.androidlearned.databinding.FragmentCoordinationLayoutExample2Binding


class CoordinationLayoutExample2Fragment : Fragment() {
    lateinit var binding: FragmentCoordinationLayoutExample2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCoordinationLayoutExample2Binding.inflate(inflater,container,false)
        return binding.root
    }

}