package com.example.androidlearned.ui.center.activitys

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.os.Bundle
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.androidlearned.R
import com.example.androidlearned.databinding.ActivityTouchPracticeBinding
import com.hjq.toast.Toaster

/**
 * @see <a href="https://blog.csdn.net/carson_ho/article/details/54136311">事件分发详解</a>
 * @see
 *Touch事件可以分为一下几类
 * @see
 * ACTION_DOWN -> 手指按下
 * @see
 * ACTION_UP -> 手指抬起
 * @see
 * ACTION_MOVE -> 手指移动
 * @see
 * ACTION_POINTER_DOWN -> 多个手指按下
 * @see
 * ACTION_POINTER_UP -> 多个手指抬起
 * @see
 * ACTION_CANCEL -> 手指取消
 * @see
 * ACTION_OUTSIDE -> 手指离开屏幕,触碰到了UI边界
 * */
class TouchPracticeActivity : AppCompatActivity() {
    lateinit var binding: ActivityTouchPracticeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTouchPracticeBinding.inflate(layoutInflater)
        watch1() // 触摸区域
        watch2() // 移动小球
        setContentView(binding.root)

    }

    @SuppressLint("ClickableViewAccessibility", "SetTextI18n")
    fun watch1() {
        val textView = binding.touchAreaDetail
        // 当手指从 touchArea 区域内移动到区域外时，setOnTouchListener 仍然会触发，这是因为触摸事件的传递机制。
        //当你按下手指在 touchArea 上时，系统会将后续的触摸事件（如 ACTION_MOVE，ACTION_UP）都发送给 touchArea，
        // 即使你的手指已经移出了 touchArea 的边界。这是为了确保触摸事件的完整性，让 View 能够正确处理手势操作，如滑动。
        //如果你希望在手指移出 touchArea 区域后停止触发事件，你需要在 onTouchListener 中添加边界判断。
        binding.touchArea.setOnTouchListener { v, event ->
            // 相对于自身的坐标
            val prefix = "位置x：${event.x},y：${event.y} 状态："
            // 边界判断。
            if (event.x in 0f..v.width.toFloat() && event.y in 0f..v.height.toFloat()) {
                when (event.action) {
                    MotionEvent.ACTION_DOWN -> textView.text = prefix + "手指按下"
                    MotionEvent.ACTION_UP -> textView.text = prefix + "手指抬起"
                    MotionEvent.ACTION_MOVE -> textView.text = prefix + "手指移动"
                    MotionEvent.ACTION_POINTER_DOWN -> textView.text = prefix + "多指按下"
                    MotionEvent.ACTION_POINTER_UP -> textView.text = prefix + "多指抬起"
                    MotionEvent.ACTION_CANCEL -> textView.text = prefix + "手指取消"
                    MotionEvent.ACTION_OUTSIDE -> textView.text = prefix + "手指离开屏幕"
                }
            }else {
                textView.text = "超出范围"
            }
             true
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    fun watch2() {
        var currentX = 0f
        var currentY = 0f
        // 两种实现方式
        // 1. 监听View的onTouchEvent事件
        // 2.自定义view,实现onTouchEvent
        binding.touchBall.setOnTouchListener { v, event ->
            when(event.action){
                MotionEvent.ACTION_DOWN -> {
                    currentX = event.rawX // 相对于屏幕的坐标
                    currentY = event.rawY
                }
                MotionEvent.ACTION_MOVE -> {
                    // 移动距离
                    val dx = event.rawX -  currentX
                    val dy = event.rawY -  currentY

                    v.x += dx
                    v.y += dy
                    // 边界处理
                    v.x = v.x.coerceIn(0f, ((v.parent as ViewGroup).width - v.width).toFloat())
                    v.y = v.y.coerceIn(0f, ((v.parent as ViewGroup).height - v.height).toFloat())
                    currentX = event.rawX
                    currentY = event.rawY
                    Toaster.show("小球位置: x:${v.x}  y:${v.y}")
                }
            }
            true
        }
    }


    override fun onTouchEvent(event: MotionEvent): Boolean {
        Toaster.show("activity触摸事件触发,x:${event.rawX} y:${event.rawY}")
        return super.onTouchEvent(event)
    }

}