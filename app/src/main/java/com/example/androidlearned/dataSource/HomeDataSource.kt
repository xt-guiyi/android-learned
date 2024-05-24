package com.example.androidlearned.dataSource

import com.example.androidlearned.adapters.RecycleViewExample3Adapter
import com.example.androidlearned.domain.CardInfo
import com.example.androidlearned.domain.People
import com.example.androidlearned.domain.StickHeadInfo
import java.util.Collections


class HomeDataSource {
    companion object Factory {
        fun loadRecycleInfoList():MutableList<CardInfo> {
            val homeLayoutInfos = mutableListOf<CardInfo>()
            homeLayoutInfos.add(CardInfo(1,"按钮控件","学习button的各种使用方式"))
            homeLayoutInfos.add(CardInfo(2,"文本控件","学习TextView的各种使用方式"))
            homeLayoutInfos.add(CardInfo(3,"图片控件","学习imageView,ShapeableImageView的各种使用方式"))
            homeLayoutInfos.add(CardInfo(4,"输入控件","学习editText控件的各种使用方式"))
            homeLayoutInfos.add(CardInfo(5,"单选按钮控件","学习radioButton控件的各种使用方式"))
            homeLayoutInfos.add(CardInfo(6,"多选按钮控件","学习checkBox控件的各种使用方式"))
            homeLayoutInfos.add(CardInfo(7,"切换按钮控件","学习switch控件的各种使用方式"))
            homeLayoutInfos.add(CardInfo(8,"Chips控件","学习Chips的各种使用方式"))
            homeLayoutInfos.add(CardInfo(9,"fab按钮控件","学习Floating action buttons的各种使用方式"))
            homeLayoutInfos.add(CardInfo(10,"导航栏控件","学习各种导航栏实现效果"))
            homeLayoutInfos.add(CardInfo(11,"导航抽屉控件","学习drawLayout,navigationView实现效果"))
            homeLayoutInfos.add(CardInfo(12,"菜单项","学习Menus的各种使用方式"))
            homeLayoutInfos.add(CardInfo(13,"进度条控件","学习ProgressIndicators的各种使用方式"))
            homeLayoutInfos.add(CardInfo(14,"SnackBar控件","学习SnackBar的各种使用方式"))
            homeLayoutInfos.add(CardInfo(15,"Toast提醒","学习Toast的各种使用方式"))
            homeLayoutInfos.add(CardInfo(16,"Dialog控件","学习FragmentDialog的各种使用方式"))
            homeLayoutInfos.add(CardInfo(17,"BottomSheet控件","学习BottomSheet控件的各种使用方式"))
            homeLayoutInfos.add(CardInfo(18,"SeekBar控件","学习SeekBar控件的各种使用方式"))
            homeLayoutInfos.add(CardInfo(19,"BadgeDrawable控件","学习BadgeDrawable控件的各种使用方式"))
            homeLayoutInfos.add(CardInfo(20,"轮播图控件","学习Banner控件的各种使用方式"))
            homeLayoutInfos.add(CardInfo(21,"Tabs控件","学习TableLayout,TabItem控件的各种使用方式"))
            homeLayoutInfos.add(CardInfo(22,"ViewPage控件","学习ViewPage控件的各种使用方式"))
            homeLayoutInfos.add(CardInfo(23,"RecycleView列表","学习RecycleView控件的各种使用方式"))
            homeLayoutInfos.add(CardInfo(24,"其他控件","学习一些杂类控件的各种使用方式"))
            homeLayoutInfos.add(CardInfo(25,"协调者布局","学习协调者布局的使用，学习scrollFlags、behavior的用法等等"))


//             for (i in 1..20) {
//                 homeLayoutInfos.add(CardInfo("标题${i}", "描述${i}"))
//             }
            return homeLayoutInfos
        }

        fun loadCenterRecycleInfoList():MutableList<CardInfo> {
            val centerLayoutInfo = mutableListOf<CardInfo>()
            centerLayoutInfo.add(CardInfo(1,"网络请求","学习如何实现加载网络数据"))
            centerLayoutInfo.add(CardInfo(2,"图片加载","学习高效率的加载图片"))
            centerLayoutInfo.add(CardInfo(3,"Activity和Fragment深度使用","学习activity,fragment的其他用法"))
            centerLayoutInfo.add(CardInfo(4,"后台服务(Service)","学习service用法"))
            centerLayoutInfo.add(CardInfo(5,"广播(Broadcast Receive)","学习广播的用法"))
            centerLayoutInfo.add(CardInfo(6,"内容提供者(Content Provider","学习内容提供者的用法"))
            centerLayoutInfo.add(CardInfo(3,"触摸进阶","学习触摸的用法"))
            centerLayoutInfo.add(CardInfo(3,"手势进阶","学习手势的用法"))
            centerLayoutInfo.add(CardInfo(3,"触觉反馈","学习触觉反馈的用法"))
            centerLayoutInfo.add(CardInfo(3,"拖放实现","学习如何实现拖放view"))
            centerLayoutInfo.add(CardInfo(3,"添加应用启动页","学习如何添加应用启动页"))
            centerLayoutInfo.add(CardInfo(3,"添加应用快捷方式","学习如何添加应用快捷方式"))
            centerLayoutInfo.add(CardInfo(3,"权限管理","学习如何添加权限管理"))
            centerLayoutInfo.add(CardInfo(3,"自定义View","学习如何渲染UI"))
            centerLayoutInfo.add(CardInfo(3,"本地存储","学习各种本地存储的方式"))
            centerLayoutInfo.add(CardInfo(2,"通知管理","学习通知用法"))
            centerLayoutInfo.add(CardInfo(3,"系统栏管理","学习状态栏的格式使用"))
            centerLayoutInfo.add(CardInfo(3,"窗口管理","学习窗口的概念"))
            centerLayoutInfo.add(CardInfo(3,"蓝牙","学习蓝牙的使用"))
            centerLayoutInfo.add(CardInfo(3,"动画","学习各种动画的使用"))
            centerLayoutInfo.add(CardInfo(3,"软键盘与输入框的最佳实践","学习如何优雅弹出软键盘的同时输入框不被遮挡"))
            centerLayoutInfo.add(CardInfo(3,"音频播放","学习播放视频"))
            centerLayoutInfo.add(CardInfo(3,"视频播放","学习播放视频"))
            centerLayoutInfo.add(CardInfo(3,"摄像头控制","学习控制摄像头"))
            centerLayoutInfo.add(CardInfo(3,"JetPack库:ViewModal和liveData","学习ViewModal和liveData相关用法"))
            centerLayoutInfo.add(CardInfo(3,"JetPack库：WorkManager","学习WorkManager用法"))
            centerLayoutInfo.add(CardInfo(3,"JetPack库：Paging分页","学习Paging分页"))

            return centerLayoutInfo
        }

        fun loadRecycleViewList(): MutableList<String> {
            val list:MutableList<String>  = mutableListOf()
            for (i in 0..<20) {
                list.add("第${i}项")
            }
            return list
        }

        fun loadRecycleViewListByPage(currentPage: Int = 1, pageSize: Int = 10): MutableList<String> {
            val list:MutableList<String>  = mutableListOf()
            for (i in 0..<200) {
                list.add("第${i}项")
            }
            // 验证开始索引是否在列表范围内
            val fromIndex: Int = (currentPage - 1) * pageSize
            val toIndex = (fromIndex + pageSize).coerceAtMost(list.size)
            // 验证开始索引是否在列表范围内
            return if (fromIndex >= list.size || fromIndex < 0) {
               return Collections.emptyList() // 如果开始索引超出范围，返回空列表
            } else list.subList(fromIndex, toIndex)
        }

        fun loadRecycleViewPeopleList(): MutableList<People> {
            val list:MutableList<People>  = mutableListOf()
            for (i in 1..20) {
                list.add(People(i,"王 $i"))
            }
            return list
        }

        fun loadRecycleViewStickHeadList(): MutableList<StickHeadInfo> {
            val list:MutableList<StickHeadInfo>  = mutableListOf()
            // 食品
            list.add(StickHeadInfo("食品分组", RecycleViewExample3Adapter.VIEW_TYPE_TITLE, "食品分组"))
            for (i in 1..15) {
                list.add(StickHeadInfo("食品-$i", RecycleViewExample3Adapter.VIEW_TYPE_CONTENT,"食品分组"))
            }
            // 水果
            list.add(StickHeadInfo("水果分组", RecycleViewExample3Adapter.VIEW_TYPE_TITLE,"水果分组"))
            for (i in 1..8) {
                list.add(StickHeadInfo("水果-$i", RecycleViewExample3Adapter.VIEW_TYPE_CONTENT,"水果分组"))
            }
            // 手机
            list.add(StickHeadInfo("手机分组", RecycleViewExample3Adapter.VIEW_TYPE_TITLE, "手机分组"))
            for (i in 1..18) {
                list.add(StickHeadInfo("手机-$i", RecycleViewExample3Adapter.VIEW_TYPE_CONTENT, "手机分组"))
            }
            // 电脑
            list.add(StickHeadInfo("电脑分组", RecycleViewExample3Adapter.VIEW_TYPE_TITLE, "电脑分组"))
            for (i in 1..4) {
                list.add(StickHeadInfo("电脑-$i", RecycleViewExample3Adapter.VIEW_TYPE_CONTENT, "电脑分组"))
            }
            // 百货
            list.add(StickHeadInfo("百货分组", RecycleViewExample3Adapter.VIEW_TYPE_TITLE, "百货分组"))
            for (i in 1..30) {
                list.add(StickHeadInfo("百货-$i", RecycleViewExample3Adapter.VIEW_TYPE_CONTENT, "百货分组"))
            }
            return list
        }
    }
}