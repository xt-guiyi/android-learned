package com.example.androidlearned.ui.me.fragments

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.WindowCompat
import com.example.androidlearned.R
import com.example.androidlearned.databinding.FragmentBottomActionsBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ActionsDialogFragment() : BottomSheetDialogFragment() {

    init {
        // 没有适配底部导航区就打开这个注释
//        setStyle(STYLE_NORMAL, R.style.CustomDialog1)
    }

    private lateinit var binding: FragmentBottomActionsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBottomActionsBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        // 设置行为属性
        val standardBottomSheetBehavior = (dialog as BottomSheetDialog).behavior
        standardBottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
        // 跳过折叠，向下滑动直接隐藏
        standardBottomSheetBehavior.skipCollapsed = true
        // 处理 WindowInsets 适配底部导航栏
        view.setOnApplyWindowInsetsListener { v, insets ->
            val params = v.layoutParams as ViewGroup.MarginLayoutParams
            // 增加底部 padding，避免被导航栏遮挡
            params.bottomMargin = insets.systemWindowInsetBottom
            v.layoutParams = params
            insets
        }
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.apply {
            val windowInsetsController = WindowCompat.getInsetsController(this, decorView)
            windowInsetsController.isAppearanceLightNavigationBars = true
            windowInsetsController.isAppearanceLightStatusBars = true
            // 配置系统栏颜色
            statusBarColor = Color.TRANSPARENT
            navigationBarColor = ResourcesCompat.getColor(resources, R.color.white, null)
            // 设置弹窗外的背景透明度
            setDimAmount(0.5f)
            setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
            // 设置弹窗的背景透明，来显示圆角，以及去掉padding
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            // decorView适配系统栏区域，可以衍生到系统栏
            // 这里必须明确设置为false, 才能让setOnApplyWindowInsetsListener处理binding.root节点
            WindowCompat.setDecorFitsSystemWindows(this, false)
        }
    }


    companion object {
        const val TAG = "ActionsDialogFragment"
    }
}