package com.example.androidlearned.utils

import android.content.Context
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment


/**
 * 定义通用方法
 * */
class CommonCode {
    companion object {
        /** 给navigation库添加自定义导航器
         * @param context 应用上下文
         * @param fragmentManager fragment管理器
         * @param containerId fragment容器的资源id
         * @param graph 导航图的资源id
         * @return navController 导航控制器
         *  */
        fun addNaviGator(context: Context, fragmentManager: FragmentManager, containerId: Int, graph: Int): NavController {
            val  navHostFragment = fragmentManager.findFragmentById(containerId) as NavHostFragment
            val navController = navHostFragment.navController
            // navHostFragment.childFragmentManager,防止导航失败， view does not have a NavController set错误
            // 先设置导航器，后设置Graph
//            navController.navigatorProvider.addNavigator(FragmentNavigator(context,navHostFragment.childFragmentManager,navHostFragment.id))
            navController.setGraph(graph)
            return navController
        }
    }
}