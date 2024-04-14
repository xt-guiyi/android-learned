package com.example.androidlearned.ui.home.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
                .setTitle("标题")
                .setMessage("消息")
                .show()
        }
    }
}