package com.example.androidlearned.dataSource

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.androidlearned.dao.UserDao
import com.example.androidlearned.entity.User

@Database(entities = [User::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}

object AppDatabaseSingleton {
    @Volatile private var instance: AppDatabase? = null

    fun getInstance(context: Context): AppDatabase {
        return instance ?: synchronized(this) {
            instance ?: Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java, "db"
            ).build().also { instance = it }
        }
    }
}
