package com.example.androidlearned.dataSource

import com.example.androidlearned.domain.HomeLayoutInfo

class HomeDataSource {
    companion object Factory {
        fun loadRecycleInfoList():MutableList<HomeLayoutInfo> {
            val homeLayoutInfos = mutableListOf<HomeLayoutInfo>()
            homeLayoutInfos.add(HomeLayoutInfo(1,"协调者布局","学习协调者布局的使用，学习scrollFlags、behavior的用法等等"))
            homeLayoutInfos.add(HomeLayoutInfo(2,"Button控件","学习button的各种展现方式"))
            homeLayoutInfos.add(HomeLayoutInfo(3,"TextView控件","学习Text的各种展现方式"))

//             for (i in 1..20) {
//                 homeLayoutInfos.add(HomeLayoutInfo("标题${i}", "描述${i}"))
//             }
            return homeLayoutInfos
        }
    }
}