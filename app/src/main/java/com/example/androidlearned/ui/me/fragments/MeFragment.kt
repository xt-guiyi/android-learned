package com.example.androidlearned.ui.me.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidlearned.adapters.CardRecycleViewAdapter
import com.example.androidlearned.dataSource.MeDataSource
import com.example.androidlearned.databinding.FragmentMeBinding
import com.example.androidlearned.ui.center.activitys.AuthorizationManageActivity
import com.example.androidlearned.ui.center.activitys.NotificationManageActivity
import com.example.androidlearned.ui.center.activitys.WindowManageActivity
import com.example.androidlearned.ui.me.acticitys.AudioPracticeActivity
import com.example.androidlearned.ui.me.acticitys.BottomDialogPracticeActivity
import com.example.androidlearned.ui.me.acticitys.DownMenuPracticeActivity


class MeFragment : Fragment() {
    lateinit var binding: FragmentMeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMeBinding.inflate(inflater,container,false)
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
        binding.meRecycleList.layoutManager = LinearLayoutManager(requireContext(),
            LinearLayoutManager.VERTICAL,false)
        val adapter = CardRecycleViewAdapter(MeDataSource.loadRecycleInfoList())
        adapter.setOnClickListener { layoutInfo,_ ->
            Toast.makeText(requireContext(), "点击了：${layoutInfo.title}", Toast.LENGTH_SHORT).show()
            val intent = when(layoutInfo.id) {
                1 -> Intent(requireContext(), AudioPracticeActivity::class.java)
                2 -> Intent(requireContext(), DownMenuPracticeActivity::class.java)
                3 -> Intent(requireContext(), BottomDialogPracticeActivity::class.java)
                else -> null
            }
            intent?.let {
                startActivity(it)
            }
        }
        binding.meRecycleList.adapter = adapter
    }

}