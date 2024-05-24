package com.example.androidlearned.ui.home.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.core.os.HandlerCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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
    lateinit var layout: LinearLayoutManager
    var currentPage = 1
    var pageSize = 20
    val pageTotals = 200 // 这里我是知道了一共就200条数据，实际情况下应该从接口拿取总共有多少数据
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
        val data = HomeDataSource.loadRecycleViewListByPage(currentPage,pageSize)
        val adapter = RecycleViewExample5Adapter(data)
        // 设置布局
        layout = LinearLayoutManager(requireContext())
        binding.refreshAndLoadMoreList.layoutManager = layout
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
                pageSize = 20
                currentPage = 1
                data.clear()
                data.add("新增项")
                data.addAll(HomeDataSource.loadRecycleViewListByPage(currentPage,pageSize))
                adapter.notifyDataSetChanged()
                binding.swipeRefresh.isRefreshing = false
            },2000)
        }

        // 上拉加载,简单实现，没有任何加载样式
        binding.refreshAndLoadMoreList.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                val lastCompleteViewItemPosition  =  layout.findLastCompletelyVisibleItemPosition()
                val pages = pageTotals / pageSize
                if(lastCompleteViewItemPosition == adapter.itemCount - 1 && pages != currentPage) {
                    currentPage++
                    val newData = HomeDataSource.loadRecycleViewListByPage(currentPage,pageSize)
                    data.addAll(newData)
                    adapter.notifyDataSetChanged()
                    Toaster.show("加载完成")
                }else if(lastCompleteViewItemPosition == adapter.itemCount - 1 && pages == currentPage) {
                    Toaster.show("没有数据辣")
                }
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                Log.i("滚动：",dy.toString())
            }
        })

    }

}