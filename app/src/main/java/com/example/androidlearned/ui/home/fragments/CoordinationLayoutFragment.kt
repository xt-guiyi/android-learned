package com.example.androidlearned.ui.home.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.androidlearned.R
import com.example.androidlearned.databinding.FragmentCoordinationLayoutBinding


class CoordinationLayoutFragment : Fragment() {
    private lateinit var binding: FragmentCoordinationLayoutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCoordinationLayoutBinding.inflate(inflater,container,false)
        initListener()
        return binding.root
    }

    private fun initListener() {
        binding.button1.setOnClickListener {
            it.findNavController().navigate(R.id.action_coordinationLayoutFragment_to_coordinationLayoutExample1Fragment)
        }
        binding.button2.setOnClickListener{
            it.findNavController().navigate(R.id.action_coordinationLayoutFragment_to_coordinationLayoutExample2Fragment)
        }
        binding.button3.setOnClickListener{
            it.findNavController().navigate(R.id.action_coordinationLayoutFragment_to_coordinationLayoutExample3Fragment)
        }
        binding.button4.setOnClickListener{
            it.findNavController().navigate(R.id.action_coordinationLayoutFragment_to_coordinationLayoutExample4Fragment)
        }
        binding.advanceButton1.setOnClickListener{
            it.findNavController().navigate(R.id.action_coordinationLayoutFragment_to_coordinationLayoutExample5Fragment)
        }
        binding.advanceButton2.setOnClickListener{
            it.findNavController().navigate(R.id.action_coordinationLayoutFragment_to_coordinationLayoutExample6Fragment)
        }
        binding.totalButton1.setOnClickListener{
            it.findNavController().navigate(R.id.action_coordinationLayoutFragment_to_coordinationLayoutExample7Fragment)
        }
    }

}