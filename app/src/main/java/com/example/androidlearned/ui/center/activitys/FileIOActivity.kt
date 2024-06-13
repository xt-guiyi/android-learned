package com.example.androidlearned.ui.center.activitys

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.androidlearned.databinding.ActivityFileIoActivityBinding
import com.hjq.toast.Toaster
import java.io.BufferedInputStream
import java.io.BufferedOutputStream
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.InputStreamReader
import java.io.OutputStreamWriter

class FileIOActivity : AppCompatActivity() {
    lateinit var binding: ActivityFileIoActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFileIoActivityBinding.inflate(layoutInflater)
        initFileIOActions1()
        initFileIOActions2()
        setContentView(binding.root)

    }

    /**
     * 传统写法，代码量较大，用来学习参考
     * */
    private fun initFileIOActions1() {
        // 字节流读取
        binding.io1.setOnClickListener {
            val file = File(filesDir, "test.txt")
            if (!file.exists()) {
                Toaster.show("文件不存在")
                return@setOnClickListener
            }
            val fis = FileInputStream(file)
            val bis = BufferedInputStream(fis)
            val bytes = ByteArray(512)
            while (bis.read(bytes) != -1) {
                Log.i("Toaster", String(bytes))
            }
            bis.close()
            fis.close()
            Toaster.show("读取成功，File路径为: ${file.canonicalPath}")
        }
        binding.io2.setOnClickListener {
            val file = File(filesDir, "test.txt")
            val fos = FileOutputStream(file) // 以覆盖模式打开
//            val fos = FileOutputStream(file,true) // 以追加模式打开
            val bos = BufferedOutputStream(fos)
            bos.write("你好".toByteArray())
            bos.close()
            fos.close()
            Toaster.show("写入成功，File路径为${file.canonicalPath}")
        }
        binding.io3.setOnClickListener {
            val file = File(filesDir, "test.txt")
            val copyFile = File(filesDir, "test_copy.txt")
            val fis = FileInputStream(file)
            val fos = FileOutputStream(copyFile)
            val bis = BufferedInputStream(fis)
            val bos = BufferedOutputStream(fos)
            val bytes = ByteArray(512)
            var length = -1
            while (bis.read(bytes).also { length = it } != -1) {
                bos.write(bytes, 0 ,length)
            }
            bos.close()
            fos.close()
            bis.close()
            fis.close()
            Toaster.show("拷贝成功，File路径为${copyFile.canonicalPath}")
        }
        binding.io4.setOnClickListener {
            val file = File(filesDir, "test.txt")
            if (!file.exists()) {
                Toaster.show("文件不存在")
                return@setOnClickListener
            }
            val fis = FileInputStream(file) // 字节流
            val isr = InputStreamReader(fis) // 转为字符流
            val br = BufferedReader(isr)

            var line = br.readLine() // 可直接读取字符串
            while (line != null) {
                Log.i("Toaster", line)
                line = br.readLine()
            }
            br.close()
            isr.close()
            fis.close()
            Toaster.show("读取成功，File路径为${file.canonicalPath}")
        }
        binding.io5.setOnClickListener {
            val file = File(filesDir, "test.txt")
            val fos = FileOutputStream(file) // 以覆盖模式打开
            val osw = OutputStreamWriter(fos)
            val bw = BufferedWriter(osw)

            bw.write("哈哈哈看🤞") // 可直接写入字符串
            bw.close()
            osw.close()
            fos.close()
            Toaster.show("写入成功，File路径为${file.canonicalPath}")
        }
        binding.io6.setOnClickListener {
            val file = File(filesDir, "test.txt")
            val copyFile = File(filesDir, "test_copy.txt")
            val fis = FileInputStream(file)
            val isr = InputStreamReader(fis)
            val br = BufferedReader(isr)
            val fos = FileOutputStream(copyFile)
            val osw = OutputStreamWriter(fos)
            val bw = BufferedWriter(osw)

            var line = br.readLine()
            while (line != null) {
                bw.write(line)
                Log.i("Toaster",line)
                bw.newLine()
                line = br.readLine()
            }
            bw.close()
            osw.close()
            fos.close()
            br.close()
            isr.close()
            fis.close()
        }
    }
    /**
     * kotlin写法，代码量少，推荐用这种
     * */
    private fun initFileIOActions2() {
        // 字节流读取
        binding.kIo1.setOnClickListener {
            val file = File(filesDir, "test.txt")
            if (!file.exists()) {
                Toaster.show("文件不存在")
                return@setOnClickListener
            }
            val bytes = file.readBytes() // 返回文件的全部字节数据，不建议在2g以上大文件上使用
            Log.i("Toaster",String(bytes))
//            file.inputStream().buffered().use { bis ->
//                val bytes = ByteArray(512)
//                while (bis.read(bytes) != -1) {
//                    Log.i("Toaster", String(bytes))
//                }
//            } // 大文件需要用流一点一点读取
            Toaster.show("读取成功，File路径为: ${file.canonicalPath}")
        }
        binding.kIo2.setOnClickListener {
            val file = File(filesDir, "test.txt")
            file.writeBytes("你好".toByteArray())
            file.appendBytes("哈哈哈".toByteArray())
//            file.outputStream().buffered().use { bos ->
//                bos.write("你好".toByteArray())
//            } // 一点一点写入
            Toaster.show("写入成功，File路径为${file.canonicalPath}")
        }
        binding.kIo3.setOnClickListener {
            val file = File(filesDir, "test.txt")
            val copyFile = File(filesDir, "test_copy.txt")
            file.copyTo(copyFile,true)
            Toaster.show("拷贝成功，File路径为${copyFile.canonicalPath}")
        }
        binding.kIo4.setOnClickListener {
            val file = File(filesDir, "test.txt")
            if (!file.exists()) {
                Toaster.show("文件不存在")
                return@setOnClickListener
            }
//            file.readLines() // 返回 List<String>,文件的全部行数据，不建议在大文件上使用,会导致list非常大
            val text = file.readText() // 返回string，文件的全部内容,不建议在2g以上大文件上使用
            Log.i("Toaster", text)
//            file.bufferedReader().use {
//                var line = it.readLine()
//                while (line != null) {
//                    Log.i("Toaster", line)
//                    line = it.readLine()
//                }
//            } // 大文件用流一点的一点读取
            Toaster.show("读取成功，File路径为${file.canonicalPath}")
        }
        binding.kIo5.setOnClickListener {
            val file = File(filesDir, "test.txt")
           file.writeText("你好")
            file.appendText("哈哈哈哈")
//            file.bufferedWriter().use { bos ->
//                bos.write("你好")
//            }
            Toaster.show("写入成功，File路径为${file.canonicalPath}")
        }
        binding.kIo6.setOnClickListener {
            val file = File(filesDir, "test.txt")
            val copyFile = File(filesDir, "test_copy.txt")
            file.copyTo(copyFile,true,1024)
            Toaster.show("拷贝成功，File路径为${copyFile.canonicalPath}")
        }
    }
}