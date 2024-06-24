package com.example.androidlearned.ui.center.activitys

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.androidlearned.dataSource.AppDatabase
import com.example.androidlearned.dataSource.AppDatabaseSingleton
import com.example.androidlearned.databinding.ActivityRoomPracticeBinding
import com.example.androidlearned.entity.User
import com.hjq.toast.Toaster
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RoomPracticeActivity : AppCompatActivity() {
    lateinit var binding: ActivityRoomPracticeBinding
    private lateinit var db: AppDatabase
    private var amount = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRoomPracticeBinding.inflate(layoutInflater)
        db = AppDatabaseSingleton.getInstance(this)
        init()
        setContentView(binding.root)
    }

    fun init() {
        binding.roomAction1.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                db.userDao().insertAll(User(0, "张$amount", 18, 1),User(0, "李${amount}", 19, 2))
                amount++
                Toaster.show("插入成功")
            }
        }
        binding.roomAction2.setOnClickListener {
           CoroutineScope(Dispatchers.IO).launch {
               try {
                   db.userDao().delete(User(29, "张$amount", 18, 1))
                   Toaster.show("删除成功")
               } catch (e: Exception) {
                   Toaster.show("删除失败${e.message}")
               }
           }
        }
        binding.roomAction3.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                val user = db.userDao().findByName("李$amount")
                Toaster.show(user.toString())
            }
        }
        binding.roomAction4.setOnClickListener {
           CoroutineScope(Dispatchers.IO).launch {
               val flag =  db.userDao().update(User(30, "张张", 18, 1))
               Toaster.show("修改成功：$flag")
           }
        }
    }
}