package com.example.androidlearned.components

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.androidlearned.databinding.FragmentRecycleViewExample3ItemTitleBinding
import com.example.androidlearned.utils.CommonCode
import com.example.androidlearned.utils.Display
import java.lang.RuntimeException
import kotlin.math.abs

/**
 * 粘性标题实现
 * */
class CustomItemDecoration2(val context: Context, val callback: Callback): ItemDecoration() {
    private val mPaint = Paint(0)
    private lateinit var mTitleViewBinding: FragmentRecycleViewExample3ItemTitleBinding
    private var mTitleHeight: Int = 0
    init {
        mPaint.color = Color.RED
        mPaint.isAntiAlias = true
        mTitleViewBinding = FragmentRecycleViewExample3ItemTitleBinding.inflate(LayoutInflater.from(context),null,false)
    }
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
    }
    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDrawOver(c, parent, state)
        // 获取第一个可见 Item 对应的 Position
        val firstViewIndex = findFirstVisibleItemPosition(parent.layoutManager!!)
        // 安全检测，防止越界
        if (firstViewIndex <= -1 || firstViewIndex >= parent.adapter!!.itemCount - 1) return
        // 获取位置对应的 view
        val firstView = parent.findViewHolderForAdapterPosition(firstViewIndex)?.itemView
        if (firstView != null) {
            var top = 0
            // 是否需要推车粘性标题,只适合标题比内容高度高的情况
            if(firstView.top < 0 && nextLineIsTitle(firstView,firstViewIndex,parent) && mTitleHeight != 0) {
               if(mTitleHeight <= firstView.height) {
                   val d = firstView.height - mTitleHeight
                   top = if (abs(firstView.top) >= d) {
                       firstView.top + d
                   }else {
                       0
                   }
               }
            }
            // 绘制
            drawStickHead(c, firstViewIndex ,parent, top)
        }
    }

    /**
     *      网格布局应该算下一行是否是Title,而不是算下一个Position
     *      @param 当前Item
     *      @param 当前position
     *      @param parent
     * */
    private fun nextLineIsTitle(
        currentView: View,
        currentPosition: Int,
        parent: RecyclerView
    ): Boolean {
        for (nextLinePosition in currentPosition + 1 until parent.adapter!!.itemCount) {
            val nextItemView = parent.findViewHolderForAdapterPosition(nextLinePosition)!!.itemView
            if (nextItemView.bottom > currentView.bottom) {
                // 找到下一行的 Position
                return callback.isHeadItem(nextLinePosition)
            }
        }
        return false
    }

    private fun findFirstVisibleItemPosition(layoutManager: LayoutManager) : Int {
        return when(layoutManager) {
            is GridLayoutManager -> layoutManager.findFirstVisibleItemPosition()
            is LinearLayoutManager -> layoutManager.findFirstVisibleItemPosition()
            is StaggeredGridLayoutManager -> layoutManager.findFirstCompletelyVisibleItemPositions(null)[0]
            else -> throw RuntimeException("no support")
        }
    }
    /**
     * 绘制粘性标题*/
    private fun drawStickHead(c: Canvas,firstViewIndex: Int,parent: RecyclerView, top: Int) {
        c.translate(0f,top.toFloat())
        // 设置标题
        mTitleViewBinding.stickTitle.text = callback.getHeadTitle(firstViewIndex)
        // 测量
        // 宽度固定为 recycleView 的宽度，无论子节点怎么设置宽度
        val widthSpec = View.MeasureSpec.makeMeasureSpec(parent.measuredWidth,View.MeasureSpec.EXACTLY)
        // 高度有子节点控制，容器不做约束
        val heightSpec = View.MeasureSpec.UNSPECIFIED
        mTitleViewBinding.root.measure(widthSpec, heightSpec)
        // 布局， 这里的值可以随意设置，只需要有调用 layout 方法，因为宽高已经在测量阶段决定了
        mTitleViewBinding.root.layout(0, 0, mTitleViewBinding.root.measuredWidth,mTitleViewBinding.root.measuredHeight )
        mTitleHeight = mTitleViewBinding.root.measuredHeight
        mTitleViewBinding.root.draw(c)
    }

    interface Callback {
        /**
         * 当前 position 对应的ViewHolder 是否是标题类型
         * */
        fun isHeadItem(position: Int): Boolean

        /**
         * 当前 position 对应的ViewHolder 是属于哪一种标题类型
         * */
        fun getHeadTitle(position: Int): String
    }
}