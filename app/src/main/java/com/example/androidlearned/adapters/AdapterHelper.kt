package com.example.androidlearned.adapters
import android.view.View

/**
 * 适配器帮助接口，用来扩充一些功能
 * */
interface AdapterHelper<T> {
    /**
     * 点击事件回调
     * */
    val mClickCall: (T,View) -> Unit
    /**
     * 设置点击事件监听器，用于对外提供事件监听。
     * */
    fun setOnClickListener(mClickCall: (T,View) -> Unit )
}