package com.example.androidlearned.ui.home.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSmoothScroller
import com.example.androidlearned.R
import com.example.androidlearned.adapters.RecycleViewExample2Adapter
import com.example.androidlearned.adapters.RecycleViewExample3Adapter
import com.example.androidlearned.components.CustomItemDecoration2
import com.example.androidlearned.dataSource.HomeDataSource
import com.example.androidlearned.databinding.FragmentRecycleViewExample3Binding
import com.example.androidlearned.domain.People
import com.example.androidlearned.domain.StickHeadInfo
import com.google.android.material.divider.MaterialDividerItemDecoration
import com.hjq.toast.Toaster
import com.hjq.toast.Toaster.init

class RecycleViewExample3Fragment : Fragment() {
    lateinit var binding: FragmentRecycleViewExample3Binding
    lateinit var adapter: RecycleViewExample3Adapter
    private lateinit var layoutManager: LinearLayoutManager
    lateinit var data: MutableList<StickHeadInfo>
    private lateinit var dividerDecoration: MaterialDividerItemDecoration
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRecycleViewExample3Binding.inflate(inflater,container,false)
        init()
        return binding.root
    }

    private fun init() {
         data = HomeDataSource.loadRecycleViewStickHeadList()
         layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL, false)
         adapter = RecycleViewExample3Adapter(data)
        // 下划线
        dividerDecoration = MaterialDividerItemDecoration(requireContext(),LinearLayoutManager.VERTICAL)
        dividerDecoration.dividerColor = requireContext().resources.getColor(R.color.bilibili_pink, null)
        dividerDecoration.isLastItemDecorated = false
        binding.stickList.adapter = adapter
        binding.stickList.layoutManager = layoutManager
        binding.stickList.addItemDecoration(dividerDecoration)
        binding.stickList.addItemDecoration(CustomItemDecoration2(requireContext(), object : CustomItemDecoration2.Callback{
            override fun isHeadItem(position: Int): Boolean {
                return data[position].type == RecycleViewExample3Adapter.VIEW_TYPE_TITLE
            }

            override fun getHeadTitle(position: Int): String {
               return data[position].parentTitle
            }

        }))

       // 滚动到指定位置
        binding.movePositionButton1.setOnClickListener {
            Toaster.show("无动画")
            layoutManager.scrollToPositionWithOffset(16, 0)
        }
        binding.movePositionButton2.setOnClickListener {
            Toaster.show("有动画")
            layoutManager.startSmoothScroll(object : LinearSmoothScroller(requireContext()){
                override fun getVerticalSnapPreference(): Int {
                    return SNAP_TO_START
                }
            }.apply { targetPosition = 49})
        }
    }


}