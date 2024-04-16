package com.example.androidlearned.components

import android.app.AlertDialog
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.DialogFragment
import com.example.androidlearned.R
import com.example.androidlearned.utils.Display

class SimpleDialogFragment2(): DialogFragment(),ComponentsHelper<CharSequence,SimpleDialogFragment2> {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(activity)
        val customView = layoutInflater.inflate(R.layout.custom_dialog_2, null)
        val cancelButton = customView.findViewById<Button>(R.id.custom_dialog_cancel)
        val confirmButton = customView.findViewById<Button>(R.id.custom_dialog_confirm)
        // 添加事件监听
        cancelButton.setOnClickListener {
            mClickCall.invoke("了解",it)
            this.dismiss()
        }
        confirmButton.setOnClickListener {
            mClickCall.invoke("放弃注销",it)
            this.dismiss()

        }
        // 添加自定义布局
        builder.setView(customView)
        return builder.create()
    }

    // 设置dialog
    override fun onStart() {
        super.onStart()
        dialog?.window?.apply {
            // 去除白色背景
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            // 设置宽度
            setLayout(Display.dip2px(requireActivity(),280f), ViewGroup.LayoutParams.WRAP_CONTENT)
        }
        // 禁用背景点击
        dialog?.setCanceledOnTouchOutside(false)
    }

    override lateinit var mClickCall: (CharSequence, View) -> Unit

    override fun setOnClickListener(mClickCall: (CharSequence, View) -> Unit): SimpleDialogFragment2 {
        this.mClickCall = mClickCall
        return this
    }
}