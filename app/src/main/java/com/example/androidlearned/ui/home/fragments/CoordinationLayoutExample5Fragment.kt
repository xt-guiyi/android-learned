package com.example.androidlearned.ui.home.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.androidlearned.R
import com.example.androidlearned.databinding.FragmentCoordinationLayoutExample5Binding

class CoordinationLayoutExample5Fragment : Fragment() {
    lateinit var binding:FragmentCoordinationLayoutExample5Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCoordinationLayoutExample5Binding.inflate(inflater,container,false)
        return binding.root
    }

}