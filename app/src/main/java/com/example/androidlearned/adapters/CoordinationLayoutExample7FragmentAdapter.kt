package com.example.androidlearned.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.androidlearned.R
import com.example.androidlearned.domain.BankMenu

class CoordinationLayoutExample7FragmentAdapter(private val data: MutableList<BankMenu>, private  val lastH: Int): RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    companion object {
        const val VIEW_TYPE_TITLE = 0
        const val VIEW_TYPE_MENU = 1
        const val VIEW_TYPE_FOOTER = 2
    }
    class ViewHolderByTitle(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView
        init {
            title = itemView.findViewById(R.id.coordination_layout_example7_rcy_item_title)
        }
    }
    class ViewHolderByMenu(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView
        init {
            title = itemView.findViewById(R.id.coordination_layout_example7_rcy_item_menu_title)
        }
    }
    class ViewHolderByFooter(itemView: View) : RecyclerView.ViewHolder(itemView) { }

    override fun getItemViewType(position: Int): Int {
      if(position == data.size) return VIEW_TYPE_FOOTER
      return data[position].itemType
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            VIEW_TYPE_TITLE -> {
                return ViewHolderByTitle(LayoutInflater.from(parent.context).inflate(R.layout.fragment_coordination_layout_example7_recycle_item_title,parent,false))
            }
            VIEW_TYPE_MENU -> {
                return ViewHolderByMenu(LayoutInflater.from(parent.context).inflate(R.layout.fragment_coordination_layout_example7_recycle_item_menu,parent,false))
            }
            else -> {
                val view = View(parent.context)
                view.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,lastH)
                return ViewHolderByFooter(view)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {
            is ViewHolderByTitle -> {
                holder.title.text = data[position].name
            }
            is ViewHolderByMenu -> {
                holder.title.text = data[position].name
            }
        }
    }

    override fun getItemCount(): Int {
        return data.size + 1
    }


}