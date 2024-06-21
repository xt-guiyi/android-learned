package com.example.androidlearned.ui.center.activitys

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.example.androidlearned.dataSource.AppDatabase
import com.example.androidlearned.dataSource.AppDatabaseSingleton
import com.example.androidlearned.databinding.ActivityRoomPracticeBinding
import com.example.androidlearned.entity.User

class RoomPracticeActivity : AppCompatActivity() {
    lateinit var binding: ActivityRoomPracticeBinding
    private lateinit var db: AppDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRoomPracticeBinding.inflate(layoutInflater)
//        db = AppDatabaseSingleton.getInstance(this)
        db = Room.databaseBuilder(
            this.applicationContext,
            AppDatabase::class.java, "db"
        ).build()
        init()
        setContentView(binding.root)
    }

    fun init() {
        binding.roomAction1.setOnClickListener {
            db.userDao().insertAll(User(0, "张三", 18, 1),User(0, "李四", 19, 2))
        }
        binding.roomAction2.setOnClickListener {
            db.userDao().delete(User(0, "张三", 18, 1))
        }
        binding.roomAction3.setOnClickListener {
            db.userDao().findByName("李四")
        }
        binding.roomAction4.setOnClickListener {
            db.userDao().getAll()
        }
    }
}