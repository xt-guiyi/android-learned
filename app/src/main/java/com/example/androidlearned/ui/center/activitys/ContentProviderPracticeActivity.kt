package com.example.androidlearned.ui.center.activitys

import android.annotation.SuppressLint
import android.content.ContentUris
import android.content.ContentValues
import android.content.pm.PackageManager
import android.database.ContentObserver
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract
import android.provider.ContactsContract.CommonDataKinds.Phone
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.androidlearned.contentProvider.MyContentProvider
import com.example.androidlearned.databinding.ActivityContentProviderPracticeBinding
import com.example.androidlearned.domain.People
import com.hjq.toast.Toaster


/**
 * 内容提供者
 *  @see <a href="https://developer.android.com/guide/topics/providers/content-provider-basics">官方文档</a>
 *  @see <a href="https://carsonho.blog.csdn.net/article/details/76101093">参考文档</a>
 * <p>主要用来帮助应用管理对自身存储或由其他应用存储的数据的访问，并提供与其他应用共享数据的方法。ps:这个功能，其实主要是被用来访问其他应用提供的数据，应用自身数据直接获取就好了，不需要在套一个内容提供者，除非你有多个应用，并且需要多应用共享数据</p>
 * */
class ContentProviderPracticeActivity : AppCompatActivity() {
    lateinit var binding: ActivityContentProviderPracticeBinding
    // 获取联系人权限
    private val requestContactPermission = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ){
        if(it != null) {
            Toaster.show("拿到权限")
            getAllContact()
        }else {
            Toaster.show("没有权限")
        }
    }

    private val ss = registerForActivityResult(
        ActivityResultContracts.PickContact()
    ){
        if(it != null) {
            Log.i("Toaster", it.toString())
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContentProviderPracticeBinding.inflate(layoutInflater)
        // 当ContentProvider 中的数据发生变化（增、删 & 改）时，就会触发该 ContentObserver类
        contentResolver.registerContentObserver(MyContentProvider.CONTENT_URI,true, object: ContentObserver(null) {
            override fun onChange(selfChange: Boolean, uri: Uri?) {
                super.onChange(selfChange, uri)
                Log.i("Toaster", "数据改变")
            }
        })
        customAction1()
        customAction2()
        setContentView(binding.root)
    }

    @SuppressLint("Range")
    private fun customAction1() {
        binding.cp1.setOnClickListener {
            val values = ContentValues().apply {
                put("name","李四")
            }
            val nUri = ContentUris.withAppendedId(MyContentProvider.CONTENT_URI,1)
            val rValue = contentResolver.insert(nUri, values)
            if(rValue != null) Toaster.show("添加成功")
        }

        binding.cp2.setOnClickListener{
            val nUri = ContentUris.withAppendedId(MyContentProvider.CONTENT_URI,5)
            val rValue =  contentResolver.delete(nUri,null,null) // where, selectionArgs，现在都用不上，因为查询的不是真正的数据库
            if(rValue != 0) Toaster.show("删除成功")

        }

        binding.cp3.setOnClickListener {
            val nUri = MyContentProvider.CONTENT_URI // 这里查全部
            // 这里的一些参数也是都用不上，因为查询的不是真正的数据库,
            contentResolver.query(nUri,null,null,null,null)?.use {
                val list = mutableListOf<People>()
                it.moveToFirst()
                while (it.moveToNext()) {
                    val name = it.getString(it.getColumnIndex("name"))
                    val id = it.getInt(it.getColumnIndex("id"))
                    list.add(People(id,name))
                }
                list.forEach { p ->
                    Log.i("List",p.toString())
                }
            }
        }

        binding.cp4.setOnClickListener {
            val nUri = ContentUris.withAppendedId(MyContentProvider.CONTENT_URI,5)
            val nValue = ContentValues().apply {
                put("name","小星星滚呀滚")
            }
            val rValue = contentResolver.update(nUri,nValue,null,null)
            if(rValue != 0) Toaster.show("修改成功")
        }
    }

    private fun customAction2() {
        binding.cp5.setOnClickListener{
            if( checkSelfPermission(android.Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {
                getAllContact()
            }else {
                requestContactPermission.launch(android.Manifest.permission.READ_CONTACTS)
            }
        }
    }

    @SuppressLint("Range")
    fun getAllContact() {
        // 投影数组，包含要查找的列名
        val mProjection: Array<String> = arrayOf(
            ContactsContract.Data._ID, // 联系人ID,
            ContactsContract.Data.DISPLAY_NAME, // 联系人姓名
            ContactsContract.Data.MIMETYPE,
            Phone.NUMBER, // 手机号码
            )
        val selection = "${ContactsContract.Data.MIMETYPE} = ?"
        val selectArg = arrayOf(Phone.CONTENT_ITEM_TYPE)
        val sortOrder = "${ContactsContract.Contacts.CONTACT_LAST_UPDATED_TIMESTAMP} ASC"
        contentResolver.query(
            ContactsContract.Data.CONTENT_URI,
            mProjection,
            selection,
            selectArg ,
            sortOrder
        ).use {
            if (it != null) {
                it.moveToFirst()
                while (it.moveToNext()){
                    val id = it.getInt(it.getColumnIndex(ContactsContract.Data._ID))
                    val name = it.getString(it.getColumnIndex(ContactsContract.Data.DISPLAY_NAME))
                    val number = it.getString(it.getColumnIndex(Phone.NUMBER))
                    val miType = it.getString(it.getColumnIndex(ContactsContract.Data.MIMETYPE))
                    Log.i("List","id:$id 姓名:$name 电话号码：$number 类型：$miType ")
                    Toaster.show("id:$id 姓名:$name 电话号码：$number 类型：$miType ")
                }
            }

        }
    }
}