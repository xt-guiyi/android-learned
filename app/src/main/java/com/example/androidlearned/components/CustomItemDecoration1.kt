package com.example.androidlearned.components

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.hjq.toast.Toaster

class CustomItemDecoration1(context: Context) : ItemDecoration() {
    private val mPaint = Paint(0)
    private var mDividerLeft: Float? = null
    private var mDividerRight: Float? = null
    init {
        mPaint.color = Color.RED
        mPaint.isAntiAlias = true
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.top = 10
    }
    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(c, parent, state)
        if (mDividerLeft == null)  mDividerLeft = parent.paddingStart.toFloat()
        if (mDividerRight == null) mDividerRight = (parent.width - parent.paddingEnd).toFloat()
        val adapter = parent.adapter
        for (i in 0..< parent.childCount) {
            val childView = parent.getChildAt(i)
            val adapterIndex = parent.getChildAdapterPosition(childView)
            val top = childView.bottom.toFloat()
            val bottom = (childView.bottom + 10).toFloat()
            if(adapter != null && adapterIndex != adapter.itemCount - 1) {
                c.drawRect(mDividerLeft!!,top, mDividerRight!!, bottom, mPaint)
            }

        }
    }
}