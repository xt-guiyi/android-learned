package com.example.androidlearned.ui.home.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidlearned.R
import com.example.androidlearned.adapters.RecycleViewExample5Adapter
import com.example.androidlearned.dataSource.HomeDataSource
import com.example.androidlearned.databinding.FragmentRecycleViewExample6Binding
import com.google.android.material.divider.MaterialDividerItemDecoration
import com.scwang.smart.refresh.footer.ClassicsFooter
import com.scwang.smart.refresh.header.ClassicsHeader
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class RecycleViewExample6Fragment : Fragment() {
    lateinit var binding: FragmentRecycleViewExample6Binding
    lateinit var layout: LinearLayoutManager
    private var currentPage = 1
    private var pageSize = 20
    private val pageTotals = 200 // 这里我是知道了一共就200条数据，实际情况下应该从接口拿取总共有多少数据
    private val pages = pageTotals / pageSize

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRecycleViewExample6Binding.inflate(inflater, container, false)
        init()
        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun init() {
        val data = HomeDataSource.loadRecycleViewListByPage(currentPage,pageSize)
        val adapter = RecycleViewExample5Adapter(data)
        // 设置布局
        layout = LinearLayoutManager(requireContext())
        binding.recyclerViewMain.layoutManager = layout
        // 设置适配器
        binding.recyclerViewMain.adapter = adapter
        // 添加分割线
        val divide = MaterialDividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL)
        divide.dividerColor = ResourcesCompat.getColor(resources, R.color.silver_2,null)
        divide.isLastItemDecorated = false
        binding.recyclerViewMain.addItemDecoration(divide)


        val smRefreshLayout = binding.smRefreshLayout
        smRefreshLayout.setRefreshHeader(ClassicsHeader(requireContext()))
        smRefreshLayout.setRefreshFooter(ClassicsFooter(requireContext()))
        smRefreshLayout.setOnRefreshListener { refreshLayout ->
            // 实际应该要在io线程进行网络请求
            lifecycleScope.launch(Dispatchers.Main) {
                delay(500)
                pageSize = 20
                currentPage = 1
                data.clear()
                data.add("新增项")
                data.addAll(HomeDataSource.loadRecycleViewListByPage(currentPage,pageSize))
                adapter.notifyDataSetChanged()
                refreshLayout.finishRefresh() //传入false表示刷新失败
            }
        }
        smRefreshLayout.setOnLoadMoreListener { refreshlayout ->
            lifecycleScope.launch(Dispatchers.Main) {
                delay(500)
                if(pages == currentPage) {
                    refreshlayout.finishLoadMoreWithNoMoreData() // 完成加载并标记没有更多数据
                }else {
                    currentPage++
                    val newData = HomeDataSource.loadRecycleViewListByPage(currentPage,pageSize)
                    data.addAll(newData)
                    adapter.notifyDataSetChanged()
                    refreshlayout.finishLoadMore() //传入false表示加载失败

                }
            }
        }
    }

}