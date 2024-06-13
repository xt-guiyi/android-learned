package com.example.androidlearned.ui.center.activitys

import android.os.Bundle
import android.os.Environment
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import com.example.androidlearned.databinding.ActivityLocalStorageBinding
import com.hjq.toast.ToastStrategy
import com.hjq.toast.Toaster
import java.io.File

class LocalStorageActivity : AppCompatActivity() {
    lateinit var binding: ActivityLocalStorageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLocalStorageBinding.inflate(layoutInflater)
        appStorageByProprietary() // 使用(应用专属文件数据)进行数据管理
        appSharedPreferences() // 使用(SharedPreferences)进行数据管理
        setContentView(binding.root)
    }

    private fun appStorageByProprietary() {
        // 访问持久性文件,不存在就创建一个
        binding.internalStorage0.setOnClickListener {
            val file = File(filesDir, "text.txt") // 访问文件
            Toaster.setStrategy(ToastStrategy(ToastStrategy.SHOW_STRATEGY_TYPE_QUEUE));
            if (!file.exists()) {
                file.createNewFile()
                Toaster.show("文件不存在，创建文件")
            }
            Toaster.show("val file = File(filesDir, \"text.txt\")")
        }
        // 使用信息流存储文件
        binding.internalStorage1.setOnClickListener {
            val fileContents = "Hello world!"
            val file = File(filesDir, "text.txt")
            file.outputStream().bufferedWriter().use {
                it.write(fileContents)
            }
            Toaster.show("写入成功")
        }
        // 使用信息流访问文件
        binding.internalStorage2.setOnClickListener {
            val file = File(filesDir, "text.txt")
            val fileContents = file.inputStream().bufferedReader().use {
                it.readText()
            }
            Toaster.show("读取成功，内容为：$fileContents")
        }
        // 查看文件列表
        binding.internalStorage3.setOnClickListener {
            // 这几种都可以
            val files1 = fileList()
            val files2 = filesDir.list()
//            val files3 = filesDir.listFiles() // 这个是返回文件对象的数组
            Toaster.setStrategy(ToastStrategy(ToastStrategy.SHOW_STRATEGY_TYPE_QUEUE));
            files1.forEach {
                Toaster.show("files1：$it")
            }
            files2?.forEach {
                Toaster.show("files2：$it")
            }
        }
        // 创建目录
        binding.internalStorage4.setOnClickListener {
            // 第一种
            getDir("customDir", MODE_PRIVATE)
            // 第二种
            val file = File(filesDir, "customDir")
            file.mkdirs()
            Toaster.show("创建成功")
        }
        // 创建缓存文件
        binding.internalStorage5.setOnClickListener {
            val externalCacheFile = File(cacheDir, "test.txt")
            if (!externalCacheFile.exists()) {
                externalCacheFile.createNewFile()
                Toaster.show("缓存文件不存在，创建文件")
            }
//            File.createTempFile("temp", ".txt", cacheDir) // 这种也可以，但是有时间戳，上面的不会有，会导致手动删除比较麻烦
            Toaster.show("val externalCacheFile = File(cacheDir, \"test.txt\")")
        }
        // 删除缓存文件
        binding.internalStorage6.setOnClickListener {
            val cacheFile = File(cacheDir, "test.txt")
            cacheFile.delete()
           // cacheFile.deleteRecursively() //删除此文件及其所有子文件。请注意，如果此操作失败，则可能发生部分删除。
            Toaster.show("移除成功")
        }
        // 验证外部存储空间的可用性
        binding.externalStorage1.setOnClickListener{
            val status = Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED
            Toaster.show("可用状态$status")
        }
        // 访问持久性文件,不存在就创建一个
        binding.externalStorage2.setOnClickListener{
            val file = File(getExternalFilesDir(null), "test.text")
            Toaster.setStrategy(ToastStrategy(ToastStrategy.SHOW_STRATEGY_TYPE_QUEUE));
            if (!file.exists()) {
                file.createNewFile()
                Toaster.show("文件不存在，创建文件")
            }
            Toaster.show("val file = File(getExternalFilesDir(null), \"test.text\")")
        }
        // 创建缓存文件
        binding.externalStorage3.setOnClickListener{
            val externalCacheFile = File(externalCacheDir, "test.text")
            if (!externalCacheFile.exists()) {
                externalCacheFile.createNewFile()
//                File.createTempFile("test", ".txt", cacheDir) // 这种也可以，但是有时间戳，上面的不会有
                Toaster.show("缓存文件不存在，创建文件")
            }
            Toaster.show("val externalCacheFile = File(externalCacheDir, \"test.text\")")
        }
        // 移除缓存文件
        binding.externalStorage4.setOnClickListener{
            val externalCacheFile = File(externalCacheDir, "test.text")
            externalCacheFile.delete()
            Toaster.show("移除成功")
        }
    }

    private fun appSharedPreferences() {
        // 获取共享偏好设置的句柄
        binding.spStorage1.setOnClickListener {
            // 这种是根据global_key获取sharedPreferences，可以全局共享
            val sharedPref = getSharedPreferences("global_key", MODE_PRIVATE)
            // 这种是获取 activity级别的sharedPreferences，没办法全局共享
            val pref = getPreferences(MODE_PRIVATE)
            Toaster.show("获取成功")
        }
        // 写入共享偏好设置
        binding.spStorage2.setOnClickListener {
            val sharedPref = getSharedPreferences("global_key", MODE_PRIVATE)
            sharedPref.edit {
                putString("name", "张三")
                putInt("age", 18)
                putBoolean("isMarried", false)
                Toaster.show("写入成功")
            }
        }
        // 读取共享偏好设置
        binding.spStorage3.setOnClickListener {
            val sharedPref = getSharedPreferences("global_key", MODE_PRIVATE)
            val name = sharedPref.getString("name", "")
            val age = sharedPref.getInt("age", 0)
            val isMarried = sharedPref.getBoolean("isMarried", false)
            Toaster.show("name:$name, age:$age, isMarried:$isMarried")
        }
    }

}