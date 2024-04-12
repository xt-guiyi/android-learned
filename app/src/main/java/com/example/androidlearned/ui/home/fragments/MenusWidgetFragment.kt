package com.example.androidlearned.ui.home.fragments

import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import com.example.androidlearned.R
import com.example.androidlearned.databinding.FragmentMenusWidgetBinding


class MenusWidgetFragment : Fragment() {

    lateinit var  binding:FragmentMenusWidgetBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMenusWidgetBinding.inflate(inflater,container,false)
        registerForContextMenu(binding.menuContextTextView)
        return binding.root
    }

    override fun onCreateContextMenu(
        menu: ContextMenu,
        v: View,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater: MenuInflater = requireActivity().menuInflater
        inflater.inflate(R.menu.menu, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.menu_1 -> {
                Toast.makeText(requireActivity(),item.title,Toast.LENGTH_SHORT).show()
                true
            }
            R.id.menu_2 -> {
                Toast.makeText(requireActivity(),item.title,Toast.LENGTH_SHORT).show()
                 true
            }
            R.id.menu_3 -> {
                Toast.makeText(requireActivity(),item.title,Toast.LENGTH_SHORT).show()
                 true
            }
            else -> {
                super.onContextItemSelected(item)
            }
        }
    }
}