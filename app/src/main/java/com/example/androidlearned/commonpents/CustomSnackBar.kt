package com.example.androidlearned.commonpents

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.example.androidlearned.R
import com.google.android.material.snackbar.BaseTransientBottomBar
/**
 * 自定义snackBar组件
 * */
class CustomSnackBar(
    private val mContext: Context, val parent: ViewGroup,
    private val content: View, contentViewCallback: com.google.android.material.snackbar.ContentViewCallback):
    BaseTransientBottomBar<CustomSnackBar>(mContext,parent,content,contentViewCallback) {

    private  var  imageView: ImageView? = null
    private var  textView: TextView? = null

    init {
        // 这里设置包裹customSnackBar视图的容器样式
        getView().setBackgroundColor(context.getColor(R.color.transparent))
        getView().setPadding(0, 0, 0, 0)
        getView().top = 0
    }


    fun setImageDrawable(id: Drawable):CustomSnackBar  {
        if(imageView == null) {
            imageView = content.findViewById(R.id.custom_snackbar_img)
        }
        imageView?.setImageDrawable(id)
        return this
    }

    fun setText(text: CharSequence):CustomSnackBar {
        if(textView == null) {
            textView = content.findViewById(R.id.custom_snackbar_textview)
        }
        textView?.text = text
        return this
    }

    companion object {
        const val LENGTH_INDEFINITE = -2
        const val LENGTH_SHORT = -1
        const val LENGTH_LONG = 0

        private fun findSuitableParent(view:View): ViewGroup? {
            var mView:View? = view
            var fallback: ViewGroup? = null

            do {
                if (mView is CoordinatorLayout) {
                    return mView
                }
                if (mView is FrameLayout) {
                    if (mView.id == android.R.id.content) {
                        return mView
                    }
                    fallback = mView
                }
                if (mView != null) {
                    val parent: ViewParent = mView.parent
                    mView = if (parent is View) parent else null
                }
            } while (mView != null)

            return fallback
        }
        fun make(context: Context, view: View,duration: Int):CustomSnackBar {
            // 找到当前view到父视图
            val parent = findSuitableParent(view) ?: throw IllegalArgumentException(
                "No suitable parent found from the given view. Please provide a valid view."
            )

            val customSnackBar = LayoutInflater.from(context).inflate(R.layout.custom_snackbar_view,parent,false)
            val contentViewCallback = object : com.google.android.material.snackbar.ContentViewCallback
                    {
                override fun animateContentIn(p0: Int, p1: Int) {
                    // 动画开始
                }

                override fun animateContentOut(p0: Int, p1: Int) {
                    // 动画结束
                }

            }
            Log.i("custom",customSnackBar.toString())
            return CustomSnackBar(context,parent,customSnackBar,contentViewCallback).setDuration(duration)
        }
    }


}