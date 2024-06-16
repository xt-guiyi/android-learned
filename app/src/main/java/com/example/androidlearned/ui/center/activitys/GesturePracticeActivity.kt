package com.example.androidlearned.ui.center.activitys

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GestureDetectorCompat
import com.example.androidlearned.databinding.ActivityGesturePracticeBinding
import com.hjq.toast.Toaster

class GesturePracticeActivity : AppCompatActivity() {
    lateinit var binding: ActivityGesturePracticeBinding
    private lateinit var gestureDetector: GestureDetectorCompat
    private lateinit var scaleGestureDetector: ScaleGestureDetector
    private var mScaleFactor = 1f
    private var scaleRate = 1f
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGesturePracticeBinding.inflate(layoutInflater)
        gestureDetector = GestureDetectorCompat(this, GestureListener())
        scaleGestureDetector = ScaleGestureDetector(this, scaleListener)
        init()
        setContentView(binding.root)

    }

    @SuppressLint("ClickableViewAccessibility")
    fun init() {
        binding.gestureArea.setOnTouchListener { v, event ->
            gestureDetector.onTouchEvent(event)
            scaleGestureDetector.onTouchEvent(event)
            true

        }
    }

    private class GestureListener():GestureDetector.SimpleOnGestureListener() {
        // 当第一次发生触摸事件时调用（手指触摸屏幕） 。 在这里返回 true 很重要，表示你想要处理与此触摸相关的后续手势事件。
        override fun onDown(e: MotionEvent): Boolean {
            return true
        }
        // 在检测到触摸并且在触发 onSingleTapUp 或 onLongPress 之前调用。它用于在用户意图明确之前提供视觉反馈（ 如突出显示按钮）
        override fun onShowPress(e: MotionEvent) {
            super.onShowPress(e)
        }

        // 当检测到单击时调用（短暂触摸后手指抬起） 。
        override fun onSingleTapUp(e: MotionEvent): Boolean {
            Toaster.show("单击,短暂触摸后手指抬起")
            return true
        }

        // 当检测到滚动动作时调用（手指在屏幕上拖动） 。
        override fun onScroll(
            e1: MotionEvent?,
            e2: MotionEvent,
            distanceX: Float,
            distanceY: Float
        ): Boolean {
            Toaster.show("滚动,手指在屏幕上拖动")
            return true
        }
        // 当检测到长按时调用（手指按住一段时间）。
        override fun onLongPress(e: MotionEvent) {
            Toaster.show("长按,手指按住一段时间")
        }

        // 当检测到快速滑动时调用（手指在屏幕上快速滑动） 。
        override fun onFling(
            e1: MotionEvent?,
            e2: MotionEvent,
            velocityX: Float,
            velocityY: Float
        ): Boolean {
            Toaster.show("快速滑动,手指在屏幕上快速滑动\n速度：x=$velocityX,y=$velocityY")
            return  true
        }
        // 当确认单击时调用（不是双击的一部分） 。
        override fun onSingleTapConfirmed(e: MotionEvent): Boolean {
            Toaster.show("确认单击,不是双击的一部分")
            return  true
        }
        // 双击时收到通知
        override fun onDoubleTap(e: MotionEvent): Boolean {
            Toaster.show("双击")
            return true
        }
        // 在双击序列中提供更精细的事件。
        override fun onDoubleTapEvent(e: MotionEvent): Boolean {
            return super.onDoubleTapEvent(e)
        }
        // 当发生上下文点击时收到通知。
        override fun onContextClick(e: MotionEvent): Boolean {
            Toaster.show("上下文点击")
            return true
        }
    }
    private val scaleListener = object : ScaleGestureDetector.SimpleOnScaleGestureListener() {
        override fun onScale(detector: ScaleGestureDetector): Boolean {
             scaleRate =  detector.scaleFactor * mScaleFactor
            // Don't let the object get too small or too large.
            scaleRate = 0.1f.coerceAtLeast(scaleRate.coerceAtMost(5.0f))
            Toaster.show("缩放:\n当前缩放因子：${detector.scaleFactor}\n当前缩放比：$scaleRate")
            return false
        }

        override fun onScaleBegin(detector: ScaleGestureDetector): Boolean {
            Toaster.show("缩放开始")
            return true

        }

        override fun onScaleEnd(detector: ScaleGestureDetector) {
            mScaleFactor = scaleRate
//            Toaster.show("缩放结束")
        }
    }
}