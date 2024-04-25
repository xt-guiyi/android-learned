package com.example.androidlearned.ui.home.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidlearned.R
import com.example.androidlearned.adapters.RecycleViewExample1Adapter
import com.example.androidlearned.adapters.RecycleViewExample2Adapter
import com.example.androidlearned.dataSource.HomeDataSource
import com.example.androidlearned.databinding.FragmentRecycleViewExample2Binding
import com.example.androidlearned.domain.People
import java.util.Collections

class RecycleViewExample2Fragment : Fragment() {
    lateinit var binding: FragmentRecycleViewExample2Binding
    lateinit var adapter:  RecycleViewExample2Adapter
    lateinit var data: MutableList<People>
    var index: Int = 10001
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRecycleViewExample2Binding.inflate(inflater, container, false)
        init()
        return binding.root
    }

    private fun init() {
        data = HomeDataSource.loadRecycleViewPeopleList()
        adapter = RecycleViewExample2Adapter(object : DiffUtil.ItemCallback<People>(){
            // 比较item是否相同
            override fun areItemsTheSame(oldItem: People, newItem: People): Boolean {
                return oldItem.id == newItem.id
            }
            // 比较内容是否相同
            override fun areContentsTheSame(oldItem: People, newItem: People): Boolean {
                return oldItem.name == newItem.name
            }

        })
        adapter.submitList(data)
        binding.list.adapter = adapter
        binding.list.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)


        // 新增
        binding.add.setOnClickListener {
            add()
        }

        ItemTouchHelper(object: ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP or ItemTouchHelper.DOWN,ItemTouchHelper.LEFT){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                move(viewHolder.layoutPosition,target.layoutPosition)
                return true
            }

            // 删除
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                remove(viewHolder.layoutPosition)
            }

        }).attachToRecyclerView(binding.list)
    }

    private fun add() {
        val item = People(index,"李$index")
        // 获取副本
        data = data.toMutableList()
        data.add(4,item)
        adapter.submitList(data)
        index++
    }

    fun remove(position: Int) {
        // 获取副本
        data = data.toMutableList()
        data.removeAt(position)
        adapter.submitList(data)
    }

    fun move(formPosition:Int, targetPosition: Int) {
        // 获取副本
        data = data.toMutableList()
        Collections.swap(data, formPosition, targetPosition)
        adapter.submitList(data)
    }
}