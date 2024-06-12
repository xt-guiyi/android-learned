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
//            homeLayoutInfos.add(CardInfo(24,"WebView控件","学习一些杂类控件的各种使用方式"))
//            homeLayoutInfos.add(CardInfo(24,"评分控件","学习一些杂类控件的各种使用方式"))
//            homeLayoutInfos.add(CardInfo(24,"骨架屏","学习一些杂类控件的各种使用方式"))
            homeLayoutInfos.add(CardInfo(25,"协调者布局","学习协调者布局的使用，学习scrollFlags、behavior的用法等等"))
//             for (i in 1..20) {
//                 homeLayoutInfos.add(CardInfo("标题${i}", "描述${i}"))
//             }
            return homeLayoutInfos
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