package com.example.androidlearned.ui.home.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.CompoundButton.OnCheckedChangeListener
import com.example.androidlearned.R
import com.example.androidlearned.databinding.FragmentCheckBoxWidgetBinding
import com.google.android.material.checkbox.MaterialCheckBox
import java.util.Objects

class CheckBoxWidgetFragment : Fragment() {

    lateinit var  binding: FragmentCheckBoxWidgetBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCheckBoxWidgetBinding.inflate(inflater,container,false)
        init()
        return binding.root
    }

    private fun init() {
        val parentNode = binding.parentNode as MaterialCheckBox
        val childNodes= mutableListOf(binding.childNode1,binding.childNode2,binding.childNode3,binding.childNode4) as MutableList<MaterialCheckBox>
        var isUpdatingChildren = false
        parentNode.setOnCheckedChangeListener { _, isChecked ->
            if(parentNode.checkedState != MaterialCheckBox.STATE_INDETERMINATE) {

                parentNode.checkedState = MaterialCheckBox.STATE_CHECKED
                isUpdatingChildren = true
                childNodes.forEach {
                    it.isChecked = isChecked
                }
                isUpdatingChildren = false
            }
        }


        val childCheckedChangeListener =
            OnCheckedChangeListener { _, isChecked ->
                val count = childNodes.count { it.isChecked }
                when(count){
                    4 -> parentNode.isChecked = true
                    0 -> parentNode.isChecked = false
                    else -> if (!isUpdatingChildren)  parentNode.checkedState = MaterialCheckBox.STATE_INDETERMINATE
                }
            }

        childNodes.forEach {
            it.setOnCheckedChangeListener(childCheckedChangeListener) //
        }
    }

}