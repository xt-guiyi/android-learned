package com.example.androidlearned.ui.center.activitys

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.androidlearned.api.ApiConfig
import com.example.androidlearned.api.MokeAPi
import com.example.androidlearned.databinding.ActivityNetworkPracticeBinding
import com.example.androidlearned.utils.OkHttpUtil
import com.hjq.toast.Toaster
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NetworkPracticeActivity : AppCompatActivity() {
    lateinit var binding: ActivityNetworkPracticeBinding
    val apiClent: MokeAPi = ApiConfig.retrofit.create(MokeAPi::class.java)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNetworkPracticeBinding.inflate(layoutInflater)
        init()
        setContentView(binding.root)
    }

    fun init() {
        binding.net1.setOnClickListener {
           lifecycleScope.launch(Dispatchers.IO) {
               val result = OkHttpUtil.get("https://www.wanandroid.com/hotkey/json")
               Toaster.show(result)
           }
        }


        binding.net2.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {
                try {
                    val result =  apiClent.getSay("不填也可以")
                    Toaster.show(result.data[0])
                }catch (e:Exception){
                    e.printStackTrace()
                }
            }
        }
    }
}