package com.example.androidlearned.dataSource

import com.example.androidlearned.domain.CardInfo


class MeDataSource {
    companion object Factory {
        fun loadRecycleInfoList(): MutableList<CardInfo> {
            val centerLayoutInfo = mutableListOf<CardInfo>()
            centerLayoutInfo.add(CardInfo(1, "音频播放", ""))
            centerLayoutInfo.add(CardInfo(2, "下拉菜单", ""))
            centerLayoutInfo.add(CardInfo(3, "底部弹框", ""))
//            centerLayoutInfo.add(CardInfo(2, "视频播放", "学习播放视频"))
//            centerLayoutInfo.add(CardInfo(3, "蓝牙", "学习蓝牙的使用"))
//            centerLayoutInfo.add(CardInfo(4, "摄像头自定义", "学习控制摄像头"))
//            centerLayoutInfo.add(CardInfo(5, "外卖点餐页", "外卖点餐页"))
//            centerLayoutInfo.add(CardInfo(6, "自定义相册", "学习自定义相册"))
            return centerLayoutInfo
        }
    }
}