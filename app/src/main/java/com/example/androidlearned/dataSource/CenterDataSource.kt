package com.example.androidlearned.dataSource

import com.example.androidlearned.adapters.RecycleViewExample3Adapter
import com.example.androidlearned.domain.CardInfo
import com.example.androidlearned.domain.People
import com.example.androidlearned.domain.StickHeadInfo
import java.util.Collections


class CenterDataSource {
    companion object Factory {
        fun loadRecycleInfoList():MutableList<CardInfo> {
            val centerLayoutInfo = mutableListOf<CardInfo>()
            centerLayoutInfo.add(CardInfo(1,"系统栏管理","设置系统栏相关状态"))
            centerLayoutInfo.add(CardInfo(2,"通知管理","学习通知用法"))
            centerLayoutInfo.add(CardInfo(3,"窗口管理","学习窗口的概念"))
            centerLayoutInfo.add(CardInfo(3,"权限管理","学习如何添加权限管理"))
            centerLayoutInfo.add(CardInfo(4,"软键盘与输入框的最佳实践","学习如何优雅弹出软键盘的同时输入框不被遮挡"))
            centerLayoutInfo.add(CardInfo(5,"Activity深度使用","学习activity用法"))
            centerLayoutInfo.add(CardInfo(6,"Fragment深度使用","学习fragment的用法"))
            centerLayoutInfo.add(CardInfo(7,"后台服务(Service)","学习service用法"))
            centerLayoutInfo.add(CardInfo(8,"广播(Broadcast Receive)","学习广播的用法"))
            centerLayoutInfo.add(CardInfo(9,"内容提供者(Content Provider","学习内容提供者的用法"))
            centerLayoutInfo.add(CardInfo(10,"本地存储","学习各种本地存储的方式"))
            centerLayoutInfo.add(CardInfo(11,"多线程与协程","学习安卓多线程和协程"))
//            centerLayoutInfo.add(CardInfo(3,"触摸进阶","学习触摸的用法"))
//            centerLayoutInfo.add(CardInfo(3,"手势进阶","学习手势的用法"))
//            centerLayoutInfo.add(CardInfo(3,"触觉反馈","学习触觉反 馈的用法"))
//            centerLayoutInfo.add(CardInfo(3,"拖放实现","学习如何实现拖放view"))
//            centerLayoutInfo.add(CardInfo(3,"自定义View","学习如何渲染UI"))
//            centerLayoutInfo.add(CardInfo(3,"添加应用启动页","学习如何添加应用启动页"))
//            centerLayoutInfo.add(CardInfo(3,"添加应用快捷方式","学习如何添加应用快捷方式"))
//            centerLayoutInfo.add(CardInfo(3,"蓝牙","学习蓝牙的使用"))
//            centerLayoutInfo.add(CardInfo(3,"动画","学习各种动画的使用"))
//            centerLayoutInfo.add(CardInfo(3,"音频播放","学习播放视频"))
//            centerLayoutInfo.add(CardInfo(3,"视频播放","学习播放视频"))
//            centerLayoutInfo.add(CardInfo(3,"摄像头控制","学习控制摄像头"))
//            centerLayoutInfo.add(CardInfo(3,"JetPack库:ViewModal和liveData","学习ViewModal和liveData相关用法"))
//            centerLayoutInfo.add(CardInfo(3,"JetPack库：WorkManager","学习WorkManager用法"))
//            centerLayoutInfo.add(CardInfo(3,"JetPack库：Paging分页","学习Paging分页"))
            //            centerLayoutInfo.add(CardInfo(1,"网络请求","学习如何实现加载网络数据"))
//            centerLayoutInfo.add(CardInfo(2,"图片加载","学习高效率的加载图片"))
            return centerLayoutInfo
        }
    }
}