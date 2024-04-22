package com.example.androidlearned.ui.home.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.androidlearned.R
import com.example.androidlearned.adapters.ViewPageAdapter
import com.example.androidlearned.databinding.FragmentViewPageBinding
import com.google.android.material.tabs.TabLayoutMediator

class ViewPageFragment : Fragment() {
    lateinit var binding:FragmentViewPageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentViewPageBinding.inflate(inflater, container, false)
        init()
        return binding.root
    }

    private fun init() {
        val tabItems = listOf("tab1","tab2")
        val fgItems = listOf(ViewPageChild_1Fragment(), ViewPageChild_2Fragment())

        binding.viewPageContent.adapter = ViewPageAdapter(this,fgItems)

        TabLayoutMediator(binding.viewPageTabs,binding.viewPageContent) { tab, position ->
            tab.text = tabItems[position]
        }.attach()
    }

}