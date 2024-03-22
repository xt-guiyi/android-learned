package com.example.androidlearned.ui.home.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.androidlearned.R
import com.example.androidlearned.databinding.FragmentCoordinationLayoutExample4Binding

class CoordinationLayoutExample4Fragment : Fragment() {
    lateinit var binding: FragmentCoordinationLayoutExample4Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCoordinationLayoutExample4Binding.inflate(inflater,container,false)
        return binding.root
    }


}