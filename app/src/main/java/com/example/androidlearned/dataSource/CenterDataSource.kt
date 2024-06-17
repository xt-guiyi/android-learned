package com.example.androidlearned.dataSource

import com.example.androidlearned.domain.CardInfo


class CenterDataSource {
    companion object Factory {
        fun loadRecycleInfoList():MutableList<CardInfo> {
            val centerLayoutInfo = mutableListOf<CardInfo>()
            centerLayoutInfo.add(CardInfo(1,"系统栏管理","设置系统栏相关状态"))
            centerLayoutInfo.add(CardInfo(2,"通知管理","学习通知用法"))
            centerLayoutInfo.add(CardInfo(3,"窗口管理","学习窗口的概念"))
            centerLayoutInfo.add(CardInfo(4,"权限管理","学习如何添加权限管理"))
            centerLayoutInfo.add(CardInfo(5,"软键盘与输入框实践","学习如何弹出软键盘的同时输入框不被遮挡"))
            centerLayoutInfo.add(CardInfo(6,"Activity使用","学习activity用法"))
            centerLayoutInfo.add(CardInfo(7,"Fragment使用","学习fragment的用法"))
            centerLayoutInfo.add(CardInfo(8,"服务(Service)","学习service用法"))
            centerLayoutInfo.add(CardInfo(9,"广播(Broadcast Receive)","学习广播的用法"))
            centerLayoutInfo.add(CardInfo(10,"内容提供者(Content Provider","学习内容提供者的用法"))
            centerLayoutInfo.add(CardInfo(11,"本地存储","学习各种本地存储的方式"))
            centerLayoutInfo.add(CardInfo(12,"文件IO操作","学习文件操作"))
            centerLayoutInfo.add(CardInfo(13,"异步方法实践","学习安卓多线程和协程"))
            centerLayoutInfo.add(CardInfo(14,"触摸事件","学习触摸的用法"))
            centerLayoutInfo.add(CardInfo(15,"手势","学习手势的用法"))
            centerLayoutInfo.add(CardInfo(16,"自定义View","学习如何渲染UI"))
            centerLayoutInfo.add(CardInfo(17,"动画","学习各种动画的使用"))
            centerLayoutInfo.add(CardInfo(18 ,"添加应用快捷方式","学习如何添加应用快捷方式"))
//            centerLayoutInfo.add(CardInfo(3,"蓝牙","学习蓝牙的使用")) // 放在案例库里实现
            //            centerLayoutInfo.add(CardInfo(3,"触觉反馈","学习触觉反 馈的用法"))
//            centerLayoutInfo.add(CardInfo(3,"拖放实现","学习如何实现拖放view"))
//            centerLayoutInfo.add(CardInfo(3,"lottie动画","学习各种动画的使用"))
//            centerLayoutInfo.add(CardInfo(3,"音频播放","学习播放视频"))
//            centerLayoutInfo.add(CardInfo(3,"视频播放","学习播放视频"))
//            centerLayoutInfo.add(CardInfo(3,"摄像头自定义","学习控制摄像头")) // 放在案例库里实现
//            centerLayoutInfo.add(CardInfo(3,"JetPack库:ViewModal和liveData","学习ViewModal和liveData相关用法"))
//            centerLayoutInfo.add(CardInfo(3,"JetPack库：WorkManager","学习WorkManager用法"))
//            centerLayoutInfo.add(CardInfo(3,"JetPack库：Paging分页","学习Paging分页")) // 放在布局的 recycleView里实现
            //            centerLayoutInfo.add(CardInfo(2,"图片加载","学习高效率的加载图片"))
            //            centerLayoutInfo.add(CardInfo(1,"网络请求","学习如何实现加载网络数据"))

            return centerLayoutInfo
        }
    }
}