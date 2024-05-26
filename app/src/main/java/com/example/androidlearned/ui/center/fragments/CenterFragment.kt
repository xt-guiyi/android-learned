package com.example.androidlearned.ui.center.fragments
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidlearned.adapters.CardRecycleViewAdapter
import com.example.androidlearned.dataSource.HomeDataSource
import com.example.androidlearned.databinding.FragmentCenterBinding
import com.example.androidlearned.ui.center.activitys.SystemBarActivity


class CenterFragment : Fragment() {
    lateinit var binding: FragmentCenterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCenterBinding.inflate(inflater, container, false)
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
        binding.centerRecycleList.layoutManager = LinearLayoutManager(requireContext(),
            LinearLayoutManager.VERTICAL,false)
        val adapter = CardRecycleViewAdapter(HomeDataSource.loadCenterRecycleInfoList())
        adapter.setOnClickListener { layoutInfo,view ->
            Toast.makeText(requireContext(), "点击了：${layoutInfo.title}", Toast.LENGTH_SHORT).show()
            val intent =  when(layoutInfo.id) {
                1 -> Intent(requireContext(),SystemBarActivity::class.java)
                else -> null
            }
            intent?.let {
                startActivity(it)
            }
        }
        binding.centerRecycleList.adapter = adapter
    }


}