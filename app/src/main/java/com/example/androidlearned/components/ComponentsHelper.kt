package com.example.androidlearned.components

import android.view.View
/**
 * 组件帮助接口，用来扩充一些功能
 * */
interface ComponentsHelper<T,R> {
    /**
     * 点击事件回调
     * */
    var mClickCall: (T, View) -> Unit
    /**
     * 设置点击事件监听器，用于对外提供事件监听。
     * */
    fun setOnClickListener(mClickCall: (T, View) -> Unit ):R
}