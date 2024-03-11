package com.example.androidlearned.dataSource

import com.example.androidlearned.domain.HomeLayoutInfo

class HomeDataSource {
    companion object Factory {
        fun loadRecycleInfoList():MutableList<HomeLayoutInfo> {
            val homeLayoutInfos = mutableListOf<HomeLayoutInfo>()
             for (i in 1..100) {
                 homeLayoutInfos.add(HomeLayoutInfo("标题${i}", "描述${i}"))
             }
            return homeLayoutInfos
        }
    }
}