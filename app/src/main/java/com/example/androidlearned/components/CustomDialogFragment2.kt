package com.example.androidlearned.components

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.TranslateAnimation
import androidx.fragment.app.DialogFragment
import com.example.androidlearned.R

class CustomDialogFragment2: DialogFragment() {
    lateinit var mRootView: View
     var isAnimation = false
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // 绑定布局
        mRootView = inflater.inflate(R.layout.custom_dialog_4, container, false)
        return mRootView
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        /*
            此方法在视图View已经创建后返回的，但是这个view 还没有添加到父级中。
            我们在这里可以重新设定view的各个数据，但是不能修改对话框最外层的ViewGroup的布局参数。
            因为这里的view还没添加到父级中，我们需要在下面onStart生命周期里修改对话框尺寸参数
         */
        // 拦截弹窗触摸事件，防止被父容器触发事件
        mRootView.setOnTouchListener { v, event -> true }
        // 添加开始动画
        slideToUp()
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onStart() {
        super.onStart()
        // 设置弹窗外的背景是否可以点击，来隐藏弹窗
//        dialog?.setCanceledOnTouchOutside(false)
        dialog?.window?.apply {
            // 设置弹窗外的背景透明度
            setDimAmount(0.3f)
            // 设置弹窗的背景透明，来显示圆角，以及去掉padding
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            // 注意：attributes上的属性都必须最后用setAttributes方法设置才会生效
            val arr = attributes
            // 改变弹窗位置
            arr.gravity = Gravity.BOTTOM
            // 设置宽度占满屏幕
            arr.width = ViewGroup.LayoutParams.MATCH_PARENT
            // 设置弹窗的透明度，一般不需要设置
//            arr.alpha = 0.7f
            setAttributes(arr)
            // 添加关闭动画
            decorView.setOnTouchListener { v, event ->
                if(event.action == MotionEvent.ACTION_UP && !isAnimation) {
                    isAnimation = true;
                    slideToDown()
                }
                true
            }

        }
    }

    // 设置从下到上弹出的动画
    private fun slideToUp() {
        // 视图动画，可用属性动画替代
        val slide =  TranslateAnimation(Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, 1.0f, Animation.RELATIVE_TO_SELF, 0f);
        slide.setDuration(400);
        slide.isFillEnabled = true;
        slide.fillAfter = true;
        mRootView.startAnimation(slide);
    }

    // 设置从上到下弹出的动画
    private fun slideToDown() {
        val slide =  TranslateAnimation(Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, 1.0f);slide.setDuration(400);
        slide.isFillEnabled = true;
        slide.fillAfter = true;
        mRootView.startAnimation(slide);
        slide.setAnimationListener(object :Animation.AnimationListener{
            override fun onAnimationStart(animation: Animation?) {
            }

            override fun onAnimationEnd(animation: Animation?) {
                //用来判断是否多次点击。防止多次执行
                isAnimation = false;
                dismiss()
            }

            override fun onAnimationRepeat(animation: Animation?) {
            }

        })
    }
}