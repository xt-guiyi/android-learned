package com.example.androidlearned.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidlearned.databinding.FragmentRecycleViewExample3ItemContentBinding
import com.example.androidlearned.databinding.FragmentRecycleViewExample3ItemTitleBinding
import com.example.androidlearned.domain.StickHeadInfo

class RecycleViewExample3Adapter(val data: MutableList<StickHeadInfo>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    companion object {
        const val VIEW_TYPE_TITLE = 0
        const val VIEW_TYPE_CONTENT = 1
    }
    class ViewHolderByTitle(val binding: FragmentRecycleViewExample3ItemTitleBinding):RecyclerView.ViewHolder(binding.root) {
        fun bindTo(stickHeadInfo: StickHeadInfo) {
            binding.stickTitle.text = stickHeadInfo.text
        }
    }

    class ViewHolderByContent(val binding: FragmentRecycleViewExample3ItemContentBinding):RecyclerView.ViewHolder(binding.root) {
        fun bindTo(stickHeadInfo: StickHeadInfo) {
            binding.stickContent.text = stickHeadInfo.text
        }
    }

    override fun getItemViewType(position: Int): Int {
        return data[position].type
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType) {
            VIEW_TYPE_TITLE -> {
                val binding = FragmentRecycleViewExample3ItemTitleBinding.inflate(LayoutInflater.from(parent.context),parent,false)
                ViewHolderByTitle(binding)
            }
            else -> {
                val binding = FragmentRecycleViewExample3ItemContentBinding.inflate(LayoutInflater.from(parent.context),parent,false)
                ViewHolderByContent(binding)
            }
        }
    }

    override fun getItemCount(): Int {
       return data.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {
            is ViewHolderByTitle -> holder.bindTo(data[position])
            is ViewHolderByContent -> holder.bindTo(data[position])
        }
    }
}