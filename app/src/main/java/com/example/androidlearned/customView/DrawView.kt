package com.example.androidlearned.customView

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

/**
 * 继承View组件，完全自定义一个可拖动的圆形View
 * @see <a href="https://carsonho.blog.csdn.net/article/details/62037696">参考文档1</a>
 * @see <a href="https://www.jianshu.com/p/ca118d704b5e">参考文档2</a>
 * */
class DrawView(context: Context, attrs: AttributeSet? = null) : View(context, attrs, 0) {
    private var currentX = 0f
    private var currentY = 0f
    private val paint: Paint = Paint(1)

    init {
        // 设置自定义属性
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.DrawView,
            0, 0
        ).apply {
            try {
                paint.color = getInt(R.styleable.DrawView_circleColor,  resources.getColor(R.color.bilibili_pink, null))
            } finally {
                recycle()
            }
        }
    }

    // 设置布局参数为WRAP_CONTENT 的情况，解决wrap_content属性与match_parent相同效果
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        // 获取宽-测量规则的模式和大小
        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)
        // 获取高-测量规则的模式和大小
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)
        val heightSize = MeasureSpec.getSize(heightMeasureSpec)

        // 设置wrap_content的默认宽 / 高值
        // 默认宽/高的设定并无固定依据,根据需要灵活设置
        // 类似TextView,ImageView等针对wrap_content均在onMeasure()对设置默认宽 / 高值有特殊处理,具体读者可以自行查看
        val mWidth = 400
        val mHeight = 400


        // 当布局参数设置为wrap_content时，设置默认值
        if (layoutParams.width == ViewGroup.LayoutParams.WRAP_CONTENT && layoutParams.height == ViewGroup.LayoutParams.WRAP_CONTENT) {
            setMeasuredDimension(mWidth, mHeight)
            // 宽 / 高任意一个布局参数为= wrap_content时，都设置默认值
        } else if (layoutParams.width == ViewGroup.LayoutParams.WRAP_CONTENT) {
            setMeasuredDimension(mWidth, heightSize)
        } else if (layoutParams.height == ViewGroup.LayoutParams.WRAP_CONTENT) {
            setMeasuredDimension(widthSize, mHeight)
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        // 绘制圆形
        val circleX = (width / 2).toFloat()
        val circleY = (height / 2).toFloat()
        // 获取绘制内容的高度和宽度（考虑了四个方向的padding值）
        val width = (width - paddingLeft - paddingRight).toFloat()
        val height = (height- paddingTop - paddingBottom).toFloat()
        // 设置圆的半径 = 宽,高最小值的2分之1
        val radius = (width.coerceAtMost(height) /2)
        canvas.drawCircle(circleX, circleY, radius, paint)
    }

    // 拖动功能
    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                currentX = event.rawX // 相对于屏幕的坐标
                currentY = event.rawY
            }

            MotionEvent.ACTION_MOVE -> {
                val dx = event.rawX - currentX
                val dy = event.rawY - currentY
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