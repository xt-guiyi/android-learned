package com.example.androidlearned.ui.home.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.androidlearned.components.CustomDialogFragment1
import com.example.androidlearned.components.CustomDialogFragment2
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

    }




}