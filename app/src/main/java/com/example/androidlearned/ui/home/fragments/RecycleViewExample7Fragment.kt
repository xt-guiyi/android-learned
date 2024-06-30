package com.example.androidlearned.ui.home.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.paging.LoadState
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidlearned.R
import com.example.androidlearned.adapters.FooterAdapter
import com.example.androidlearned.adapters.RecycleViewExample7Adapter
import com.example.androidlearned.databinding.FragmentRecycleViewExample7Binding
import com.example.androidlearned.entity.Article
import com.example.androidlearned.viewModal.ExampleViewModel
import com.google.android.material.divider.MaterialDividerItemDecoration
import com.hjq.toast.Toaster
import com.scwang.smart.refresh.header.ClassicsHeader
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class RecycleViewExample7Fragment : Fragment() {
    lateinit var binding: FragmentRecycleViewExample7Binding
    lateinit var layout: LinearLayoutManager
//    private val viewModel by viewModels<ExampleViewModel>() // 默认创建方式
    private val viewModel by viewModels<ExampleViewModel> { ExampleViewModel.Factory } // 工厂函数创建方式

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

    private fun init() {
        val adapter = RecycleViewExample7Adapter(object : DiffUtil.ItemCallback<Article>() {
            override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem == newItem
            }

            override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
                Log.i("toaster", "areItemsTheSame: ${oldItem.id == newItem.id}")
                return oldItem.id == newItem.id
            }

        })

        // 设置布局
        layout = LinearLayoutManager(requireContext())
        binding.rvList.layoutManager = layout
        // 设置适配器
        binding.rvList.adapter = adapter.withLoadStateFooter(FooterAdapter(adapter::retry)) // 添加页脚加载状态
        // 添加分割线
        val divide = MaterialDividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL)
        divide.dividerColor = ResourcesCompat.getColor(resources,R.color.silver_2,null)
        divide.isLastItemDecorated = false
        binding.rvList.addItemDecoration(divide)

        lifecycleScope.launch(Dispatchers.IO) {
           repeatOnLifecycle(Lifecycle.State.STARTED) {
                   delay(500) // 延迟500秒，等待进入动画完成，不然进入动画会卡顿
                   viewModel.examplePagingDataFlow.collect { pagingData ->
                       // 提交数据
                       adapter.submitData(pagingData)
                   }
           }
        }


        // 加载状态监听,推荐写法
        lifecycleScope.launch(Dispatchers.Main) {
            adapter.loadStateFlow.collectLatest {
                val isListEmpty = it.refresh is LoadState.NotLoading && adapter.itemCount == 0 // 判断是否为空，说明一条数据都没有，这时应该显示空页面图片
                binding.progressBar.visibility = if (it.refresh is LoadState.Loading) View.VISIBLE else View.INVISIBLE
                binding.rvList.visibility = if (it.refresh is LoadState.Loading) View.INVISIBLE else View.VISIBLE
                if(it.refresh is LoadState.Error) {
                    val state = it.refresh as LoadState.Error
                    Toaster.show("加载错误: ${state.error.message}")
                }

            }
        }

        // 加载状态监听, 另一种写法
//        adapter.addLoadStateListener {
//            when (it.refresh) {
//                is LoadState.NotLoading -> {
//                    binding.progressBar.visibility = View.INVISIBLE
//                    binding.rvList.visibility = View.VISIBLE
//                }
//                is LoadState.Loading -> {
//                    binding.progressBar.visibility = View.VISIBLE
//                    binding.rvList.visibility = View.INVISIBLE
//                }
//                is LoadState.Error -> {
//                    val state = it.refresh as LoadState.Error
//                    binding.progressBar.visibility = View.INVISIBLE
//                    Toaster.show("加载错误: ${state.error.message}")
//                }
//            }
//        }
        val smRefreshLayout = binding.smRefreshLayout
        smRefreshLayout.setRefreshHeader(ClassicsHeader(requireContext()))
        smRefreshLayout.setOnRefreshListener { refreshLayout ->
            adapter.refresh()
            refreshLayout.finishRefresh()
        }
    }

}