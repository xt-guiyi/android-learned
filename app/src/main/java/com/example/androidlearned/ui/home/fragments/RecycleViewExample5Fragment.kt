package com.example.androidlearned.ui.home.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidlearned.R
import com.example.androidlearned.adapters.RecycleViewExample1Adapter
import com.example.androidlearned.adapters.RecycleViewExample5Adapter
import com.example.androidlearned.dataSource.HomeDataSource
import com.example.androidlearned.databinding.FragmentRecycleViewExample5Binding
import com.google.android.material.divider.MaterialDividerItemDecoration
import com.hjq.toast.Toaster.init

class RecycleViewExample5Fragment : Fragment() {
    lateinit var binding: FragmentRecycleViewExample5Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRecycleViewExample5Binding.inflate(inflater, container, false)
        init()
        return binding.root
    }

    private fun init() {
        val data = HomeDataSource.loadRecycleViewListByPage(1,20)
        val adapter = RecycleViewExample5Adapter(data)
        // 设置布局
        binding.refreshAndLoadMoreList.layoutManager = LinearLayoutManager(requireContext())
        // 设置适配器
        binding.refreshAndLoadMoreList.adapter = adapter
        // 添加分割线
        val divide = MaterialDividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL)
        divide.dividerColor = ResourcesCompat.getColor(resources,R.color.silver_2,null)
        divide.isLastItemDecorated = false
        binding.refreshAndLoadMoreList.addItemDecoration(divide)

    }

}