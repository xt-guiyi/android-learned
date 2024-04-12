package com.example.androidlearned.ui.home.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.androidlearned.R
import com.example.androidlearned.databinding.FragmentNavigationDrawBinding


class NavigationDrawFragment : Fragment() {
    lateinit var binding: FragmentNavigationDrawBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNavigationDrawBinding.inflate(inflater,container,false)
        init()
        return binding.root
    }

    private fun init() {
        binding.openMenu.setOnClickListener {
            binding.navDrawLayout.open()
        }
    }

}