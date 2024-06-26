package com.example.androidlearned.dataSource

import com.example.androidlearned.domain.CardInfo


class MeDataSource {
    companion object Factory {
        fun loadRecycleInfoList(): MutableList<CardInfo> {
            val centerLayoutInfo = mutableListOf<CardInfo>()
            centerLayoutInfo.add(CardInfo(1, "音频播放", "学习播放视频"))
            centerLayoutInfo.add(CardInfo(2, "视频播放", "学习播放视频")) // 放在案例库里实现
            centerLayoutInfo.add(CardInfo(3, "蓝牙", "学习蓝牙的使用")) // 放在案例库里实现
            centerLayoutInfo.add(CardInfo(4, "摄像头自定义", "学习控制摄像头")) // 放在案例库里实现
            centerLayoutInfo.add(CardInfo(5, "外卖点餐页", "外卖点餐页")) // 放在案例库里实现
//            centerLayoutInfo.add(CardInfo(3,"JetPack库:ViewModal和liveData","学习ViewModal和liveData相关用法")) // 不用写了
//            centerLayoutInfo.add(CardInfo(3,"JetPack库：Paging分页","学习Paging分页")) // 放在布局的 recycleView里实现，不用单独写了


            return centerLayoutInfo
        }
    }
}