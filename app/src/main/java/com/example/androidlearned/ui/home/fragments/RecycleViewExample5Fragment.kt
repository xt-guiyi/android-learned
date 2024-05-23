package com.example.androidlearned.ui.home.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.core.os.HandlerCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidlearned.R
import com.example.androidlearned.adapters.RecycleViewExample1Adapter
import com.example.androidlearned.adapters.RecycleViewExample5Adapter
import com.example.androidlearned.dataSource.HomeDataSource
import com.example.androidlearned.databinding.FragmentRecycleViewExample5Binding
import com.google.android.material.divider.MaterialDividerItemDecoration
import com.hjq.toast.Toaster
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

    @SuppressLint("NotifyDataSetChanged")
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
        // 下拉刷新
        binding.swipeRefresh.setColorSchemeColors(requireContext().resources.getColor(R.color.bilibili_pink,null))
        binding.swipeRefresh.setOnRefreshListener {
            Handler(requireContext().mainLooper).postDelayed({
                Toaster.show("刷新完成")
                data.add(0,"新增项")
                adapter.notifyDataSetChanged()
                binding.swipeRefresh.isRefreshing = false
            },2000)
        }

    }

}