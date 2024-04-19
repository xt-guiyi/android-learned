package com.example.androidlearned.components

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.FrameLayout
import androidx.fragment.app.DialogFragment
import com.example.androidlearned.R
import com.example.androidlearned.utils.Display
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class ModalBottomSheet1:BottomSheetDialogFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setStyle(DialogFragment.STYLE_NORMAL, R.style.ModalBottomSheetDialog)
        val mView = inflater.inflate(R.layout.custom_dialog_4, container, false)
        // 一句话实现全屏，爽歪歪
        mView.minimumHeight = resources.displayMetrics.heightPixels
        return mView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // 设置行为属性
        val standardBottomSheetBehavior = (dialog as BottomSheetDialog).behavior
        standardBottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
        standardBottomSheetBehavior.skipCollapsed = true
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.apply {
            setDimAmount(0.1f)
        }

    }

    companion object {
        const val TAG = "ModalBottomSheet"
    }
}