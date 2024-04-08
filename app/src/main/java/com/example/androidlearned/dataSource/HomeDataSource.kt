package com.example.androidlearned.dataSource

import com.example.androidlearned.domain.HomeLayoutInfo

class HomeDataSource {
    companion object Factory {
        fun loadRecycleInfoList():MutableList<HomeLayoutInfo> {
            val homeLayoutInfos = mutableListOf<HomeLayoutInfo>()
            homeLayoutInfos.add(HomeLayoutInfo(1,"协调者布局","学习协调者布局的使用，学习scrollFlags、behavior的用法等等"))
            homeLayoutInfos.add(HomeLayoutInfo(2,"按钮控件","学习button的各种展现方式"))
            homeLayoutInfos.add(HomeLayoutInfo(3,"文本控件","学习TextView的各种展现方式"))
            homeLayoutInfos.add(HomeLayoutInfo(4,"图片控件","学习imageView的各种展现方式"))
            homeLayoutInfos.add(HomeLayoutInfo(5,"输入控件","学习editText控件的各种展现方式"))
            homeLayoutInfos.add(HomeLayoutInfo(6,"单选按钮控件","学习radioButton控件的各种展现方式"))
            homeLayoutInfos.add(HomeLayoutInfo(7,"多选按钮控件","学习checkBox控件的各种展现方式"))
//             for (i in 1..20) {
//                 homeLayoutInfos.add(HomeLayoutInfo("标题${i}", "描述${i}"))
//             }
            return homeLayoutInfos
        }
    }
}