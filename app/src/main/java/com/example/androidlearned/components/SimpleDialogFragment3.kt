package com.example.androidlearned.components

import android.app.AlertDialog
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import com.example.androidlearned.R


class SimpleDialogFragment3 : DialogFragment(){
    private lateinit var mClickCall: (CharSequence, View) -> Unit

    fun  setOnClickListener(mClickCall: (CharSequence, View) -> Unit): SimpleDialogFragment3 {
        this.mClickCall = mClickCall
        return this
    }
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(activity)
        val contentView = layoutInflater.inflate(R.layout.custom_dialog_1,null)
        val submitButton = contentView.findViewById<Button>(R.id.custom_dialog_1_button_submit)
        val contentEdit =  contentView.findViewById<EditText>(R.id.custom_dialog_1_edit_content)
        // 设置提交事件
        submitButton.setOnClickListener {
            this.mClickCall.invoke(contentEdit.text,it)
            this.dismiss()
        }
        builder.setView(contentView)
        return  builder.create()
    }

    override fun onStart() {
        super.onStart()
        // 设置点击外部空白处不会关闭对话框
        dialog?.setCanceledOnTouchOutside(true)
        dialog?.window?.apply {

            // 设置透明背景，去除圆角，以及去掉padding
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
//             decorView.background = ColorDrawable(Color.TRANSPARENT)
            // 设置弹窗外的背景不透明度
            setDimAmount(0.2f)
            // 注意：attributes上的属性都必须最后用setAttributes方法设置才会生效
            val arr = attributes
            // 设置铺满高度，要与设置透明背景一起用才生效，不然会有padding
            arr.width = ViewGroup.LayoutParams.MATCH_PARENT
            arr.height = ViewGroup.LayoutParams.WRAP_CONTENT
            // 设置出现位置
            arr.gravity = Gravity.BOTTOM
            arr.alpha = 0.8f
            setAttributes(arr)
        }
    }
}