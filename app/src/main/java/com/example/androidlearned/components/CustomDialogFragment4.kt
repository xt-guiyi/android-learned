package com.example.androidlearned.components

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.example.androidlearned.R
import com.example.androidlearned.utils.Display

class CustomDialogFragment4: DialogFragment() {
    private  var position: Int = Gravity.CENTER
    private  lateinit var message: CharSequence
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.custom_dialog_6,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        message.let {
            view.findViewById<TextView>(R.id.custom_dialog_6_text).text = it
        }
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.apply {
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            setDimAmount(0f)
            val attr = attributes
//            attr.width = Display.dip2px(requireContext(),200f)
//            attr.width = ViewGroup.LayoutParams.MATCH_PARENT
            attr.gravity = position
            // 设置底部间距
            if(position == Gravity.BOTTOM) attr.y = 100
            setAttributes(attr)
        }
        dialog?.setCanceledOnTouchOutside(false)
    }

    fun setMessage(message: CharSequence): CustomDialogFragment4 {
        this.message = message
        return this
    }

    fun setPosition(position: Int): CustomDialogFragment4 {
        this.position = position
        return this
    }
}