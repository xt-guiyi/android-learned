package com.example.androidlearned.components

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.OvershootInterpolator
import android.view.animation.ScaleAnimation
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.example.androidlearned.R
import com.example.androidlearned.utils.Display

class CustomDialogFragment3: DialogFragment() {
    private  lateinit var img: Drawable
    private  lateinit var message: CharSequence

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.custom_dialog_5,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
         img.let {
             view.findViewById<ImageView>(R.id.custom_dialog_5_image).setImageDrawable(it)
         }
         message.let {
             view.findViewById<TextView>(R.id.custom_dialog_5_text).text = it
         }
        // 设置动画
        animateContentUp(view)
    }


    // 从下到上
    private fun animateContentUp(view:View) {
        val scaleX = ObjectAnimator.ofFloat(view, View.SCALE_X, 0f, 1f)
        val scaleY = ObjectAnimator.ofFloat(view, View.SCALE_Y, 0f, 1f)
        val animatorSet = AnimatorSet().apply {
            setDuration(400)
            playTogether(scaleX, scaleY)
        }
        animatorSet.start()
    }


    override fun onStart() {
        super.onStart()
        dialog?.window?.apply {
            // 设置弹窗内背景透明度为0
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            // 设置弹窗外背景透明度为0
            setDimAmount(0f)
            val attr = attributes
            attr.width = Display.dip2px(requireContext(),200f)
            // 取消掉系统动画
            attr.windowAnimations = 0
            setAttributes(attr)
        }
        dialog?.setCanceledOnTouchOutside(false)
    }

    fun setMessage(message: CharSequence): CustomDialogFragment3 {
        this.message = message
        return this
    }

    fun setImage(img: Drawable): CustomDialogFragment3 {
        this.img = img
        return this
    }
}