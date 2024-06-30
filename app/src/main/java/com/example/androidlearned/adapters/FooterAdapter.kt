package com.example.androidlearned.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.androidlearned.databinding.FooterItemBinding

class FooterAdapter(private val retry: () -> Unit) : LoadStateAdapter<FooterAdapter.ViewHolder>() {

    class ViewHolder(val binding: FooterItemBinding, private val retry: () -> Unit): RecyclerView.ViewHolder(binding.root) {
        fun bingTo(loadState: LoadState) {
            binding.progressBar.isVisible = loadState is LoadState.Loading
            binding.retryAction.isVisible = loadState is LoadState.Error
            binding.retryAction.setOnClickListener {
                retry.invoke()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): ViewHolder {
        val binding = FooterItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding, retry)
    }

    override fun onBindViewHolder(holder: ViewHolder, loadState: LoadState) {
        holder.bingTo(loadState)
    }

}