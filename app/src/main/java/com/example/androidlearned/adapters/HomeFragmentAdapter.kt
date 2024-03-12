package com.example.androidlearned.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.androidlearned.domain.HomeLayoutInfo
import com.example.androidlearned.R

class HomeFragmentAdapter(val homeLayoutInfoList: MutableList<HomeLayoutInfo>): RecyclerView.Adapter<HomeFragmentAdapter.ViewHolder> ()
{

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        Log.i("test","onCreateViewHolder")

        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_home_recycle_item,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text  = homeLayoutInfoList[position].title
        holder.description.text = homeLayoutInfoList[position].description
        holder.itemView.setOnClickListener {
            Toast.makeText(it.context, "标题为:${homeLayoutInfoList[position].title}", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int {
        return homeLayoutInfoList.size
    }

    class ViewHolder(itemView :View ): RecyclerView.ViewHolder(itemView){
         val title: TextView
         val description: TextView
        init {
            title = itemView.findViewById(R.id.title)
            description = itemView.findViewById(R.id.description)
        }
    }
}