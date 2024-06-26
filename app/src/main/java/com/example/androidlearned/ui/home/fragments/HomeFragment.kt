package com.example.androidlearned.ui.home.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidlearned.R
import com.example.androidlearned.adapters.CardRecycleViewAdapter
import com.example.androidlearned.dataSource.HomeDataSource
import com.example.androidlearned.databinding.FragmentHomeBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeFragment : Fragment() {
    lateinit var binding:FragmentHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        Toaster.show("homeFragment")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
         binding = FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycleView()
    }


    /**
     * 初始化列表
     * */
    private fun initRecycleView() {
        binding.homeRecycleList.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
        lifecycleScope.launch(Dispatchers.IO) {
            val adapter = CardRecycleViewAdapter(HomeDataSource.loadRecycleInfoList())
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
                    17-> view.findNavController().navigate(R.id.action_homeFragment_to_bottomSheetWidgetActivity)
                    18-> view.findNavController().navigate(R.id.action_homeFragment_to_seekBarWidgetActivity)
                    19-> view.findNavController().navigate(R.id.action_homeFragment_to_badgeDrawableActivity)
                    20-> view.findNavController().navigate(R.id.action_homeFragment_to_bannerWidgetActivity)
                    21-> view.findNavController().navigate(R.id.action_homeFragment_to_tabsActivity)
                    22-> view.findNavController().navigate(R.id.action_homeFragment_to_viewPageActivity)
                    23-> view.findNavController().navigate(R.id.action_homeFragment_to_recycleViewActivity)
                    24-> view.findNavController().navigate(R.id.action_homeFragment_to_otherWidgetActivity)
                    25-> view.findNavController().navigate(R.id.action_homeFragment_to_otherWidgetActivity)
                    26 -> view.findNavController().navigate(R.id.action_homeFragment_to_coordinationLayoutActivity)

                }
            }
            withContext(Dispatchers.Main) {
                binding.homeRecycleList.adapter = adapter
            }
        }
    }

}