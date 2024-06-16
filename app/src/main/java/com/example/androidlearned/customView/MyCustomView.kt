package com.example.androidlearned.customView

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText
import com.example.androidlearned.R

/**
 * 继承EditText组件，创建了带输入字符的自定义View
 * */
class MyCustomView(context: Context, attrs: AttributeSet): AppCompatEditText(context, attrs) {
    private var maxLength: Int = 500
    private var counterLength = 0
    init {
        // 设置自定义属性
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.MyCustomView,
            0, 0
        ).apply {
            try {
                maxLength = getInt(R.styleable.MyCustomView_maxLength, 100)
            } finally {
                recycle()
            }
        }
        // 限制长度
        filters = arrayOf(InputFilter.LengthFilter(maxLength))
        // 字符变化
        addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s != null) {
                    updateCounter(s.length)
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        })

    }
    @SuppressLint("SetTextI18n")
    private fun updateCounter(length: Int) {
        counterLength = length
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        // 绘制额外文本
        val text = "$counterLength/$maxLength"
        val textWidth = paint.measureText(text)
        val textHeight = paint.fontMetrics.bottom - paint.fontMetrics.top
        val x = width - paddingRight - textWidth
        val y = height / 2 + textHeight / 2 - paint.fontMetrics.bottom
        canvas.drawText(text, x, y, paint)
    }
}