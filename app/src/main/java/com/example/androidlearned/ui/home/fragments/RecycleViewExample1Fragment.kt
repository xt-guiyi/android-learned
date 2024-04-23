package com.example.androidlearned.ui.home.fragments

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.example.androidlearned.R
import com.example.androidlearned.adapters.RecycleViewExample1Adapter
import com.example.androidlearned.dataSource.HomeDataSource
import com.example.androidlearned.databinding.FragmentRecycleViewExample1Binding
import com.google.android.material.divider.MaterialDividerItemDecoration
import com.hjq.toast.Toaster

class RecycleViewExample1Fragment : Fragment() {
   lateinit var binding: FragmentRecycleViewExample1Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRecycleViewExample1Binding.inflate(inflater, container, false)
        init()
        return binding.root
    }

    private fun init() {
        val data = HomeDataSource.loadRecycleView1List()
        val adapter = RecycleViewExample1Adapter(data)
        // 设置布局
        binding.list.layoutManager = LinearLayoutManager(requireContext())
        // 设置适配器
        binding.list.adapter = adapter
        // 添加分割线
        val divide = MaterialDividerItemDecoration(requireContext(),LinearLayoutManager.VERTICAL)
        divide.dividerColor = ResourcesCompat.getColor(resources,R.color.silver_2,null)
        divide.isLastItemDecorated = false
        binding.list.addItemDecoration(divide)

        // 新增项
        var newIndex = 1001 // 插入项
        binding.add.setOnClickListener {
            data.add(4,"新增项-${newIndex++}")
            adapter.notifyItemInserted(4)
        }
        // 删除项
        adapter.setOnClickListener { position, text, view ->
            Toaster.show("删除了${text}，位置在${position}")
            data.removeAt(position)
            adapter.notifyItemRemoved(position)
//            adapter.notifyDataSetChanged()
//            adapter.notifyItemRangeChanged(position,2)
        }
        // 动画
    }

}