package com.example.androidlearned.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.androidlearned.R



class RecycleViewExample5Adapter(val list: MutableList<String>) : RecyclerView.Adapter<RecycleViewExample5Adapter.ViewHolder>() {
    private var mClickCall: ((position: Int,text: String, view: View) -> Unit)? = null
    public  fun setOnClickListener(mClickCall: (position: Int, text: String, view: View) -> Unit) {
        this.mClickCall = mClickCall
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.fragment_recycle_view_example1_item, parent, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.i("onBindViewHolder",position.toString())
        holder.title.text = list[position]
        holder.itemView.setOnClickListener {
            mClickCall?.invoke(position, list[position] ,it)
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView
        init {
            title = itemView.findViewById(R.id.list_item_content)
        }
    }
}