package com.example.androidlearned.ui.home.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ItemTouchHelper.ACTION_STATE_DRAG
import androidx.recyclerview.widget.ItemTouchHelper.ACTION_STATE_IDLE
import androidx.recyclerview.widget.ItemTouchHelper.ACTION_STATE_SWIPE
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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
        val data = HomeDataSource.loadRecycleViewList()
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
        // 点击删除项
        adapter.setOnClickListener { position, text, _ ->
            Toaster.show("删除了${text}，位置在${position}")
            if (data.size <=  4) return@setOnClickListener
            data.removeAt(position)
            adapter.notifyItemRemoved(position)
            adapter.notifyItemRangeChanged(position,data.size - position)
        }

        // 触摸帮助类
        ItemTouchHelper(object :ItemTouchHelper.Callback(){
            override fun getMovementFlags(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder
            ): Int {
                val idleStatus = makeFlag(ACTION_STATE_IDLE, ItemTouchHelper.RIGHT) // 空闲时允许向右移动
                val swipeStatus = makeFlag(ACTION_STATE_SWIPE, ItemTouchHelper.LEFT) // 只能向左滑
                val dragStatus = makeFlag(ACTION_STATE_DRAG, ItemTouchHelper.UP or ItemTouchHelper.DOWN) // 上下移动
                return idleStatus or swipeStatus or dragStatus
            }


            // 拖拽移动位置
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                val formPosition = viewHolder.layoutPosition
                val targetPosition = target.layoutPosition
                // 移动数据
                val oldVal = data.removeAt(formPosition)
                data.add(targetPosition, oldVal)
                // 通知适配器更新，触发视觉(ui)上的移动效果
                adapter.notifyItemMoved(formPosition, targetPosition)
                // 更新影响范围内的ViewHolder,从而触发onBindViewHolder
                if(formPosition > targetPosition) {
                    adapter.notifyItemRangeChanged(targetPosition,formPosition + 1,1)
                }else {
                    adapter.notifyItemRangeChanged(formPosition,targetPosition + 1,1)
                }
                return true

            }

            // 滑动删除
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.layoutPosition
                data.removeAt(position)
                adapter.notifyItemRemoved(position)
                adapter.notifyItemRangeChanged(position, data.size - position)
            }

        }).attachToRecyclerView(binding.list)

        // 动画
    }

}