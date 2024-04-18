package com.example.androidlearned.ui.home.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidlearned.R
import com.example.androidlearned.adapters.HomeFragmentAdapter
import com.example.androidlearned.dataSource.HomeDataSource
import com.example.androidlearned.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("test","homeFragment")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentHomeBinding.inflate(inflater,container,false)
        initRecycleView(binding)
        return binding.root
    }


    /**
     * 初始化列表
     * */
    private fun initRecycleView(binding: FragmentHomeBinding) {
        binding.homeRecycleList.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
        val adapter = HomeFragmentAdapter(HomeDataSource.loadRecycleInfoList())
        adapter.setOnClickListener { layoutInfo,view ->
            Toast.makeText(requireContext(), "点击了：${layoutInfo.title}", Toast.LENGTH_SHORT).show()
            when(layoutInfo.id) {
                1 -> view.findNavController().navigate(R.id.action_homeFragment_to_buttonWidgetActivity)
                2 -> view.findNavController().navigate(R.id.action_homeFragment_to_textViewWidgetActivity)
                3 -> view.findNavController().navigate(R.id.action_homeFragment_to_imageViewWidgetActivity)
                4 -> view.findNavController().navigate(R.id.action_homeFragment_to_editTextWidgetActivity)
                5 -> view.findNavController().navigate(R.id.action_homeFragment_to_radioButtonWidgetActivity)
                6 -> view.findNavController().navigate(R.id.action_homeFragment_to_checkBoxWidgetActivity)
                7 -> view.findNavController().navigate(R.id.action_homeFragment_to_switchWidgetActivity)
                8 -> view.findNavController().navigate(R.id.action_homeFragment_to_chipsWidgetActivity)
                9 -> view.findNavController().navigate(R.id.action_homeFragment_to_fabWidgetActivity)
                10 -> view.findNavController().navigate(R.id.action_homeFragment_to_navigationWidgetActivity)
                11 -> view.findNavController().navigate(R.id.action_homeFragment_to_navigationDrawActivity)
                12 -> view.findNavController().navigate(R.id.action_homeFragment_to_menusWidgetActivity)
                13 -> view.findNavController().navigate(R.id.action_homeFragment_to_progressIndicatorsWidgetActivity)
                14-> view.findNavController().navigate(R.id.action_homeFragment_to_snackbarWidgetActivity)
                15-> view.findNavController().navigate(R.id.action_homeFragment_to_toastActivity)
                16-> view.findNavController().navigate(R.id.action_homeFragment_to_dialogActivity)
                18-> view.findNavController().navigate(R.id.action_homeFragment_to_badgeDrawableActivity)
                23 -> view.findNavController().navigate(R.id.action_homeFragment_to_coordinationLayoutActivity)

            }
        }
        binding.homeRecycleList.adapter = adapter
    }

}