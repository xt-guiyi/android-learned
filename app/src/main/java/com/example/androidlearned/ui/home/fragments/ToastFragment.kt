package com.example.androidlearned.ui.home.fragments

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.util.TypedValue
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.Fragment
import com.example.androidlearned.R
import com.example.androidlearned.databinding.FragmentToastBinding
import com.example.androidlearned.utils.Display
import com.hjq.toast.ToastParams
import com.hjq.toast.ToastStrategy
import com.hjq.toast.Toaster
import com.hjq.toast.style.BlackToastStyle
import com.hjq.toast.style.CustomToastStyle


class ToastFragment : Fragment() {
    lateinit var binding: FragmentToastBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentToastBinding.inflate(inflater,container,false)
        init()
        return binding.root
    }

    private fun init() {

        binding.customToast1.setOnClickListener {
            val view = LayoutInflater.from(requireContext()).inflate(R.layout.custom_dialog_6,null,false)
            val windowParams = WindowManager.LayoutParams()
            windowParams.type = WindowManager.LayoutParams.TYPE_APPLICATION_PANEL
            windowParams.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
            windowParams.gravity = Gravity.CENTER
            windowParams.height = WindowManager.LayoutParams.WRAP_CONTENT
            windowParams.width = WindowManager.LayoutParams.MATCH_PARENT
            windowParams.dimAmount = 0.5f
//            windowParams.alpha = 0.5f
            requireActivity().windowManager.addView(view,windowParams)
        }
        // 自定义
        binding.customToast2.setOnClickListener {
            val view = LayoutInflater.from(requireContext()).inflate(R.layout.custom_toast_2,null,false)
            val windowParams = WindowManager.LayoutParams()
            windowParams.type = WindowManager.LayoutParams.TYPE_APPLICATION_PANEL
            windowParams.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
            windowParams.gravity = Gravity.CENTER
            windowParams.height = WindowManager.LayoutParams.WRAP_CONTENT
            windowParams.width = Display.dip2px(requireContext(),120f)
            windowParams.dimAmount = 0.1f
//            windowParams.alpha = 0.5f
            requireActivity().windowManager.addView(view,windowParams)
        }



        // 第三方实现
        binding.openToast1.setOnClickListener {
            Toaster.show("一个普通的Toast")
        }

        binding.openToast2.setOnClickListener {
            Toaster.showShort("一个短Toast")
        }


        binding.openToast3.setOnClickListener {
            Toaster.showLong("一个长Toast")
        }

        binding.openToast4.setOnClickListener {
            val toastParams = ToastParams()
            toastParams.apply {
                text = "一个跨页面Toast"
                crossPageShow = true
                duration = 3000
            }
            Toaster.show(toastParams)
        }

        binding.openToast5.setOnClickListener {
            Toaster.delayedShow("延迟两秒显示一个Toast", 2000)
        }

        binding.openToast6.setOnClickListener {
            Thread {
                Toaster.show("子线程显示一个Toast")
            }.start()
        }

        binding.openToast7.setOnClickListener {
            val toastParams = ToastParams()
            toastParams.apply {
                text = "白色样式的Toast"
                style = WhiteToastStyle()
            }
            Toaster.show(toastParams)
        }

        binding.openToast8.setOnClickListener {
            val toastParams = ToastParams()
            toastParams.apply {
                text = "成功"
                style = CustomToastStyle(R.layout.custom_toast_1)
            }
            Toaster.show(toastParams)
        }

        binding.openToast9.setOnClickListener {
            val toastParams = ToastParams()
            toastParams.apply {
                text = "失败"
                style = CustomToastStyle(R.layout.custom_toast_2)
            }
            Toaster.show(toastParams)
        }

        binding.openToast10.setOnClickListener {
            val toastParams = ToastParams()
            toastParams.apply {
                text = "警告警告警告警告警告警告警告警告警告警告警告警告警告"
                style = CustomToastStyle(R.layout.custom_toast_3)
            }
            Toaster.show(toastParams)
        }

        binding.openToast11.setOnClickListener {
            Toaster.setView(R.layout.custom_toast_4);
            Toaster.setGravity(Gravity.CENTER);
            Toaster.show("会全局生效");
        }

        binding.openToast12.setOnClickListener {
            Toaster.setStrategy(ToastStrategy(ToastStrategy.SHOW_STRATEGY_TYPE_QUEUE));
            for (i in 0..2) {
                Toaster.show("第${i}个Toast")
            }
        }

        binding.openToast13.setOnClickListener {
            Toaster.setGravity(Gravity.BOTTOM,0,200)
            Toaster.show("一个底部Toast")
            // 显示完后重新设置会中间
            Toaster.setGravity(Gravity.CENTER)
        }

        binding.openToast14.setOnClickListener {
            Toaster.setGravity(Gravity.TOP,0,200)
            Toaster.show("一个顶部Toast")
            // 显示完后重新设置会中间
            Toaster.setGravity(Gravity.CENTER)
        }

        binding.openToast15.setOnClickListener {
            Toaster.debugShow("一个debugToast,会显示在控制台")
        }

        binding.openToast16.setOnClickListener {
            Toaster.cancel();
        }
    }

    // 白色样式
    class WhiteToastStyle : BlackToastStyle() {
        override fun getTextColor(context: Context): Int {
            return -0x45000000
        }

        override fun getBackgroundDrawable(context: Context): Drawable {
            val drawable = GradientDrawable()
            // 设置颜色
            drawable.setColor(-0x151516)
            // 设置圆角
            drawable.setCornerRadius(
                TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP,
                    10f, context.resources.displayMetrics
                )
            )
            return drawable
        }
    }
}