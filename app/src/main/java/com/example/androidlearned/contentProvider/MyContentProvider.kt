package com.example.androidlearned.contentProvider

import android.content.ContentProvider
import android.content.ContentUris
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.database.MatrixCursor
import android.net.Uri
import android.widget.CursorAdapter
import com.example.androidlearned.domain.People

/**
 * 自定义ContentProvider
 * @see <a href="https://developer.android.com/guide/topics/providers/content-provider-basics">官方文档</a>
 * <p>ps: 这里没有使用数据库实现，而是采用了定义在内存中的MutableList，目的是为了轻量化演示ContentProvider用法</p>
 * */
class MyContentProvider : ContentProvider() {
    private val mCachePeople:MutableList<People> = mutableListOf()
    private val mUriMatcher = UriMatcher(UriMatcher.NO_MATCH).apply {
        addURI(AUTHORITY, "cache", 1) // 匹配content://com.xtguiyi.provider/cache
        addURI(AUTHORITY, "cache/*", 2) // 匹配content://com.xtguiyi.provider/cache/1
    }

    companion object {
        const val AUTHORITY   = "com.xtguiyi.provider"
        val CONTENT_URI: Uri = Uri.parse("content://${AUTHORITY}/cache")
    }
    override fun onCreate(): Boolean {
        repeat(20) {
            mCachePeople.add(People(it, "王$it"))
        }
        return  true
    }
    override fun getType(uri: Uri): String? {
        return null
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        val code = mUriMatcher.match(uri)
        if(code == 2) {
            val id = ContentUris.parseId(uri)
            mCachePeople.add(id.toInt(), People(id.toInt(), values?.get("name").toString())) // 在指定位置，新增一行
            context?.contentResolver?.notifyChange(uri, null)
            return uri
        }else {
            return null
        }
    }
    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        val code = mUriMatcher.match(uri)
        when (code) {
            2 -> {
                val id = ContentUris.parseId(uri)
                mCachePeople.removeAt(id.toInt())
                context?.contentResolver?.notifyChange(uri, null)
                return 1
            }
            1 -> {
                val size = mCachePeople.size
                mCachePeople.clear()
                context?.contentResolver?.notifyChange(uri, null)
                return size
            }
            else -> return 0
        }
    }
    override fun query(
        uri: Uri, projection: Array<String>?, selection: String?,
        selectionArgs: Array<String>?, sortOrder: String?
    ): Cursor? {
        val code = mUriMatcher.match(uri)
        when (code) {
            2 -> {
                val id = ContentUris.parseId(uri)
                return mCachePeople[id.toInt()]?.let {
                    transformCursor(mutableListOf(it))
                }
            }
            1 -> return transformCursor(mCachePeople)
            else -> return null
        }
    }

    override fun update(
        uri: Uri, values: ContentValues?, selection: String?,
        selectionArgs: Array<String>?
    ): Int {
        val code = mUriMatcher.match(uri)
        if(code == 2) {
            val id = ContentUris.parseId(uri)
            mCachePeople[id.toInt()].name = values?.get("name").toString() // 更新指定行
            context?.contentResolver?.notifyChange(uri, null)
            return 1
        }else {
            return 0
        }
    }

    /**
     * MutableList 转 Cursor
     * */
    private fun transformCursor(aList: MutableList<People>): Cursor{
        val columnNames = arrayOf("id", "name")
        val cursor = MatrixCursor(columnNames)
        for (item in aList) {
            val row = arrayOf(item.id, item.name)
            cursor.addRow(row)
        }
        return cursor
    }
}