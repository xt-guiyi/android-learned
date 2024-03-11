package com.example.androidlearned.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.androidlearned.domain.HomeLayoutInfo
import com.example.androidlearned.R

class HomeFragmentAdapter(val homeLayoutInfoList: MutableList<HomeLayoutInfo>): RecyclerView.Adapter<HomeFragmentAdapter.ViewHolder> ()
{
//    val homeLayoutInfoList = homeLayoutInfoList
    init {
        Log.i("test",homeLayoutInfoList[0].title)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        Log.i("test","onCreateViewHolder")

        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_home_recycle_item,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text  = homeLayoutInfoList[position].title
        holder.description.text = homeLayoutInfoList[position].description
    }

    override fun getItemCount(): Int {
        return homeLayoutInfoList.size
    }

    class ViewHolder(view :View ): RecyclerView.ViewHolder(view){
         val title: TextView
         val description: TextView
        init {
            title = view.findViewById(R.id.title)
            description = view.findViewById(R.id.description)
        }
    }
}