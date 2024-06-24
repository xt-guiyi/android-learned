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
import com.example.androidlearned.dataSource.CenterDataSource
import com.example.androidlearned.databinding.FragmentCenterBinding
import com.example.androidlearned.ui.center.activitys.AcPracticeActivity
import com.example.androidlearned.ui.center.activitys.AnimationPracticeActivity
import com.example.androidlearned.ui.center.activitys.AsynchronousPracticeActivity
import com.example.androidlearned.ui.center.activitys.AuthorizationManageActivity
import com.example.androidlearned.ui.center.activitys.BroadcastPracticeActivity
import com.example.androidlearned.ui.center.activitys.ContentProviderPracticeActivity
import com.example.androidlearned.ui.center.activitys.CustomViewPracticeActivity
import com.example.androidlearned.ui.center.activitys.FileIOActivity
import com.example.androidlearned.ui.center.activitys.FragPracticeActivity
import com.example.androidlearned.ui.center.activitys.GesturePracticeActivity
import com.example.androidlearned.ui.center.activitys.GlidePracticeActivity
import com.example.androidlearned.ui.center.activitys.LocalStorageActivity
import com.example.androidlearned.ui.center.activitys.NetworkPracticeActivity
import com.example.androidlearned.ui.center.activitys.NotificationManageActivity
import com.example.androidlearned.ui.center.activitys.RoomPracticeActivity
import com.example.androidlearned.ui.center.activitys.ServicePracticeActivity
import com.example.androidlearned.ui.center.activitys.SoftKeyboardActivity
import com.example.androidlearned.ui.center.activitys.SystemBarManageActivity
import com.example.androidlearned.ui.center.activitys.TouchPracticeActivity
import com.example.androidlearned.ui.center.activitys.WindowManageActivity
import com.example.androidlearned.ui.center.activitys.WorkManagerPracticeActivity


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
        val adapter = CardRecycleViewAdapter(CenterDataSource.loadRecycleInfoList())
        adapter.setOnClickListener { layoutInfo,_ ->
            Toast.makeText(requireContext(), "点击了：${layoutInfo.title}", Toast.LENGTH_SHORT).show()
            val intent = when(layoutInfo.id) {
                1 -> Intent(requireContext(), SystemBarManageActivity::class.java)
                2 -> Intent(requireContext(), NotificationManageActivity::class.java)
                3 -> Intent(requireContext(),WindowManageActivity::class.java)
                4 -> Intent(requireContext(), AuthorizationManageActivity::class.java)
                5 -> Intent(requireContext(), SoftKeyboardActivity::class.java)
                6 -> Intent(requireContext(), AcPracticeActivity::class.java)
                7 -> Intent(requireContext(), FragPracticeActivity::class.java)
                8 -> Intent(requireContext(), ServicePracticeActivity::class.java)
                9 -> Intent(requireContext(), BroadcastPracticeActivity::class.java)
                10 -> Intent(requireContext(), ContentProviderPracticeActivity::class.java)
                11 -> Intent(requireContext(), LocalStorageActivity::class.java)
                12 -> Intent(requireContext(), FileIOActivity::class.java)
                13 -> Intent(requireContext(), AsynchronousPracticeActivity::class.java)
                14 -> Intent(requireContext(), TouchPracticeActivity::class.java)
                15 -> Intent(requireContext(), GesturePracticeActivity::class.java)
                16 -> Intent(requireContext(), CustomViewPracticeActivity::class.java)
                17 -> Intent(requireContext(), AnimationPracticeActivity::class.java)
                18 -> Intent(requireContext(), AnimationPracticeActivity::class.java)
                19 -> Intent(requireContext(), WorkManagerPracticeActivity::class.java)
                20 -> Intent(requireContext(), RoomPracticeActivity::class.java)
                21 -> Intent(requireContext(), GlidePracticeActivity::class.java)
                22 -> Intent(requireContext(), NetworkPracticeActivity::class.java)
                else -> null
            }
            intent?.let {
                startActivity(it)
            }
        }
        binding.centerRecycleList.adapter = adapter
    }


}