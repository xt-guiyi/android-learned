package com.example.androidlearned.customView

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import android.widget.TextView
import com.example.androidlearned.R

/**
 * 将一组 View 组件组合成一个新的组件，也可以制作组合框（由弹出式窗口列表和自由输入文本字段的组合）、
 * 双窗格选择器控件（左右窗格，每个窗格内包含一个列表，您可以在哪个列表中重新分配该项）等。
 * */
class MyCustomViewGroup(context: Context, attrs: AttributeSet?): LinearLayout(context, attrs) {
    private var title: TextView
    private var des: TextView
     init {
         orientation = VERTICAL
         inflate(context, R.layout.acticity_custom_view_child_1, this) // 重点：填充子布局
         title = findViewById<TextView>(R.id.custom_view_1_title)
         des = findViewById<TextView>(R.id.custom_view_1_des)
         // 设置自定义属性
         context.theme.obtainStyledAttributes(attrs, R.styleable.MyCustomViewGroup, 0, 0).also {
             val titleValue = it.getString(R.styleable.MyCustomViewGroup_title)
             val subtitleValue = it.getString(R.styleable.MyCustomViewGroup_des)
             title.text = titleValue
             des.text = subtitleValue
             it.recycle()
         }
     }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }
    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        super.onLayout(changed, l, t, r, b)
    }

    fun setTitle(text: String?) {
        title.text = text
    }

    fun setSubtitle(text: String?) {
        des.text = text
    }
}