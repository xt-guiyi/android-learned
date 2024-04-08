package com.example.androidlearned.ui.home.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.androidlearned.R
import com.example.androidlearned.databinding.FragmentEditTextWidgetBinding

class EditTextWidgetFragment : Fragment() {
    lateinit var binding: FragmentEditTextWidgetBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentEditTextWidgetBinding.inflate(inflater,container,false)
        init()
        return binding.root
    }

    private fun init() {
        val editTextClear = binding.editTextClear
        val clearImg = binding.clearImg

        editTextClear.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                if(s!!.isNotEmpty()) {
                    clearImg.visibility = View.VISIBLE
                }else {
                    clearImg.visibility = View.INVISIBLE
                }
            }

        })

        editTextClear.setOnFocusChangeListener { v, hasFocus ->
            if(hasFocus && editTextClear.text.isNotEmpty())  {
                clearImg.visibility = View.VISIBLE
            }else {
                clearImg.visibility = View.INVISIBLE
            }
        }

        clearImg.setOnClickListener {
            editTextClear.text = null
        }
    }
}