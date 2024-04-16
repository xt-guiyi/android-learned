package com.example.androidlearned.components

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.TranslateAnimation
import androidx.fragment.app.DialogFragment
import com.example.androidlearned.R

class CustomDialogFragment2: DialogFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // 绑定布局
        return inflater.inflate(R.layout.custom_dialog_4, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        /*
            此方法在视图View已经创建后返回的，但是这个view 还没有添加到父级中。
            我们在这里可以重新设定view的各个数据，但是不能修改对话框最外层的ViewGroup的布局参数。
            因为这里的view还没添加到父级中，我们需要在下面onStart生命周期里修改对话框尺寸参数
         */
        slideToUp(view)
    }

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
        }
    }

    // 设置从下到上弹出的动画
    private fun slideToUp(view: View) {
        val slide =  TranslateAnimation(Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, 1.0f, Animation.RELATIVE_TO_SELF, 0f);
        slide.setDuration(400);
        slide.isFillEnabled = true;
        slide.fillAfter = true;
        view.startAnimation(slide);
    }


}