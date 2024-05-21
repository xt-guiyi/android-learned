package com.example.androidlearned.ui.home.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidlearned.adapters.RecycleViewExample4Adapter
import com.example.androidlearned.dataSource.HomeDataSource
import com.example.androidlearned.databinding.FragmentRecycleViewExample4Binding
import com.example.androidlearned.domain.People
import com.google.android.material.divider.MaterialDividerItemDecoration
import com.hjq.toast.Toaster

class RecycleViewExample4Fragment : Fragment() {
    lateinit var binding: FragmentRecycleViewExample4Binding
    lateinit var data: MutableList<People>
     var forceUpdate = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRecycleViewExample4Binding.inflate(inflater, container, false)
        init()
        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun init() {
        data = HomeDataSource.loadRecycleViewPeopleList()
        val layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        val adapter = RecycleViewExample4Adapter(AsyncDifferConfig.Builder<People>(object : DiffUtil.ItemCallback<People>() {
            override fun areItemsTheSame(oldItem: People, newItem: People): Boolean {
                Log.i("areItemsTheSame", "$oldItem,$newItem")
                return  oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: People, newItem: People): Boolean {
                Log.i("测试", "$oldItem,$newItem")
                if(forceUpdate) return false
                return oldItem.name == newItem.name && oldItem.isSelected == newItem.isSelected
            }

            override fun getChangePayload(oldItem: People, newItem: People): Any? {
                return 1
            }
        }).build())

        adapter.submitList(data)
        binding.multiplyList.layoutManager = layoutManager
        binding.multiplyList.adapter = adapter
        binding.multiplyList.addItemDecoration(MaterialDividerItemDecoration(requireContext(),LinearLayoutManager.VERTICAL))

        adapter.setClickListener { position, check ->
//            val  newData = adapter.currentList
//            Toaster.show("$check-$newData")
        }

        binding.noAllSelectedButton.setOnClickListener {
            if(binding.noAllSelectedButton.text == "打开多选") {
                binding.noAllSelectedButton.text = "关闭多选"
                binding.allSelectedButton.visibility = View.VISIBLE
                adapter.setMultipleMode(true)
            }else {
                binding.noAllSelectedButton.text = "打开多选"
                binding.allSelectedButton.visibility = View.GONE
                adapter.setMultipleMode(false)
            }
            forceUpdate = true
            adapter.submitList(HomeDataSource.loadRecycleViewPeopleList())
        }

        binding.allSelectedButton.setOnClickListener { _ ->
            // 这里必须获取新的数据，不能使用 data.toMutableList,因为这是浅拷贝，会导致areContentsTheSame的oldItem和 newItem相同
            // adapter.submitList(newData)的坑是新的数据必须和旧的数据必须引用地址不相同，如果相同则不会比较，
            val newData = HomeDataSource.loadRecycleViewPeopleList() // 拿到全新数据
            var isAll = true
            if(binding.allSelectedButton.text == "全选") {
                binding.allSelectedButton.text = "取消全选"
            }else {
                binding.allSelectedButton.text = "全选"
                isAll = false
            }
            if (isAll) {
                  newData.forEach {
                      it.isSelected = true
                  }
            }
            forceUpdate = false
            adapter.submitList(newData)
        }
    }

}