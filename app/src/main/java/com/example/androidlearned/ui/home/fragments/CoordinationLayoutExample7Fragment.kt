package com.example.androidlearned.ui.home.fragments

import android.annotation.SuppressLint
import android.graphics.Canvas
import android.graphics.Rect
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.example.androidlearned.R
import com.example.androidlearned.adapters.CoordinationLayoutExample7FragmentAdapter
import com.example.androidlearned.databinding.FragmentCoordinationLayoutExample7Binding
import com.example.androidlearned.domain.BankMenu
import com.example.androidlearned.utils.Display
import com.google.android.material.tabs.TabLayout

/**
 * 注意点：
 * 1，适配器需要根据不同的类型，绑定不同的viewHolder
 * 2.如果数据不多，一定要在后面加一个footer,用来填充高度，作用主要有两个，一是保证滚动到最底部时，tabLayout能同步更新到最后一个，
 *   二是防止滚动特别快时，tableLayout无法及时同步更新，因此滚动到底部时，最后一个标题要保证能刚刚好能到tabLayout下面。
 * */
class CoordinationLayoutExample7Fragment : Fragment() {
    lateinit var binding: FragmentCoordinationLayoutExample7Binding
    val data: MutableList<BankMenu> = mutableListOf()
    /** key为列表标题项的下标，value为tabLayout标题位置下标 */
    val titlePosition: MutableMap<Int,Int> = mutableMapOf()
    /** key为tabLayout标题位置下标，value为列表标题项的下标 */
    val menuTitlePosition: MutableMap<Int,Int> = mutableMapOf()
//    lateinit var smoothScroller: LinearSmoothScroller 平滑滚动在低段设备性能不高，一般不用
    lateinit var tab_7: TabLayout
    lateinit var rcv_7: RecyclerView
    lateinit var gridLayout: GridLayoutManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCoordinationLayoutExample7Binding.inflate(inflater,container,false)
        tab_7 = binding.coordinationLayoutTab7
        rcv_7 = binding.coordinationLayoutRcv7
        initData()
        initTabLayout()
        initRecycleView()
        return binding.root
    }

    /**
     * 初始化数据
     * */
    private fun initData() {
        for (i in 0..<8) {
            data.add(BankMenu("标题$i",CoordinationLayoutExample7FragmentAdapter.VIEW_TYPE_TITLE))
            menuTitlePosition[i] = i + (i * 10)
            titlePosition[i + (i * 10)]  =i
            for (ci in 0..<10) {
                data.add(BankMenu("内容_$ci",CoordinationLayoutExample7FragmentAdapter.VIEW_TYPE_MENU))
            }
        }
    }

    private fun initTabLayout() {
        // 添加tab
        data.forEach {
            if (it.itemType == CoordinationLayoutExample7FragmentAdapter.VIEW_TYPE_TITLE) {
                val tab = tab_7.newTab()
                tab.text = it.name
                // 取消长按事件
                tab.view.isLongClickable = false
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    tab.view.tooltipText = ""
                }
                tab_7.addTab(tab)
            }
        }

        // 点击tab,跳转到recycleView指定位置
        tab_7.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener {
            override fun onTabSelected(p0: TabLayout.Tab?) {
                if(rcv_7.scrollState == RecyclerView.SCROLL_STATE_IDLE) {
                    if (p0 != null) {
                        // smoothScroller.targetPosition = menuTitlePosition[p0.position]!!
                        // gridLayout.startSmoothScroll(smoothScroller)
                        menuTitlePosition[p0.position]?.let {
                            gridLayout.scrollToPositionWithOffset(it,if(it == 0) 0 else -60)
                        }
                    }

                }
            }
            override fun onTabUnselected(p0: TabLayout.Tab?) {
            }
            override fun onTabReselected(p0: TabLayout.Tab?) {
            }

        })
    }
    @SuppressLint("ResourceType")
    private fun initRecycleView() {
        // 设置网格布局
        gridLayout = GridLayoutManager(requireContext(),4)
        gridLayout.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                if(position == data.size) return 4
                when(data[position].itemType) {
                     CoordinationLayoutExample7FragmentAdapter.VIEW_TYPE_TITLE -> return 4
                    CoordinationLayoutExample7FragmentAdapter.VIEW_TYPE_MENU -> return 1
                }
                return  1
            }

        }

       // 使用平滑滚动方式
      // smoothScroller = object :LinearSmoothScroller(requireContext()) {
      //    override fun getVerticalSnapPreference(): Int {
      //      return SNAP_TO_START
      //   }
      // }

        rcv_7.layoutManager = gridLayout
        // 设置footer高度
        val screenHeight = Display.getScreenHeight(requireContext())
        val statusHeight = Display.getStatusBarHeight(requireContext())
        val tabHeight = Display.dip2px(requireContext(),50f)
        val lastItemHeight = Display.dip2px(requireContext(),278f)
        var lastH =  screenHeight - statusHeight - tabHeight - lastItemHeight
        if(lastH < 0) lastH = 0
        rcv_7.adapter = CoordinationLayoutExample7FragmentAdapter(data,lastH)


        // 设置每项间距
        rcv_7.addItemDecoration(object : RecyclerView.ItemDecoration(){
            override fun getItemOffsets(
                outRect: Rect,
                view: View,
                parent: RecyclerView,
                state: RecyclerView.State
            ) {
                val viewHolder = parent.getChildViewHolder(view)
                val position = parent.getChildLayoutPosition(view)
                if(viewHolder.itemViewType == CoordinationLayoutExample7FragmentAdapter.VIEW_TYPE_MENU) {
                    outRect.top = Display.dip2px(requireContext(),24f);
                }
                if(viewHolder.itemViewType == CoordinationLayoutExample7FragmentAdapter.VIEW_TYPE_TITLE) {
                    if(position!=0) outRect.top = 60;
                }
            }
        })
        // 监听滚动事件
        rcv_7.addOnScrollListener(object: RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                titlePosition[gridLayout.findFirstVisibleItemPosition()]?.let {
                    tab_7.getTabAt(it)?.select()
                }
            }

        })

    }
}