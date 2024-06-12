package com.example.androidlearned.ui.center.activitys

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.androidlearned.databinding.ActivityFragPracticeBinding
import com.example.androidlearned.ui.center.fragments.FragPracticeChild1Fragment

/**
 * Fragment 使用
 * @see <a href="https://developer.android.com/guide/fragments?hl=zh-cn">官方文档</a>
 * */
class FragPracticeActivity : AppCompatActivity() {
    lateinit var binding: ActivityFragPracticeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityFragPracticeBinding.inflate(layoutInflater)
        if(savedInstanceState == null) {
            val ft =  FragPracticeChild1Fragment.newInstance("1", "Hello")
            supportFragmentManager.beginTransaction()
                .setReorderingAllowed(true)
                .add(binding.fragmentContainerViewFrag.id,ft,"child_1")
                .setPrimaryNavigationFragment(ft) // 该 fragment 的 childFragmentManager 具有主要控制权。用于同层级添加两个fragment. ps：这里只有一个
//                .addToBackStack("child1") 此处不应保存到返回堆栈
                .commit()
        }
        setContentView(binding.root)
    }
}