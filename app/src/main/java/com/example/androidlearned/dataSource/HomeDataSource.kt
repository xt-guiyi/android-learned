package com.example.androidlearned.dataSource

import com.example.androidlearned.domain.HomeLayoutInfo

class HomeDataSource {
    companion object Factory {
        fun loadRecycleInfoList():MutableList<HomeLayoutInfo> {
            val homeLayoutInfos = mutableListOf<HomeLayoutInfo>()
            homeLayoutInfos.add(HomeLayoutInfo(1,"按钮控件","学习button的各种使用方式"))
            homeLayoutInfos.add(HomeLayoutInfo(2,"文本控件","学习TextView的各种使用方式"))
            homeLayoutInfos.add(HomeLayoutInfo(3,"图片控件","学习imageView,ShapeableImageView的各种使用方式"))
            homeLayoutInfos.add(HomeLayoutInfo(4,"输入控件","学习editText控件的各种使用方式"))
            homeLayoutInfos.add(HomeLayoutInfo(5,"单选按钮控件","学习radioButton控件的各种使用方式"))
            homeLayoutInfos.add(HomeLayoutInfo(6,"多选按钮控件","学习checkBox控件的各种使用方式"))
            homeLayoutInfos.add(HomeLayoutInfo(7,"切换按钮控件","学习switch控件的各种使用方式"))
            homeLayoutInfos.add(HomeLayoutInfo(8,"Chips控件","学习Chips的各种使用方式"))
            homeLayoutInfos.add(HomeLayoutInfo(9,"fab按钮控件","学习Floating action buttons的各种使用方式"))
            homeLayoutInfos.add(HomeLayoutInfo(10,"导航栏控件","学习各种导航栏实现效果"))
            homeLayoutInfos.add(HomeLayoutInfo(11,"导航抽屉控件","学习drawLayout,navigationView实现效果"))
            homeLayoutInfos.add(HomeLayoutInfo(12,"菜单项","学习Menus的各种使用方式"))
            homeLayoutInfos.add(HomeLayoutInfo(13,"进度条控件","学习ProgressIndicators的各种使用方式"))
            homeLayoutInfos.add(HomeLayoutInfo(14,"SnackBar控件","学习SnackBar的各种使用方式"))
            homeLayoutInfos.add(HomeLayoutInfo(15,"Toast提醒","学习Toast的各种使用方式"))
            homeLayoutInfos.add(HomeLayoutInfo(16,"Dialog控件","学习FragmentDialog的各种使用方式"))
            homeLayoutInfos.add(HomeLayoutInfo(17,"BottomSheet控件","学习BottomSheet控件的各种使用方式"))
            homeLayoutInfos.add(HomeLayoutInfo(18,"SeekBar控件","学习SeekBar控件的各种使用方式"))
            homeLayoutInfos.add(HomeLayoutInfo(19,"BadgeDrawable控件","学习BadgeDrawable控件的各种使用方式"))
            homeLayoutInfos.add(HomeLayoutInfo(20,"轮播图控件","学习Banner控件的各种使用方式"))
            homeLayoutInfos.add(HomeLayoutInfo(21,"tabs控件","学习TableLayout,TabItem控件的各种使用方式"))
            homeLayoutInfos.add(HomeLayoutInfo(22,"RecycleView控件","学习RecycleView控件的各种使用方式"))
            homeLayoutInfos.add(HomeLayoutInfo(23,"其他控件","学习一些杂类控件的各种使用方式"))
            homeLayoutInfos.add(HomeLayoutInfo(24,"协调者布局","学习协调者布局的使用，学习scrollFlags、behavior的用法等等"))


//             for (i in 1..20) {
//                 homeLayoutInfos.add(HomeLayoutInfo("标题${i}", "描述${i}"))
//             }
            return homeLayoutInfos
        }
    }
}