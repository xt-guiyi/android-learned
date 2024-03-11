package com.example.androidlearned.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidlearned.R
import com.example.androidlearned.adapter.HomeFragmentAdapter
import com.example.androidlearned.dataSource.HomeDataSource
import com.example.androidlearned.databinding.FragmentHomeBinding
import com.example.androidlearned.domain.HomeLayoutInfo

class HomeFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("test","homeFragment")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentHomeBinding.inflate(inflater,container,false)
        Log.i("test","homeLayoutInfoList[0].title")

//        initRecycleView(binding)
        Log.i("test","homeLayoutInfoList[0].title")

        binding.homeRecycleList.adapter = HomeFragmentAdapter(mutableListOf(HomeLayoutInfo("1","2")))
        return binding.root

    }

    fun initRecycleView(binding: FragmentHomeBinding) {
//        binding.homeRecycleList.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)

        binding.homeRecycleList.adapter = HomeFragmentAdapter(mutableListOf(HomeLayoutInfo("1","2")))
    }

}