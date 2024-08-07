package com.example.androidlearned.ui.home.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.androidlearned.R
import com.example.androidlearned.databinding.FragmentButtonWidgetBinding


class ButtonWidgetFragment : Fragment() {
    lateinit var binding: FragmentButtonWidgetBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentButtonWidgetBinding.inflate(inflater,container,false)
        init()
        return binding.root
    }

    private fun init() {
        binding.toggleButton.addOnButtonCheckedListener { materialButtonToggleGroup, checkedId, isChecked  ->
            Log.i("toggleButton","索引项：${checkedId} 是否选中${isChecked}" )
        }
    }

}