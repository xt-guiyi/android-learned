package com.example.androidlearned.ui.home.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.androidlearned.R
import com.example.androidlearned.databinding.FragmentRecycleViewBinding


class RecycleViewFragment : Fragment() {
    lateinit var binding: FragmentRecycleViewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRecycleViewBinding.inflate(inflater, container, false)
        init()
        return binding.root
    }

    private fun init() {
        binding.recycleViewExample1.setOnClickListener {
            it.findNavController().navigate(R.id.action_recycleViewFragment_to_recycleViewExample1Fragment)
        }
        binding.recycleViewExample2.setOnClickListener{
            it.findNavController().navigate(R.id.action_recycleViewFragment_to_recycleViewExample2Fragment)
        }
        binding.recycleViewExample3.setOnClickListener{
            it.findNavController().navigate(R.id.action_recycleViewFragment_to_recycleViewExample3Fragment)
        }
        binding.recycleViewExample4.setOnClickListener{
            it.findNavController().navigate(R.id.action_recycleViewFragment_to_recycleViewExample4Fragment)
        }
        binding.recycleViewExample5.setOnClickListener{
            it.findNavController().navigate(R.id.action_recycleViewFragment_to_recycleViewExample5Fragment)
        }

        binding.recycleViewExample6.setOnClickListener{
            it.findNavController().navigate(R.id.action_recycleViewFragment_to_recycleViewExample6Fragment)
        }

        binding.recycleViewExample7.setOnClickListener{
            it.findNavController().navigate(R.id.action_recycleViewFragment_to_recycleViewExample7Fragment)
        }
    }

}