package com.example.androidlearned.dataSource

import com.example.androidlearned.domain.HomeLayoutInfo

class HomeDataSource {
    companion object Factory {
        fun loadRecycleInfoList():MutableList<HomeLayoutInfo> {
            val homeLayoutInfos = mutableListOf<HomeLayoutInfo>()
            homeLayoutInfos.add(HomeLayoutInfo(1,"协调者布局","学习协调者布局的使用，学习scrollFlags、behavior的用法等等"))
            homeLayoutInfos.add(HomeLayoutInfo(2,"按钮控件","学习button的各种使用方式"))
            homeLayoutInfos.add(HomeLayoutInfo(3,"文本控件","学习TextView的各种使用方式"))
            homeLayoutInfos.add(HomeLayoutInfo(4,"图片控件","学习imageView,ShapeableImageView的各种使用方式"))
            homeLayoutInfos.add(HomeLayoutInfo(5,"输入控件","学习editText控件的各种使用方式"))
            homeLayoutInfos.add(HomeLayoutInfo(6,"单选按钮控件","学习radioButton控件的各种使用方式"))
            homeLayoutInfos.add(HomeLayoutInfo(7,"多选按钮控件","学习checkBox控件的各种使用方式"))
            homeLayoutInfos.add(HomeLayoutInfo(8,"切换按钮控件","学习switch控件的各种使用方式"))
//            homeLayoutInfos.add(HomeLayoutInfo(9,"卡片布局","学习cardView控件的各种使用方式"))
//            homeLayoutInfos.add(HomeLayoutInfo(10,"Chips控件","学习Chips的各种使用方式"))
//            homeLayoutInfos.add(HomeLayoutInfo(11,"Dialogs弹框","学习Dialogs的各种使用方式"))
//            homeLayoutInfos.add(HomeLayoutInfo(12,"菜单项","学习Menus的各种使用方式"))
//            homeLayoutInfos.add(HomeLayoutInfo(13,"导航","学习底部导航栏，导航抽屉的各种使用方式"))
//            homeLayoutInfos.add(HomeLayoutInfo(14,"进度条","学习Progress indicators的各种使用方式"))
//            homeLayoutInfos.add(HomeLayoutInfo(14,"sheet控件","学习sheet的各种使用方式"))
//             for (i in 1..20) {
//                 homeLayoutInfos.add(HomeLayoutInfo("标题${i}", "描述${i}"))
//             }
            return homeLayoutInfos
        }
    }
}