package com.example.androidlearned.utils

import android.annotation.SuppressLint
import android.content.Context

class Display {
    companion object {
        /** 获取设备屏幕高度，单位为像素*/
        fun getScreenHeight(context: Context): Int {
            return context.resources.displayMetrics.heightPixels
        }
        /** 获取设备状态栏高度，单位为像素*/
        @SuppressLint("DiscouragedApi", "InternalInsetResource")
        fun getStatusBarHeight(context: Context): Int {
            var result = 0
            val resourcesId = context.resources.getIdentifier("status_bar_height","dimen","android")
            if(resourcesId > 0) {
                result = context.resources.getDimensionPixelSize(resourcesId)
            }
            return result
        }

        /** dp转px */
        fun dip2px(context: Context, dpValue: Float): Int {
            val scale = context.resources.displayMetrics.density
            return ((dpValue * scale) + 0.5f).toInt()
        }

        /** px转dp */
        fun px2dip(context: Context, pxValue: Float): Int {
            val scale = context.resources.displayMetrics.density
            return ((pxValue -  0.5f) / scale).toInt()
        }

    }
}