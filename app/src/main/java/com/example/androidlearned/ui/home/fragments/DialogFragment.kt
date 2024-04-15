package com.example.androidlearned.ui.home.fragments

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.androidlearned.R
import com.example.androidlearned.databinding.FragmentDialogBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder


class DialogFragment : Fragment() {

    lateinit var binding: FragmentDialogBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDialogBinding.inflate(inflater,container,false)
        init()
        return binding.root
    }

    private fun init() {
        binding.bottomMaterialDialog.setOnClickListener {
            MaterialAlertDialogBuilder(requireContext())
                .setTitle("注意")
                .setMessage("是否退出应用？")
                .setPositiveButton("确定",null)
                .setNegativeButton("取消", null)
                .show()
        }

        binding.bottomOriginDialog1.setOnClickListener {
            SimpleDialogFragment1().show(requireActivity().supportFragmentManager,"SimpleDialogFragment1")
        }

        binding.bottomOriginDialog2.setOnClickListener {
            SimpleDialogFragment2()
                .setOnClickListener { charSequence, view ->
                    Toast.makeText(requireContext(),"输入了${charSequence}",Toast.LENGTH_LONG).show()
                }
                .show(requireActivity().supportFragmentManager,"SimpleDialogFragment2")
        }


    }

    class SimpleDialogFragment1 : DialogFragment(){
        override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
                val builder = AlertDialog.Builder(activity)
                builder
                    .setTitle("注意")
                    .setMessage("是否退出应用？")
                    .setPositiveButton("确定"){dialog, id ->
                        Toast.makeText(requireContext(),"确定${id}",Toast.LENGTH_SHORT).show()
                    }
                    .setNegativeButton("取消", null)
                 return  builder.create()
        }
    }

    class SimpleDialogFragment2 : DialogFragment(){
        private lateinit var mClickCall: (CharSequence, View) -> Unit

        fun  setOnClickListener(mClickCall: (CharSequence,View) -> Unit): SimpleDialogFragment2 {
            this.mClickCall = mClickCall
            return this
        }
        override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
            val builder = AlertDialog.Builder(activity)
            val  dialogCustom1View = layoutInflater.inflate(R.layout.dialog_custom_1,null)
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
}