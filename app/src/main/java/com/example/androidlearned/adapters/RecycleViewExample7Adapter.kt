package com.example.androidlearned.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.androidlearned.databinding.FragmentRecycleViewExample1ItemBinding
import com.example.androidlearned.entity.Article


class RecycleViewExample7Adapter(private val diffCallback: DiffUtil.ItemCallback<Article>) : PagingDataAdapter<Article, RecycleViewExample7Adapter.ViewHolder>(diffCallback) {
    class ViewHolder(val binding: FragmentRecycleViewExample1ItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindTo(article: Article?) {
            article?.let {
                binding.listItemContent.text = it.title
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       val binding = FragmentRecycleViewExample1ItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val article = getItem(position)
        holder.bindTo(article)
    }
}