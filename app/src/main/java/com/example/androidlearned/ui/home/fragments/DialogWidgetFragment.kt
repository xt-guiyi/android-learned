package com.example.androidlearned.ui.home.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.androidlearned.R
import com.example.androidlearned.components.CustomDialogFragment1
import com.example.androidlearned.components.CustomDialogFragment2
import com.example.androidlearned.components.CustomDialogFragment3
import com.example.androidlearned.components.CustomDialogFragment4
import com.example.androidlearned.components.CustomDialogFragment5
import com.example.androidlearned.components.SimpleDialogFragment1
import com.example.androidlearned.components.SimpleDialogFragment2
import com.example.androidlearned.components.SimpleDialogFragment3
import com.example.androidlearned.databinding.FragmentDialogBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder


class DialogWidgetFragment : Fragment() {

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

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun init() {
        binding.bottomMaterialDialog.setOnClickListener {
            MaterialAlertDialogBuilder(requireContext())
                .setTitle("注意")
                .setMessage("是否退出应用？")
                .setPositiveButton("确定",null)
                .setNegativeButton("取消", null)
                .show()
        }
        // onCreateDialog使用方式
        binding.bottomOriginDialog1.setOnClickListener {
            SimpleDialogFragment1().show(requireActivity().supportFragmentManager,"SimpleDialogFragment1")
        }

        binding.bottomOriginDialog2.setOnClickListener {
           SimpleDialogFragment2()
               .setOnClickListener { charSequence, view ->
                   Toast.makeText(requireContext(),charSequence,Toast.LENGTH_LONG).show()
               }
               .show(requireActivity().supportFragmentManager,"SimpleDialogFragment2")
        }

        binding.bottomOriginDialog3.setOnClickListener {
            SimpleDialogFragment3()
                .setOnClickListener { charSequence, view ->
                    Toast.makeText(requireContext(),"输入了${charSequence}",Toast.LENGTH_LONG).show()
                }
                .show(requireActivity().supportFragmentManager,"SimpleDialogFragment3")
        }

        // onCreateView使用方式,推荐这种
        binding.bottomCustomViewDialog1.setOnClickListener {
            CustomDialogFragment1().show(requireActivity().supportFragmentManager,"CustomDialogFragment1")
        }
        binding.bottomCustomViewDialog2.setOnClickListener {
            CustomDialogFragment2().show(requireActivity().supportFragmentManager,"CustomDialogFragment2")
        }

        binding.bottomCustomViewDialog3.setOnClickListener {
            val dialog = CustomDialogFragment3()
                .setImage(resources.getDrawable(R.drawable.status_success, requireActivity().theme))
                .setMessage("处理成功")

            dialog.show(requireActivity().supportFragmentManager,"CustomDialogFragment3")
            Handler(requireActivity().mainLooper).postDelayed({ dialog.dismiss() },2000)

        }

        binding.bottomCustomViewDialog4.setOnClickListener {
            val dialog = CustomDialogFragment3()
                .setImage(resources.getDrawable(R.drawable.status_error, requireActivity().theme))
                .setMessage("处理失败")

            dialog.show(requireActivity().supportFragmentManager,"CustomDialogFragment3")
            Handler(requireActivity().mainLooper).postDelayed({ dialog.dismiss() },2000)
        }

        binding.bottomCustomViewDialog5.setOnClickListener {
            val dialog =  CustomDialogFragment3()
                .setImage(resources.getDrawable(R.drawable.status_wrong, requireActivity().theme))
                .setMessage("警告")

            dialog.show(requireActivity().supportFragmentManager,"CustomDialogFragment3")
            Handler(requireActivity().mainLooper).postDelayed({ dialog.dismiss() },2000)
        }

        binding.bottomCustomViewDialog6.setOnClickListener {
            val dialog =  CustomDialogFragment4()
                .setPosition(Gravity.CENTER)
                .setMessage("反馈成功反馈成功反馈成功反馈成功反馈成功")
            dialog.show(requireActivity().supportFragmentManager,"CustomDialogFragment4")
            Handler(requireActivity().mainLooper).postDelayed({ dialog.dismiss() },2000)
        }

        binding.bottomCustomViewDialog7.setOnClickListener {
            val dialog =  CustomDialogFragment4()
                .setPosition(Gravity.BOTTOM)
                .setMessage("反馈成功，请稍后联系")
            dialog.show(requireActivity().supportFragmentManager,"CustomDialogFragment4")
            Handler(requireActivity().mainLooper).postDelayed({ dialog.dismiss() },2000)
        }

        binding.bottomCustomViewDialog8.setOnClickListener {
            val dialog =  CustomDialogFragment5()
            dialog.show(requireActivity().supportFragmentManager,"CustomDialogFragment5")
            Handler(requireActivity().mainLooper).postDelayed({ dialog.dismiss() },5000)
        }

    }




}