package com.example.androidlearned.components

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.DialogFragment

class SimpleDialogFragment1 : DialogFragment(){
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(activity)
        builder
            .setTitle("注意")
            .setMessage("是否退出应用？")
            .setPositiveButton("确定"){dialog, id ->
                Toast.makeText(requireContext(),"确定${id}", Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("取消", null)
        return  builder.create()
    }
}