package com.example.androidlearned.components

import android.app.AlertDialog
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.View
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
        val  dialogCustom1View = layoutInflater.inflate(R.layout.custom_dialog_1,null)
        val submitButton = dialogCustom1View.findViewById<Button>(R.id.dialog_custom_1_button_submit)
        val contentEdit =  dialogCustom1View.findViewById<EditText>(R.id.dialog_custom_1_edit_content)
        // 设置提交事件
        submitButton.setOnClickListener {
            this.mClickCall.invoke(contentEdit.text,it)
            this.dismiss()
        }
        builder
            .setView(dialogCustom1View)
        val alertDialog = builder.create()
        alertDialog.window?.let {
            // 去除白色背景
            it.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            // 设置谈框出现位置
            it.attributes.gravity = Gravity.BOTTOM
            // 设置谈框外背景不透明度
            it.setDimAmount(0.2f)
            // 设置谈框内不透明度
            it.attributes.alpha = 1f
        }
        return  alertDialog
    }
}