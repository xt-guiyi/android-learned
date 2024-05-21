package com.example.androidlearned.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.androidlearned.databinding.FragmentRecycleViewExample4ItemBinding
import com.example.androidlearned.domain.People
import com.hjq.toast.Toaster

class RecycleViewExample4Adapter(private val config: AsyncDifferConfig<People>) : ListAdapter<People, RecycleViewExample4Adapter.ViewHolder>(config) {
    private var mClick:  ((position: Int, check: Boolean) -> Unit)? = null
    private var multipleMode = false
    class ViewHolder(val binding: FragmentRecycleViewExample4ItemBinding) :RecyclerView.ViewHolder(binding.root) {
        fun bindTo(people: People) {
            binding.multiplyContent.text = people.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = FragmentRecycleViewExample4ItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int ,payload:List<Any>) {
        Log.i("onBindViewHolder","${payload.toString()}-$position-${payload.isNotEmpty()}")
        val client = getItem(position)
        holder.bindTo(client)
        if(multipleMode) {
            holder.binding.multiplyCheckbox.visibility = View.VISIBLE
            holder.binding.multiplyContent.isSelected = client.isSelected
            holder.binding.multiplyCheckbox.isChecked = client.isSelected
            holder.binding.root.setOnClickListener { _ ->
                client.isSelected = !client.isSelected
                holder.binding.multiplyCheckbox.isChecked = client.isSelected
                holder.binding.multiplyContent.isSelected = client.isSelected
                mClick?.invoke(position, holder.binding.multiplyCheckbox.isChecked)
            }
            // 用setOnClickListener，设置isChecked就不会触发这个事件
            holder.binding.multiplyCheckbox.setOnClickListener { _ ->
                client.isSelected = !client.isSelected
                holder.binding.multiplyCheckbox.isChecked = client.isSelected
                holder.binding.multiplyContent.isSelected = client.isSelected
                mClick?.invoke(position, holder.binding.multiplyCheckbox.isChecked)
            }
        }else {
            holder.binding.multiplyCheckbox.visibility = View.GONE
            holder.binding.multiplyContent.isSelected = false
            holder.binding.multiplyCheckbox.isChecked = false
        }
    }

    fun setClickListener(callback:(position: Int, check: Boolean) -> Unit) {
        mClick = callback
    }

    fun setMultipleMode(start: Boolean) {
        multipleMode = start
    }
}