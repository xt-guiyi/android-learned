package com.example.androidlearned.ui.home.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.paging.LoadState
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidlearned.R
import com.example.androidlearned.adapters.RecycleViewExample7Adapter
import com.example.androidlearned.databinding.FragmentRecycleViewExample7Binding
import com.example.androidlearned.entity.Article
import com.example.androidlearned.viewModal.ExampleViewModel
import com.google.android.material.divider.MaterialDividerItemDecoration
import com.hjq.toast.Toaster
import kotlinx.coroutines.launch

class RecycleViewExample7Fragment : Fragment() {
    lateinit var binding: FragmentRecycleViewExample7Binding
    lateinit var layout: LinearLayoutManager
    private val viewModel by viewModels<ExampleViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRecycleViewExample7Binding.inflate(inflater, container, false)
        init()
        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun init() {
        val adapter = RecycleViewExample7Adapter(object : DiffUtil.ItemCallback<Article>() {
            override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem == newItem
            }

            override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem.id == newItem.id
            }

        })
        // 设置布局
        layout = LinearLayoutManager(requireContext())
        binding.rvList.layoutManager = layout
        // 设置适配器
        binding.rvList.adapter = adapter
        // 添加分割线
        val divide = MaterialDividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL)
        divide.dividerColor = ResourcesCompat.getColor(resources,R.color.silver_2,null)
        divide.isLastItemDecorated = false
        binding.rvList.addItemDecoration(divide)

        lifecycleScope.launch {
           repeatOnLifecycle(Lifecycle.State.STARTED) {
               viewModel.ExamplePagingDataFlow.collect { pagingData ->
                   adapter.submitData(pagingData)
               }
           }
        }

        adapter.addLoadStateListener {
            when (it.refresh) {
                is LoadState.NotLoading -> {
                    binding.progressBar.visibility = View.INVISIBLE
                    binding.rvList.visibility = View.VISIBLE
                }
                is LoadState.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.rvList.visibility = View.INVISIBLE
                }
                is LoadState.Error -> {
                    val state = it.refresh as LoadState.Error
                    binding.progressBar.visibility = View.INVISIBLE
                    Toaster.show("加载错误: ${state.error.message}")
                }
            }
        }
//        // 下拉刷新
//        binding.swipeRefresh.setColorSchemeColors(requireContext().resources.getColor(R.color.bilibili_pink,null))
//        binding.swipeRefresh.setOnRefreshListener {
//            Handler(requireContext().mainLooper).postDelayed({
//                Toaster.show("刷新完成")
//                pageSize = 20
//                currentPage = 1
//                data.clear()
//                data.add("新增项")
//                data.addAll(HomeDataSource.loadRecycleViewListByPage(currentPage,pageSize))
//                adapter.notifyDataSetChanged()
//                binding.swipeRefresh.isRefreshing = false
//            },2000)
//        }
//
//        // 上拉加载,简单实现，没有任何加载样式
//        binding.refreshAndLoadMoreList.addOnScrollListener(object : RecyclerView.OnScrollListener(){
//            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
//                super.onScrollStateChanged(recyclerView, newState)
//                val lastCompleteViewItemPosition  =  layout.findLastCompletelyVisibleItemPosition()
//                val pages = pageTotals / pageSize
//                if(lastCompleteViewItemPosition == adapter.itemCount - 1 && pages != currentPage) {
//                    currentPage++
//                    val newData = HomeDataSource.loadRecycleViewListByPage(currentPage,pageSize)
//                    data.addAll(newData)
//                    adapter.notifyDataSetChanged()
//                    Toaster.show("加载完成")
//                }else if(lastCompleteViewItemPosition == adapter.itemCount - 1 && pages == currentPage) {
//                    Toaster.show("没有数据辣")
//                }
//            }
//
//            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//                super.onScrolled(recyclerView, dx, dy)
//                Log.i("滚动：",dy.toString())
//            }
//        })

    }

}