package com.example.androidlearned.components

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.androidlearned.R
import com.example.androidlearned.utils.Display

class CustomDialogFragment5: DialogFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.custom_dialog_7,container,false)
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.apply {
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            setDimAmount(0.4f)
            val attr = attributes
            attr.width = Display.dip2px(requireContext(),120f)
            attr.height = Display.dip2px(requireContext(),120f)
            setAttributes(attr)
        }
        dialog?.setCanceledOnTouchOutside(false)
    }
}