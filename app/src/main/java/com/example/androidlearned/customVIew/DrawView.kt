package com.example.androidlearned.customVIew

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import com.example.androidlearned.R
import com.hjq.toast.Toaster

class DrawView(context: Context, attrs: AttributeSet? = null): View(context,attrs,0) {
    private var currentX = 0f
    private var currentY = 0f
    private val paint: Paint = Paint(1)

    init {
        paint.color = resources.getColor(R.color.bilibili_pink,null)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        when(event.action){
            MotionEvent.ACTION_DOWN -> {
                currentX = event.rawX // 相对于屏幕的坐标
                currentY = event.rawY
            }
            MotionEvent.ACTION_MOVE -> {
                val dx = event.rawX -  currentX
                val dy = event.rawY -  currentY
                x += dx
                y += dy
                x = x.coerceIn(0f, ((parent as ViewGroup).width - width).toFloat())
                y = y.coerceIn(0f, ((parent as ViewGroup).height - height).toFloat())
                currentX = event.rawX
                currentY = event.rawY
                Toaster.show("小球位置: x:${x}  y:${y}")
            }
        }
        return true
    }



}