package com.example.androidlearned.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.androidlearned.domain.HomeLayoutInfo
import com.example.androidlearned.R

class HomeFragmentAdapter(private val homeLayoutInfoList: MutableList<HomeLayoutInfo>):RecyclerView.Adapter<HomeFragmentAdapter.ViewHolder> ()
{
    private  var mClickCall: ( (HomeLayoutInfo,View) -> Unit)? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        Log.i("test","onCreateViewHolder")

        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_home_recycle_item,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val layoutInfo = homeLayoutInfoList[position]
        holder.title.text  = layoutInfo.title
        holder.description.text = layoutInfo.description
        holder.itemView.setOnClickListener {
            mClickCall?.invoke(layoutInfo,it)
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

     public  fun setOnClickListener(mClickCall: (HomeLayoutInfo, View) -> Unit ) {
        this.mClickCall = mClickCall
    }
}