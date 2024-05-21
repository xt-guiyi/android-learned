package com.example.androidlearned.adapters

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.androidlearned.R
import com.example.androidlearned.databinding.FragmentRecycleViewExample2ItemBinding
import com.example.androidlearned.domain.People

// ListAdapter集成了DiffUtil和 AsyncListDiffer，实现更简单，但相对自定义程度没有那么高
class RecycleViewExample2Adapter(diffCallback: DiffUtil.ItemCallback<People>) : ListAdapter<People,RecycleViewExample2Adapter.ViewHolder>(diffCallback){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = FragmentRecycleViewExample2ItemBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindTo(getItem(position))
    }

    class ViewHolder(val binding: FragmentRecycleViewExample2ItemBinding) : RecyclerView.ViewHolder(binding.root) {
       @SuppressLint("SetTextI18n")
       fun bindTo(people: People) {
            binding.listItemId.text = "id: ${people.id}"
            binding.listItemContent.text = "姓名：${people.name}"
       }
    }


}